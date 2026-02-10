package io.github.RimIsland.Game;
import io.github.RimIsland.Utilities.Grid;

public class Map extends Grid<Tile>
{
    protected Map(int cols, int rows) {
        super(cols, rows);
    }

    @Override
    public void set(int col, int row, Tile object) {
        System.out.println("Not Implemented");
    }

    @Override
    public Tile get(int col, int row) {
        System.out.println("Not Implemented");
        return null;
    }
}
