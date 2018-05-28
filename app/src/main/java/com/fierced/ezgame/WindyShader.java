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

public class WindyShader extends ShaderProgram {


    public WindyShader() {
        super(VERTEXT_SHADER, FRAGMENT_SHADER);
    }

    private static WindyShader INSTANCE = null;

    private static float angle;

    private static int mModelViewProjectionMatrixLocation = ShaderProgramConstants.LOCATION_INVALID;
    private static int mTexture0Location = ShaderProgramConstants.LOCATION_INVALID;
    private static int mPosLocation = ShaderProgramConstants.LOCATION_INVALID;

    private static final String VERTEXT_SHADER =
            "uniform mat4 " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX + ";\n" +
                    "attribute vec4 " + ShaderProgramConstants.ATTRIBUTE_POSITION + ";\n" +
                    "attribute vec2 " + ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
                    "attribute vec4 " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
                    "varying vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
                    "varying vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
                    "uniform float angle;\n" +
                    "vec4 world_pos;\n" +
                    "void main() {\n" +
                    "   " + ShaderProgramConstants.VARYING_COLOR +
                    " = " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
                    "   " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + " = " +
                    ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
                    "world_pos = " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX +
                    "*" + ShaderProgramConstants.ATTRIBUTE_POSITION + ";" +
                    "   gl_Position = world_pos + vec4(sin(angle+world_pos.y*world_pos.x*50.0)*(1.0-"+ShaderProgramConstants.ATTRIBUTE_POSITION+".y/160.0)/20.0,0.0,0.0,0.0) ;\n" +
                    "}";

    private static final String FRAGMENT_SHADER =
            "precision lowp float;\n" +
                    "uniform float intensity;\n" +
                    "uniform sampler2D " + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ";\n" +
                    "varying mediump vec2 " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ";\n" +
                    "varying lowp vec4 " + ShaderProgramConstants.VARYING_COLOR + ";\n" +
                    "void main() {\n" +
                    "   gl_FragColor = " + ShaderProgramConstants.VARYING_COLOR + " * " +
                    "texture2D(" + ShaderProgramConstants.UNIFORM_TEXTURE_0 + ", " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + ");\n" +
                    "}";

    public static WindyShader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WindyShader();
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
        mPosLocation =
                getUniformLocation("angle");
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
        GLES20.glUniform1f(mPosLocation, angle);

    }

    public void Update(float delta) {
        angle += delta;
        if (angle > 2 * 3.14f) angle = 0;
    }


}