package com.voyagegames.demos.libgdx.example05.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

public class Screen1 extends AbstractScreen {

    private final List<RenderGroup> mRenderGroups;
    
    private OrthographicCamera mCamera;
    private RenderEntity mEntity;
    private float mElapsedTime;

	public Screen1(final Game game) {
		super(game);
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
        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();
        float aspectRatio = (float)width / (float)height;
        
        mCamera = new OrthographicCamera(2f * aspectRatio, 2f);
        mElapsedTime = 0f;
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// Want a fun effect? Don't use these glClear methods. ;-)
    	Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
        mElapsedTime += delta;
		
		final float angle = (360f * mElapsedTime) % 360f;
		final Matrix4 modelMatrix = new Matrix4().rotate(0f, 0f, 1f, angle);
		
		mEntity.setModelMatrix(modelMatrix);
		
		for (final RenderGroup rg : mRenderGroups) {
			rg.render(delta, mCamera);
		}
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
		super.dispose();
	}

}
