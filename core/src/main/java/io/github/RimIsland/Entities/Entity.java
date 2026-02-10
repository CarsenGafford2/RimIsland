package io.github.RimIsland.Entities;

import com.badlogic.gdx.math.Vector2;
import io.github.RimIsland.Tasks.Task;
import io.github.RimIsland.Tasks.TaskStatus;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Entity
{
    // Settings
    protected Vector2 position;
    protected Vector2 velocity;

    // main task stack set as an Integer for now while
    protected Deque<Task> taskStack = new ArrayDeque<>();


    /**
     * BEGIN ENTITY STATS
     */

    // health, when it reaches 0 the entity dies
    public byte health = 100;

    // melee attack strength
    public byte melee = 0;

    // ranged attack strength
    public byte ranged = 0;

    // chance to make impulsive decision rather than assigned task
    public byte impulsiveness = 0;

    // resistance to mental breaks and addiction
    public byte willpower = 0;

    // determines how much they prioritize themselved over others
    public byte ego = 0;

    // how much pain they can take before passing out or running from battle
    public byte painTolerance = 0;

    // how much work they can endure before needing to rest
    public byte endurance = 0;

    // determines their resistance to diseases and poisons
    public byte immunity = 0;

    // how often they need to eat and how much they need to eat when they do
    public byte metabolism = 0;

    // how deep of a sleeper they are, determining what can wake them up
    public byte sleep = 0;

    // their general social skills
    public byte charisma = 0;

    // how aggressive they are
    public byte aggression = 0;

    // how socially aware they are, determining how they insult others and how they react to insults
    public byte socialAwareness = 0;

    // how fast they can learn new skills
    public byte learning = 0;

    // how much they care about others and prisoners
    public byte compassion = 0;

    // how loyal they are to others
    public byte loyalty = 0;

    // represents their mental state
    public byte sanity = 0;

    // how prominent they are in the story - mad lib style
    public byte narrativeWeight = 0;

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

    public void move(int deltaTime)
    {
        this.position.add(this.velocity);
    }

    public void pushTask(Task task) {
        pushTask(task);
        task.onStart(this);
    }

    protected void clearTasks() {
        while (!taskStack.isEmpty()) {
            taskStack.pop().onEnd(this);
        }
    }

    protected void interruptWith(Task task) {
        clearTasks();
        pushTask(task);
    }

    public void damage(int amount)
    {
        this.health -= amount;
        if (this.health <= 0)
        {
            this.die();
        }
    }

    public void update(int deltaTime) {
        if (!alive) return;

        if (!taskStack.isEmpty()) {
            Task current = taskStack.peek();
            TaskStatus status = current.update(this, deltaTime);

            if (status != TaskStatus.RUNNING) {
                current.onEnd(this);
                taskStack.pop();
            }
        } else {
            decideNextTask();
        }
    }

    abstract protected void decideNextTask();

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
