package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Line extends GameObject {
	
	private final static float thickness = 0.6f;
	
	public Line(final World world, final Vector2 position, final float angle, final float length) {
		this(world, position, angle, length, thickness);
	}
	
	public Line(final World world, final Vector2 position, final float angle, final float length, final float thickness) {
		super(world);
		texture = new Texture("px_white.png");
	    sprite = new Sprite(texture);
	    sprite.setSize(length, thickness);
	    sprite.setOrigin(0, thickness/2);
	    
	    BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyDef.BodyType.KinematicBody;
	    body = world.createBody(bodyDef);
	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(length/2, thickness/2, new Vector2(length/2, 0), 0); 
	    body.createFixture(shape, 0.0f);

	    shape.dispose();
	    body.setTransform(position.x, position.y + thickness/2, (float) Math.toRadians(angle));
	    sprite.setPosition(position.x, position.y);
	    sprite.setRotation(angle);
	}
	
	public void update(float dt) {
	    sprite.setRotation((float) Math.toDegrees(body.getAngle()));
	}
	
	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	    sprite.setRotation((float) Math.toDegrees(body.getAngle()));
	}
}
