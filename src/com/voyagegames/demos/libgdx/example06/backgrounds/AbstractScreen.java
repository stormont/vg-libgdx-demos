package com.voyagegames.demos.libgdx.example06.backgrounds;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AbstractScreen implements Screen {
	
	protected final SpriteBatch mBatch;
	protected final List<TextureRegion> mBackgrounds;
	protected final List<TextureRegion> mForegrounds;
	
	public AbstractScreen() {
		mBatch = new SpriteBatch();
		mBackgrounds = new ArrayList<TextureRegion>();
		mForegrounds = new ArrayList<TextureRegion>();
	}

	@Override
	public void dispose() {
		mBatch.dispose();
	}
	
	public void clearBackgrounds() {
		mBackgrounds.clear();
	}
	
	public void addBackground(final TextureRegion textureRegion) {
		mBackgrounds.add(textureRegion);
	}
	
	public void clearForegrounds() {
		mForegrounds.clear();
	}
	
	public void addForeground(final TextureRegion textureRegion) {
		mForegrounds.add(textureRegion);
	}
	
	public void renderBackground(final float delta, final Camera camera) {
		if (mBackgrounds.size() == 0) {
			return;
		}
		
		mBatch.setProjectionMatrix(camera.combined);
		mBatch.disableBlending();
		mBatch.begin();
		
		for (final TextureRegion region : mBackgrounds) {
			final int width = region.getRegionWidth();
			final int height = region.getRegionHeight();
			
			mBatch.draw(region, -width / 2, -height / 2, region.getRegionWidth(), region.getRegionHeight());
		}
		
		mBatch.end();
	}
	
	public void renderForeground(final float delta, final Camera camera) {
		if (mForegrounds.size() == 0) {
			return;
		}
		
		mBatch.setProjectionMatrix(camera.combined);
		mBatch.enableBlending();  
		mBatch.begin();
		
		// Not a great long term solution, but it will demonstrate single regions for now
		for (final TextureRegion region : mForegrounds) {
			final int width = region.getRegionWidth();
			final int height = region.getRegionHeight();
			
			mBatch.draw(region, -width / 2, -height / 2, region.getRegionWidth(), region.getRegionHeight());
		}
		
		mBatch.end();
	}

}
