package surface;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import animals.*;

public class Surface extends JFrame {
    private int rabbitsNumber;
    private int delay;
    private int height;
    private int width;
    private Field[][] table;
    private Random generator;
    private ArrayList<Rabbit> rabbitList;
    private Wolf Wolf;

    public Surface(int rabbitsNumber, int delay, int height, int width) {
        this.rabbitsNumber = rabbitsNumber;
        this.delay = delay;
        this.height = height;
        this.width = width;
        this.generator = new Random();
        this.table = new Field[height][width];

        this.setLayout(new GridLayout(height, width));
        this.setSize(width * 50, height * 50);

        addTableToSurface();
        addAnimalsToSurface();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public int getHeightofTable() {
        return this.height;
    }

    public int getWidthofTable() {
        return this.width;
    }

    public int getDelay() {
        return this.delay;
    }

    public Field getField(int i, int j) {
        return this.table[i][j];
    }

    public int getRandomInt(int bound) {
        return generator.nextInt(bound);
    }

    public int getRabbitsNumber() {
        return this.rabbitsNumber;
    }

    public void RabbitDead() {
        this.rabbitsNumber = this.rabbitsNumber - 1;
    }

    public Wolf getWolf() {
        return this.Wolf;
    }

    public void moveRabbit(int xCurr, int yCurr, int xNext, int yNext) {
        if (table[xCurr][yCurr].getColor().equals(table[0][0].getEmptyFieldColor())) {
            table[xCurr][yCurr].setEmptyFiledColor();
            table[xNext][yNext].setRabbitColor();
        }
    }

    public void moveWolf(int xCurr, int yCurr, int xNext, int yNext) {
        table[xCurr][yCurr].setEmptyFiledColor();
        table[xNext][yNext].setWolfColor();
    }

    private void addTableToSurface() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.table[i][j] = new Field();
                this.add(table[ i ] [ j ]);
            }
        }
    }

    private void addAnimalsToSurface() {
        int i;
        int j;

        this.rabbitList = new ArrayList<Rabbit>();
        Color emptyFieldColor = table[0][0].getEmptyFieldColor();

        for (int k = 0; k < rabbitsNumber; k++) {
            do {
                i = generator.nextInt(height);
                j = generator.nextInt(width);
            } while (!table[i][j].getBackground().equals(emptyFieldColor));

            this.rabbitList.add(new Rabbit(this, i, j));
            this.table[i][j].setRabbitColor();
        }

        do {
            i = generator.nextInt(height);
            j = generator.nextInt(width);
        } while (!table[i][j].getBackground().equals(emptyFieldColor));

        this.Wolf = new Wolf(this, i, j);
        this.table[i][j].setWolfColor();
    }
}
