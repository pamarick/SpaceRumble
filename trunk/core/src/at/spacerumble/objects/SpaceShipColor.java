package at.spacerumble.objects;

public enum SpaceShipColor {
	BLACK("ship_black.png"),
	BLUE("ship_blue.png"),
	GREEN("ship_green.png"),
	ORANGE("ship_orange.png"),
	PINK("ship_pink.png"),
	RED("ship_red.png"),
	YELLOW("ship_yellow.png");
	
	private final String string;
	
	private SpaceShipColor(final String string) {
		this.string = string;
	}
	
	public String get() {
		return string;
	}
}
