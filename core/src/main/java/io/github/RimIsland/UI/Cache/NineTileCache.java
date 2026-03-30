package io.github.RimIsland.UI.Cache;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import io.github.RimIsland.UI.Graphics.NineTileDrawable;

import java.util.HashMap;

/**
 * A static cache for {@link NineTileDrawable} using {@link SpriteCache}.
 *
 * The class avoids re-building nine-tile drawables with every frame.
 * Pre-renders geometry based on:
 *
 * <ul>
 *     <li>{@link NineTileDrawable}</li>
 *     <li></li>
 * </ul>
 */
public class NineTileCache
{
    private static SpriteCache cache = new SpriteCache();
    private static HashMap<Key, Integer> entries = new HashMap<>();


    /**
     *
     */
    private static class Key
    {
        NineTileDrawable drawable;
        int width;
        int height;

        Key(NineTileDrawable drawable, int width, int height)
        {
            this.drawable = drawable;
            this.width = width;
            this.height = height;
        }

        @Override
        public boolean equals(Object object)
        {
            if (!(object instanceof Key)) return false;
            Key key = (Key) object;
            return key.drawable == drawable && key.width == width && key.height == height;
        }

        @Override
        public int hashCode()
        {
            return drawable.hashCode() * 31 + width * 7 + height;
        }
    }

    public static int get(NineTileDrawable drawable, int width, int height)
    {
        Key key = new Key(drawable, width, height);
        Integer id = entries.get(key);

        if (id != null) return id;

        cache.beginCache();
        drawable.draw(cache, 0, 0, width, height);
        id = cache.endCache();

        entries.put(key, id);
        return id;
    }

    public static void draw(Batch batch, NineTileDrawable drawable, int x, int y, int width, int height)
    {
        int id = get(drawable, width, height);
        cache.begin();
        cache.draw(id, x, y);
        cache.end();
    }

    public static void clear()
    {
        cache.clear();
        entries.clear();
    }
}
