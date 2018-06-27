package at.spacerumble.players;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import at.spacerumble.objects.SpaceShip;

public class Player {

	private String name;
	private Controller gamepad;
	private SpaceShip spaceShip;
	
	
	public Player(String name) {
		this.name = name;
		this.gamepad = null;
		this.spaceShip = new SpaceShip(new World(new Vector2(0, 0), true));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setGamepad(Controller gamepad) {
		this.gamepad = gamepad;
	}

	public Controller getGamepad() {
		return gamepad;
	}
	
	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}
	
	public SpaceShip getSpaceShip() {
		return spaceShip;
	}
	
	public void draw(SpriteBatch sb) {
		spaceShip.draw(sb);
	}
	
	public void update(float dt) {
		spaceShip.update(dt);
	}
	
	public void dispose() {
		spaceShip.dispose();
	}
		
}
