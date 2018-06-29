package at.spacerumble.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import at.spacerumble.SpaceRumble;
import at.spacerumble.debugrenderer.Renderer;
import at.spacerumble.objects.SpaceShip;
import at.spacerumble.players.Player;
import at.spacerumble.players.PlayerManager;

public class MenuState extends State {

  private final PlayerManager playerManager;
  private final World world;

  private final BitmapFont font;
  private final int counter;

  public MenuState(GameStateManager gsm, int counter) {
    super(gsm);

    this.counter = counter;
    setZoomFactor(6f);

    font = new BitmapFont();
    font.setUseIntegerPositions(false);
    font.getData().setScale(10, 10);
    font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

    world = new World(new Vector2(0, 0), true);

    playerManager = new PlayerManager();
    playerManager.addPlayer("Player1");
    playerManager.addPlayer("Player2");
    playerManager.get("Player1").setSpaceShip(new SpaceShip(world, 0, 0));
    playerManager.get("Player2").setSpaceShip(new SpaceShip(world, 100, 100));
    playerManager.addGamepads(Controllers.getControllers());
  }

  @Override
  public void handleInput() {
    if (Gdx.input.justTouched()) {
      endState();
    }
  }

  @Override
  public void update(float dt) {
    super.update(dt);
    world.step(dt, 6, 2);
    playerManager.getAll().forEach(player -> player.update(dt));
  }

  @Override
  public void render(SpriteBatch sb) {
    super.render(sb);
    sb.begin();
    font.draw(sb, "SpaceRumble: " + counter, 0, SpaceRumble.HEIGHT * getZoomFactor());
    playerManager.getAll().forEach(player -> player.draw(sb));
    sb.end();
    Renderer.debugRenderer.render(world, cam.combined);
  }

  @Override
  public void dispose() {
    font.dispose();
    world.dispose();
    playerManager.getAll().forEach(Player::dispose);
    gsm.set(new MenuState(gsm, counter + 1));
  }

  @Override
  public void connected(Controller controller) {
  }

  @Override
  public void disconnected(Controller controller) {
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    System.out.println("buttonDown");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("buttonCode: " + buttonCode);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    System.out.println("buttonUp");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("buttonCode: " + buttonCode);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    // System.out.println("axisMoved");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("axisCode: " + axisCode + ", value: " + value);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    System.out.println("povMoved");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("povCode: " + povCode + ", PovDirection: " + value);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    System.out.println("xSliderMoved");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("sliderCode: " + sliderCode + ", boolean: " + value);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    System.out.println("ySliderMoved");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("sliderCode: " + sliderCode + ", boolean: " + value);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    System.out.println("ySliderMoved");
    Player current = null;
    if (playerManager != null) {
      current = playerManager.get(controller);
    }
    if (current != null) {
      switch (playerManager.get(controller).getName()) {
        case "Player1":
          System.out.println("accelerometerCode: " + accelerometerCode + ", value: " + value);
          break;
        case "Player2":
          break;
        case "Player3":
          break;
        case "Player4":
          break;
        default:
      }
    }
    return false;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.D) {
      playerManager.get("Player1").getSpaceShip().setRight(true);
      System.out.println("Right");
    }
    if (keycode == Input.Keys.A) {
      playerManager.get("Player1").getSpaceShip().setLeft(true);
      System.out.println("Left");
    }
    if (keycode == Input.Keys.W) {
      playerManager.get("Player1").getSpaceShip().setBoost(true);
      System.out.println("Up");
    }
    if (keycode == Input.Keys.S) {
      playerManager.get("Player1").getSpaceShip().stop();
      System.out.println("Down");
    }

    if (keycode == Input.Keys.RIGHT) {
      playerManager.get("Player2").getSpaceShip().setRight(true);
      System.out.println("Right");
    }
    if (keycode == Input.Keys.LEFT) {
      playerManager.get("Player2").getSpaceShip().setLeft(true);
      System.out.println("Left");
    }
    if (keycode == Input.Keys.UP) {
      playerManager.get("Player2").getSpaceShip().setBoost(true);
      System.out.println("Up");
    }
    if (keycode == Input.Keys.DOWN) {
      playerManager.get("Player2").getSpaceShip().stop();
      System.out.println("Down");
    }

    if (keycode == Input.Keys.R) {
      System.out.println("Reset");
      endState();
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Input.Keys.D) {
      playerManager.get("Player1").getSpaceShip().setRight(false);
      System.out.println("Right");
    }
    if (keycode == Input.Keys.A) {
      playerManager.get("Player1").getSpaceShip().setLeft(false);
      System.out.println("Left");
    }
    if (keycode == Input.Keys.W) {
      playerManager.get("Player1").getSpaceShip().setBoost(false);
      System.out.println("Up");
    }
    if (keycode == Input.Keys.S) {
      playerManager.get("Player1").getSpaceShip().stop();
      System.out.println("Down");
    }

    if (keycode == Input.Keys.RIGHT) {
      playerManager.get("Player2").getSpaceShip().setRight(false);
      System.out.println("Right");
    }
    if (keycode == Input.Keys.LEFT) {
      playerManager.get("Player2").getSpaceShip().setLeft(false);
      System.out.println("Left");
    }
    if (keycode == Input.Keys.UP) {
      playerManager.get("Player2").getSpaceShip().setBoost(false);
      System.out.println("Up");
    }
    if (keycode == Input.Keys.DOWN) {
      playerManager.get("Player2").getSpaceShip().stop();
      System.out.println("Down");
    }
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }
}
