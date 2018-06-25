package at.spacerumble.threads;

import at.spacerumble.states.GameStateManager;

public class HandleInputThread extends Thread {
	
	GameStateManager gsm;
	
	public HandleInputThread(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	@Override
	public void run() {	
		gsm.handleInput();
	}
}
