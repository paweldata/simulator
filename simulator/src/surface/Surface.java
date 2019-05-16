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

            this.rabbitList.add(new Rabbit(i, j));
            this.table[i][j].setRabbitColor();
        }

        do {
            i = generator.nextInt(height);
            j = generator.nextInt(width);
        } while (!table[i][j].getBackground().equals(emptyFieldColor));

        this.Wolf = new Wolf(i, j);
        this.table[i][j].setWolfColor();
    }
}
