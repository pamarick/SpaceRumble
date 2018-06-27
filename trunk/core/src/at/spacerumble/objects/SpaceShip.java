package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceShip extends GameObject {

	private boolean boost, left, right;

	public SpaceShip(World world) {
		super(new Texture("yellowship.png"), world);
		sprite.setPosition(400, 400);
		//sprite.setSize(15, 30);
		boost = left = right = false;
	}

	public SpaceShip(World world, float x, float y) {
		super(new Texture("yellowship.png"), world);
		sprite.setPosition(x, y);
		//sprite.setSize(15, 30);
		boost = left = right = false;
	}

	private Vector2 getBoostVelocity(float force) {
		Vector2 v = new Vector2();
		v.x = (float) (force * Math.cos(body.getAngle() + Math.PI / 2));
		v.y = (float) (force * Math.sin(body.getAngle() + Math.PI / 2));
		return v;
	}

	public void update(float dt) {
		if (isBoost()) {
			body.applyForceToCenter(getBoostVelocity(0.1f), true);
		}
		if (isRight()) {
			body.setAngularVelocity(-5);
		} else if (isLeft()) {
			body.setAngularVelocity(5);
		} else {
			body.setAngularVelocity(0);
		}

		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
		System.out.println("position:" + body.getPosition());
		System.out.println("position:" + sprite.getX() + ", " + sprite.getY());
		sprite.setRotation((float) Math.toDegrees(body.getAngle()));
	}

	public void stop() {
		body.setLinearVelocity(0, 0);
		body.setAngularVelocity(0);
	}

	public boolean isBoost() {
		return boost;
	}

	public void setBoost(boolean boost) {
		this.boost = boost;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

}
