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
 *     <li>Width</li>
 *     <li>Height</li>
 * </ul>
 *
 * The drawable can be rendered using their cache ID
 * This class overall improves performance.
 * <br>
 * <br>
 * <b>This cache is global and static</b>
 */
public class NineTileCache
{
    private static SpriteCache cache = new SpriteCache();
    private static HashMap<Key, Integer> entries = new HashMap<>();

    /**
     * Internal key class, uniquely identifies cached drawables.
     */
    private static class Key
    {
        NineTileDrawable drawable;
        int width;
        int height;

        /**
         * Cache key
         * @param drawable drawable instance
         * @param width width of drawable
         * @param height height of drawable
         */
        Key(NineTileDrawable drawable, int width, int height)
        {
            this.drawable = drawable;
            this.width = width;
            this.height = height;
        }

        /**
         * Compares key with another object
         * @param object comparable object
         * @return true, only if the object is identical, instance, width, and height.
         */
        @Override
        public boolean equals(Object object)
        {
            if (!(object instanceof Key)) return false;
            Key key = (Key) object;
            return key.drawable == drawable &&
                key.width == width &&
                key.height == height;
        }

        /**
         * Generates hashcode
         * @return int made from drawable, width, and height.
         */
        @Override
        public int hashCode()
        {
            return drawable.hashCode() * 31 + width * 7 + height;
        }
    }

    /**
     * Creates or retrieves a cached SpriteCache ID for a drawable.
     *
     * If the drawable with the specified dimensions is already cached, an existing ID will automatically be returned.
     * Else, the drawable will be rendered into the {@link SpriteCache}.
     *
     * @param drawable drawable
     * @param width width
     * @param height height
     * @return SpriteCache ID for this drawable configuration
     */
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

    /**
     * Draws a cached NineTileDrawable using {@link Batch}.
     *
     * Method fetches id, renders the id with parameters.
     *
     * @param batch UI batch used for rendering
     * @param drawable drawable instance
     * @param x x position
     * @param y y position
     * @param width width of drawable
     * @param height height of drawable
     */
    public static void draw(Batch batch, NineTileDrawable drawable, int x, int y, int width, int height)
    {
        int id = get(drawable, width, height);
        cache.begin();
        cache.draw(id, x, y);
        cache.end();
    }

    /**
     * Clears all cached drawables.
     *
     * Removes all cached entries and clears {@link SpriteCache}
     *
     * Only if you REALLY need to reload assets.
     */
    public static void clear()
    {
        cache.clear();
        entries.clear();
    }
}
