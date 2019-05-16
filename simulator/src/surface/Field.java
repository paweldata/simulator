package surface;

import java.awt.Color;

import javax.swing.JButton;

public class Field extends JButton {
    private final Color emptyFieldColor = new Color(100, 100, 0);
    private final Color rabbitColor = new Color(200, 200, 200);
    private final Color wolfColor = new Color(0, 0, 0);

    public Field() {
        this.setEnabled(false);
        this.setBackground(this.emptyFieldColor);
    }

    public void setEmptyFiledColor() {
        this.setBackground(this.emptyFieldColor);
    }

    public void setRabbitColor() {
        this.setBackground(this.rabbitColor);
    }

    public void setWolfColor() {
        this.setBackground(this.wolfColor);
    }

    public Color getColor() {
        return this.getBackground();
    }

    public Color getEmptyFieldColor() {
        return this.emptyFieldColor;
    }
}