package at.spacerumble.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import at.spacerumble.SpaceRumble;

public abstract class State implements ControllerListener, InputProcessor, ContactListener  {

  private boolean endState;
  private float zoomFactor;

  protected OrthographicCamera cam;
  protected GameStateManager gsm;

  protected World world;
  
  protected Body bA, bB;
  
  protected State(GameStateManager gsm) {
    this.gsm = gsm;
    cam = new OrthographicCamera(SpaceRumble.WIDTH, SpaceRumble.HEIGHT);
    zoomFactor = 1f;
    // cam.viewportWidth = SpaceRumble.WIDTH;
    // cam.viewportHeight = SpaceRumble.HEIGHT;
    cam.position.set(SpaceRumble.WIDTH / 2, SpaceRumble.HEIGHT / 2, 0);
    // cam.setToOrtho(false, cam.viewportWidth * SpaceRumble.PPM, cam.viewportHeight * SpaceRumble.PPM);
    // cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    endState = false;
    Controllers.addListener(this);
    Gdx.input.setInputProcessor(this);
    bA = bB = null;
    world = null;
  }

  protected abstract void dispose();

  protected void update(float dt) {
    cam.update();
  };

  protected void render(SpriteBatch sb) {
    if (endState) {
      dispose();
    }
    sb.setProjectionMatrix(cam.combined);
  };

  protected void endState() {
    endState = true;
  }

  public float getZoomFactor() {
    return zoomFactor;
  }

  public void setZoom(float zoomFactor) {
    this.zoomFactor = zoomFactor;
    cam.viewportWidth = SpaceRumble.WIDTH * zoomFactor;
    cam.viewportHeight = SpaceRumble.HEIGHT * zoomFactor;
    cam.position.set((SpaceRumble.WIDTH * zoomFactor) / 2, (SpaceRumble.HEIGHT * zoomFactor) / 2, 0);
  }	
	
	@Override
	public void beginContact(Contact contact) {
		System.out.println("beginContact");
		bA = contact.getFixtureA().getBody();
		bB = contact.getFixtureB().getBody();
		System.out.println(bA.getUserData());
		System.out.println(bB.getUserData());
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
}
