package io.github.RimIsland.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import io.github.RimIsland.Cache.FontCache;

public class Label {

    private int size = 8;
    private int x;
    private int y;
    private Color color = Color.BLACK;
    private String text;
    private String fontPath = "fonts/TitilliumWeb-Regular.ttf";
    private SpriteBatch batch;

    public Label(String text)
    {
        this.text = text;
    }

    public void setFont(String newFontName)
    {
        fontPath = "fonts/" + newFontName + ".ttf";
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setText(String newText)
    {
        text = newText;
    }

    public void setBatch(SpriteBatch newBatch)
    {
        batch = newBatch;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch)
    {
        BitmapFont font = FontCache.get(fontPath, size);

        Color old = batch.getColor();
        batch.setColor(color);
        font.draw(batch, text, x, y);
        batch.setColor(old);
    }

}
