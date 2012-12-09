package com.voyagegames.demos.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.voyagegames.demos.libgdx.example07.positionedtextureregion.Game07;

public class MainEntry {
	
	public static void main(final String[] args) {
		new LwjglApplication(new Game07(), "Demo", 640, 360, true);
	}

}
