package io.github.marine.customnodes;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public final class CustomTooltip extends Tooltip {

    public CustomTooltip(String text) {
        this.setShowDelay(Duration.ZERO);
        this.setText(text);
        this.setStyle("-fx-background-color: #181B1F; -fx-background-border: 30; -fx-text-fill: white; -fx-font-size: 15;");
    }
}
