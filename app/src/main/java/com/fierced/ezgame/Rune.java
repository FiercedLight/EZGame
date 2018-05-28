package com.fierced.ezgame;

import android.util.Log;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import java.util.Random;

/**
 * Created by Fierced on 3/7/2018.
 */

public class Rune {

    public static int globalRuneID = 0;

    public int runeID;
    public float pX, pY, power;
    private float prevPX, prevPY;
    public RuneType type;
    public ITiledTextureRegion textureRegion;
    public float size = 60;
    private VectorArea vectorArea;
    public int special; // rotation for mirror
    public float attack, hp, capacity;
    private Rectangle powerIndicator;
    private Scene scene;
    public AnimatedSprite runeSprite;
    public AnimatedSprite treeSprite;
    boolean hasTree = false;
    public int layerX = 0, layerY = 0;

    public Rune(float pX, float pY, float power, float attack, float hp, float capacity, RuneType type, ITiledTextureRegion textureRegion) {
        this.pX = pX;
        this.pY = pY;
        this.prevPX = pX;
        this.prevPY = pY;
        this.power = power;
        this.type = type;
        this.textureRegion = textureRegion;
        this.attack = attack;
        this.hp = hp;
        this.capacity = capacity;
        this.special = 0;
    }

    public void SetTree(AnimatedSprite treeSprite) {
        hasTree = true;
        this.treeSprite = treeSprite;
    }

    public void Init(VertexBufferObjectManager vbo, final Scene scene, final VectorArea vectorArea) {

        runeID = globalRuneID;
        globalRuneID++;

        this.vectorArea = vectorArea;
        this.scene = scene;

        powerIndicator = new Rectangle(pX, pY + size + 2, size, 2, vbo);
        powerIndicator.setColor(Color.WHITE);
        scene.attachChild(powerIndicator);

        runeSprite = new AnimatedSprite(pX, pY, size, size, textureRegion, vbo) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

                //PerspectiveShader.getInstance().setPos(pSceneTouchEvent.getX() / 540, pSceneTouchEvent.getY() / 960, 0.0f, 0.0f);
                pX = pSceneTouchEvent.getX() - size / 2;
                pY = pSceneTouchEvent.getY() - size / 2;
                this.setPosition(pX, pY);
                powerIndicator.setPosition(pX, pY + size + 2);

                if (pSceneTouchEvent.isActionDown())
                    this.setScale(1.5f);
                if (pSceneTouchEvent.isActionUp()) {


                    layerX = (int) ((pSceneTouchEvent.getX() + 30) / 60);
                    layerY = (int) ((pSceneTouchEvent.getY() + 30) / 60);
                    PlaceRune(layerX, layerY, this, true);

                }

                return true;
            }
        };

        runeSprite.setRotationCenter(size / 2, size / 2);
        scene.registerTouchArea(runeSprite);
        scene.setTouchAreaBindingOnActionDownEnabled(true);
        Random tempRandom = new Random();
        long[] runeAnimData = new long[]{
                700 + tempRandom.nextInt(800),
                100, 100, 100, 100,
                100, 100, 100, 100,
                100, 100, 100};
        runeSprite.animate(runeAnimData);
        scene.getChildByIndex(0).attachChild(runeSprite);
        vectorArea.AddRune(this);

    }

    public void Delete() {
        scene.unregisterTouchArea(runeSprite);
        runeSprite.detachSelf();
        scene.detachChild(powerIndicator);
        if (hasTree)
            treeSprite.detachSelf();
    }

    public boolean PlaceRune(int lX, int lY, AnimatedSprite sprite, boolean touchAct) { //If placing fails, returns false

        layerX = lX;
        layerY = lY;
        pX = lX * 60 - size / 2;
        pY = lY * 60 - size / 2;
        sprite.setPosition(pX, pY);
        powerIndicator.setPosition(pX, pY + size + 2);
        sprite.setScale(1.0f);

        for (Rune r : vectorArea.runeList) {
            if (r.runeID == runeID) continue;
            if (r.layerX == layerX && r.layerY == layerY) {
                if (!touchAct) {
                    pX = prevPX;
                    pY = prevPY;
                    sprite.setPosition(pX, pY);
                    powerIndicator.setPosition(pX, pY + size + 2);
                    return false;
                }
                if (type == RuneType.TRASH) {
                    power += r.power + r.attack + r.capacity + r.hp;
                    vectorArea.DelRune(r);
                    r.Delete();
                    break;

                } else {
                    pX = prevPX;
                    pY = prevPY;
                    sprite.setPosition(pX, pY);
                    powerIndicator.setPosition(pX, pY + size + 2);
                }
            }
        }

        if (type == RuneType.MIRROR) {
            if (Math.abs(prevPX - pX) + Math.abs(prevPY - pY) < 60) {
                //Log.println(Log.DEBUG, "Debug", Double.toString((((float)special / 8.0f) * Math.PI / 4)));
                special = (special + 1) % 8;
                sprite.setRotation((((float) (special - 2) / 8.0f) * 360.0f));
            }
        }

        prevPX = pX;
        prevPY = pY;

        if (hasTree) {
            treeSprite.setPosition(layerX * 60 - 10, layerY * 15 - 20);
            treeSprite.detachSelf();
            //Log.println(Log.DEBUG, "Debug", Integer.toString(layerY));
            scene.getChildByIndex(layerY - 4).attachChild(treeSprite);
        }

        return true;
    }

    public boolean OnPowerBallHit(PowerBall p) {

        boolean destroyBall = true;
        switch (type) {
            case MECH:
                destroyBall = false;
                break;
            case MIRROR:
                //Log.println(Log.DEBUG, "Debug", Float.toString(attack));
                float angle = ((float) special / 8.0f) * 3.14f * 2;
                vectorArea.ApplyOneForce(pX - vectorArea.pX + (float) Math.cos(angle) * size / 2 + size / 2,
                        pY - vectorArea.pY + (float) Math.sin(angle) * size / 2 + size / 2,
                        (int) attack, angle, p.power);
                break;
            default:
                this.power += p.power;
                if (power > capacity) power = capacity;
                break;
        }

        powerIndicator.setWidth((power * size) / capacity); // Update power indicator

        return destroyBall;

    }

    public void OnGameClock() {

        switch (type) {
            case CRYSTAL:
                break;
            case DARK:
                break;
            case EARTH:
                break;
            case FIRE:
                break;
            case LEAF:
                break;
            case HEART:
                break;
            case MECH:
                vectorArea.ApplyForce(pX - vectorArea.pX + size / 2, pY - vectorArea.pY + size / 2, 20, (int) attack);
                break;
            case MIRROR:
                break;
            case SNOW:
                break;
            case STAR:
                break;
            case TRASH:
                break;
            case VOLT:
                break;
            case WATER:
                break;
            case WIND:
                break;
        }
        power = 0;
        powerIndicator.setWidth((power * size) / capacity); // Update power indicator

    }

}
