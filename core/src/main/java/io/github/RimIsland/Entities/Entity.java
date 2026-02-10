package io.github.RimIsland.Entities;

import com.badlogic.gdx.math.Vector2;
import java.util.Stack;

public abstract class Entity
{
    // Settings
    protected Vector2 position;
    protected Vector2 velocity;

    // main task stack set as an Integer for now while
    protected Stack<Integer> tasks = new Stack<>();


    /**
     * BEGIN ENTITY STATS
     */

    // health, when it reaches 0 the entity dies
    protected byte health = 100;

    // melee attack strength
    protected byte melee = 0;

    // chance to make impulsive decision rather than assigned task
    protected byte impulsiveness = 0;

    // resistance to mental breaks and addiction
    protected byte willpower = 0;

    // determines how much they prioritize themselved over others
    protected byte ego = 0;

    // how much pain they can take before passing out or running from battle
    protected byte painTolerance = 0;

    // how much work they can endure before needing to rest
    protected byte endurance = 0;

    // determines their resistance to diseases and poisons
    protected byte immunity = 0;

    // how often they need to eat and how much they need to eat when they do
    protected byte metabolism = 0;

    // how deep of a sleeper they are, determining what can wake them up
    protected byte sleep = 0;

    // their general social skills
    protected byte charisma = 0;

    // how aggressive they are
    protected byte aggression = 0;

    // how socially aware they are, determining how they insult others and how they react to insults
    protected byte socialAwareness = 0;

    // how fast they can learn new skills
    protected byte learning = 0;

    // how much they care about others and prisoners
    protected byte compassion = 0;

    // how loyal they are to others
    protected byte loyalty = 0;

    // represents their mental state
    protected byte sanity = 0;

    // how prominent they are in the story - mad lib style
    protected byte narrativeWeight = 0;

    /**
     * END ENTITY STATS
     */

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

    public void update() {
        if (this.alive)
        {
            // Move along the path or complete the next frame of the current task
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
