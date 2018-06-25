package at.spacerumble.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends Sprite {

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

	public void boost() {
		System.out.println("rotation: " + this.getRotation());

		float to360 = this.getRotation() - ((int) (this.getRotation() / 360f) * 360f);
		
		System.out.println("to360: " + to360);
		
		if (to360 < 0)
			to360 = 360 + to360;

		int quadrant = (int) (to360 / 90f);
		float factor = 1 - (to360 - (90f * quadrant)) / 90f;
		
		if (quadrant == 0) { // 0 bis 89.9 Grad
			velocity.y = velocity.y + factor * 1f;
			velocity.x = velocity.x - (1 - factor) * 1f;
		}
		if (quadrant == 1) { // 90 bis 179.9 Grad
			velocity.y = velocity.y - (1 - factor) * 1f;
			velocity.x = velocity.x - factor * 1f;
		}
		if (quadrant == 2) { // 180 bis 269.9 Grad
			velocity.y = velocity.y - factor * 1f;
			velocity.x = velocity.x + (1 - factor) * 1f;
		}
		if (quadrant == 3) { // 270 bis 359.9 Grad
			velocity.y = velocity.y + (1 - factor) * 1f;
			velocity.x = velocity.x + factor * 1f;
		}

		System.out.println("degree: " + to360 + ", rotation: " + this.getRotation());
	}

	public void left() {
		this.setRotation(this.getRotation() + 15);
	}

	public void right() {
		this.setRotation(this.getRotation() - 15);
	}

	public void stop() {
		velocity.x = 0;
		velocity.y = 0;
	}

}
