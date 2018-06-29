package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameObject {

  public final static float PIXELS_TO_METERS = 100f;

  protected Texture texture;
  protected Sprite sprite;
  protected Body body;

  public void draw(SpriteBatch sb) {
    sb.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
  };

  public void dispose() {
    texture.dispose();
  }
}
