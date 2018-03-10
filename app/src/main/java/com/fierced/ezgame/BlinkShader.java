package com.fierced.ezgame;

import android.opengl.GLES20;

import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.shader.exception.ShaderProgramException;
import org.andengine.opengl.shader.exception.ShaderProgramLinkException;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

/**
 * Created by Fierced on 3/10/2018.
 */

public class BlinkShader extends ShaderProgram {


    public BlinkShader() {
        super(VERTEXT_SHADER, FRAGMENT_SHADER);
    }

    public static final String UNIFORM_INTENCITY = "intencity";
    public static final double START_ANGLE = 60.0;

    private static BlinkShader INSTANCE = null;
    private static final float BLINKING_SPEED = 0.09f;
    private static final float MAX_ANGLE = 90;
    private static final float MIN_ANGLE = 0;
    private static final float MIN_INTENCITY = 0.5f;
    private static final float MAX_INTENCITY = 1.5f;
    private static final float mDiv = (float) ((MAX_INTENCITY - MIN_INTENCITY) /
            (Math.cos(Math.toRadians(MIN_ANGLE)) - Math.cos(Math.toRadians(MAX_ANGLE))));
    private static int mModelViewProjectionMatrixLocation = ShaderProgramConstants.LOCATION_INVALID;
    private static int mTexture0Location = ShaderProgramConstants.LOCATION_INVALID;
    private static int mIntencityLocation = ShaderProgramConstants.LOCATION_INVALID;

    private static boolean mBlink = false;
    private static double mCurAngle = 0.0f;
    private static long mPrevTime = 0;
    private static boolean mGrow = true;

    private static final String VERTEXT_SHADER =
            "uniform mat4 " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX + ";\n" +
                    "attribute vec4 " + ShaderProgramConstants.ATTRIBUTE_POSITION + ";\n" +
                    "attribute vec2 " + ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
                    "attribute vec4 " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
                    "varying vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
                    "varying vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
                    "void main() {\n" +
                    "   " + ShaderProgramConstants.VARYING_COLOR +
                    " = " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
                    "   " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + " = " +
                    ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
                    "   gl_Position = " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX +
                    "*" + ShaderProgramConstants.ATTRIBUTE_POSITION + ";\n" +
                    "}";

    private static final String FRAGMENT_SHADER =
            "precision lowp float;\n" +
                    "uniform float " + UNIFORM_INTENCITY + ";\n" +
                    "uniform sampler2D " + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ";\n" +
                    "varying mediump vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
                    "varying lowp vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
                    "void main() {\n" +
                    "   vec4 inten = vec4(" + UNIFORM_INTENCITY + ", " +
                    UNIFORM_INTENCITY + ", " +
                    UNIFORM_INTENCITY + ", 1.0);\n" +
                    "   gl_FragColor = inten * " + ShaderProgramConstants.VARYING_COLOR + " * " +
                    "texture2D(" + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ", " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ");\n" +
                    "}";

    public static BlinkShader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BlinkShader();
        }

        return INSTANCE;
    }

    @Override
    protected void link(GLState pGLState) throws ShaderProgramLinkException {
        GLES20.glBindAttribLocation(mProgramID,
                ShaderProgramConstants.ATTRIBUTE_POSITION_LOCATION,
                ShaderProgramConstants.ATTRIBUTE_POSITION);
        GLES20.glBindAttribLocation(mProgramID,
                ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES_LOCATION,
                ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES);
        GLES20.glBindAttribLocation(mProgramID,
                ShaderProgramConstants.ATTRIBUTE_COLOR_LOCATION,
                ShaderProgramConstants.ATTRIBUTE_COLOR);
        super.link(pGLState);
        mModelViewProjectionMatrixLocation =
                getUniformLocation(ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX);
        mTexture0Location =
                getUniformLocation(ShaderProgramConstants.UNIFORM_TEXTURE_0);
        mIntencityLocation =
                getUniformLocation(UNIFORM_INTENCITY);
    }

    @Override
    public void bind(GLState pGLState, VertexBufferObjectAttributes pVertexBufferObjectAttributes) throws ShaderProgramException {
        super.bind(pGLState, pVertexBufferObjectAttributes);
        GLES20.glUniformMatrix4fv(
                mModelViewProjectionMatrixLocation,
                1,
                false,
                pGLState.getModelViewProjectionGLMatrix(),
                0
        );
        GLES20.glUniform1i(mTexture0Location, 0);
        if (mBlink) {
            long curTime = System.currentTimeMillis();
            long timeDelta = curTime - mPrevTime;
            mPrevTime = curTime;
            float angleDelta = timeDelta * BLINKING_SPEED;
            if (mGrow) {
                mCurAngle += angleDelta;
            } else {
                mCurAngle -= angleDelta;
            }
            if (mCurAngle > MAX_ANGLE) {
                mCurAngle -= 2 * angleDelta;
                mGrow = false;
            } else if (mCurAngle < 0) {
                mCurAngle += 2 * angleDelta;
                mGrow = true;
            }
        }
        GLES20.glUniform1f(mIntencityLocation, map(mCurAngle));
    }

    private static float map(double angle) {
        return (float) (MIN_INTENCITY + mDiv * Math.cos(Math.toRadians(angle)));
    }

    public static void setmBlink(boolean mBlink) {
        mPrevTime = System.currentTimeMillis();
        mGrow = false;
        mCurAngle = START_ANGLE;
        BlinkShader.mBlink = mBlink;
    }
}
