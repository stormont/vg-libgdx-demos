package com.voyagegames.demos.libgdx.example07.positionedtextureregion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Game07 extends Game {

    private FPSLogger mFPSLogger;
    private ExampleScreen mScreen;
    
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
        
        final Mesh mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked());
        mesh.setVertices(vertices());
        mesh.setIndices(indices());
        
        final RenderEntity entity = new RenderEntity(mesh, GL10.GL_TRIANGLES);
        final RenderGroup renderGroup = new RenderGroup(new ShaderProgram(vertexShader(), fragmentShader()));

        renderGroup.addEntity(entity);
        
        mScreen = new ExampleScreen();
        mScreen.setRenderEntity(entity);
        mScreen.addRenderGroup(renderGroup);
        
        setScreen(mScreen);
	}
 
    @Override
    public void render() {
        mFPSLogger.log();
        
        final float timeDelta = Gdx.graphics.getDeltaTime();
        final Screen screen = getScreen();
        
        if (screen != null) {
        	screen.render(timeDelta);
        } else {
        	Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        
        // We can still do our own rendering here, if we want to
    }

	@Override
	public void dispose() {
		final Screen screen = getScreen();
		
		if (screen != null) {
			screen.dispose();
		}
	}

	@Override
	public void pause() {
		final Screen screen = getScreen();
		
		if (screen != null) {
			screen.pause();
		}
		
	}

	@Override
	public void resize(final int width, final int height) {
		final Screen screen = getScreen();
		
		if (screen != null) {
			screen.resize(width, height);
		}
		
	}

	@Override
	public void resume() {
		final Screen screen = getScreen();
		
		if (screen != null) {
			screen.resume();
		}
	}

}
