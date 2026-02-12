package io.github.RimIsland.UI.Enums;

import com.badlogic.gdx.utils.Align;

public enum TextAlign
{
    BOTTOM(Align.bottom),
    BOTTOMLEFT(Align.bottomLeft),
    BOTTOMRIGHT(Align.bottomRight),
    CENTER(Align.center),
    LEFT(Align.left),
    RIGHT(Align.right),
    TOP(Align.top),
    TOPLEFT(Align.topLeft),
    TOPRIGHT(Align.topRight);

    private final int align;

    TextAlign(int align)
    {
        this.align = align;
    }

    public int getValue()
    {
        return this.align;
    }
}
