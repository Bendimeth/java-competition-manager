package com.competition.bracket;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Utils {
    public void GenerateBorders(GridPane root, int colIndex, int rowIndex) {
        ArrayList<String> cssClassNames = new ArrayList<String>();
        cssClassNames.add("bracket-top-1");
        cssClassNames.add("bracket-middle-1");
        cssClassNames.add("bracket-bottom-1");
        cssClassNames.add("bracket-top-2");
        cssClassNames.add("bracket-middle-2");
        cssClassNames.add("bracket-bottom-2");
        cssClassNames.add("bracket-top-3");
        cssClassNames.add("bracket-middle-3");
        cssClassNames.add("bracket-bottom-3");
        cssClassNames.add("bracket-top-4");
        cssClassNames.add("bracket-middle-4");
        cssClassNames.add("bracket-bottom-4");

        for (int i = 0; i < 3; i++) {
            Label bracketBorder = new Label(" ");
            if (colIndex == 2) {
                bracketBorder.getStyleClass().add(cssClassNames.get(i));
                root.add(bracketBorder, colIndex, rowIndex + i);
            }
            if (colIndex == 5) {
                bracketBorder.getStyleClass().add(cssClassNames.get(i + 3));
                root.add(bracketBorder, colIndex, rowIndex + i * 2);
            }
            if (colIndex == 8) {
                bracketBorder.getStyleClass().add(cssClassNames.get(i + 6));
                root.add(bracketBorder, colIndex, rowIndex +  i * 4);
            }
            if (colIndex == 11) {
                bracketBorder.getStyleClass().add(cssClassNames.get(i + 9));
                root.add(bracketBorder, colIndex, rowIndex +  i * 8);
            }
        }
    }
}
