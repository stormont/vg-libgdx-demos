package com.voyagegames.demos.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.voyagegames.demos.libgdx.example05.screens.Scene05;

public class MainEntry {
	
	public static void main(final String[] args) {
		new LwjglApplication(new Scene05(), "Demo", 640, 360, true);
	}

}
