package io.github.marine.customnodes;

import javafx.scene.layout.VBox;

public final class CustomPanel extends VBox {

    public CustomPanel(double width, double height) {
        this.setStyle("-fx-background-color: #191B20; -fx-background-radius: 25; -fx-padding: 25 0 0 0;");
        this.setPrefSize(width, height);
    }
}
