package at.spacerumble.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import at.spacerumble.SpaceRumble;

public abstract class State implements ControllerListener, InputProcessor {

	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected GameStateManager gsm;

	private boolean endState;

	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera(SpaceRumble.WIDTH, SpaceRumble.HEIGHT);
//		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mouse = new Vector3();
		endState = false;
		Controllers.addListener(this);
		Gdx.input.setInputProcessor(this);
	}

	protected abstract void handleInput();

	protected abstract void dispose();

	protected void update(float dt) {
		cam.update();
	};

	protected void render(SpriteBatch sb) {
		if (endState) {
			dispose();
		}
		System.out.println("zoom: " + cam.zoom);
	};

	protected void endState() {
		endState = true;
	}
}
