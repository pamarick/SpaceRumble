package at.spacerumble.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceShip extends Sprite {

  private static Texture ship = new Texture("yellowship.png");

  private final BodyDef bodyDef;

  private final FixtureDef fixtureDef;

  private final Body body;

  boolean drawSprite = true;

  final float PIXELS_TO_METERS = 100f;

  boolean boost, left, right;

  public SpaceShip(Color color, float x, float y, World world) {
    super(ship);
    this.setSize(15, 30);
    this.setOriginCenter();
    this.setRotation(0f);
    this.setColor(color);
    this.setPosition(x, y);
    bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set((this.getX() + this.getWidth() / 2) / PIXELS_TO_METERS, (this.getY() + this.getHeight() / 2) / PIXELS_TO_METERS);
    body = world.createBody(bodyDef);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(this.getWidth() / 2 / PIXELS_TO_METERS, this.getHeight() / 2 / PIXELS_TO_METERS);
    fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 0.5f;
    body.createFixture(fixtureDef);
    shape.dispose();

    boost = false;
    left = false;
    right = false;
  }

  public void update(float dt) {

    if (boost) {
      body.applyForceToCenter(getBoostVelocity(0.1f), true);
    }
    if (right) {
      body.setAngularVelocity(-5);
    } else if (left) {
      body.setAngularVelocity(5);
    } else {
      body.setAngularVelocity(0);
    }

    this.setPosition((body.getPosition().x * PIXELS_TO_METERS) - this.getWidth() / 2, (body.getPosition().y * PIXELS_TO_METERS) - this.getHeight() / 2);
    this.setRotation((float) Math.toDegrees(body.getAngle()));
 }

  public void startBoost() {
    boost = true;
  }

  public void endBoost() {
    boost = false;
  }

  public void down() {
    body.setLinearVelocity(0f, 0f);
    body.setAngularVelocity(0f);
  }

  public void startLeft() {
    left = true;
  }

  public void endLeft() {
    left = false;
  }

  public void startRight() {
    right = true;
  }

  public void endRight() {
    right = false;
  }

  private Vector2 getBoostVelocity(float force) {
    Vector2 v = new Vector2();
    v.x = (float) (force * Math.cos(body.getAngle() + Math.PI / 2));
    v.y = (float) (force * Math.sin(body.getAngle() + Math.PI / 2));
    return v;
  }

}
