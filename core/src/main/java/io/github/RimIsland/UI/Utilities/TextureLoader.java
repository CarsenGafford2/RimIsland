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
     * Fetches a {@link NineTileDrawable} from the loaded Texture Atlas.
     * @param id region fetched.
     * @param size size of fetched region.
     * @return region fetched with specified size.
     */
    public NineTileDrawable loadNineTile(int id, Vector2 size)
    {
        loadedTexture = getTexture();

        if (loadedTexture == null)
            throw new RuntimeException("Missing texture: " + loadedPath);

        tileMap = new TextureRegion[3][3];
        regionWeAreCutting = new TextureRegion(
            loadedTexture


            );

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++)
            {
                tileMap[column][row] = cut(column, row);
            }
        }

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
        // Inverted because LibGDX likes bottom left more than top left.
        int invertedRow = 2 - row;
        int tileOffset = tileSize + spacing; // 4px + 1px;


        int x = regionWeAreCutting.getRegionX()
            + offsetX // atlas offset
            + col * tileOffset; // index * offset

        int y = regionWeAreCutting.getRegionY() +
            offsetY + // atlas offset
            invertedRow * tileOffset; // index * offset

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
            return;
        }

        if (loadedTexture != null)
        {
            loadedTexture.dispose(); // counters memory leak
            loadedTexture = null;
        }

        this.loadedPath = path;
    }

    /**
     * Returns new Texture from loadedPath
     * @return Texture
     */
    public Texture getTexture()
    {
        if (loadedTexture == null)
        {
            loadedTexture = new Texture(Gdx.files.internal(loadedPath));
        }
        return loadedTexture;
    }
}
