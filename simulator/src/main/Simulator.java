package main;

import entry.EntryFrame;

public class Simulator {
    private int rabbitsNumber;
    private int delay;
    private int height;
    private int width;

    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        EntryFrame EntryFrame = new EntryFrame(simulator);
    }

    public int getRabbitsNumber() {
        return this.rabbitsNumber;
    }

    public void setRabbitsNumber(int newValue) {
        this.rabbitsNumber = newValue;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int newValue) {
        this.delay = newValue;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int newValue) {
        this.height = newValue;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int newValue) {
        this.width = newValue;
    }
}
