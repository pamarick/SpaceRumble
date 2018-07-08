package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject {

	public final static float PIXELS_TO_METERS = 100f;

	public boolean destroy = false;

	protected Texture texture;
	protected Sprite sprite;
	protected Body body;
	protected World world;

	public void draw(SpriteBatch sb) {
		sb.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(),
				sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
	}

	public void update(float dt) {
		if(destroy) {
			dispose();
		}	
	}
	
	public void dispose() {
		texture.dispose();
		sprite = null;
		world.destroyBody(body);
		body.setUserData(null);
		body = null;
		world = null;
	}
	
	public void destroy() {
		destroy = true;
	}

}
