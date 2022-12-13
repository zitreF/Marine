package io.github.marine;

import io.github.marine.menus.MainMenu;
import io.github.marine.menus.RegisterMenu;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public final class ApplicationStage extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Marine");
        stage.setWidth(450);
        stage.setHeight(600);
        stage.getIcons().add(new Image("/assets/icons/marine.png"));

        File user = new File(System.getProperty("user.home")+"\\AppData\\Roaming\\Marine\\user.txt");

        if (!user.exists()) {
            Files.createDirectory(Paths.get(System.getProperty("user.home")+"\\AppData\\Roaming\\Marine"));
            user.createNewFile();
        }

        String name = Files.readString(user.toPath(), StandardCharsets.UTF_8);
        stage.setScene((name.isEmpty() ? new RegisterMenu(this).getScene() : new MainMenu(this.getStage(), name).getScene()));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}