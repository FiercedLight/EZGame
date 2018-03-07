package com.fierced.ezgame;

/**
 * Created by Fierced on 3/6/2018.
 */

public class PowerBall {

    public float speed, pX, pY, power;
    private float velX, velY;

    public PowerBall(int speed, float pX, float pY, double dir, float power) {
        this.speed = speed;
        this.pX = pX;
        this.pY = pY;
        this.power = power;
        this.velX = (float) Math.cos(dir) * speed;
        this.velY = (float) Math.sin(dir) * speed;
    }

    public void Update(float delta) {
        pX += velX * delta;
        pY += velY * delta;
        velX *= 1.05f; // Scientific Number don't change
        velY *= 1.05f;
        power -= delta * 3.0f;
    }

}
