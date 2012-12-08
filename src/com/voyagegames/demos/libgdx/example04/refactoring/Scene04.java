package com.voyagegames.demos.libgdx.example04.refactoring;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public class Scene04 implements ApplicationListener {
	
    private FPSLogger mFPSLogger;
    private OrthographicCamera mCamera;
    private RenderGroup mRenderGroup;
    private RenderEntity mEntity;
    
    private float mElapsedTime;
    
    private String vertexShader() {
    	return  "attribute vec4 a_position;                                 \n" +
                "attribute vec4 a_color;                                    \n" +
                "uniform mat4 u_viewProj;                                   \n" +
                "uniform mat4 u_modelMatrix;                                \n" +
                "varying vec4 v_color;                                      \n" +
                "void main() {                                              \n" +
                "   gl_Position = u_viewProj * u_modelMatrix * a_position;  \n" +
                "   v_color = a_color;                                      \n" +
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
    
    private float[] vertices() {
    	return new float[] {
        		-0.5f, -0.5f,  0f,  1f,  0f,  0f,  1f,
	             0.5f, -0.5f,  0f,  0f,  1f,  0f,  1f,
	             0.5f,  0.5f,  0f,  0f,  0f,  1f,  1f,
	            -0.5f,  0.5f,  0f,  1f,  1f,  1f,  1f
       		};
    }
    
    private short[] indices() {
    	return new short[] { 0, 1, 2, 2, 3, 0 };
    }

	@Override
	public void create() {
        mFPSLogger = new FPSLogger();
        mElapsedTime = 0f;
        
        final Mesh mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked());
        mesh.setVertices(vertices());
        mesh.setIndices(indices());
        
        mEntity = new RenderEntity(mesh);

        mRenderGroup = new RenderGroup(new ShaderProgram(vertexShader(), fragmentShader()));
        mRenderGroup.addEntity(mEntity);
        
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
		
		final float angle = (360f * mElapsedTime) % 360f;
		final Matrix4 modelMatrix = new Matrix4().rotate(0f, 0f, 1f, angle);
		
		mEntity.setModelMatrix(modelMatrix);
		mRenderGroup.render(timeDelta, mCamera);
    }

	@Override
	public void dispose() {
		mRenderGroup.shader.dispose();
		mEntity.mesh.dispose();
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
