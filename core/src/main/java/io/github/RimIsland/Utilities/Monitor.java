package io.github.RimIsland.Utilities;

import com.badlogic.gdx.Gdx;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class Monitor {

    private final OperatingSystemMXBean osBean;
    private final int cores;

    private long lastCpuTime;
    private long lastSampleTime;

    private double cachedCpu = 0;
    private float sampleTimer = 0f;

    public Monitor()
    {
        osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        cores = osBean.getAvailableProcessors();

        lastCpuTime = osBean.getProcessCpuTime();
        lastSampleTime = System.nanoTime();
    }

    public void update(float delta)
    {
        sampleTimer += delta;

        // sample 4 times per second
        if(sampleTimer < 0.25f) return;
        sampleTimer = 0f;

        long nowCpu = osBean.getProcessCpuTime();
        long nowTime = System.nanoTime();

        long cpuDiff = nowCpu - lastCpuTime;
        long timeDiff = nowTime - lastSampleTime;

        lastCpuTime = nowCpu;
        lastSampleTime = nowTime;

        if(timeDiff <= 0) return;

        double usage = (double) cpuDiff / (timeDiff * cores);
        cachedCpu = Math.max(0, Math.min(usage * 100.0, 100.0));
    }

    // ---- READ ----
    public String getCpuPercent()
    {
        return String.format("%.1f%%", cachedCpu);
    }

    public String getFrameRate()
    {
        return Integer.toString(Gdx.graphics.getFramesPerSecond());
    }
}
