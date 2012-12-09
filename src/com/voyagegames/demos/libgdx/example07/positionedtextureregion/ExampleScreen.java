package com.voyagegames.demos.libgdx.example07.positionedtextureregion;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public class ExampleScreen extends AbstractScreen {

    private final List<RenderGroup> mRenderGroups;
    
    private OrthographicCamera mCamera;
    private RenderEntity mEntity;
    private float mElapsedTime;
    
    private Texture mBackground;
    private Texture mForeground;

	public ExampleScreen() {
		super();
		
		mRenderGroups = new ArrayList<RenderGroup>();
	}
	
	public void setRenderEntity(final RenderEntity entity) {
		mEntity = entity;
	}
	
	public void addRenderGroup(final RenderGroup renderGroup) {
		mRenderGroups.add(renderGroup);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
        mElapsedTime = 0f;
        
        mBackground = new Texture("background.jpg");
        mBackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        mForeground = new Texture("foreground.jpg");
        mForeground.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void render(final float delta) {
    	Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
        mElapsedTime += delta;
        
        super.renderBackground(delta, mCamera);
		
		final float angle = (360f * mElapsedTime) % 360f;
		final float scale = Gdx.graphics.getWidth() / 3f;
		final Matrix4 modelMatrix = new Matrix4()
				.rotate(0f, 0f, 1f, angle)
				.scale(scale, scale, scale);
		
		mEntity.setModelMatrix(modelMatrix);
		
		for (final RenderGroup rg : mRenderGroups) {
			rg.render(delta, mCamera);
		}
        
        super.renderForeground(delta, mCamera);
	}

	@Override
	public void resize(final int width, final int height) {
        final float aspectRatio = (float)width / (float)height;
        final float size = width > height ? width : height;
        
        mCamera = new OrthographicCamera(size * aspectRatio, size);
        
        final TextureRegion background = new TextureRegion(mBackground);
        final PositionedTextureRegion backgroundRegion = new PositionedTextureRegion(
        		background,
        		-background.getRegionWidth() / 2f,
        		-background.getRegionHeight() / 2f);
        
        final TextureRegion foreground = new TextureRegion(mForeground);
        final PositionedTextureRegion foregroundRegion = new PositionedTextureRegion(
        		foreground,
        		-mCamera.viewportWidth / 2f,
        		mCamera.viewportHeight / 2f - foreground.getRegionHeight());
        
        super.clearBackgrounds();
        super.addBackground(backgroundRegion);
        
        super.clearForegrounds();
        super.addForeground(foregroundRegion);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		for (final RenderGroup rg : mRenderGroups) {
			rg.shader.dispose();
		}
		
		mEntity.mesh.dispose();
		mBackground.dispose();
		
		super.dispose();
	}

}
