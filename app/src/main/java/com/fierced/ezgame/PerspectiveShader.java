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

public class PerspectiveShader extends ShaderProgram {


    public PerspectiveShader() {
        super(VERTEXT_SHADER, FRAGMENT_SHADER);
    }

    private static PerspectiveShader INSTANCE = null;

    private static float camPosX = 0.0f;
    private static float camPosY = 0.0f;

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
                    "uniform vec4 pos;\n" +
                    "vec4 world_pos;\n" +
                    "void main() {\n" +
                    "   " + ShaderProgramConstants.VARYING_COLOR +
                    " = " + ShaderProgramConstants.ATTRIBUTE_COLOR + ";\n" +
                    "   " + ShaderProgramConstants.VARYING_TEXTURECOORDINATES + " = " +
                    ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES + ";\n" +
                    "world_pos = " + ShaderProgramConstants.UNIFORM_MODELVIEWPROJECTIONMATRIX +
                    "*" + ShaderProgramConstants.ATTRIBUTE_POSITION + ";" +
                    "   gl_Position = world_pos + vec4((world_pos.x-1.5+pos.x)*(-world_pos.y-0.25)*pos.y*2.0,(-world_pos.y-0.25)*0.1, pos.z, 0.0) ;\n" +
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

    public static PerspectiveShader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PerspectiveShader();
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
                getUniformLocation("pos");
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
        GLES20.glUniform4f(mPosLocation, camPosX, camPosY, 0.0f, 1.0f);
        //TODO UPDATE

    }

    public void setPos(float v, float v1, float v2, float v3) {
        camPosX = v;
        camPosY = v1;
    }

}