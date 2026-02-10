package io.github.RimIsland.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import java.util.ArrayList;

public class Layer
{
    private final int priority;
    private final ArrayList<Label> labels = new ArrayList<>();

    public Layer(int priority)
    {
        this.priority = priority;
    }

    public void render(SpriteBatch batch)
    {
        for (Label label : labels)
        {
            label.draw(batch);
        }
    }

    public void addLabel(Label label)
    {
        labels.add(label);
    }

    public int getPriority()
    {
        return this.priority;
    }
}
