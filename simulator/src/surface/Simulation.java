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
        synchronized (this) {
            Rabbit.doMove();
            try {
                //Thread.sleep(Surface.getDelay());
                wait();
            } catch (InterruptedException e) {}
        }
        
    }

    public void doMove(Wolf Wolf) {
        synchronized (this) {
            Wolf.doMove();
            try {
                Thread.sleep(Surface.getDelay());
                this.notifyAll();
            } catch (InterruptedException e) {
                System.out.println("Lipa");
            }
        }
    }
}