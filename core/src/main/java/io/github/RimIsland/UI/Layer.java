package io.github.RimIsland.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Layer
{
    private final int priority;
    private final ArrayList<Label> labels = new ArrayList<>();
    private final HashMap<String, Label> bindings = new HashMap<>();

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

    public void bind(String name, Label label)
    {
        bindings.put(name, label);
    }

    public Label getBound(String name)
    {
        return bindings.get(name);
    }

    public boolean hasBinding(String name)
    {
        return bindings.containsKey(name);
    }

    public int getPriority()
    {
        return this.priority;
    }
}
