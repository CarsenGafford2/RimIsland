package io.github.RimIsland.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import io.github.RimIsland.Cache.FontManager;
import io.github.RimIsland.UI.Enums.Anchor;

public class Label {

    private BitmapFont font;
    private BitmapFontCache cache;
    private int size = 8;
    private float x = 0;
    private float y = 0;
    private Anchor xAnchor = Anchor.LEFT;
    private Anchor yAnchor = Anchor.TOP;
    private int alignment = Align.topLeft;
    private Color color = Color.WHITE;
    private String text = "Placeholder";
    private String fontPath = "fonts/TitilliumWeb-Regular.ttf";
    private SpriteBatch batch;

    public Label()
    {
        font = FontManager.get(fontPath, size);
        cache = font.getCache();
    }
    public void setFont(String newFontName)
    {
        fontPath = "fonts/" + newFontName + ".ttf";
        font = FontManager.get(fontPath, size);
        cache = font.getCache();
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setSize(int size)
    {
        this.size = size;
        font = FontManager.get(fontPath, size);
        cache = font.getCache();
    }

    public void setText(String newText)
    {
        text = newText;
    }

    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
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

    public void setXAnchor(Anchor anchor)
    {
        this.xAnchor = anchor;
    }

    public void setYAnchor(Anchor anchor)
    {
        this.yAnchor = anchor;
    }

    public String getText()
    {
        return this.text;
    }

    public void draw(SpriteBatch batch)
    {
        float drawX = x;
        switch (xAnchor)
        {
            case LEFT:
                break;
            case CENTER:
                drawX = (Gdx.graphics.getWidth() / 2f) + x;
                break;
            case RIGHT:
                drawX = (Gdx.graphics.getWidth() - x);
                break;
        }

        float drawY = y;
        switch (yAnchor)
        {
            case BOTTOM:
                break;
            case CENTER:
                drawY = (Gdx.graphics.getHeight() / 2f) - y;
                break;
            case TOP:
                drawY = (Gdx.graphics.getHeight() - y);
                break;
        }

        cache.clear();
        cache.setColor(color);
        cache.setText(text, drawX, drawY, Gdx.graphics.getWidth(), alignment, false);
        cache.draw(batch);
    }

}
