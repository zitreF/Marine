package io.github.marine.customnodes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class CustomProgressField extends StackPane {

    private final ProgressBar progressBar;
    private final HBox values;


    public CustomProgressField(String name, float progress, float limit, ImageView icon) {
        this.setStyle("-fx-background-color: transparent;");

        this.setAlignment(Pos.CENTER_LEFT);

        this.setPrefSize(350, 150);
        this.setPadding(new Insets(0, 0, 0, 75));

        this.progressBar = new ProgressBar();
        progressBar.setProgress(progress / limit);
        progressBar.setPrefSize(350, 25);
        progressBar.setId("progress-bar");
        progressBar.getStylesheets().add("css/ProgressBarStyle.css");
        this.values = new HBox();
        values.setPrefSize(350, 75);

        Text value = new Text(String.format("%.0f/%.0f", progress, limit));
        value.setFont(Font.font("Arial", 20));
        value.setStyle("-fx-fill: #45464b; -fx-font-family: Arial; -fx-font-size: 20;");
        value.setTranslateY(-60);

        Text text = new Text(name);
        text.setFont(Font.font("Arial", 15));
        text.setStyle("-fx-fill: #414246; -fx-font-family: Arial; -fx-font-size: 15;");
        text.setTranslateY(-40);

        icon.setTranslateX(-65);
        icon.setTranslateY(-40);

        icon.setStyle("-fx-background-radius: 90; -fx-border-radius: 90;");

        StackPane.setAlignment(icon, Pos.CENTER_LEFT);

        this.getChildren().addAll(icon, values, value, text, progressBar);
    }

    public void addNodeToHBox(Node node) {
        this.values.getChildren().add(node);
    }
}
