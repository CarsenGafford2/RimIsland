package io.github.RimIsland.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;

    public void create()
    {
        batch = new SpriteBatch();
    }

    public void render()
    {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // MAIN BATCH
        batch.begin();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println();
    }

    public void dispose()
    {
        batch.dispose();
    }
}
