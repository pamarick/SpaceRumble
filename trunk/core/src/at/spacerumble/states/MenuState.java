package at.spacerumble.states;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import at.spacerumble.SpaceRumble;
import at.spacerumble.gamepads.XboxGamepad;
import at.spacerumble.players.Player;
import at.spacerumble.players.PlayerManager;
import at.spacerumble.sprites.SpaceShip;

public class MenuState extends State {

	private BitmapFont font;
	private int counter;

	private PlayerManager playerManager;

	private SpaceShip spaceShip;

	public MenuState(GameStateManager gsm, int counter) {
		super(gsm);
		this.counter = counter;
		cam.setToOrtho(false);

		font = new BitmapFont();
		font.setUseIntegerPositions(false);
		font.getData().setScale(2, 2);

		playerManager = new PlayerManager();

		playerManager.addPlayer("Player1");
		playerManager.addPlayer("Player2");
		playerManager.addPlayer("Player3");
		playerManager.addPlayer("Player4");

		playerManager.addGamepads(Controllers.getControllers());
		System.out.println(playerManager.toString());

		playerManager.get("Player1").setSpaceShip(new SpaceShip(Color.RED, 0, 0));
		playerManager.get("Player2").setSpaceShip(new SpaceShip(Color.WHITE, 0, 360));
		playerManager.get("Player3").setSpaceShip(new SpaceShip(Color.GREEN, 640, 0));
		playerManager.get("Player4").setSpaceShip(new SpaceShip(Color.PINK, 640, 360));
	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched()) {
			endState = true;
		}
	}

	@Override
	public void update(float dt) {
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
			player.draw(sb);
		}
		sb.end();
	}

	@Override
	public void dispose() {
		gsm.set(new MenuState(gsm, counter + 1));
		font.dispose();
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
		if (playerManager != null)
			current = playerManager.get(controller);
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
		if (playerManager != null)
			current = playerManager.get(controller);
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
		// Player current = null;
		// if (playerManager != null)
		// current = playerManager.get(controller);
		// if (current != null) {
		// switch (playerManager.get(controller).getName()) {
		// case "Player1":
		// System.out.println("axisCode: " + axisCode + " , value: " + value);
		// break;
		// case "Player2":
		// break;
		// case "Player3":
		// break;
		// case "Player4":
		// break;
		// default:
		// }
		// }
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		System.out.println("povMoved");
		Player current = null;
		if (playerManager != null)
			current = playerManager.get(controller);
		if (current != null) {
			switch (playerManager.get(controller).getName()) {
			case "Player1":
				System.out.println("povCode: " + povCode + " , PovDirection: " + value);
				switch (value) {
				case north:
					current.getSpaceShip().addVY();
					break;
				case south:
					current.getSpaceShip().subVY();
					break;
				case west:
					current.getSpaceShip().subVX();
					break;
				case east:
					current.getSpaceShip().addVX();
					break;
				case northEast:
					current.getSpaceShip().addVY();
					current.getSpaceShip().addVX();
					break;
				case southEast:
					current.getSpaceShip().subVY();
					current.getSpaceShip().addVX();
					break;
				case northWest:
					current.getSpaceShip().addVY();
					current.getSpaceShip().subVX();
					break;
				case southWest:
					current.getSpaceShip().subVY();
					current.getSpaceShip().subVX();
					break;
				case center:
					current.getSpaceShip().stopV();
					break;
				}
				if (value == PovDirection.north)
					current.getSpaceShip().addVY();
				if (value == PovDirection.northEast)
					current.getSpaceShip().addVY();

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
		if (playerManager != null)
			current = playerManager.get(controller);
		if (current != null) {
			switch (playerManager.get(controller).getName()) {
			case "Player1":
				System.out.println("sliderCode: " + sliderCode + " , boolean: " + value);
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
		if (playerManager != null)
			current = playerManager.get(controller);
		if (current != null) {
			switch (playerManager.get(controller).getName()) {
			case "Player1":
				System.out.println("sliderCode: " + sliderCode + " , boolean: " + value);
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
		if (playerManager != null)
			current = playerManager.get(controller);
		if (current != null) {
			switch (playerManager.get(controller).getName()) {
			case "Player1":
				System.out.println("accelerometerCode: " + accelerometerCode + " , Vector3: " + value);
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

}
