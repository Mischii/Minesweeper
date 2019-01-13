package Minesweeper.Core;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldMouseListener implements MouseListener {

    private Controller controller;
    private Field field;

    public FieldMouseListener(Field field, Controller controller)
    {
        this.field = field;
        this.controller = controller;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            controller.tagField(field);
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            controller.handleClick(field);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
