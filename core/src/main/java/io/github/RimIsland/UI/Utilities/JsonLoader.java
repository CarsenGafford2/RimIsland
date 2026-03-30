package io.github.RimIsland.UI.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import io.github.RimIsland.UI.Enums.Anchor;
import io.github.RimIsland.UI.Exceptions.InvalidJsonValue;
import io.github.RimIsland.UI.Layer;
import io.github.RimIsland.UI.Label;

public class JsonLoader
{

    public static void load(String path, String section, Layer layer)
    {
        JsonReader reader = new JsonReader();
        JsonValue root = reader.parse(Gdx.files.internal(path));
        JsonValue sectionData = root.get(section);
        if (sectionData == null)
        {
            System.err.println("UILoader: section \"" + section + "\" not found in \"" + path + "\"");
            return;
        }

        for (JsonValue labelData : sectionData)
        {

        String name = "";

            try
            {
                name = labelData.name();
                int x = labelData.getInt("x", 0);
                int y = labelData.getInt("y", 0);
                int fontSize = labelData.getInt("text-size", 16);
                Color color = Color.valueOf(labelData.getString("text-color", "#FFFFFF"));
                String text = labelData.getString("text", "placeholder");
                String bind = labelData.getString("bind", "");
                String fontName = labelData.getString("font", "TitilliumWeb-Regular");
                //int align = TextAlign.valueOf(labelData.getString("text-align", "TOPLEFT")).getValue();
                Anchor xAnchor = Anchor.valueOf(labelData.getString("x-anchor", "LEFT"));
                Anchor yAnchor = Anchor.valueOf(labelData.getString("y-anchor", "TOP"));
                Label label = new Label();

                label.setText(text);
                label.setFont(fontName);
                //label.setAlignment(align);
                label.setXAnchor(xAnchor);
                label.setYAnchor(yAnchor);
                label.setPosition(x, y);
                label.setSize(fontSize);
                label.setColor(color);

                layer.addLabel(label);

                //System.out.println(name + x + y + fontSize + color + text + bind);
                if (!bind.isEmpty())
                {
                    layer.bind(bind, label);
                }
            }
            catch (Exception e)
            {
                throw new InvalidJsonValue(
                    "Invalid Json Value/Type:\n" +
                    "Make sure the fields are mapped correctly!\n" +
                        labelData + "\n"
                );
            }
        }
    }
}
