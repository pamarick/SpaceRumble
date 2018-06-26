package at.spacerumble;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import at.spacerumble.states.GameStateManager;
import at.spacerumble.states.MenuState;
import at.spacerumble.threads.HandleInputThread;
import at.spacerumble.threads.UpdateThread;

public class SpaceRumble extends ApplicationAdapter implements ControllerListener, InputProcessor {

  public static final int WIDTH = 1280;
  public static final int HEIGHT = 720;

  public static final String TITLE = "SpaceRumble";

  private GameStateManager gsm;

  private SpriteBatch batch;

  private HandleInputThread handleInputThread;
  private UpdateThread updateThread;

  @Override
  public void create() {
    batch = new SpriteBatch();
    gsm = new GameStateManager();
    handleInputThread = new HandleInputThread(gsm);
    updateThread = new UpdateThread(gsm, Gdx.graphics.getDeltaTime());
    Gdx.gl.glClearColor(0, 0, 0, 0);
    Controllers.addListener(this);
    Gdx.input.setInputProcessor(this);
    gsm.push(new MenuState(gsm, 0));
  }

  @Override
  public void render() {

    gsm.handleInput();
    gsm.update(Gdx.graphics.getDeltaTime());

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    gsm.render(batch);

  }

  @Override
  public void dispose() {
    super.dispose();
    batch.dispose();
  }

  @Override
  public void connected(Controller controller) {
    gsm.connected(controller);
  }

  @Override
  public void disconnected(Controller controller) {
    gsm.disconnected(controller);
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    return gsm.buttonDown(controller, buttonCode);
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    return gsm.buttonUp(controller, buttonCode);
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    return gsm.axisMoved(controller, axisCode, value);
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    return gsm.povMoved(controller, povCode, value);
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    return gsm.xSliderMoved(controller, sliderCode, value);
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    return gsm.ySliderMoved(controller, sliderCode, value);
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    return gsm.accelerometerMoved(controller, accelerometerCode, value);
  }

  @Override
  public boolean keyDown(int keycode) {
    return gsm.keyDown(keycode);
  }

  @Override
  public boolean keyUp(int keycode) {
    return gsm.keyUp(keycode);
  }

  @Override
  public boolean keyTyped(char character) {
    return gsm.keyTyped(character);
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return gsm.touchDown(screenX, screenY, pointer, button);
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return gsm.touchUp(screenX, screenY, pointer, button);
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return gsm.touchDragged(screenX, screenY, pointer);
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return gsm.mouseMoved(screenX, screenY);
  }

  @Override
  public boolean scrolled(int amount) {
    return gsm.scrolled(amount);
  }
}
