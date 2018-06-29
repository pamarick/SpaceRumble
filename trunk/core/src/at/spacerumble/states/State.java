package at.spacerumble.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.spacerumble.SpaceRumble;

public abstract class State implements ControllerListener, InputProcessor {

  private boolean endState;
  private float zoomFactor;

  protected OrthographicCamera cam;
  protected GameStateManager gsm;

  protected State(GameStateManager gsm) {
    this.gsm = gsm;
    cam = new OrthographicCamera(SpaceRumble.WIDTH, SpaceRumble.HEIGHT);
    zoomFactor = 1f;
    cam.viewportWidth = SpaceRumble.WIDTH;
    cam.viewportHeight = SpaceRumble.HEIGHT;
    cam.position.set(SpaceRumble.WIDTH / 2, SpaceRumble.HEIGHT / 2, 0);
    // cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    endState = false;
    Controllers.addListener(this);
    Gdx.input.setInputProcessor(this);
  }

  protected abstract void handleInput();

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

  public void setZoomFactor(float zoomFactor) {
    this.zoomFactor = zoomFactor;
    setZoom(zoomFactor);
  }

  private void setZoom(float factor) {
    cam.viewportWidth = SpaceRumble.WIDTH * factor;
    cam.viewportHeight = SpaceRumble.HEIGHT * factor;
    cam.position.set((SpaceRumble.WIDTH * factor) / 2, (SpaceRumble.HEIGHT * factor) / 2, 0);
  }
}
