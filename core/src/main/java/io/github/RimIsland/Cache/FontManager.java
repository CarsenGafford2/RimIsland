package io.github.RimIsland.Cache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import java.util.HashMap;

public class FontManager
{
    private static final HashMap<String, BitmapFont> fonts = new HashMap<>();
    private static final HashMap<String, FreeTypeFontGenerator> generators = new HashMap<>();

    public static BitmapFont get(String path, int size)
    {
        String key = path + "#" + size;
        BitmapFont font = fonts.get(key);
        if (font != null) return font;

        FreeTypeFontGenerator generator = generators.get(path);
        {
            if (generator == null) {
                generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
                generators.put(path, generator);
            }
        }

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;

        font = generator.generateFont(parameter);
        fonts.put(key, font);
        return font;
    }

    public static void dispose()
    {
        for (BitmapFont font : fonts.values())
        {
            font.dispose();
        }
        for (FreeTypeFontGenerator generator : generators.values())
        {
            generator.dispose();
        }
        fonts.clear();
        generators.clear();
    }
}
