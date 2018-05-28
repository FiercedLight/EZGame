package com.fierced.ezgame;

import android.util.Log;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.*;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.util.Random;

public class GameActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener, IAccelerationListener {

    static final int CAMERA_WIDTH = 540;
    static final int CAMERA_HEIGHT = 960;

    private float pers_pX, pers_pY;

    private BitmapTextureAtlas bottomScreenFrameTextureAtlas;
    private ITextureRegion bottomScreenFrameRegion;

    private BitmapTextureAtlas bottomScreenTextureAtlas;
    private ITextureRegion bottomScreenTableRegion;

    private BitmapTextureAtlas treesTextureAtlas;
    private ITiledTextureRegion treesRegion;

    private BitmapTextureAtlas groundTextureAtlas;
    private ITextureRegion groundRegion;

    private BitmapTextureAtlas crystalRuneTextureAtlas;
    private BitmapTextureAtlas darkRuneTextureAtlas;
    private BitmapTextureAtlas earthRuneTextureAtlas;
    private BitmapTextureAtlas fireRuneTextureAtlas;
    private BitmapTextureAtlas leafRuneTextureAtlas;
    private BitmapTextureAtlas heartRuneTextureAtlas;
    private BitmapTextureAtlas mechRuneTextureAtlas;
    private BitmapTextureAtlas mirrorRuneTextureAtlas;
    private BitmapTextureAtlas snowRuneTextureAtlas;
    private BitmapTextureAtlas starRuneTextureAtlas;
    private BitmapTextureAtlas trashRuneTextureAtlas;
    private BitmapTextureAtlas voltRuneTextureAtlas;
    private BitmapTextureAtlas waterRuneTextureAtlas;
    private BitmapTextureAtlas windRuneTextureAtlas;

    private ITiledTextureRegion crystalRuneTextureRegion;
    private ITiledTextureRegion darkRuneTextureRegion;
    private ITiledTextureRegion earthRuneTextureRegion;
    private ITiledTextureRegion fireRuneTextureRegion;
    private ITiledTextureRegion leafRuneTextureRegion;
    private ITiledTextureRegion heartRuneTextureRegion;
    private ITiledTextureRegion mechRuneTextureRegion;
    private ITiledTextureRegion mirrorRuneTextureRegion;
    private ITiledTextureRegion snowRuneTextureRegion;
    private ITiledTextureRegion starRuneTextureRegion;
    private ITiledTextureRegion trashRuneTextureRegion;
    private ITiledTextureRegion voltRuneTextureRegion;
    private ITiledTextureRegion waterRuneTextureRegion;
    private ITiledTextureRegion windRuneTextureRegion;

    private Scene scene;
    private VectorArea vectorArea;
    private Random random;


    private void CreateLayers() {
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());
        scene.attachChild(new Entity());

        scene.attachChild(new Entity());

    }

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

        bottomScreenFrameTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1080, 720, TextureOptions.DEFAULT);
        bottomScreenFrameRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bottomScreenFrameTextureAtlas, this, "BottomScreenFrame.png", 0, 0);
        bottomScreenFrameTextureAtlas.load();

        bottomScreenTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1080, 720, TextureOptions.DEFAULT);
        bottomScreenTableRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bottomScreenTextureAtlas, this, "BottomScreen.png", 0, 0);
        bottomScreenTextureAtlas.load();

        groundTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 450, 405, TextureOptions.REPEATING_NEAREST);
        groundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(groundTextureAtlas, this, "GroundTileable.png", 0, 0);
        groundTextureAtlas.load();

        crystalRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        crystalRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(crystalRuneTextureAtlas, this, "RuneSprites/CrystalRune.png", 0, 0, 3, 4);
        crystalRuneTextureAtlas.load();

        darkRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        darkRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(darkRuneTextureAtlas, this, "RuneSprites/DarkRune.png", 0, 0, 3, 4);
        darkRuneTextureAtlas.load();

        earthRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        earthRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(earthRuneTextureAtlas, this, "RuneSprites/EarthRune.png", 0, 0, 3, 4);
        earthRuneTextureAtlas.load();

        fireRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        fireRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(fireRuneTextureAtlas, this, "RuneSprites/FireRune.png", 0, 0, 3, 4);
        fireRuneTextureAtlas.load();

        heartRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        heartRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(heartRuneTextureAtlas, this, "RuneSprites/HeartRune.png", 0, 0, 3, 4);
        heartRuneTextureAtlas.load();

        leafRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        leafRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(leafRuneTextureAtlas, this, "RuneSprites/LeafRune.png", 0, 0, 3, 4);
        leafRuneTextureAtlas.load();

        mechRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        mechRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mechRuneTextureAtlas, this, "RuneSprites/MechRune.png", 0, 0, 3, 4);
        mechRuneTextureAtlas.load();

        mirrorRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        mirrorRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mirrorRuneTextureAtlas, this, "RuneSprites/MirrorRune.png", 0, 0, 3, 4);
        mirrorRuneTextureAtlas.load();

        snowRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        snowRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(snowRuneTextureAtlas, this, "RuneSprites/SnowRune.png", 0, 0, 3, 4);
        snowRuneTextureAtlas.load();

        starRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        starRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(starRuneTextureAtlas, this, "RuneSprites/StarRune.png", 0, 0, 3, 4);
        starRuneTextureAtlas.load();

        trashRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        trashRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(trashRuneTextureAtlas, this, "RuneSprites/TrashRune.png", 0, 0, 3, 4);
        trashRuneTextureAtlas.load();

        voltRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        voltRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(voltRuneTextureAtlas, this, "RuneSprites/VoltRune.png", 0, 0, 3, 4);
        voltRuneTextureAtlas.load();

        waterRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        waterRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(waterRuneTextureAtlas, this, "RuneSprites/WaterRune.png", 0, 0, 3, 4);
        waterRuneTextureAtlas.load();

        windRuneTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 192, 256, TextureOptions.DEFAULT);
        windRuneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(windRuneTextureAtlas, this, "RuneSprites/WindRune.png", 0, 0, 3, 4);
        windRuneTextureAtlas.load();

        treesTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1200, 1920, TextureOptions.DEFAULT);
        treesRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(treesTextureAtlas, this, "TreesSheet.PNG", 0, 0, 3, 3);
        treesTextureAtlas.load();

    }

    @Override
    protected Scene onCreateScene() {
        scene = new Scene();
        scene.setBackground(new Background(0.5f, 0.92f, 0.98f));
        CreateLayers();

        pers_pX = 0f;
        pers_pY = 0.5f;

        Sprite botScreen = new Sprite(0, 360, 540, 600, bottomScreenTableRegion, getVertexBufferObjectManager());
        scene.getChildByIndex(0).attachChild(botScreen);
        //topScreen.setShaderProgram(PerspectiveShader.botScreen());

        vectorArea = new VectorArea(0, 360
                , 540, 600, 30);
        vectorArea.Init();
        vectorArea.Draw(mEngine.getVertexBufferObjectManager(), scene.getChildByIndex(0));

        Sprite bottomScreenFrame = new Sprite(0, 360, 540, 600, bottomScreenFrameRegion, getVertexBufferObjectManager());
        scene.getChildByIndex(0).attachChild(bottomScreenFrame);

        Sprite ground = new Sprite(-1000, -180, 2540, 300, groundRegion, getVertexBufferObjectManager(), PerspectiveShader.getInstance());
        scene.getChildByIndex(0).attachChild(ground);

        random = new Random();

        SummonRune(RuneType.MIRROR, 10, 0, 100);
        SummonRune(RuneType.MIRROR, 25, 0, 100);
        SummonRune(RuneType.MIRROR, 50, 0, 100);
        SummonRune(RuneType.MECH, 300, 0, 100);
        SummonRune(RuneType.MECH, 50, 0, 100);
        SummonRune(RuneType.TRASH, 300, 0, 1000);
        SummonRune(RuneType.CRYSTAL, 300, 0, 1000);
        SummonRune(RuneType.HEART, 300, 0, 1000);

        for (int i = 0; i < 15; i++) {
            SummonRandomAttackRune(random);
        }


        scene.registerUpdateHandler(new IUpdateHandler() {
            public void reset() {
            }

            float tempTimer;

            public void onUpdate(float pSecondsElapsed) {

                WindyShader.getInstance().Update(pSecondsElapsed);
                vectorArea.Update(pSecondsElapsed);

                for (int i = 1; i < 12; i++)
                    scene.getChildByIndex(12 - i).setPosition(-pers_pX * i * 20, 0);

                tempTimer += pSecondsElapsed;
                if (tempTimer > 0) {
                    vectorArea.RuneUpdate();
                    tempTimer -= 2f;
                }
            }
        });

        scene.setOnSceneTouchListener(this);
        this.getShaderProgramManager().loadShaderProgram(BlinkShader.getInstance());
        this.getShaderProgramManager().loadShaderProgram(PerspectiveShader.getInstance());
        this.getShaderProgramManager().loadShaderProgram(WindyShader.getInstance());
        return scene;
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

        float pX = pSceneTouchEvent.getX();
        float pY = pSceneTouchEvent.getY() - 360;


        if (pSceneTouchEvent.isActionDown() && pSceneTouchEvent.getY() > 360) {

            vectorArea.ApplyForce(pX, pY, 50, 80);
        }
        return false;
    }

    public void SummonRune(RuneType type, float attack, float hp, float capacity) {
        int treeType = -1;
        ITiledTextureRegion tempTextureRegion = crystalRuneTextureRegion;
        Rune tempRune;
        if (type == RuneType.CRYSTAL) {
            tempTextureRegion = crystalRuneTextureRegion;
        } else if (type == RuneType.DARK) {
            tempTextureRegion = darkRuneTextureRegion;
            treeType = 0;
        } else if (type == RuneType.EARTH) {
            tempTextureRegion = earthRuneTextureRegion;
            treeType = 1;
        } else if (type == RuneType.FIRE) {
            tempTextureRegion = fireRuneTextureRegion;
            treeType = 2;
        } else if (type == RuneType.LEAF) {
            tempTextureRegion = leafRuneTextureRegion;
            treeType = 3;
        } else if (type == RuneType.HEART) {
            tempTextureRegion = heartRuneTextureRegion;
        } else if (type == RuneType.MECH) {
            tempTextureRegion = mechRuneTextureRegion;
        } else if (type == RuneType.MIRROR) {
            tempTextureRegion = mirrorRuneTextureRegion;
        } else if (type == RuneType.SNOW) {
            tempTextureRegion = snowRuneTextureRegion;
            treeType = 4;
        } else if (type == RuneType.STAR) {
            tempTextureRegion = starRuneTextureRegion;
            treeType = 5;
        } else if (type == RuneType.TRASH) {
            tempTextureRegion = trashRuneTextureRegion;
        } else if (type == RuneType.VOLT) {
            tempTextureRegion = voltRuneTextureRegion;
            treeType = 6;
        } else if (type == RuneType.WATER) {
            tempTextureRegion = waterRuneTextureRegion;
            treeType = 7;
        } else if (type == RuneType.WIND) {
            tempTextureRegion = windRuneTextureRegion;
            treeType = 8;
        }


        tempRune = new Rune(200, 800, 0, attack, hp, capacity, type, tempTextureRegion);
        tempRune.Init(getVertexBufferObjectManager(), scene, vectorArea);
        if (treeType != -1) {
            AnimatedSprite treeSprite = new AnimatedSprite(100, 100, 100, 160, treesRegion, getVertexBufferObjectManager(), WindyShader.getInstance());
            treeSprite.stopAnimation(treeType);
            scene.attachChild(treeSprite);
            tempRune.SetTree(treeSprite);
        }
        while(!tempRune.PlaceRune(random.nextInt(8)+1, random.nextInt(9)+7, tempRune.runeSprite, false));
    }

    public void SummonRandomRune(Random random) {
        RuneType tempType = RuneType.getRandomType(random);
        SummonRune(tempType, random.nextFloat() * 100, random.nextFloat() * 100, random.nextFloat() * 100);
    }

    public void SummonRandomAttackRune(Random random) {

        RuneType tempType = RuneType.getRandomAttackType(random);
        SummonRune(tempType, random.nextFloat() * 100, random.nextFloat() * 100, random.nextFloat() * 100);

    }

    /*
    public void CreateBushes() {
        for (int i = 0; i < 4; i++) {
            AnimatedSprite tempSprite = new AnimatedSprite(random.nextFloat() * 500, random.nextFloat() * 200 + 400, bushesRegion, getVertexBufferObjectManager());
            scene.getChildByIndex(random.nextInt(5) + 1).attachChild(tempSprite);
            tempSprite.stopAnimation(random.nextInt(4));

        }
    }
    */

    float Lerp(float point1, float point2, float alpha) {
        return point1 + alpha * (point2 - point1);
    }

    @Override
    public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {

    }

    @Override
    public void onAccelerationChanged(AccelerationData pAccelerationData) {

        //Log.println(Log.DEBUG, "Debug", Float.toString(pAccelerationData.getX()));

        if (pAccelerationData.getX() > 5f)
            pers_pX = Lerp(pers_pX, 1.5f, 0.07f);
        else if (pAccelerationData.getX() < -5f)
            pers_pX = Lerp(pers_pX, -0.5f, 0.07f);
        else
            pers_pX = Lerp(pers_pX, 0.5f, 0.07f);
        PerspectiveShader.getInstance().setPos(pers_pX, pers_pY, 0.0f, 0.0f);

    }

    @Override
    public void onResumeGame() {
        super.onResumeGame();

        this.enableAccelerationSensor(this);
    }

    @Override
    public void onPauseGame() {
        super.onPauseGame();

        this.disableAccelerationSensor();
    }
}