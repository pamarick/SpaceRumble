package at.spacerumble.gamepads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.GraphicsType;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class XboxGamepad extends Gamepad {
	static {
		A = 0;
		B = 1;
		X = 2;
		Y = 3;
		L1 = 4;
		R1 = 5;
		BACK = 6;
		START = 7;
		L3 = 8;
		R3 = 9;
		
		D_UP = PovDirection.north;
		D_DOWN = PovDirection.south;
		D_LEFT = PovDirection.east;
		D_RIGHT = PovDirection.west;
		D_LEFTUP = PovDirection.northEast;
		D_LEFTDOWN = PovDirection.southEast;
		D_RIGHTUP = PovDirection.northWest;
		D_RIGHTDOWN = PovDirection.southWest;
		D_CENTER = PovDirection.center;
		

		AXIS_LEFT_Y = 0;
		AXIS_LEFT_X = 1;
		AXIS_RIGHT_Y = 2;
		AXIS_RIGHT_X = 3;
		L3 = 4;
		R3 = 4;
	
	}
	
	public static boolean isXboxController(Controller controller) {
		String controllerName = controller.getName().toLowerCase();
		return (controllerName.contains("xbox") || controllerName.contains("x-box"));
	}
}
