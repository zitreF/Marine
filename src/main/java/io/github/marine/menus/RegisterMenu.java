package io.github.marine.menus;

import io.github.marine.ApplicationStage;
import io.github.marine.bars.TopBar;
import io.github.marine.customnodes.CustomButton;
import io.github.marine.customnodes.CustomTextField;
import io.github.marine.customnodes.CustomTooltip;
import io.github.marine.draggable.Draggable;
import io.github.marine.draggable.DraggableStage;
import io.github.marine.utils.ReaderUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Dimension2D;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class RegisterMenu extends AnchorPane {

    private final ApplicationStage applicationStage;

    public RegisterMenu(ApplicationStage applicationStage) {
        this.applicationStage = applicationStage;
        new Scene(this);
        this.setPrefSize(applicationStage.getStage().getWidth(), applicationStage.getStage().getHeight());
        this.setId("mainmenu");
        this.getStylesheets().add("css/MainMenuStyle.css");
        this.registerChildren();
        Draggable<Stage> draggable = new DraggableStage<>(applicationStage.getStage(), this);
        draggable.apply();

    }

    private void registerChildren() {

        TopBar topBar = new TopBar(this.getPrefWidth(), this.getTranslateY());

        Text marine = new Text("Marine");

        marine.setTranslateY(topBar.getTranslateY() + 150);

        marine.setFont(Font.font("Arial", 75));

        marine.setStyle("-fx-fill: #414246; -fx-font-family: Arial; -fx-font-size: 75;");

        marine.setTranslateX(this.getPrefWidth()/2 - marine.getBoundsInLocal().getWidth()/2);

        CustomTextField nick = new CustomTextField("Nickname", new Dimension2D(300, 50));

        nick.translateXProperty().bind(this.prefWidthProperty().divide(2).subtract(nick.getPrefWidth()/2));
        nick.translateYProperty().bind(this.prefHeightProperty().divide(2).subtract(nick.getPrefHeight()/2));

        ImageView warning = new ImageView("assets/icons/warning.png");

        warning.setFitHeight(40);
        warning.setFitWidth(40);

        warning.setTranslateX(nick.getTranslateX() + nick.getPrefWidth() + 10);
        warning.setTranslateY(nick.getTranslateY() + 5);
        warning.setPickOnBounds(true);

        CustomTooltip customTooltip = new CustomTooltip("Please use your Galaxy Life nickname");

        customTooltip.setOnShown(event -> {
            Bounds bounds = warning.localToScreen(warning.getBoundsInLocal());

            customTooltip.setX(bounds.getMaxX());
            customTooltip.setY(bounds.getMinY());
        });

        Tooltip.install(warning, customTooltip);

        CustomButton login = new CustomButton("LOGIN", new Dimension2D(350, 100));

        login.translateXProperty().bind(this.prefWidthProperty().divide(2).subtract(login.getPrefWidth()/2));
        login.translateYProperty().bind(this.prefHeightProperty().subtract(125));

        login.setOnMousePressed(event -> {

            if (nick.getText().isBlank()) {
                return;
            }

            String validator = ReaderUtil.readFromWebsite("https://api.galaxylifegame.net/users/name?name=" + nick.getText());

            if (validator.isEmpty()) {
                return;
            }

            Path path = Paths.get(System.getProperty("user.home") + "\\AppData\\Roaming\\Marine\\user.txt");

            try {
                if (Files.readAllLines(path, StandardCharsets.UTF_8).isEmpty()) {
                    Files.writeString(path, nick.getText());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.getChildren().removeAll(topBar, marine, nick, warning, login);

            applicationStage.getStage().setWidth(960);
            this.setPrefWidth(960);

            applicationStage.getStage().centerOnScreen();

            Text welcome = new Text("Welcome, " + nick.getText());
            welcome.setTranslateY(this.getPrefHeight());
            welcome.setFont(Font.font("Arial", 75));
            welcome.setStyle("-fx-fill: #414246; -fx-font-family: Arial; -fx-font-size: 75;");

            welcome.setTranslateX(this.getPrefWidth()/2 - welcome.getBoundsInLocal().getWidth()/2);

            welcome.setCache(true);
            welcome.setCacheHint(CacheHint.SPEED);

            TranslateTransition welcomeTransition = new TranslateTransition(Duration.seconds(2));
            welcomeTransition.setNode(welcome);
            welcomeTransition.setFromY(welcome.getTranslateY());
            welcomeTransition.setToY(this.getPrefHeight()/2);

            welcomeTransition.play();

            this.getChildren().add(welcome);

            welcomeTransition.setOnFinished(finish -> {
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), x -> {
                    this.getChildren().clear();
                    this.applicationStage.getStage().setScene(new MainMenu(applicationStage.getStage(), nick.getText()).getScene());
                }));
                timeline.play();
            });
        });

        this.getChildren().addAll(topBar, marine, nick, warning, login);
    }
}
