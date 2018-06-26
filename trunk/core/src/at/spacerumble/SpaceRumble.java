package at.spacerumble;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.spacerumble.states.GameStateManager;
import at.spacerumble.states.MenuState;

public class SpaceRumble extends ApplicationAdapter {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static final String TITLE = "SpaceRumble";

	private GameStateManager gsm;

	private SpriteBatch batch;

	FPSLogger fpsLog;

	@Override
	public void create() {
		fpsLog = new FPSLogger();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		gsm.push(new MenuState(gsm, 0));
	}

	@Override
	public void render() {
		gsm.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.render(batch);

		fpsLog.log();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
