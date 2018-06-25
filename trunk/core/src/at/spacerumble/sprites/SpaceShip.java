package at.spacerumble.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class SpaceShip extends Sprite{

	private static Texture ship = new Texture("yellowship.png");
	
	private Vector2 velocity;
	
	public SpaceShip(Color color, float x, float y) {
		super(ship);
		this.setSize(15, 30);
		this.setOriginCenter();
		this.setRotation(0f);
		this.setColor(color);
		this.setPosition(x, y);
		velocity = new Vector2(0, 0);
	}
	
	public void update(float dt) {
		this.setPosition(this.getX() + velocity.x, this.getY() + velocity.y);
	}

	public void addVX() {
		velocity.x = 0.5f;
	}
	
	public void addVY() {
		velocity.y = 0.5f;
	}
	
	public void subVX() {
		velocity.x = -0.5f;
	}
	
	public void subVY() {
		velocity.y = -0.5f;
	}
	
	public void stopV() {
		velocity.y = 0;
		velocity.x = 0;
	}
}
