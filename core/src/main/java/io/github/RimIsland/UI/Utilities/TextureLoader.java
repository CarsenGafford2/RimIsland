package io.github.RimIsland.UI.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.RimIsland.UI.Graphics.NineTileDrawable;

import java.util.Objects;

public class TextureLoader
{
    private int tileSize = 4;
    private int spacing = 1;
    private int offsetX = 1;
    private int offsetY = 1;
    private String loadedPath = "textures/ui/basic.png"; // default
    private Texture loadedTexture;
    private TextureRegion regionWeAreCutting;
    private TextureRegion[][] tileMap;

    /**
     * Fetches a NineTileDrawable from loadedTexture.
     * @param id region fetched.
     * @param size size of fetched region.
     * @return region fetched with specified size.
     */
    public NineTileDrawable loadNineTile(int id, Vector2 size)
    {
        // Updates Texture with current Path
        loadedTexture = getTexture();

        // Checks if Texture exists
        if (loadedTexture == null)
            throw new RuntimeException("Missing texture: " + loadedPath);

        // Sets up a multiarray of textures
        tileMap = new TextureRegion[3][3];

        // Cuts the region into multiple slices, feeding it into a tileMap table
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++)
            {
                tileMap[column][row] = cut(column, row);
            }
        }

        // we feed the tileMap table into a NineTileDrawable
        return new NineTileDrawable(tileMap);
    }

    /**
     * Cuts portions of a Texture for NineTileDrawable
     * @param col x-index offset
     * @param row y-index offset
     * @return Sliced Region
     */
    private TextureRegion cut(int col, int row)
    {
        int x = regionWeAreCutting.getRegionX() + offsetX + col * (tileSize + spacing);

        // Inverted because LibGDX likes bottom left more than top left.
        int invertedRow = 2 - row;
        int y = regionWeAreCutting.getRegionY() + offsetY + invertedRow * (tileSize + spacing);

        return new TextureRegion(regionWeAreCutting.getTexture(), x, y, tileSize, tileSize);
    }

    /**
     * Update loadedPath for loadedTexture.
     * @param path path of loadable texture.
     */
    public void setTexturePath(String path)
    {
        if (Objects.equals(this.loadedPath, path))
        {
            System.err.println("WARNING: Path is already the given path!");
        }
        else
        {
            try
            {
                this.loadedPath = path;
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Returns new Texture from loadedPath
     * @return Texture
     */
    public Texture getTexture()
    {
        return new Texture(Gdx.files.internal(loadedPath));
    }
}
