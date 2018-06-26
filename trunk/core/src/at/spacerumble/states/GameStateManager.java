package at.spacerumble.states;

import java.util.Stack;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameStateManager implements ControllerListener, InputProcessor {

  private final Stack<State> states;

  public GameStateManager() {
    states = new Stack<State>();
  }

  public void push(State state) {
    states.push(state);
  }

  public void pop() {
    states.pop();
  }

  public void set(State state) {
    states.pop();
    states.push(state);
  }

  public void update(float dt) {
    states.peek().update(dt);
  }

  public void render(SpriteBatch sb) {
    states.peek().render(sb);
  }

  public void handleInput() {
    states.peek().handleInput();
  }

  @Override
  public void connected(Controller controller) {
    states.peek().connected(controller);
  }

  @Override
  public void disconnected(Controller controller) {
    states.peek().disconnected(controller);
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    return states.peek().buttonDown(controller, buttonCode);
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    return states.peek().buttonUp(controller, buttonCode);
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    return states.peek().axisMoved(controller, axisCode, value);
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    return states.peek().povMoved(controller, povCode, value);
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    return states.peek().xSliderMoved(controller, sliderCode, value);
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    return states.peek().ySliderMoved(controller, sliderCode, value);
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    return states.peek().accelerometerMoved(controller, accelerometerCode, value);
  }

  @Override
  public boolean keyDown(int keycode) {
    return states.peek().keyDown(keycode);
  }

  @Override
  public boolean keyUp(int keycode) {
    return states.peek().keyUp(keycode);
  }

  @Override
  public boolean keyTyped(char character) {
    return states.peek().keyTyped(character);
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return states.peek().touchDown(screenX, screenY, pointer, button);
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return states.peek().touchUp(screenX, screenY, pointer, button);
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return states.peek().touchDragged(screenX, screenY, pointer);
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return states.peek().mouseMoved(screenX, screenY);
  }

  @Override
  public boolean scrolled(int amount) {
    return states.peek().scrolled(amount);
  }
}
