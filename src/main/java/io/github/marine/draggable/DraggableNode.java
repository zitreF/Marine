package io.github.marine.draggable;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;

public final class DraggableNode<E extends Node> implements Draggable<E> {

    private final E node;

    private double x;
    private double y;

    public DraggableNode(E node) {
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
            node.setTranslateX(event.getScreenX() - x);
            node.setTranslateY(event.getScreenY() - y);
        });
    }

    @Override
    public E getElement() {
        return node;
    }
}
