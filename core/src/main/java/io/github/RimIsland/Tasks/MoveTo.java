package io.github.RimIsland.Tasks;

import com.badlogic.gdx.math.Vector2;
import io.github.RimIsland.Entities.Entity;

public class MoveTo implements Task {
    private Vector2 target;

    public MoveTo(Vector2 target) {
        this.target = target;
    }

    @Override
    public void onStart(Entity e) {}

    @Override
    public TaskStatus update(Entity e, int dt) {
        Vector2 dir = target.cpy().sub(e.getPosition());

        if (dir.len() < 0.2f) {
            e.setVelocity(Vector2.Zero);
            return TaskStatus.SUCCESS;
        }

        dir.nor().scl(e.getVelocity());
        e.setVelocity(dir);
        e.move(dt);

        return TaskStatus.RUNNING;
    }

    @Override
    public void onEnd(Entity e) {
        e.setVelocity(Vector2.Zero);
    }
}
