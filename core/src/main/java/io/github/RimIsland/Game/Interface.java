package io.github.RimIsland.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.RimIsland.Cache.FontManager;
import io.github.RimIsland.UI.Debugging;
import io.github.RimIsland.UI.Layer;
import io.github.RimIsland.UI.Utilities.JsonLoader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Interface {

    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private SpriteBatch batch;

    private List<Layer> layers = new ArrayList<>();

    // UI WIDGETS
    private Debugging debugging;

    public Interface()
    {

    }

    public void create()
    {

        // Load more when wanted

        // Ortho Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        // Viewport
        viewport = new ScreenViewport(camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        // Batch
        batch = new SpriteBatch();

        // Debugging
        Layer debugLayer = new Layer( 1);
        JsonLoader.load("ui/debugging.json", "debugging", debugLayer);
        debugging = new Debugging(debugLayer);
        layers.add(debugLayer);

        // Random Placeholder
        layers.add(new Layer(2));
        layers.sort(Comparator.comparingInt(Layer::getPriority));
    }

    public void render(float delta)
    {
        debugging.update(delta);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Layer layer : layers)
        {
            layer.render(batch);
        }
        batch.end();
    }

    public void dispose()
    {
        // FontCache.dispose(); Deprecated
        FontManager.dispose();
        batch.dispose();
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        //System.out.println(width + ", " + height);
    }
}
