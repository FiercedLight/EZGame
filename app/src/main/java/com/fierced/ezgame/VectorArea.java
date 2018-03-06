package com.fierced.ezgame;

import android.support.annotation.NonNull;
import android.util.Log;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Fierced on 3/5/2018.
 */

public class VectorArea {

    int pX, pY, width, height, cell_size;
    int area_w, area_h;
    short[][] dirArray;   // Holds direction info
    // 0-right 1-upRight 2-up 3-upLeft 4-left 5-downLeft 6-down 7-down-right

    Boolean[][] powArray;  // Holds tile info
    Rectangle[][] rectList;


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

        powArray = new Boolean[area_h][area_w];
        rectList = new Rectangle[area_h][area_w];
    }

    public void ApplyForce(int pX, int pY, short amount, short dir) {

        if (pX <= 0 || pX >= area_w || pY <= 0 || pY >= area_h) return;
    }

    public void Update() {
        //Log.println(Log.DEBUG, "Debug", "Update");
        /*
        short[][] tempPowArray = new short[area_h][area_w];
        short[][] tempDirArray = new short[area_h][area_w];

        for (int i = 1; i < area_h - 1; i++) {
            for (int j = 1; j < area_w - 1; j++) {

                short tempPow = powArray[i][j];
                rectList[i][j].setColor(tempPow, tempPow, tempPow);
                short lowPow = (short) (tempPow /2);
                short hiPow = (short) (tempPow -4);
                if (tempPow <= 45 || tempDirArray[i][j] > 50) continue;
                switch (dirArray[i][j]) {

                    // 0-right 1-upRight 2-up 3-upLeft 4-left 5-downLeft 6-down 7-down-right

                    case 0:
                        tempDirArray[i - 1][j + 1] = 1;
                        tempDirArray[i][j + 1] = 0;
                        tempDirArray[i + 1][j + 1] = 7;

                        tempPowArray[i - 1][j + 1] = lowPow;
                        tempPowArray[i][j + 1] = hiPow;
                        tempPowArray[i + 1][j + 1] = lowPow;
                        break;

                    case 1:
                        tempDirArray[i - 1][j] = 2;
                        tempDirArray[i - 1][j + 1] = 1;
                        tempDirArray[i][j + 1] = 0;

                        tempPowArray[i - 1][j] = lowPow;
                        tempPowArray[i - 1][j + 1] = hiPow;
                        tempPowArray[i][j + 1] = lowPow;
                        break;

                    case 2:
                        tempDirArray[i - 1][j - 1] = 3;
                        tempDirArray[i - 1][j] = 2;
                        tempDirArray[i - 1][j + 1] = 1;

                        tempPowArray[i - 1][j - 1] = lowPow;
                        tempPowArray[i - 1][j] = hiPow;
                        tempPowArray[i - 1][j + 1] = lowPow;
                        break;

                    case 3:
                        tempDirArray[i - 1][j] = 2;
                        tempDirArray[i - 1][j - 1] = 3;
                        tempDirArray[i][j - 1] = 4;

                        tempPowArray[i - 1][j] = lowPow;
                        tempPowArray[i - 1][j - 1] = hiPow;
                        tempPowArray[i][j - 1] = lowPow;
                        break;

                    case 4:
                        tempDirArray[i - 1][j - 1] = 3;
                        tempDirArray[i][j - 1] = 4;
                        tempDirArray[i + 1][j - 1] = 5;

                        tempPowArray[i - 1][j - 1] = lowPow;
                        tempPowArray[i][j - 1] = hiPow;
                        tempPowArray[i + 1][j - 1] = lowPow;
                        break;

                    case 5:
                        tempDirArray[i + 1][j] = 6;
                        tempDirArray[i + 1][j - 1] = 5;
                        tempDirArray[i][j - 1] = 4;

                        tempPowArray[i + 1][j] = lowPow;
                        tempPowArray[i + 1][j - 1] = hiPow;
                        tempPowArray[i][j - 1] = lowPow;
                        break;

                    case 6:
                        tempDirArray[i + 1][j - 1] = 5;
                        tempDirArray[i + 1][j] = 6;
                        tempDirArray[i + 1][j + 1] = 7;

                        tempPowArray[i + 1][j - 1] = lowPow;
                        tempPowArray[i + 1][j] = hiPow;
                        tempPowArray[i + 1][j + 1] = lowPow;
                        break;

                    case 7:
                        tempDirArray[i + 1][j] = 6;
                        tempDirArray[i + 1][j + 1] = 7;
                        tempDirArray[i][j + 1] = 0;

                        tempPowArray[i + 1][j] = lowPow;
                        tempPowArray[i + 1][j + 1] = hiPow;
                        tempPowArray[i][j + 1] = lowPow;
                        break;
                    // 0-right 1-upRight 2-up 3-upLeft 4-left 5-downLeft 6-down 7-down-right
                }
            }
        }

        if(tempPowArray[3][3]!=0)tempPowArray[3][3]=0;
        if(tempPowArray[4][3]!=0)tempPowArray[4][3]=0;
        if(tempPowArray[4][4]!=0)tempPowArray[4][4]=0;
        if(tempPowArray[3][4]!=0)tempPowArray[3][4]=0;


        powArray = tempPowArray;
        dirArray = tempDirArray;
        */
    }

    public void Draw(VertexBufferObjectManager vbo, Scene scene) {

        for (int i = 0; i < area_h; i++) {
            for (int j = 0; j < area_w; j++) {
                Rectangle tempRect = new Rectangle(pX + j * cell_size, pY + i * cell_size, cell_size, cell_size, vbo);
                tempRect.setColor(0, 0, 0);
                rectList[i][j] = tempRect;
                scene.attachChild(tempRect);
            }
        }

    }

}
