package at.spacerumble.players;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.utils.Array;

public class PlayerManager {
	private List<Player> playerManager;

	public List<Player> getAll() {
		return playerManager;
	}
	
	public PlayerManager() {
		playerManager = new ArrayList<>();
	}

	public PlayerManager(List<Player> players) {
		playerManager = players;
	}

	public Player get(Controller gamepad) {
		for (Player player : playerManager) {
			if (player.getGamepad() == gamepad)
				return player;
		}
		return null;
	}

	public Player get(String name) {
		for (Player player : playerManager) {
			if (player.getName() == name)
				return player;
		}
		return null;
	}

	public void addGamepad(Controller gamepad) {
		for (Player player : playerManager) {
			if (player.getGamepad() == null) {
				player.setGamepad(gamepad);
				break;
			}
		}
	}

	public void addGamepads(Array<Controller> gamepads) {
		for (Controller gamepad : gamepads) {
			for (Player player : playerManager) {
				if (player.getGamepad() == null) {
					player.setGamepad(gamepad);
					break;
				}
			}
		}
	}

	public void removeGamepad(Controller gamepad) {
		get(gamepad).setGamepad(null);
	}

	public void addPlayer(String name) {
		playerManager.add(new Player(name));
	}

	public void addPlayer(Player player) {
		playerManager.add(player);
	}

	public void removePlayer(String name) {
		playerManager.remove(get(name));
	}

	public void removePlayer(Player player) {
		playerManager.remove(player);
	}
}
