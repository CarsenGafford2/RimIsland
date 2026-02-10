package io.github.RimIsland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RimIsland.Game.Game;
import io.github.RimIsland.Game.Interface;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Game game;
    private Interface face;

    @Override
    public void create() {
        game = new Game();
        game.create();
        face = new Interface();
        face.create();
    }

    @Override
    public void render() {
        game.render();
        face.render();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
        face.resize(width, height);
    }

    @Override
    public void dispose() {
        game.dispose();
        face.dispose();
    }
}
