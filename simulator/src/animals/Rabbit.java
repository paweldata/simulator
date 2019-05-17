package animals;

import java.awt.Color;

import surface.Surface;

public class Rabbit extends Thread {
   private int x;
   private int y;
   private Surface Surface;

   public Rabbit(Surface s ,int i, int j) {
      this.Surface = s;
      this.x = i;
      this.y = j;
   }

   private int distanceToWolf(int i, int j) {
      return Math.max(Math.abs(Surface.getWolf().getX() - i), Math.abs(Surface.getWolf().getY() - j));
   }

   public void doMove() {
      int actualDiscance = distanceToWolf(this.x, this.y);
      int height = Surface.getHeightofTable();
      int width = Surface.getWidthofTable();
      Color emptyFieldColor = Surface.getField(x,y).getEmptyFieldColor();

      if (x - 1 >= 0 && x + 1 <= height && y -1 >= 0 && y + 1 <= width) {
         //choose the best options and then choose random
      } else {
         //choose random
      }
   }
}