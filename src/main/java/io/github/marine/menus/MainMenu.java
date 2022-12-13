package io.github.marine.menus;

import io.github.marine.bars.TopBar;
import io.github.marine.customnodes.CustomTextField;
import io.github.marine.draggable.Draggable;
import io.github.marine.draggable.DraggableStage;
import io.github.marine.menus.guis.MainMenuGui;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class MainMenu extends AnchorPane {

    private final String name;
    private final Stage stage;
    private ScrollPane scrollPane;

    public MainMenu(Stage stage, String name) {
        new Scene(this);
        this.stage = stage;
        stage.setWidth(1280);
        stage.setHeight(800);
        this.setPrefSize(1280, 800);
        stage.centerOnScreen();
        this.setStyle("-fx-background-color: #1B1E23;");
        this.name = name;
        Draggable<Stage> draggable = new DraggableStage<>(stage, this);
        draggable.apply();
        this.registerChildren();
    }

    private void registerChildren() {

        MainMenuGui gui = new MainMenuGui(this.name, this);

        this.scrollPane = new ScrollPane(gui);
        scrollPane.setId("scroll-pane");
        scrollPane.getStylesheets().add("css/ScrollbarStyle.css");
        scrollPane.setPrefWidth(1280);
        scrollPane.setPrefHeight(700);
        scrollPane.setFitToHeight(true);
        scrollPane.setTranslateY(100);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent;");

        CustomTextField search = new CustomTextField("Search", new Dimension2D(400, 70));

        search.setStyle("-fx-background-color: #2C2E32; -fx-background-radius: 90;" +
                "-fx-font-size: 30; -fx-alignment: center; -fx-font-family: Arial; -fx-text-fill: #56575A; -fx-prompt-text-fill: #56575A;");

        TopBar topBar = new TopBar(this.getPrefWidth(), this.getTranslateY());

        topBar.setPrefHeight(100);

        search.setTranslateX(topBar.getPrefWidth()/2 - search.getPrefWidth()/2);

        search.setTranslateY(15);

        search.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                Platform.runLater(() -> {
                    gui.getChildren().clear();
                    gui.registerChildren(search.getText());
                });
            }
        });

        topBar.getChildren().add(search);

        this.getChildren().addAll(topBar, scrollPane);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}
