package com.voyagegames.demos.libgdx.example05.screens;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.Matrix4;

public class RenderEntity {
	
	public final Mesh mesh;
	public Matrix4 modelMatrix;
	
	public RenderEntity(final Mesh mesh) {
		this.mesh = mesh;
		this.modelMatrix = new Matrix4();
	}
	
	public void setModelMatrix(final Matrix4 modelMatrix) {
		this.modelMatrix = modelMatrix;
	}

}
