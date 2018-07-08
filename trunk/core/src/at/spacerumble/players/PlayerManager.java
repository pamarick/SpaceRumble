package at.spacerumble.players;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;

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

	public Player get(String name) {
		for (Player player : playerManager) {
			if (player.getName().equals(name))
				return player;
		}
		return null;
	}

	public Player get(int inputId) {
		for (Player player : playerManager) {
			if (player.getInputId() == inputId)
				return player;
		}
		return null;
	}

	public Player get(Body body) {
		for (Player player : playerManager) {
			if (player.getSpaceShip().getBody().equals(body))
				return player;
		}
		return null;
	}

	public void addPlayer(Player player) {
		playerManager.add(player);
	}

	public void removePlayer(String name) {
		if (get(name) == null) {
			get(name).dispose();
			playerManager.remove(get(name));
		}
	}

	public void removePlayer(Player player) {
		player.dispose();
		playerManager.remove(player);
	}

	public void dispose() {
		getAll().forEach(Player::dispose);
	}
}
