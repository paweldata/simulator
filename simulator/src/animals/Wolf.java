package animals;

import java.util.ArrayList;

import surface.Surface;

public class Wolf extends Thread {
    private int x;
    private int y;
    private Surface Surface;

    public Wolf(Surface s,int i, int j) {
        this.Surface = s;
        this.x = i;
        this.y = j;
    }

    @Override
    public void run() {
        while(true) {
            Surface.getSimulation().doMove(this);
            Thread.yield();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private int distanceToRabbit(Rabbit rabbit) {
        return Math.max(Math.abs(this.x - rabbit.getX()), Math.abs(this.y - rabbit.getY()));
    }

    private int distanceToRabbit(int i, int j, Rabbit rabbit) {
        return Math.max(Math.abs(i - rabbit.getX()), Math.abs(j - rabbit.getY()));
    }

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

    private boolean fieldExist(int i, int j) {
        if (i >= 0 && i < Surface.getHeight() && j >= 0 && j < Surface.getWidth())
            return true;
        return false;
    }

    public void doMove() {
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

        }
    }
 }