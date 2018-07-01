package at.spacerumble.players;

import java.util.ArrayList;
import java.util.List;

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

	public void addPlayer(Player player) {
		playerManager.add(player);
	}

	public void removePlayer(String name) {
		playerManager.remove(get(name));
	}

	public void removePlayer(Player player) {
		playerManager.remove(player);
	}
	
	public void dispose() {
		getAll().forEach(Player::dispose);
	}
}
