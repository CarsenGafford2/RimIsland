package io.github.RimIsland.Entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity
{
    // Settings
    protected Vector2 position;
    protected Vector2 velocity;

    // Stats
    protected int health = 100;
    protected int melee = 0;
    protected int mogging = 0;

    // Checks
    protected boolean alive = true;

    protected Entity(Vector2 position)
    {
        this.position = position;
        this.velocity = new Vector2().set(0,0);
    }

    abstract void update(int deltaTime);

    protected void move(int deltaTime)
    {
        this.position.add(this.velocity);
    }

    public void damage(int amount)
    {
        this.health -= amount;
        if (this.health <= 0)
        {
            this.die();
        }
    }

    protected void die()
    {
        this.alive = false;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getPosition()
    {
        return this.position;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public Vector2 getVelocity()
    {
        return this.velocity;
    }
}
