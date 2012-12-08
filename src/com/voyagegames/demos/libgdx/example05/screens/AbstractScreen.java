package com.voyagegames.demos.libgdx.example05.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractScreen implements Screen {
	
	protected final Game        mGame;
	protected final SpriteBatch mBatch;
	protected final Stage       mStage;
	
	public AbstractScreen(final Game game) {
		mGame = game;
		mBatch = new SpriteBatch();
		mStage = new Stage(0, 0, true);
	}

	@Override
	public void dispose() {
		mBatch.dispose();
		mStage.dispose();
	}

	@Override
	public void render(final float delta) {
        mStage.act(delta);
        mStage.draw();
	}
	
	@Override
	public void resize(final int width, final int height) {
        mStage.setViewport(width, height, true);
	}

}
