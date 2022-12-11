package io.github.marine.draggable;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public final class DraggableStage<E extends Stage, T extends Node> implements Draggable<E> {

    private final E stage;
    private final T node;
    private double x;
    private double y;

    public DraggableStage(E stage, T node) {
        this.stage = stage;
        this.node = node;
    }

    @Override
    public void apply() {
        node.setOnMousePressed(event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) return;
            this.x = event.getSceneX();
            this.y = event.getSceneY();
        });
        node.setOnMouseDragged(event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) return;
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    @Override
    public E getElement() {
        return stage;
    }

    public T getNode() {
        return node;
    }
}
