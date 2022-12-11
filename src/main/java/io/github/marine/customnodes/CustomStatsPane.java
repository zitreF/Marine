package io.github.marine.customnodes;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public final class CustomStatsPane extends FlowPane {

    public CustomStatsPane() {
        this.setPrefWidth(1280);
        this.setPrefWrapLength(1000);
        this.setOrientation(Orientation.HORIZONTAL);
        this.setHgap(25);
        this.setVgap(25);
        this.setAlignment(Pos.TOP_CENTER);
    }

    public void addStats(CustomStat... stats) {
        this.getChildren().addAll(stats);
    }

    @Override
    protected double computeMinHeight(double v) {
        return super.computeMinHeight(getWidth());
    }
}
