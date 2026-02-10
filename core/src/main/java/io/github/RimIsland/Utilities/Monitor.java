package io.github.RimIsland.Utilities;

import com.badlogic.gdx.Gdx;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class Monitor {

    private final OperatingSystemMXBean osBean;
    // CPU
    private double processCpu;
    private double systemCpu;
    // FPS
    private int frameRate;

    public Monitor()
    {
        osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    // CPU
    public String getProcessCpuLoad()
    {
        processCpu = osBean.getProcessCpuLoad();
        return String.valueOf(processCpu);
    }

    public String getSystemCpuLoad()
    {
        systemCpu = osBean.getSystemCpuLoad();
        return String.valueOf(systemCpu);
    }

    public String getCpuPercent()
    {
        return String.valueOf((float) processCpu * 100);
    }

    // FPS
    public String getFrameRate()
    {
        frameRate = Gdx.graphics.getFramesPerSecond();
        return String.valueOf(frameRate);
    }
}
