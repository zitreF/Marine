package io.github.marine.bars;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public final class TopBar extends AnchorPane {

    public TopBar(double width, double y) {
        this.setStyle("-fx-background-color: #181B1F;");
        this.setPrefSize(width, 60);
        this.setTranslateY(y);

        ImageView exit = new ImageView("/assets/icons/close.png");

        exit.setPickOnBounds(true);

        exit.setTranslateX(this.getPrefWidth() - exit.getBoundsInLocal().getWidth());

        exit.setOnMousePressed(event -> {
            System.exit(0);
        });

        this.getChildren().add(exit);
    }
}
