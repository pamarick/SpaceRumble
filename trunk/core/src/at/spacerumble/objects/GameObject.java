package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject {

	public final static float PIXELS_TO_METERS = 100f;

	protected final Texture texture;
	protected final Sprite sprite;
	protected final Body body;
	
	protected GameObject(Texture texture, World world, float width, float height) {
		this.texture = texture;
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
		sprite.setOriginCenter();
		sprite.setRotation(0f);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
//		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS, (sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
		bodyDef.position.set(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
//		Vector2[] vertices = {new Vector2(0, 0), new Vector2(10, 0), new Vector2(0, 10)};
//		shape.set(vertices);
//		shape.setAsBox((sprite.getWidth() / 2 / PIXELS_TO_METERS)*100, (sprite.getHeight() / 2 / PIXELS_TO_METERS)*100);
		shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.0000005f;
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
