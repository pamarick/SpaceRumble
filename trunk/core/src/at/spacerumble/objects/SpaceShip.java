package at.spacerumble.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceShip extends GameObject {

  private boolean boost, left, right;

  public SpaceShip(World world, float x, float y) {
    texture = new Texture("yellowship.png");
    sprite = new Sprite(texture);
    sprite.setSize(1, 2);
    sprite.setOriginCenter();
    sprite.setRotation(0f);
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    // bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS, (sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
    bodyDef.position.set(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
    body = world.createBody(bodyDef);
    PolygonShape shape = new PolygonShape();
    // Vector2[] vertices = {new Vector2(0, 0), new Vector2(10, 0), new Vector2(0, 10)};
    // shape.set(vertices);
    // shape.setAsBox((sprite.getWidth() / 2 / PIXELS_TO_METERS)*100, (sprite.getHeight() / 2 / PIXELS_TO_METERS)*100);
    shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 0.5f;
    // fixtureDef.friction = 1f;
    // fixtureDef.restitution = 1f;
    body.createFixture(fixtureDef);
    shape.dispose();
    setPosition(x, y);
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
      body.applyForceToCenter(getBoostVelocity(100f), true);
    }
    if (isRight()) {
      body.setAngularVelocity(-5);
    } else if (isLeft()) {
      body.setAngularVelocity(5);
    } else {
      body.setAngularVelocity(0);
    }

    // sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2,
    // (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
    sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);

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

  public void setPosition(final float x, final float y) {
    // float rx = (x + (sprite.getWidth() / 2)) / PIXELS_TO_METERS;
    // float ry = (y + (sprite.getHeight() / 2)) / PIXELS_TO_METERS;
    float rx = (x + (sprite.getWidth() / 2));
    float ry = (y + (sprite.getHeight() / 2));
    body.setTransform(rx, ry, body.getAngle());
  }

}
