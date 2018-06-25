package at.spacerumble.gamepads;

import com.badlogic.gdx.controllers.PovDirection;

public abstract class Gamepad {	
	public static int A;
	public static int B;
	public static int X;
	public static int Y;
	public static int L1;
	public static int R1;
	public static int L3;
	public static int R3;
	public static int BACK;
	public static int START;
	
	public static PovDirection D_UP;
	public static PovDirection D_DOWN;
	public static PovDirection D_LEFT;
	public static PovDirection D_RIGHT;
	public static PovDirection D_LEFTUP;
	public static PovDirection D_LEFTDOWN;
	public static PovDirection D_RIGHTUP;
	public static PovDirection D_RIGHTDOWN;
	public static PovDirection D_CENTER;
	
	public static int L2; 				// value = 0 to 1f
	public static int R2;				// value = 0 to -1f
    public static int AXIS_LEFT_X;		// value = -1 (left) <-> +1 (right)
    public static int AXIS_LEFT_Y;		// value = -1 (up) <-> +1 (down)
    public static int AXIS_RIGHT_X;		// value = -1 (left) <-> +1 (right)
    public static int AXIS_RIGHT_Y;		// value = -1 (up) <-> +1 (down)
}
