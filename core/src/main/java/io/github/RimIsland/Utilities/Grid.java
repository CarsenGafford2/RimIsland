package io.github.RimIsland.Utilities;

public abstract class Grid<Type>
{
    protected int cols, rows;

    protected Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public abstract void set(int col, int row, Type object);

    public abstract Type get(int col, int row);
}
