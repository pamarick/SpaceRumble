package at.spacerumble.states;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import at.spacerumble.SpaceRumble;
import at.spacerumble.debugrenderer.Renderer;
import at.spacerumble.gamepads.XboxGamepad;
import at.spacerumble.objects.Line;
import at.spacerumble.objects.SpaceShip;
import at.spacerumble.objects.SpaceShipColor;
import at.spacerumble.players.Player;
import at.spacerumble.players.PlayerManager;

public class MenuState extends State {

  private final PlayerManager playerManager;
  private final World world;
  private final List<Line> map;
  
  private final Player enemy;
  
  private final BitmapFont font;

  public MenuState(GameStateManager gsm, PlayerManager playerManager) {
    super(gsm);
    setZoom(1f);

    font = new BitmapFont();
    font.setUseIntegerPositions(false);
    font.getData().setScale(0.1f, 0.1f);
    font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

    world = new World(new Vector2(0, 0), true);

//    map = Arrays.asList(new Line(world, new Vector2(5, 2), 0f, SpaceRumble.WIDTH - 2), new Line(world, new Vector2(5, SpaceRumble.HEIGHT - 5), 0f, SpaceRumble.WIDTH - 10), new Line(world, new Vector2(5, 5), 90f, SpaceRumble.HEIGHT - 10),
//        new Line(world, new Vector2(SpaceRumble.WIDTH - 5, 5), 90f, SpaceRumble.HEIGHT - 10));

    float srw = SpaceRumble.WIDTH;
    float srh = SpaceRumble.HEIGHT;
    
    map = Arrays.asList(new Line(world, new Vector2(5, 2), 0f, srw - 10), 
    		new Line(world, new Vector2(srw-5, 2), 45f, (float) Math.sqrt(18)),
    		new Line(world, new Vector2(srw-2, 5), 90f, srh - 10),
    		new Line(world, new Vector2(srw-2, srh-5), 135f, (float) Math.sqrt(18)),
    		new Line(world, new Vector2(srw-5, srh-2), 180f, srw - 10),
    		new Line(world, new Vector2(5, srh-2), 225f, (float) Math.sqrt(18)),
    		new Line(world, new Vector2(2, srh-5), 270f, srh - 10),
    		new Line(world, new Vector2(2, 5), 315f, (float) Math.sqrt(18)));
    
    if(playerManager == null) {
    	this.playerManager = new PlayerManager();
    	this.playerManager.addPlayer(new Player("1", Input.Keys.UP, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE));
    	this.playerManager.get("1").setInputId(1);
    	
    } else {
        this.playerManager = playerManager;
    }
    	
    if (this.playerManager.get("1") != null) {
    	this.playerManager.get("1").setSpaceShip(new SpaceShip(world, SpaceShipColor.GREEN, 10, 10, 0f));
    }
    if (this.playerManager.get("2") != null) {
    	this.playerManager.get("2").setSpaceShip(new SpaceShip(world, SpaceShipColor.RED, srw - 10, 10, 0f));
    }
    if (this.playerManager.get("3") != null) {
    	this.playerManager.get("3").setSpaceShip(new SpaceShip(world, SpaceShipColor.PINK, srw - 10, srh - 10, 180f));
    }
    if (this.playerManager.get("4") != null) {
    	this.playerManager.get("4").setSpaceShip(new SpaceShip(world, SpaceShipColor.ORANGE, 10, srh - 10, 180f));
    }

    enemy = new Player("0", Input.Keys.UP, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE);
    enemy.setSpaceShip(new SpaceShip(world, SpaceShipColor.RED, srw / 2, srh / 2, 180f));
    
  }

  @Override
  public void update(float dt) {
    super.update(dt);
    world.step(dt, 6, 2);
    playerManager.getAll().forEach(player -> player.update(dt));
    map.forEach(line -> line.update(dt));
    enemy.update(dt);
  }

  @Override
  public void render(SpriteBatch sb) {
    super.render(sb);
    sb.begin();
    font.draw(sb, "SpaceRumble", 0, SpaceRumble.HEIGHT * getZoomFactor());
    playerManager.getAll().forEach(player -> player.draw(sb));
    enemy.draw(sb);
    map.forEach(line -> line.draw(sb));
    sb.end();
    //Renderer.debugRenderer.render(world, cam.combined);
  }

  @Override
  public void dispose() {
    map.forEach(Line::dispose);
    font.dispose();
    world.dispose();
    playerManager.getAll().forEach(Player::dispose);
    enemy.dispose();
    gsm.set(new SetPlayerState(gsm));
  }

  @Override
  public void connected(Controller controller) {
  }

  @Override
  public void disconnected(Controller controller) {
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    if (buttonCode == XboxGamepad.BACK) {
      endState();
    }
    if (playerManager.get(controller.hashCode()) != null) {
      playerManager.get(controller.hashCode()).input(buttonCode);
    }
    return false;
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    if (playerManager.get(controller.hashCode()) != null) {
      playerManager.get(controller.hashCode()).input(buttonCode);
    }
    return false;
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    return false;
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    return false;
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    return false;
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    return false;
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    return false;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.ESCAPE) {
      endState();
    }
    if (playerManager.get(1) != null) {
      playerManager.get(1).input(keycode);
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (playerManager.get(1) != null) {
      playerManager.get(1).input(keycode);
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
