package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject implements ContactListener {

	public final static float PIXELS_TO_METERS = 100f;

	protected Texture texture;
	protected Sprite sprite;
	protected Body body;
	protected World world;

	protected GameObject(World world) {
		world.setContactListener(this);
	}
	
	public void draw(SpriteBatch sb) {
		sb.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(),
				sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
	};

	public void dispose() {
		texture.dispose();
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void beginContact(Contact contact) {
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
