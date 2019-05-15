package main;

import entry.EntryFrame;

public class Simulator {
    private int rabbitNumber;
    private double delay;
    private int height;
    private int wigth;

    public int getRabbitNumber() {
        return this.rabbitNumber;
    }

    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        EntryFrame EntryFrame = new EntryFrame(simulator);
    }
}
