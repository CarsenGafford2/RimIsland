package io.github.RimIsland.UI;

import com.badlogic.gdx.graphics.Camera;
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

        fps = new Label(monitor.getFrameRate(), layer.getBatch());
        cpu = new Label(monitor.getCpuPercent(), layer.getBatch());
        layer.addLabel(fps);
        layer.addLabel(cpu);
    }
}
