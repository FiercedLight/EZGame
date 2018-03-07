package com.fierced.ezgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.*;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.util.Random;

public class GameActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener {

    static final int CAMERA_WIDTH = 540;
    static final int CAMERA_HEIGHT = 960;

    private BitmapTextureAtlas enemyTextureAtlas;
    private ITiledTextureRegion enemyTextureRegion;

    private BitmapTextureAtlas gunTextureAtlas;
    private ITiledTextureRegion gunTextureRegion;

    private BitmapTextureAtlas turtleTextureAtlas;
    private ITiledTextureRegion turtleTextureRegion;

    private BitmapTextureAtlas bottomScreenFrameTextureAtlas;
    private ITextureRegion bottomScreenFrameRegion;

    private BitmapTextureAtlas bottomScreenTableTextureAtlas;
    private ITextureRegion bottomScreenTableRegion;

    private BitmapTextureAtlas crystalRuneTextureAtlas;
    private BitmapTextureAtlas darkRuneTextureAtlas;
    private BitmapTextureAtlas earthRuneTextureAtlas;
    private BitmapTextureAtlas fireRuneTextureAtlas;
    private BitmapTextureAtlas leafRuneTextureAtlas;
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

        bottomScreenFrameTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1080, 720, TextureOptions.DEFAULT);
        bottomScreenFrameRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bottomScreenFrameTextureAtlas, this, "BottomScreenFrame.png", 0, 0);
        bottomScreenFrameTextureAtlas.load();

        bottomScreenTableTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1080, 720, TextureOptions.DEFAULT);
        bottomScreenTableRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bottomScreenTableTextureAtlas, this, "bottomScreenTable.png", 0, 0);
        bottomScreenTableTextureAtlas.load();

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


    }

    @Override
    protected Scene onCreateScene() {
        scene = new Scene();
        scene.setBackground(new Background(0.69804f, 0.9274f, 0.7f));


        Sprite bottomScreenTable = new Sprite(0, 600, 540, 360, bottomScreenTableRegion, getVertexBufferObjectManager());
        scene.attachChild(bottomScreenTable);

        vectorArea = new VectorArea(0, 600, 540, 360, 20);
        vectorArea.Init();
        vectorArea.Draw(mEngine.getVertexBufferObjectManager(), scene);

        Sprite bottomScreenFrame = new Sprite(0, 600, 540, 360, bottomScreenFrameRegion, getVertexBufferObjectManager());
        scene.attachChild(bottomScreenFrame);

        AnimatedSprite enemy = createTiledSprite(enemyTextureRegion);
        AnimatedSprite gun = createTiledSprite(gunTextureRegion);
        AnimatedSprite turtle = createTiledSprite(turtleTextureRegion);



        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            SummonRandomAttackRune(random);
        }
        SummonRune(RuneType.MIRROR, 10, 0, 100);
        SummonRune(RuneType.MECH, 300, 0, 100);
        SummonRune(RuneType.TRASH, 300, 0, 1000);

        enemy.animate(100);
        gun.animate(60);
        turtle.animate(200);
        enemy.setX(300);
        gun.setY(200);
        turtle.setY(200);


        scene.registerUpdateHandler(new IUpdateHandler() {
            public void reset() {
            }

            float tempTimer;

            public void onUpdate(float pSecondsElapsed) {

                vectorArea.Update(pSecondsElapsed);

                tempTimer += pSecondsElapsed;
                if (tempTimer > 0) {
                    vectorArea.RuneUpdate();
                    tempTimer -= 2f;
                }
            }
        });

        scene.setOnSceneTouchListener(this);
        return scene;
    }

    private AnimatedSprite createTiledSprite(ITiledTextureRegion tiledReg) {
        AnimatedSprite as = new AnimatedSprite(0, 0, tiledReg, mEngine.getVertexBufferObjectManager());
        scene.attachChild(as);
        return as;
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

        float pX = pSceneTouchEvent.getX();
        float pY = pSceneTouchEvent.getY() - 600;


        if (pSceneTouchEvent.isActionDown() && pSceneTouchEvent.getY() > 600) {

            vectorArea.ApplyForce(pX, pY, 100, 80);
        }
        return false;
    }

    public void SummonRune(RuneType type, float attack, float hp, float capacity) {
        ITiledTextureRegion tempTextureRegion = crystalRuneTextureRegion;
        Rune testRune;
        if (type == RuneType.CRYSTAL)
            tempTextureRegion = crystalRuneTextureRegion;
        else if (type == RuneType.DARK)
            tempTextureRegion = darkRuneTextureRegion;
        else if (type == RuneType.EARTH)
            tempTextureRegion = earthRuneTextureRegion;
        else if (type == RuneType.FIRE)
            tempTextureRegion = fireRuneTextureRegion;
        else if (type == RuneType.LEAF)
            tempTextureRegion = leafRuneTextureRegion;
        else if (type == RuneType.MECH)
            tempTextureRegion = mechRuneTextureRegion;
        else if (type == RuneType.MIRROR)
            tempTextureRegion = mirrorRuneTextureRegion;
        else if (type == RuneType.SNOW)
            tempTextureRegion = snowRuneTextureRegion;
        else if (type == RuneType.STAR)
            tempTextureRegion = starRuneTextureRegion;
        else if (type == RuneType.TRASH)
            tempTextureRegion = trashRuneTextureRegion;
        else if (type == RuneType.VOLT)
            tempTextureRegion = voltRuneTextureRegion;
        else if (type == RuneType.WATER)
            tempTextureRegion = waterRuneTextureRegion;
        else if (type == RuneType.WIND)
            tempTextureRegion = windRuneTextureRegion;

        testRune = new Rune(200, 200, 0, attack, hp, capacity, type, tempTextureRegion);
        testRune.Init(getVertexBufferObjectManager(), scene, vectorArea);
    }

    public void SummonRandomRune(Random random) {
        RuneType tempType = RuneType.getRandomType(random);
        SummonRune(tempType, random.nextFloat() * 100, random.nextFloat() * 100, random.nextFloat() * 100);
    }

    public void SummonRandomAttackRune(Random random) {

        RuneType tempType = RuneType.getRandomAttackType(random);
        SummonRune(tempType, random.nextFloat() * 100, random.nextFloat() * 100, random.nextFloat() * 100);

    }
}