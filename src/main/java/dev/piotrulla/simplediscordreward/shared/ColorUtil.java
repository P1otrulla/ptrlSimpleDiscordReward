package dev.piotrulla.simplediscordreward.shared;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorUtil {

    private static final Map<String, Color> COLORS = new HashMap<>();

    static {
        COLORS.put("RED", Color.RED);
        COLORS.put("GREEN", Color.GREEN);
        COLORS.put("BLUE", Color.BLUE);
        COLORS.put("WHITE", Color.WHITE);
        COLORS.put("BLACK", Color.BLACK);
        COLORS.put("YELLOW", Color.YELLOW);
        COLORS.put("CYAN", Color.CYAN);
        COLORS.put("MAGENTA", Color.MAGENTA);
        COLORS.put("ORANGE", Color.ORANGE);
        COLORS.put("PINK", Color.PINK);
        COLORS.put("GRAY", Color.GRAY);
        COLORS.put("LIGHT_GRAY", Color.LIGHT_GRAY);
        COLORS.put("DARK_GRAY", Color.DARK_GRAY);
    }

    public static Color getColor(String colorName) {
        Color color = COLORS.get(colorName.toUpperCase());

        if (color == null) {
            return Color.BLACK;
        }

        return color;
    }
}
