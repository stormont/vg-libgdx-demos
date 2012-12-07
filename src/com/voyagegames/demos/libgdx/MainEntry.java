package com.voyagegames.demos.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.voyagegames.demos.libgdx.example01.Scene01;

public class MainEntry {
	
	public static void main(final String[] args) {
		new LwjglApplication(new Scene01(), "Demo", 640, 360, true);
	}

}
