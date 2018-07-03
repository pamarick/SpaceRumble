package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Line extends GameObject {
	
	private final static float thickness = 1f;
	
	public Line(final World world, final Vector2 position, final float angle, final float length) {
		this(world, position, angle, length, thickness);
	}
	
	public Line(final World world, final Vector2 position, final float angle, final float length, final float thickness) {
		texture = new Texture("px_white.png");
	    sprite = new Sprite(texture);
	    sprite.setSize(length, thickness);
	    sprite.setOrigin(0, 0);
	    
	    BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyDef.BodyType.StaticBody;
	    body = world.createBody(bodyDef);
	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(length/2, thickness/2); 
	    body.createFixture(shape, 0.0f);
	    //body.setTransform((position.x) + getOffset(angle, length).x, position.y + getOffset(angle, length).y, (float)Math.toRadians(angle));
	    body.setTransform((position.x), position.y, (float)Math.toRadians(angle));
	    shape.dispose();

	    sprite.setPosition(position.x, position.y);
	    sprite.setRotation(angle);
	}
	
	private Vector2 getOffset(final float angle, final float length) {
		Vector2 offset = new Vector2();
		offset.x = (float) (Math.cos(Math.toRadians(angle))*(length/2));
		offset.y = (float) (Math.sin(Math.toRadians(angle))*(length/2));
		return offset;
	}
	
}
