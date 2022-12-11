package io.github.marine.menus.guis;

import io.github.marine.customnodes.CustomPanel;
import io.github.marine.customnodes.CustomProgressField;
import io.github.marine.customnodes.CustomStat;
import io.github.marine.customnodes.CustomStatsPane;
import io.github.marine.data.Planet;
import io.github.marine.data.User;
import io.github.marine.utils.ReaderUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.NumberFormat;
import java.util.Locale;

public final class MainMenuGui extends VBox {

    private User user;

    public MainMenuGui(String name) {
        this.setPrefWidth(1280);
        this.setId("mainmenu");
        this.getStylesheets().add("css/MainMenuStyle.css");
        this.setSpacing(50);
        this.setFillWidth(false);
        this.registerChildren(name);
    }

    public void registerChildren(String name) {
        this.user = new User(ReaderUtil.readFromWebsite("https://api.galaxylifegame.net/users/name?name=" + name), ReaderUtil.readStatsFromApi(name));
        Text nickname = new Text(user.getName());
        nickname.setFont(Font.font("Arial", 100));
        nickname.setStyle("-fx-fill: #414246; -fx-font-family: Arial; -fx-font-size: 100;");
        nickname.setTranslateX(this.getPrefWidth()/2 - nickname.getBoundsInLocal().getWidth()/2);

        CustomStatsPane statsPane = new CustomStatsPane();

        this.customizeStatsPane(statsPane);

        statsPane.setTranslateY(-25);

        CustomPanel stats = new CustomPanel(500, 500);

        stats.setMaxHeight(500);

        stats.setTranslateX(50);

        ImageView basesDestroyed = new ImageView("assets/characters/Bazooka.png");

        basesDestroyed.setFitWidth(60);
        basesDestroyed.setFitHeight(45);

        ImageView colonyMovedImage = new ImageView("assets/galaxy/Planet_yellow.png");

        colonyMovedImage.setFitWidth(60);
        colonyMovedImage.setFitHeight(60);

        ImageView base = new ImageView("assets/buildings/starbase_"+user.getMainPlanet().level()+".png");

        base.setFitHeight(75);
        base.setFitWidth(60);

        CustomProgressField bases = new CustomProgressField("Bases destroyed", user.getStarbasesDestroyed(), user.getPlayersAttacked(), basesDestroyed);
        CustomProgressField coloniesMoved = new CustomProgressField("Colonies moved", user.getColoniesMoved(), user.getColonies().size()-1, colonyMovedImage);
        CustomProgressField starBase = new CustomProgressField("Starbase level", user.getMainPlanet().level(), 9, base);

        stats.setAlignment(Pos.CENTER);

        stats.getChildren().addAll(bases, coloniesMoved, starBase);

        CustomPanel colonies = new CustomPanel(400, 700);

        colonies.setTranslateX(200);

        for (int i = 1; i < user.getColonies().size(); i++) {
            Planet planet = user.getColonies().get(i);
            ImageView colonyImage = new ImageView("assets/galaxy/Planet_yellow.png");
            colonyImage.setFitWidth(60);
            colonyImage.setFitHeight(60);
            CustomProgressField colony = new CustomProgressField("Colony #"+i, planet.level(), 9, colonyImage);
            colony.setPadding(new Insets(50, 50, 0, 100));
            colonies.getChildren().add(colony);
        }

        colonies.setAlignment(Pos.TOP_CENTER);

        HBox holder = new HBox(stats, colonies);
        holder.setPrefSize(1280, 700);

        this.getChildren().addAll(nickname, statsPane, holder);
    }

    private void customizeStatsPane(CustomStatsPane pane) {

        NumberFormat format = NumberFormat.getNumberInstance(Locale.ROOT);

        ImageView level = new ImageView("assets/other/level.png");
        level.setFitWidth(40);
        level.setFitHeight(40);
        CustomStat levelStat = new CustomStat(level, format.format(user.getLevel()), "Level");

        ImageView timesAttacked = new ImageView("assets/buildings/Sniper_Tower.png");
        timesAttacked.setFitWidth(45);
        timesAttacked.setFitHeight(40);
        CustomStat timesAttackedStat = new CustomStat(timesAttacked, format.format(user.getTimesAttacked()), "Times Attacked");

        ImageView nuke = new ImageView("assets/other/nuke.png");
        nuke.setFitWidth(40);
        nuke.setFitHeight(45);
        CustomStat nukesUsedStat = new CustomStat(nuke, format.format(user.getUsedNukes()), "Nukes used");

        ImageView chipsSpent = new ImageView("assets/other/Chip.png");
        chipsSpent.setFitWidth(45);
        chipsSpent.setFitHeight(40);
        CustomStat chipsSpentStat = new CustomStat(chipsSpent, format.format(user.getChipsSpent()), "Chips spent");

        ImageView elderby = new ImageView("assets/characters/Elderby.png");
        elderby.setFitWidth(40);
        elderby.setFitHeight(40);

        CustomStat friendsHelpedStat = new CustomStat(elderby, format.format(user.getFriendsHelped()), "Friends Helped");

        ImageView obstacle = new ImageView("assets/galaxy/obstacle.png");
        obstacle.setFitWidth(42);
        obstacle.setFitHeight(40);

        CustomStat obstaclesRecycledStat = new CustomStat(obstacle, format.format(user.getObstaclesRecycled()), "Obstacles recycled");

        pane.addStats(levelStat, timesAttackedStat, nukesUsedStat, chipsSpentStat, friendsHelpedStat, obstaclesRecycledStat);
    }
}
