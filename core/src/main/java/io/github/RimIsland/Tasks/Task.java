package io.github.RimIsland.Tasks;

import io.github.RimIsland.Entities.Entity;

public interface Task {
    void onStart(Entity entity);
    TaskStatus update(Entity entity, int deltaTime);
    void onEnd(Entity entity);
}
