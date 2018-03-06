package com.fierced.ezgame;

import android.util.Log;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.color.constants.ColorConstants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fierced on 3/5/2018.
 */

public class VectorArea {

    private int pX, pY, width, height, cell_size;
    private int area_w, area_h;

    private int[][] tileArray;  // Holds tile info
    private Rectangle[][] rectArray; // Holds drawable tiles

    private ArrayList<PowerBall> powList; // Holds powerBalls
    private int powerBallAmount = 0;
    private ArrayList<PowerBall> delPowList; // Powerballs to be deleted

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
    }

    public void ApplyForce(float pX, float pY, int amount) {

        if (pX <= 0 || pX >= width || pY <= 0 || pY >= height) return;

        Log.println(Log.DEBUG, "Debug", "POWER");

        powerBallAmount = amount;
        for (int i = 0; i < amount; i++) {
            PowerBall tempPB = new PowerBall(80, pX, pY, (double) i / amount * Math.PI * 2);
            powList.add(tempPB);
        }

    }

    public void Update(float delta) {
        for (PowerBall p : powList) {
            p.Update(delta);
            if (p.pX > width || p.pY > height ||
                    p.pX < 0 || p.pY < 0) {

                delPowList.add(p);
                continue;
            }
            int tempPX = (int) p.pX / cell_size;
            int tempPY = (int) p.pY / cell_size;

            if (tempPX < area_w && tempPY < area_h
                    && tempPX >= 0 && tempPY >= 0) {
                tileArray[tempPY][tempPX] = (int) (Math.random() * 5) + 2;
                rectArray[tempPY][tempPX].setVisible(true);
            }

        }

        powList.removeAll(delPowList);
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

    public void Draw(VertexBufferObjectManager vbo, Scene scene) {
        Rectangle backRect = new Rectangle(pX, pY, width, height, vbo);
        backRect.setColor(38.0f / 255.0f, 57.0f / 255.0f, 81.0f / 255.0f);
        scene.attachChild(backRect);
        for (int i = 0; i < area_h; i++) {
            for (int j = 0; j < area_w; j++) {
                Rectangle tempRect = new Rectangle(pX + j * cell_size, pY + i * cell_size, cell_size - 2, cell_size - 2, vbo);
                //tempRect.setVisible(true);
                rectArray[i][j] = tempRect;
                scene.attachChild(tempRect);
            }
        }

    }

}
