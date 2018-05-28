package com.fierced.ezgame;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;

/**
 * Created by Fierced on 3/5/2018.
 */

public class VectorArea {

    public int pX, pY, width, height, cell_size;
    private int area_w, area_h;

    private int[][] tileArray;  // Holds tile info
    private Rectangle[][] rectArray; // Holds drawable tiles

    private ArrayList<PowerBall> powList; // Holds powerBalls
    private int powerBallAmount = 0;
    private ArrayList<PowerBall> delPowList; // Powerballs to be deleted
    private ArrayList<PowerBall> addPowList; // Powerballs to be added

    public ArrayList<Rune> runeList;

    public VectorArea(int pX, int pY, int width, int height, int cell_size) {
        this.pX = pX;
        this.pY = pY;
        this.width = width;
        this.height = height;
        this.cell_size = cell_size;
    }

    public void Init() {

        area_w = width / cell_size;
        area_h = height / cell_size;

        tileArray = new int[area_h][area_w];
        rectArray = new Rectangle[area_h][area_w];
        powList = new ArrayList<>();
        delPowList = new ArrayList<>();
        addPowList = new ArrayList<>();
        runeList = new ArrayList<>();
    }

    public void ApplyForce(float pX, float pY, int amount, int speed) {

        if (pX <= 0 || pX >= width || pY <= 0 || pY >= height) return;

        powerBallAmount = amount;
        for (int i = 0; i < amount; i++) {
            PowerBall tempPB = new PowerBall(speed, pX, pY, (double) i / amount * Math.PI * 2, 10);
            addPowList.add(tempPB);
        }

    }

    public void ApplyOneForce(float pX, float pY, int speed, float dir, float power) {

        if (pX <= 0 || pX >= width || pY <= 0 || pY >= height) return;

        PowerBall tempPB = new PowerBall(speed, pX, pY, dir, power);
        addPowList.add(tempPB);
    }

    public void Update(float delta) {
        for (PowerBall p : powList) {
            p.Update(delta);
            if (p.pX > width || p.pY > height ||
                    p.pX < 0 || p.pY < 0) {
                delPowList.add(p);
                continue;
            }
            for (Rune r : runeList)
                CheckPowerBallCollision(p, r);

            int tempPX = (int) p.pX / cell_size;
            int tempPY = (int) p.pY / cell_size;

            if (tempPX < area_w && tempPY < area_h
                    && tempPX >= 0 && tempPY >= 0) {
                tileArray[tempPY][tempPX] = (int) (Math.random() * 5) + 2;
                rectArray[tempPY][tempPX].setVisible(true);
            }

        }

        powList.removeAll(delPowList);
        powList.addAll(addPowList);
        addPowList.clear();
        delPowList.clear();


        for (int i = 0; i < area_h; i++) {
            for (int j = 0; j < area_w; j++) {
                if (tileArray[i][j] == -1) continue;
                if (tileArray[i][j] > 0) {
                    tileArray[i][j]--;
                } else {
                    rectArray[i][j].setVisible(false);
                    tileArray[i][j] = -1;
                }

            }
        }


    }

    public void Draw(VertexBufferObjectManager vbo, IEntity scene) {
        for (int i = 0; i < area_h; i++) {
            for (int j = 0; j < area_w; j++) {
                Rectangle tempRect = new Rectangle(pX + j * cell_size, pY + i * cell_size, cell_size - 2, cell_size - 2, vbo);
                //tempRect.setVisible(true);
                rectArray[i][j] = tempRect;
                scene.attachChild(tempRect);
            }
        }

    }

    public void CheckPowerBallCollision(PowerBall p, Rune r) {
        if (Math.sqrt(Math.pow(pX + p.pX - r.pX - r.size / 2, 2) + Math.pow(pY + p.pY - r.pY - r.size / 2, 2)) < r.size / 2) {
            if (r.OnPowerBallHit(p))
                delPowList.add(p);
        }
    }

    public void AddRune(Rune r) {
        runeList.add(r);
    }

    public void DelRune(Rune r) {
        runeList.remove(r);
    }

    public void RuneUpdate() {
        for (Rune r : runeList)
            r.OnGameClock();
    }

}
