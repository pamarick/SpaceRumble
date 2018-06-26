package at.spacerumble.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import at.spacerumble.SpaceRumble;
import at.spacerumble.players.Player;
import at.spacerumble.players.PlayerManager;
import at.spacerumble.sprites.SpaceShip;
import at.spacerumble.sprites.SpaceShip2;

public class MenuState extends State {

  private final BitmapFont font;
  private final int counter;

  private final PlayerManager playerManager;

  private final World world;

  SpaceShip2 spaceShip2;
  
  public MenuState(GameStateManager gsm, int counter) {
    super(gsm);
    this.counter = counter;
    cam.setToOrtho(false, SpaceRumble.WIDTH * 2, SpaceRumble.HEIGHT * 2);

    font = new BitmapFont();
    font.setUseIntegerPositions(false);
    font.getData().setScale(2, 2);

    world = new World(new Vector2(0, 0), true);

    playerManager = new PlayerManager();

    playerManager.addPlayer("Player1");
    // playerManager.addPlayer("Player2");
    // playerManager.addPlayer("Player3");
    // playerManager.addPlayer("Player4");

    playerManager.addGamepads(Controllers.getControllers());
    System.out.println(playerManager.toString());

    playerManager.get("Player1").setSpaceShip(new SpaceShip(Color.RED, 640, 360, world));
    // playerManager.get("Player2").setSpaceShip(new SpaceShip(Color.WHITE, 0, 360));
    // playerManager.get("Player3").setSpaceShip(new SpaceShip(Color.GREEN, 640, 0));
    // playerManager.get("Player4").setSpaceShip(new SpaceShip(Color.PINK, 640, 360));
    
    spaceShip2 = new SpaceShip2(world);
  }

  @Override
  public void handleInput() {
    if (Gdx.input.justTouched()) {
      endState = true;
    }
  }

  @Override
  public void update(float dt) {
    world.step(dt, 6, 2);
    for (Player player : playerManager.getAll()) {
      player.update(dt);
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    super.render(sb);
    sb.setProjectionMatrix(cam.combined);
    sb.begin();
    font.draw(sb, "SpaceRumble: " + counter, SpaceRumble.WIDTH / 2, SpaceRumble.HEIGHT / 2);
    for (Player player : playerManager.getAll()) {
      sb.draw(player.getSpaceShip(), player.getSpaceShip().getX(), player.getSpaceShip().getY(), player.getSpaceShip().getOriginX(), player.getSpaceShip().getOriginY(), player.getSpaceShip().getWidth(), player.getSpaceShip().getHeight(),
          player.getSpaceShip().getScaleX(), player.getSpaceShip().getScaleY(), player.getSpaceShip().getRotation());
    }
    spaceShip2.draw(sb);
    sb.end();
  }

  @Override
  public void dispose() {
    gsm.set(new MenuState(gsm, counter + 1));
    font.dispose();
    world.dispose();
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
    if (keycode == Input.Keys.RIGHT) {
      playerManager.get("Player1").getSpaceShip().startRight();
      System.out.println("Right");
    }
    if (keycode == Input.Keys.LEFT) {
      playerManager.get("Player1").getSpaceShip().startLeft();
      System.out.println("Left");
    }
    if (keycode == Input.Keys.UP) {
      playerManager.get("Player1").getSpaceShip().startBoost();
      System.out.println("Up");
    }
    if (keycode == Input.Keys.DOWN) {
      playerManager.get("Player1").getSpaceShip().down();
      System.out.println("Down");
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Input.Keys.RIGHT) {
      playerManager.get("Player1").getSpaceShip().endRight();
      System.out.println("Right");
    }
    if (keycode == Input.Keys.LEFT) {
      playerManager.get("Player1").getSpaceShip().endLeft();
      System.out.println("Left");
    }
    if (keycode == Input.Keys.UP) {
      playerManager.get("Player1").getSpaceShip().endBoost();
      System.out.println("Up");
    }
    if (keycode == Input.Keys.DOWN) {
      playerManager.get("Player1").getSpaceShip().down();
      System.out.println("Down");
    }
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
