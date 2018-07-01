package at.spacerumble.players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.spacerumble.objects.SpaceShip;

public class Player {
	
	private String name;
	private SpaceShip spaceShip;
	private int InputId;
	
	private int boostKey;
	private int leftKey;
	private int rightKey;
	
	public Player(String name, int boostKey, int leftKey, int rightKey) {
		this.name = name;
		this.spaceShip = null;
		this.boostKey = boostKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getInputId() {
		return InputId;
	}

	public void setInputId(int inputId) {
		InputId = inputId;
	}
	
	public void input(int key) {
		if(key == boostKey)
			spaceShip.setBoost(!spaceShip.isBoost());
		if(key == leftKey)
			spaceShip.setLeft(!spaceShip.isLeft());
		if(key == rightKey)
			spaceShip.setRight(!spaceShip.isRight());
	}
		
}
