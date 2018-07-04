package at.spacerumble.objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceShip extends GameObject {

	private boolean boost, left, right, shoot;

	private final List<Bullet> bullets;
	
	private final float shotsPerSeconds;
	private int hasShot;
	
	private long timer;
	
	public SpaceShip(final World world, final SpaceShipColor spaceShipColor, final float x, final float y,
			final float angle) {
		this.world = world;
		texture = new Texture(spaceShipColor.get());
		sprite = new Sprite(texture);
		sprite.setSize(75, 150);
		sprite.setOriginCenter();
		sprite.setScale(1 / 75f);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.set(getVertices());
		// shape.setAsBox((sprite.getWidth()*sprite.getScaleX()) / 2,
		// (sprite.getHeight()*sprite.getScaleX()) / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		// fixtureDef.friction = 1f;
		// fixtureDef.restitution = 1f;
		body.createFixture(fixtureDef);
		shape.dispose();
		setPosition(x, y, angle);
		boost = left = right = false;
		bullets = new ArrayList<>();
		shotsPerSeconds = 0.5f;
		timer = System.currentTimeMillis();
		hasShot = 0;
	}

	private Vector2 getBoostVelocity(float force) {
		Vector2 v = new Vector2();
		v.x = (float) (force * Math.cos(body.getAngle() + Math.PI / 2));
		v.y = (float) (force * Math.sin(body.getAngle() + Math.PI / 2));
		return v;
	}

	private void shoot() {
		if(System.currentTimeMillis() >= (timer + 1*1000)) {
			hasShot = 0;
			timer = System.currentTimeMillis();
		}
		if(shotsPerSeconds >= hasShot) {
			bullets.add(new Bullet(world, body.getPosition().x + (float) Math.cos(body.getAngle() + Math.PI / 2) * 1.3f - 0.075f, body.getPosition().y + (float) Math.sin(body.getAngle() + Math.PI / 2) * 1.3f - 0.075f,
					(float) Math.toDegrees(body.getAngle()), body.getLinearVelocity()));
			hasShot = hasShot + 1;
		}
	}
	
	public void update(float dt) {
		if (isBoost()) {
			body.applyForceToCenter(getBoostVelocity(25f), true);
		}

		if (isShoot())
			shoot();

		if (isRight()) {
			body.setAngularVelocity(-5);
		} else if (isLeft()) {
			body.setAngularVelocity(5);
		} else {
			body.setAngularVelocity(0);
		}

		if (body.getLinearVelocity().len() > 25f) {
			body.setLinearVelocity((25f / body.getLinearVelocity().len()) * body.getLinearVelocity().x,
					(25f / body.getLinearVelocity().len()) * body.getLinearVelocity().y);
		}

		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
		sprite.setRotation((float) Math.toDegrees(body.getAngle()));

		bullets.forEach(bullet -> bullet.update(dt));
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

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		bullets.forEach(bullet -> bullet.draw(sb));
	}

	@Override
	public void dispose() {
		super.dispose();
		bullets.forEach(Bullet::dispose);
	}

	public void setPosition(final float x, final float y, final float angle) {
		float rx = (x + ((sprite.getWidth() * sprite.getScaleX()) / 2));
		float ry = (y + ((sprite.getHeight() * sprite.getScaleY()) / 2));
		body.setTransform(rx, ry, (float) Math.toRadians(angle));
	}
	
	private Vector2[] getVertices() {
		float sX = sprite.getScaleX();
		float sY = sprite.getScaleY();
		float w = sprite.getWidth() * sX;
		float h = sprite.getHeight() * sY;
		float wS = 74 / sprite.getWidth();
		float hS = 148 / sprite.getHeight();
		return new Vector2[] { new Vector2(-w / 2 + 0f * sX * (1 / wS), -h / 2 + 0f * sY * (1 / hS)),
				new Vector2(-w / 2 + 0f * sX * (1 / wS), -h / 2 + 56f * sY * (1 / hS)),
				new Vector2(-w / 2 + 10f * sX * (1 / wS), -h / 2 + 97f * sY * (1 / hS)),
				new Vector2(-w / 2 + 37f * sX * (1 / wS), -h / 2 + 149f * sY * (1 / hS)),
				new Vector2(-w / 2 + 64f * sX * (1 / wS), -h / 2 + 97f * sY * (1 / hS)),
				new Vector2(-w / 2 + 74f * sX * (1 / wS), -h / 2 + 56f * sY * (1 / hS)),
				new Vector2(-w / 2 + 74f * sX * (1 / wS), -h / 2 + 0f * sY * (1 / hS)) };
	}

}
