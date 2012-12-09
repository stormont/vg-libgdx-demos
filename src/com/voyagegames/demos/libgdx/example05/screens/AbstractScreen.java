package com.voyagegames.demos.libgdx.example05.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {
	
	protected final SpriteBatch mBatch;
	
	public AbstractScreen() {
		mBatch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		mBatch.dispose();
	}

}
