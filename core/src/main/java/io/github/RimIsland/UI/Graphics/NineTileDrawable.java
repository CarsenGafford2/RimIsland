package io.github.RimIsland.UI.Graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NineTileDrawable
{
    private TextureRegion[][] tiles = new TextureRegion[3][3];

    public int tileSize = 4;

    public NineTileDrawable (TextureRegion[][] regions)
    {
        for (int column = 0; column < 3; column++)
        {
            for (int row = 0; row < 3; row++)
            {
                tiles[column][row] = regions[column][row];
            }
        }
    }

    public TextureRegion get(int col, int row)
    {
        return tiles[col][row];
    }

    public void set(int col, int row, TextureRegion region)
    {
        tiles[col][row] = region;
    }

    public static void draw(Object target, TextureRegion region, float x, float y, float width, float height)
    {
        if (target instanceof Batch batch)
        {
            batch.draw(region, x, y, width, height);
        }
        else if (target instanceof SpriteCache cache)
        {
            cache.add(region, x, y, width, height);
        }
    }

    // target is the texture atlas u put in
    public void draw(Object target, float x, float y, float width, float height)
    {

        float innerWidth = width - tileSize * 2;
        float innerHeight = height - tileSize * 2;

        // Number of Tiles
        int tilesX = (int)Math.ceil(innerWidth / tileSize);
        int tilesY = (int)Math.ceil(innerHeight / tileSize);

        // Corners
        draw(target, tiles[0][2], x, y + height - tileSize, tileSize, tileSize);
        draw(target, tiles[2][2], x + width - tileSize, y + height - tileSize, tileSize, tileSize);
        draw(target, tiles[0][0], x, y, tileSize, tileSize);
        draw(target, tiles[2][0], x + width - tileSize, y, tileSize, tileSize);

        // Horizontal Edges
        for (int index = 0; index < tilesX; index++)
        {
            float deltaX = x + tileSize + index * tileSize;
            float drawWidth = Math.min(tileSize, x + width - tileSize - deltaX);

            draw(target, tiles[1][2], deltaX, y + height - tileSize, drawWidth, tileSize);
            draw(target, tiles[1][0], deltaX, y, drawWidth, tileSize);
        }

        // Vertical Edges
        for (int jndex = 0; jndex < tilesY; jndex++)
        {
            float deltaY = y + tileSize + jndex * tileSize;
            float drawHeight = Math.min(tileSize, y + height - tileSize - deltaY);

            draw(target, tiles[0][1], x, deltaY, tileSize, drawHeight);
            draw(target, tiles[2][1], x + width - tileSize, deltaY, tileSize, drawHeight);
        }

        // Center
        for (int index = 0; index < tilesX; index++)
        {
            for (int jndex = 0; jndex < tilesY; jndex++)
            {
                float deltaX = x + tileSize + index * tileSize;
                float deltaY = y + tileSize + jndex * tileSize;

                float drawWidth = Math.min(tileSize, x + width - tileSize - deltaX);
                float drawHeight = Math.min(tileSize, y + height - tileSize - deltaY);

                draw(target, tiles[1][1], deltaX, deltaY, drawWidth, drawHeight);
            }
        }
    }
}
