package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject {

	public final static float PIXELS_TO_METERS = 100f;

	protected Texture texture;
	protected Sprite sprite;
	protected Body body;

	protected GameObject(Texture texture, World world) {
		 super();
		 this.texture = texture;
		 sprite = new Sprite(texture);
		 sprite.setOriginCenter();
		 sprite.setRotation(0f);
		 sprite.setColor(Color.BLACK);
		 sprite.setPosition(-1000, -1000);
		
		 BodyDef bodyDef = new BodyDef();
		 bodyDef.type = BodyDef.BodyType.DynamicBody;
		 bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) /
		 PIXELS_TO_METERS, (sprite.getY() + sprite.getHeight() / 2) /
		 PIXELS_TO_METERS);
		
		 PolygonShape shape = new PolygonShape();
		 shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight() /
		 2 / PIXELS_TO_METERS);
		
		 FixtureDef fixtureDef = new FixtureDef();
		 fixtureDef.shape = shape;
		 fixtureDef.density = 0.5f;
		
		 body = world.createBody(bodyDef);
		 body.createFixture(fixtureDef);
		 
		 shape.dispose();
	}

	public void draw(SpriteBatch sb) {
		sb.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(),
				sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
	};

	public void dispose() {
		texture.dispose();
	}
}
