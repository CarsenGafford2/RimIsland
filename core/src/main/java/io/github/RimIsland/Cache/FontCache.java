package io.github.RimIsland.Cache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

public class FontCache
{
    private static final HashMap<String, BitmapFont> cache = new HashMap<>();
    private static final HashMap<String, FreeTypeFontGenerator> generators = new HashMap<>();

    public static BitmapFont get(String path, int size)
    {
        String key = path + "#" + size;
        BitmapFont font = cache.get(key);
        if (font != null) return font;

        FreeTypeFontGenerator gen = generators.get(path);
        if (gen == null)
        {
            gen = new FreeTypeFontGenerator(Gdx.files.internal(path));
            generators.put(path, gen);
        }

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
            new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;

        font = gen.generateFont(parameter);
        cache.put(key, font);
        return font;
    }

    public static void dispose()
    {
        for (BitmapFont font : cache.values())
        {
            font.dispose();
        }

        for (FreeTypeFontGenerator gen : generators.values())
        {
            gen.dispose();
        }
    }
}
