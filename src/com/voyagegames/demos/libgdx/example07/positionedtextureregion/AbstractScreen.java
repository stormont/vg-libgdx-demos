package com.voyagegames.demos.libgdx.example07.positionedtextureregion;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {
	
	protected final SpriteBatch mBatch;
	protected final List<PositionedTextureRegion> mBackgrounds;
	protected final List<PositionedTextureRegion> mForegrounds;
	
	public AbstractScreen() {
		mBatch = new SpriteBatch();
		mBackgrounds = new ArrayList<PositionedTextureRegion>();
		mForegrounds = new ArrayList<PositionedTextureRegion>();
	}

	@Override
	public void dispose() {
		mBatch.dispose();
	}
	
	public void clearBackgrounds() {
		mBackgrounds.clear();
	}
	
	public void addBackground(final PositionedTextureRegion textureRegion) {
		mBackgrounds.add(textureRegion);
	}
	
	public void clearForegrounds() {
		mForegrounds.clear();
	}
	
	public void addForeground(final PositionedTextureRegion textureRegion) {
		mForegrounds.add(textureRegion);
	}
	
	public void renderBackground(final float delta, final Camera camera) {
		if (mBackgrounds.size() == 0) {
			return;
		}
		
		mBatch.setProjectionMatrix(camera.combined);
		mBatch.disableBlending();
		mBatch.begin();
		
		for (final PositionedTextureRegion region : mBackgrounds) {
			final int width = region.textureRegion.getRegionWidth();
			final int height = region.textureRegion.getRegionHeight();
			
			mBatch.draw(region.textureRegion, region.xOffset, region.yOffset, width, height);
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
		
		for (final PositionedTextureRegion region : mForegrounds) {
			final int width = region.textureRegion.getRegionWidth();
			final int height = region.textureRegion.getRegionHeight();
			
			mBatch.draw(region.textureRegion, region.xOffset, region.yOffset, width, height);
		}
		
		mBatch.end();
	}

}
