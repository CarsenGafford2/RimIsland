package io.github.RimIsland.UI;

import io.github.RimIsland.Utilities.Monitor;

public class Debugging
{

    private final Monitor monitor;
    private final Layer layer;
    public Debugging(Layer layer)
    {
        this.layer = layer;
        this.monitor = new Monitor();
    }

    public void update(float delta)
    {
        monitor.update(delta);
        layer.getBound("fps").setText("FPS: " + monitor.getFrameRate());
        layer.getBound("cpu").setText("CPU: " + monitor.getCpuPercent());
    }
}
