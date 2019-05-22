package animals;

import java.util.ArrayList;

import surface.Surface;

/**
 * Wolf tries to eat all rabbits.
 * @author Paweł Data
 */
public class Wolf extends Thread {
    private int x;
    private int y;
    private Surface Surface;
    private int pauseTime;
    private boolean ifAlive;

    /**
     * Class constructor
     * @param s Surface
     * @param i height coordinate
     * @param j width coordinate
     */
    public Wolf(Surface s,int i, int j) {
        this.Surface = s;
        this.x = i;
        this.y = j;
        this.pauseTime = 0;
        this.ifAlive = true;
    }

    /**
     * Start moving wolf.
     */
    @Override
    public void run() {
        while(ifAlive) {
            synchronized (Surface) {
                if (pauseTime > 0) {
                    pauseTime--;
                } else {
                    doMove();
                    Surface.validate();
                }
                
                try {
                    Surface.wait(Surface.getDelay());
                } catch (InterruptedException ex) {}
            }
        }
    }

    /**
    * Return wolf's height coordinate.
    * @return wolf's height coordinate
    */
    public int getX() {
        return this.x;
    }

    /**
    * Return wolf's width coordinate.
    * @return wolf's width coordinate
    */
    public int getY() {
        return this.y;
    }

    /**
     * Set ifAlive
     * @param ifAlive true if wolf is alive, false otherwise
     */
    public void setIfALive(boolean ifAlive) {
        this.ifAlive = ifAlive;
    }

    /**
     * Return distance from wolf to rabbit.
     * @param rabbit rabbit
     * @return distance to rabbit
     */
    private int distanceToRabbit(Rabbit rabbit) {
        return Math.max(Math.abs(this.x - rabbit.getX()), Math.abs(this.y - rabbit.getY()));
    }

    /**
     * Return distance from coordinates to rabbit.
     * @param i height coordinate
     * @param j width coordinate
     * @param rabbit rabbit
     * @return distance to rabbit
     */
    private int distanceToRabbit(int i, int j, Rabbit rabbit) {
        return Math.max(Math.abs(i - rabbit.getX()), Math.abs(j - rabbit.getY()));
    }

    /**
     * Choose the nearest rabbit.
     * @return the nearest rabbit
     */
    private Rabbit chooseRabbit() {
        ArrayList<Rabbit> NearRabbitsList = new ArrayList<Rabbit>();
        NearRabbitsList.add(Surface.getRabbitList().get(0));

        for (int i = 0; i < Surface.getRabbitList().size(); i++) {
            if (distanceToRabbit(Surface.getRabbitList().get(i)) < distanceToRabbit(NearRabbitsList.get(0))) {
                NearRabbitsList.clear();
                NearRabbitsList.add(Surface.getRabbitList().get(i));
            } else if (distanceToRabbit(Surface.getRabbitList().get(i)) == distanceToRabbit(NearRabbitsList.get(0))) {
                NearRabbitsList.add(Surface.getRabbitList().get(i));
            }
        }

        return NearRabbitsList.get(Surface.getRandomInt(NearRabbitsList.size()));
    }

    /**
     * Check if field exists.
     * @param i height coordinate
     * @param j width coordinate
     * @return true if field exists, false otherwise
     */
    private boolean fieldExist(int i, int j) {
        if (i >= 0 && i < Surface.getHeightofTable() && j >= 0 && j < Surface.getWidthofTable())
            return true;
        return false;
    }

    /**
     * Move wolf.
     */
    private void doMove() {
        Rabbit targetRabbit = chooseRabbit();
        int actualDiscance = distanceToRabbit(targetRabbit);

        ArrayList<Integer> BestFieldsX = new ArrayList<Integer>();
        ArrayList<Integer> BestFieldsY = new ArrayList<Integer>();

        for (int i = this.x - 1; i <= this.x + 1; i++) {
            for (int j = this.y - 1; j <= this.y + 1; j++) {
               if (fieldExist(i, j) && distanceToRabbit(i, j, targetRabbit) < actualDiscance) {
                    BestFieldsX.add(i);
                    BestFieldsY.add(j);
               }
            }
        }

        int chooser = Surface.getRandomInt(BestFieldsX.size());
        int i = BestFieldsX.get(chooser);
        int j = BestFieldsY.get(chooser);

        Surface.moveWolf(this.x, this.y, i, j);
        this.x = i;
        this.y = j;

        if (Surface.checkRabbitDead()) {
            pauseTime = 5;   
        }
    }
 }