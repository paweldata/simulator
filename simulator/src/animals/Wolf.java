package animals;

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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
 }