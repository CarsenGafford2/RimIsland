package io.github.RimIsland.Game;

import com.badlogic.gdx.math.Vector2;

public abstract class Tile
{
     public Vector2 position;
     public boolean traversable = true;

     public Tile(Vector2 position)
     {
         this.position = position;
     }
}
