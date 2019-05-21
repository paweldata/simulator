package surface;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import animals.Rabbit;
import animals.Wolf;

/**
 * Frame. Create field, add animals and start simulation.
 * @author Paweł Data
 */
public class Surface extends JFrame {
    private int rabbitsNumber;
    private int delay;
    private int height;
    private int width;
    private Field[][] table;
    private Random generator;
    private ArrayList<Rabbit> rabbitList;
    private Wolf Wolf;

    /**
     * Class constructor.
     * Prepare surface and start simulation.
     * @param rabbitsNumber number of rabbits
     * @param delay delay
     * @param height height of frame
     * @param width width of frame
     */
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

        start();
    }

    /**
     * Start simulation.
     */
    private void start() {
        for (int i = 0; i < rabbitList.size(); i++) {
            rabbitList.get(i).start();
        }
        Wolf.start();
    }

    /**
     * Return a height of frame.
     * @return height
     */
    public int getHeightofTable() {
        return this.height;
    }

    /**
     * Return a width of frame.
     * @return width
     */
    public int getWidthofTable() {
        return this.width;
    }

    /**
     * Return a delay of simulation.
     * @return number from [delay * 0,5. delay * 1,5]
     */
    public int getDelay() {
        return getRandomInt(this.delay) + this.delay/2;
    }

    /**
     * Return one field of surface.
     * @param i height coordinate
     * @param j width coordinate
     * @return field
     */
    public Field getField(int i, int j) {
        return this.table[i][j];
    }

    /**
     * Return arrayList of Rabbits.
     * @return arrayList of Rabbits
     */
    public ArrayList<Rabbit> getRabbitList() {
        return this.rabbitList;
    }

    /**
     * Return random integer.
     * @param bound bound of random
     * @return random integer
     */
    public int getRandomInt(int bound) {
        return generator.nextInt(bound);
    }

    /**
     * Return number of rabbits.
     * @return number of rabbits
     */
    public int getRabbitsNumber() {
        return this.rabbitsNumber;
    }

    /**
     * Return wolf.
     * @return wolf
     */
    public Wolf getWolf() {
        return this.Wolf;
    }

    /**
     * Check if wolf eats a rabbit.
     * If true, remove eaten rabbit from rabbitList and set wolf'pause.
     * @return true if rabbit was eaten, false otherwise
     */
    public boolean checkRabbitDead() {
        for (int i = 0; i < rabbitList.size(); i++) {
            if (rabbitList.get(i).getX() == Wolf.getX() && rabbitList.get(i).getY() == Wolf.getY()) {
                rabbitList.get(i).setIfAlive(false);
                rabbitList.remove(i);
                rabbitsNumber--;
                if (rabbitsNumber == 0)
                    Wolf.setIfALive(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Move rabbit.
     * @param xCurr currently height coordinate
     * @param yCurr currently width coordinate
     * @param xNext next height coordinate
     * @param yNext next width coordinate
     */
    public void moveRabbit(int xCurr, int yCurr, int xNext, int yNext) {
        table[xCurr][yCurr].setEmptyFiledColor();
        table[xNext][yNext].setRabbitColor();
    }

    /**
     * Move Wolf.
     * @param xCurr currently height coordinate
     * @param yCurr currently width coordinate
     * @param xNext next height coordinate
     * @param yNext next width coordinate
     */
    public void moveWolf(int xCurr, int yCurr, int xNext, int yNext) {
        table[xCurr][yCurr].setEmptyFiledColor();
        table[xNext][yNext].setWolfColor();
    }

    /**
     * Create table od fields and add to Surface.
     */
    private void addTableToSurface() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.table[i][j] = new Field();
                this.add(table[ i ] [ j ]);
            }
        }
    }

    /**
     * Create animals and add to Surface.
     */
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
