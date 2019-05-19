package surface;

import java.awt.Color;

import javax.swing.JButton;

/**
 * One square on Surface.
 * @author Paweł Data
 */
public class Field extends JButton {
    private final Color emptyFieldColor = new Color(100, 100, 0);
    private final Color rabbitColor = new Color(200, 200, 200);
    private final Color wolfColor = new Color(0, 0, 0);

    /**
     * Class constructor.
     */
    public Field() {
        this.setEnabled(false);
        this.setBackground(this.emptyFieldColor);
    }

    /**
     * set field's color to green.
     */
    public void setEmptyFiledColor() {
        this.setBackground(this.emptyFieldColor);
    }

    /**
     * set field's color to white.
     */
    public void setRabbitColor() {
        this.setBackground(this.rabbitColor);
    }

    /**
     * set field's color to black.
     */
    public void setWolfColor() {
        this.setBackground(this.wolfColor);
    }

    /**
     * Return Field's color.
     * @return Field's color
     */
    public Color getColor() {
        return this.getBackground();
    }

    /**
     * Return an empty field's color (green).
     * @return green color
     */
    public Color getEmptyFieldColor() {
        return this.emptyFieldColor;
    }
}