package io.github.marine.customnodes;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class CustomStat extends HBox {

    public CustomStat(ImageView icon, String value, String tooltip) {
        // #191B20 | #2C2F37
        this.setStyle("-fx-background-color: #191B20; -fx-background-radius: 50");

        this.setSpacing(15);

        Text valueText = new Text(value);

        valueText.setFont(Font.font("Arial", 20));

        valueText.setStyle("-fx-fill: #45464b; -fx-font-family: Arial; -fx-font-size: 20;");

        this.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(10, 10, 10, 10));

        CustomTooltip customTooltip = new CustomTooltip(tooltip);

        customTooltip.setOnShown(event -> {
            Bounds bounds = this.localToScreen(this.getBoundsInLocal());

            customTooltip.setX(bounds.getCenterX() - customTooltip.getWidth()/2);
            customTooltip.setY(bounds.getMaxY());
        });

        Tooltip.install(this, customTooltip);

        this.getChildren().addAll(icon, valueText);
    }
}
