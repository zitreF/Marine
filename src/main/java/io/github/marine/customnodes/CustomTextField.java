package io.github.marine.customnodes;

import javafx.geometry.Dimension2D;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public final class CustomTextField extends TextField {

    public CustomTextField(String text, Dimension2D size) {
        this.setPromptText(text);
        this.setFocused(false);
        this.setFocusTraversable(false);
        this.setPrefSize(size.getWidth(), size.getHeight());
        this.setEffect(new DropShadow(5d, Color.web("#27292C")));
        this.setStyle("-fx-background-color: #2C2E32; -fx-background-radius: 50; -fx-border-color: #27292C; -fx-border-radius: 50; -fx-border-width: 3; " +
                "-fx-font-size: 18; -fx-alignment: center; -fx-font-family: Arial; -fx-text-fill: #56575A; -fx-prompt-text-fill: #56575A;");
    }
}
