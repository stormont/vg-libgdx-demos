package com.voyagegames.demos.libgdx.example03.addrotation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public class Game03 implements ApplicationListener {
	
    private FPSLogger mFPSLogger;
    private OrthographicCamera mCamera;
    private ShaderProgram mShader;
    private Mesh mMesh;
    
    private float mElapsedTime;
    
    private String vertexShader() {
    	return  "attribute vec4 a_position;                  \n" +
                "attribute vec4 a_color;                     \n" +
                "uniform mat4 u_viewProj;                    \n" +
                "uniform mat4 u_modelMatrix;                 \n" +
                "varying vec4 v_color;                       \n" +
                "void main() {                               \n" +
                "   gl_Position =  u_viewProj * u_modelMatrix * a_position;  \n" +
                "   v_color = a_color;                       \n" +
                "}";
    }
    
    private String fragmentShader() {
    	return  "#ifdef GL_ES               \n" +
                "precision mediump float;   \n" + 
                "#endif                     \n" + 
                "varying vec4 v_color;      \n" + 
                "void main() {              \n" + 
                "  gl_FragColor = v_color;  \n" +
                "}";
    }

	@Override
	public void create() {
        mFPSLogger = new FPSLogger();
        mElapsedTime = 0f;
        
        mShader = new ShaderProgram(vertexShader(), fragmentShader());
        
        mMesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked());
        mMesh.setVertices(new float[] {
        		-0.5f, -0.5f,  0f,  1f,  0f,  0f,  1f,
	             0.5f, -0.5f,  0f,  0f,  1f,  0f,  1f,
	             0.5f,  0.5f,  0f,  0f,  0f,  1f,  1f,
	            -0.5f,  0.5f,  0f,  1f,  1f,  1f,  1f
        	});
        mMesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
        
        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();
        float aspectRatio = (float)width / (float)height;
        
        mCamera = new OrthographicCamera(2f * aspectRatio, 2f);
	}
 
    @Override
    public void render() {
        mFPSLogger.log();
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final float timeDelta = Gdx.graphics.getDeltaTime();
        
        mElapsedTime += timeDelta;

		mShader.begin();
		mShader.setUniformMatrix("u_viewProj", mCamera.combined);
		
		final float angle = (360f * mElapsedTime) % 360f;
		final Matrix4 modelMatrix = new Matrix4().rotate(0f, 0f, 1f, angle);
		
		mShader.setUniformMatrix("u_modelMatrix", modelMatrix);
		mMesh.render(mShader, GL10.GL_TRIANGLES);
		mShader.end();
    }

	@Override
	public void dispose() {
		mShader.dispose();
		mMesh.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(final int width, final int height) {
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
