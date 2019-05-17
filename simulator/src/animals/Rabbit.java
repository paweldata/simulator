package animals;

import java.util.ArrayList;

import surface.*;

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

      ArrayList<Integer> BestFieldsX = new ArrayList<Integer>();
      ArrayList<Integer> BestFieldsY = new ArrayList<Integer>();

      if (this.x - 1 >= 0 && this.x + 1 <= height && this.y -1 >= 0 && this.y + 1 <= width) {
         //choose the best options and then choose random

         for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
               if (distanceToWolf(i, j)  < actualDiscance) {
                  BestFieldsX.add(i);
                  BestFieldsX.add(j);
               }
            }
         }
      } else {
         //choose random

         for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
               if (i >= 0 && i <= height && j >= 0 && j <= width) {
                  BestFieldsX.add(i);
                  BestFieldsX.add(j);
               }
            }
         }
      }

      int chooser = Surface.getRandomInt(BestFieldsX.size());
      Surface.moveRabbit(this.x, this.y, BestFieldsX.get(chooser), BestFieldsY.get(chooser));
   }
}