package at.spacerumble.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import at.spacerumble.SpaceRumble;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SpaceRumble";
		config.height = 720;
		config.width = 1280;
		config.samples = 4;
		new LwjglApplication(new SpaceRumble(), config);
	}
}
