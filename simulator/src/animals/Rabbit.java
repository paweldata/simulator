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

   @Override
   public void run() {
      while(true) {
         synchronized (Surface) {
            doMove();
            try {
               Surface.wait();
            } catch (InterruptedException ex) {
               System.out.println("Lipa");
            }
         }
         Thread.yield();
      }
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
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

      if (this.x - 1 >= 0 && this.x + 1 < height && this.y -1 >= 0 && this.y + 1 < width) {
         //choose the best options and then choose random

         for (int i = this.x - 1; i <= this.x + 1; i++) {
            for (int j = this.y - 1; j <= this.y + 1; j++) {
               if (distanceToWolf(i, j)  > actualDiscance) {
                  BestFieldsX.add(i);
                  BestFieldsY.add(j);
               }
            }
         }
      } else {
         //choose random

         for (int i = this.x - 1; i <= this.x + 1; i++) {
            for (int j = this.y - 1; j <= this.y + 1; j++) {
               if (i >= 0 && i < height && j >= 0 && j < width) {
                  BestFieldsX.add(i);
                  BestFieldsY.add(j);
               }
            }
         }
      }

      if (BestFieldsX.size() == 0)
         return;
      
      int chooser = Surface.getRandomInt(BestFieldsX.size());
      int i = BestFieldsX.get(chooser);
      int j = BestFieldsY.get(chooser);

      if (Surface.getField(i,j).getColor().equals(Surface.getField(i, j).getEmptyFieldColor())) {
         Surface.moveRabbit(this.x, this.y, i, j);
         this.x = i;
         this.y = j;
      }
   }
}