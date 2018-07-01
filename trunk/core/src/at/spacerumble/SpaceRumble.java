package at.spacerumble;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.spacerumble.states.GameStateManager;
import at.spacerumble.states.SetPlayerState;

public class SpaceRumble extends ApplicationAdapter {

  // in meter
  public static final float WIDTH = 100f;
  public static final float HEIGHT = 56.25f;

  public static final String TITLE = "SpaceRumble";

  private GameStateManager gsm;

  private SpriteBatch batch;

  private FPSLogger fpsLog;

  @Override
  public void create() {
    fpsLog = new FPSLogger();
    batch = new SpriteBatch();
    gsm = new GameStateManager();
    Gdx.gl.glClearColor(0, 0, 0, 0);
    gsm.push(new SetPlayerState(gsm));
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
