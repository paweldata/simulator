package surface;

import animals.Rabbit;
import animals.Wolf;

public class Simulation {
    Surface Surface;

    public Simulation(Surface s) {
        this.Surface = s;
    }

    public void run() {
        for (int i = 0; i < Surface.getRabbitList().size(); i++) {
            Surface.getRabbitList().get(i).start();
        }
        Surface.getWolf().start();
    }

    public void doMove(Rabbit Rabbit) {
        synchronized (Surface) {
            Rabbit.doMove();
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Lipa");
            }
            Thread.yield();
        }
        
    }

    public void doMove(Wolf Wolf) {
        synchronized (Surface) {
            Wolf.doMove();
            try {
                Thread.sleep(Surface.getDelay());
                Thread.yield();
            } catch (InterruptedException e) {
                System.out.println("Lipa");
            }
        }
    }
}