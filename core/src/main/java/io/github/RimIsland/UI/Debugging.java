package io.github.RimIsland.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.RimIsland.Utilities.Monitor;

public class Debugging
{

    private final Monitor monitor;

    private final Label fps;
    private final Label cpu;

    public Debugging(Layer layer)
    {
        monitor = new Monitor();

        // FPS label
        fps = new Label("");
        fps.setSize(16);
        fps.setColor(Color.LIME);
        fps.setPosition(10, 20);

        // CPU label
        cpu = new Label("");
        cpu.setSize(16);
        cpu.setColor(Color.CYAN);
        cpu.setPosition(10, 40);

        // Attach to the UI layer
        layer.addLabel(fps);
        
    }
}
