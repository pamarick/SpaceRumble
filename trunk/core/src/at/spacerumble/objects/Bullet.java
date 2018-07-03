package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Bullet extends GameObject {

  public Bullet(final World world, final float x, final float y, final float angle) {
    texture = new Texture("px_white.png");
    sprite = new Sprite(texture);
    sprite.setSize(1, 1);
    sprite.setOriginCenter();
    sprite.setScale(1 / 5f);
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    body = world.createBody(bodyDef);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox((sprite.getWidth() * sprite.getScaleX()) / 2, (sprite.getHeight() * sprite.getScaleX()) / 2);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 7.750f;
    fixtureDef.friction = 0f;
    fixtureDef.restitution = 1f;
    body.createFixture(fixtureDef);
    shape.dispose();
    setPosition(x, y, angle);
    body.applyForceToCenter(getBoostVelocity(1000f), true);
  }

  private Vector2 getBoostVelocity(float force) {
    Vector2 v = new Vector2();
    v.x = (float) (force * Math.cos(body.getAngle() + Math.PI / 2));
    v.y = (float) (force * Math.sin(body.getAngle() + Math.PI / 2));
    return v;
  }

  public void update(float dt) {

    if (body.getLinearVelocity().len() > 25f) {
      body.setLinearVelocity((25f / body.getLinearVelocity().len()) * body.getLinearVelocity().x, (25f / body.getLinearVelocity().len()) * body.getLinearVelocity().y);
    }

    sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
    sprite.setRotation((float) Math.toDegrees(body.getAngle()));
  }

  public void stop() {
    body.setLinearVelocity(0, 0);
    body.setAngularVelocity(0);
  }

  public void setPosition(final float x, final float y, final float angle) {
    float rx = (x + ((sprite.getWidth() * sprite.getScaleX()) / 2));
    float ry = (y + ((sprite.getHeight() * sprite.getScaleY()) / 2));
    body.setTransform(rx, ry, (float) Math.toRadians(angle));
  }

}
