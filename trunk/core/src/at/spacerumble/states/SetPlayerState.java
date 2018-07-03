package at.spacerumble.states;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import at.spacerumble.SpaceRumble;
import at.spacerumble.gamepads.XboxGamepad;
import at.spacerumble.players.Player;
import at.spacerumble.players.PlayerManager;

public class SetPlayerState extends State {

  private final int max;

  private final Map<Integer, Player> playerMap;
  private final List<Integer> controllersHash;
  private final PlayerManager playerManager;

  private final BitmapFont font;

  public SetPlayerState(GameStateManager gsm) {
    super(gsm);
    // TODO Auto-generated constructor stub
    this.max = 4;

    playerManager = new PlayerManager();
    playerMap = new HashMap<>();
    controllersHash = new ArrayList<>();

    font = new BitmapFont();
    font.setUseIntegerPositions(false);
    font.getData().setScale(0.1f, 0.1f);
    font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
  }

  @Override
  public void update(float dt) {
    super.update(dt);
  }

  @Override
  public void render(SpriteBatch sb) {
    super.render(sb);
    sb.begin();
    font.draw(sb, "playerMap: " + playerMap + ", controllersHash: " + controllersHash, 0, SpaceRumble.HEIGHT * getZoomFactor());
    sb.end();
    // Renderer.debugRenderer.render(world, cam.combined);
  }

  @Override
  protected void dispose() {
    playerMap.forEach((key, value) -> {
      playerManager.addPlayer(value);
    });
    gsm.set(new MenuState(gsm, playerManager));
  }

  @Override
  public void connected(Controller controller) {
    // TODO Auto-generated method stub
  }

  @Override
  public void disconnected(Controller controller) {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    System.out.println("controller: " + controller + ", buttonCode: " + buttonCode);
    for (int i = 1; i <= max; i++) {
      if (!playerMap.containsKey(i) && !controllersHash.contains(controller.hashCode())) {
        Player player = new Player("" + i, XboxGamepad.A, XboxGamepad.L1, XboxGamepad.R1, XboxGamepad.X);
        player.setInputId(controller.hashCode());
        playerMap.put(i, player);
        controllersHash.add(controller.hashCode());
        break;
      }
    }
    return false;
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (Input.Keys.ENTER == keycode) {
      endState();
    } else {
      for (int i = 1; i <= max; i++) {
        if (!playerMap.containsKey(i) && !controllersHash.contains(1)) {
          Player player = new Player("" + i, Input.Keys.UP, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE);
          player.setInputId(1);
          playerMap.put(i, player);
          controllersHash.add(1);
          break;
        }
      }
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    // TODO Auto-generated method stub
    return false;
  }

}
