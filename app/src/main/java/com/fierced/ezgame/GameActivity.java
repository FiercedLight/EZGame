package com.fierced.ezgame;

import android.util.Log;
import android.view.MotionEvent;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.*;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class GameActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener {

    static final int CAMERA_WIDTH = 540;
    static final int CAMERA_HEIGHT = 960;

    private BitmapTextureAtlas enemyTextureAtlas;
    private ITiledTextureRegion enemyTextureRegion;

    private BitmapTextureAtlas gunTextureAtlas;
    private ITiledTextureRegion gunTextureRegion;

    private BitmapTextureAtlas turtleTextureAtlas;
    private ITiledTextureRegion turtleTextureRegion;

    private Scene scene;
    private VectorArea vectorArea;

    @Override
    public EngineOptions onCreateEngineOptions() {
        Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
                new FillResolutionPolicy(), mCamera);
        return engineOptions;
    }

    @Override
    protected void onCreateResources() {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        enemyTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1200, 900, TextureOptions.DEFAULT);
        enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(enemyTextureAtlas, this, "enemySpriteSheet.PNG", 0, 0, 4, 3);
        enemyTextureAtlas.load();

        gunTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1200, 600, TextureOptions.DEFAULT);
        gunTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gunTextureAtlas, this, "gunSpriteSheet.PNG", 0, 0, 4, 2);
        gunTextureAtlas.load();

        turtleTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 900, 900, TextureOptions.DEFAULT);
        turtleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(turtleTextureAtlas, this, "turtleSpriteSheet.PNG", 0, 0, 3, 3);
        turtleTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {
        scene = new Scene();
        scene.setBackground(new Background(0.69804f, 0.9274f, 0.7f));

        vectorArea = new VectorArea(0, 600, 540, 360, 30);
        vectorArea.Init();
        vectorArea.Draw(mEngine.getVertexBufferObjectManager(), scene);

        AnimatedSprite enemy = createTiledSprite(enemyTextureRegion);
        AnimatedSprite gun = createTiledSprite(gunTextureRegion);
        AnimatedSprite turtle = createTiledSprite(turtleTextureRegion);
        enemy.animate(100);
        gun.animate(60);
        turtle.animate(200);
        enemy.setX(600);
        gun.setY(200);
        turtle.setY(200);


        scene.registerUpdateHandler(new IUpdateHandler() {
            public void reset() {
            }

            float tempTimer;

            public void onUpdate(float pSecondsElapsed) {
                //Log.println(Log.DEBUG, "Debug", Float.toString(pSecondsElapsed));
                tempTimer += pSecondsElapsed;
                if (tempTimer > 0) {
                    vectorArea.Update();
                    //vectorArea.ApplyForce(20, 10, (short) 127, (short) 0);
                    tempTimer -= 0f;
                }
            }
        });

        scene.setOnSceneTouchListener(this);
        return scene;
    }

    private AnimatedSprite createTiledSprite(ITiledTextureRegion tiledReg) {
        AnimatedSprite as = new AnimatedSprite(0, 0, tiledReg, mEngine.getVertexBufferObjectManager());
        //scene.attachChild(as);
        return as;
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

        int pX = (int) (pSceneTouchEvent.getX() / 30);
        int pY = (int) ((pSceneTouchEvent.getY()-600) / 30);


        if(pSceneTouchEvent.isActionDown() && pSceneTouchEvent.getY()>600) {

            vectorArea.ApplyForce(pX - 1, pY, (short) 120, (short) 4);
            vectorArea.ApplyForce(pX + 1, pY, (short) 120, (short) 0);
            vectorArea.ApplyForce(pX, pY + 1, (short) 120, (short) 6);
            vectorArea.ApplyForce(pX, pY - 1, (short) 120, (short) 2);
        }

        return false;
    }
}