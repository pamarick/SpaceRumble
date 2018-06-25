package at.spacerumble.threads;

import at.spacerumble.states.GameStateManager;

public class UpdateThread extends Thread {

	GameStateManager gsm;
	float dt;
	
	public UpdateThread(GameStateManager gsm, float dt) {
		this.gsm = gsm;
		this.dt = dt;
	}
	
	@Override
	public void run() {	
		gsm.update(dt);
	}
	
}
