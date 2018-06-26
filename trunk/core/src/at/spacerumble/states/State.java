package at.spacerumble.states;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State implements ControllerListener, InputProcessor {

  protected OrthographicCamera cam;
  protected Vector3 mouse;
  protected GameStateManager gsm;

  protected boolean endState;

  protected State(GameStateManager gsm) {
    this.gsm = gsm;
    cam = new OrthographicCamera();
    mouse = new Vector3();
    endState = false;
  }

  protected abstract void handleInput();

  protected abstract void dispose();

  protected abstract void update(float dt);

  protected void render(SpriteBatch sb) {
    if (endState) {
      this.dispose();
    }
  };

}
