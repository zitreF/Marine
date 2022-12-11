package io.github.marine.customnodes;

import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public final class CustomButton extends Button {

    public CustomButton(String text, Dimension2D size) {
        super(text);
        this.setCursor(Cursor.HAND);
        this.setPrefSize(size.getWidth(), size.getHeight()); // #2C2E32
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2C2E32; -fx-background-radius: 30; -fx-text-fill: #414246; -fx-font-size: 40;");
    }
}
