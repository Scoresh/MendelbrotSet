import java.awt.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import java.util.*; 
import java.awt.event.*; 

public class Mendelbrot extends Canvas implements ActionListener{
   public int widthFinal = 900;
   public int heightFinal = 900;
   public int iterationsFinal = 50;
   public double range = 4.0;
   public double startX = -2.0;
   public double endX = 2.0;
   public double startY = -2.0;
   public double endY = 2.0;
   
   public static void main(String[] args) {
      Mendelbrot mendelbrot = new Mendelbrot(); // Create instance of Mendelbrot
   
      JFrame frame = new JFrame("Mendelbrot Set");/*Creates the JFrame and gives it a title */
      Canvas canvas = mendelbrot;/*This class extends Canvas so it is itself a canvas class and inherits everything Canvas has, so the consructor call has to be the same name as the class we are in */
      
      JPanel p = new JPanel();
      
      
      JButton leftButton = new JButton("Left");
      leftButton.setActionCommand("LeftButton");
      leftButton.addActionListener(mendelbrot);
      
      JButton rightButton = new JButton("Right");
      rightButton.setActionCommand("RightButton");
      rightButton.addActionListener(mendelbrot);
      
      JButton upButton = new JButton("Up");
      upButton.setActionCommand("UpButton");
      upButton.addActionListener(mendelbrot);
      
      JButton downButton = new JButton("Down");
      downButton.setActionCommand("DownButton");
      downButton.addActionListener(mendelbrot);
      
      JButton zInButton = new JButton("Zoom In");
      zInButton.setActionCommand("ZoomIn");
      zInButton.addActionListener(mendelbrot);
      
      JButton zOutButton = new JButton("Zoom Out");
      zOutButton.setActionCommand("ZoomOut");
      zOutButton.addActionListener(mendelbrot);
      
      JButton fineButton = new JButton("Increase Quality");
      fineButton.setActionCommand("Fine");
      fineButton.addActionListener(mendelbrot);
      
      JButton roughButton = new JButton("Decrease Quality");
      roughButton.setActionCommand("Rough");
      roughButton.addActionListener(mendelbrot);
      
      
      canvas.setSize(900, 900);/*Set dimensions of canvas changing these numbers will change the size of your canvas */
      canvas.setBackground(Color.BLACK);/* Set the background color of the canvas */
      // frame.add(canvas);/* add the canvas object to the frame object */
      p.add(canvas,BorderLayout.NORTH);
      
      leftButton.setSize(100,100);
      p.add(leftButton,BorderLayout.SOUTH);
      
      
      rightButton.setSize(100,100);
      p.add(rightButton,BorderLayout.SOUTH);
      
      upButton.setSize(100,100);
      p.add(upButton,BorderLayout.SOUTH);
      
      downButton.setSize(100,100);
      p.add(downButton,BorderLayout.SOUTH);
      
      zInButton.setSize(100,100);
      p.add(zInButton,BorderLayout.SOUTH);
      
      zOutButton.setSize(100,100);
      p.add(zOutButton,BorderLayout.SOUTH);
      
      fineButton.setSize(100,100);
      p.add(fineButton,BorderLayout.SOUTH);
      
      
      roughButton.setSize(100,100);
      p.add(roughButton,BorderLayout.SOUTH);
      
      // frame.add(button);
      frame.add(p);
      frame.setSize(900,1000);
      // frame.pack();/* pack the canvas object into the frame object based on the canvas objects size and other settings */
      
      
      frame.setVisible(true);/* display the thing*/
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);//will end the application on close
        
   }
   public void paint(Graphics g){
        g.setColor(Color.WHITE);
        int width = widthFinal;
        int height = heightFinal;
        int maxIterations = iterationsFinal;
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double real = startX + (double)x / width * (endX - startX);
                double imag = startY + (double)y / height * (endY - startY);
                int iterations = doCalculations(real,imag,maxIterations);
                g.setColor(Color.WHITE);
                if (iterations == maxIterations) {
                    g.drawLine(x,y,x,y);
                } 
            }
        }
   }
   
   public int doCalculations(double real,double imag, int maxIterations){
        double real1 = 0;
        double imag1 = 0;
        double tempr1 = 0;
        double tempi1 = 0;
        int n = 0;
        while (Math.sqrt(real1 * real1 + imag1 * imag1) <= 2 && n < maxIterations) {
            tempr1 = real1;
            tempi1 = imag1;
            real1 = (real1 * real1 - imag1 * imag1) + real;
            imag1 = (2 * tempr1 * tempi1) + imag;
            n++;
        }
        return n;
   }
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      
      if (command.equals("LeftButton")){
         double deltaX = (endX-startX)/4.0;
         startX -=deltaX;
         endX -= deltaX;
      }
      else if (command.equals("RightButton")){
         double deltaX = (endX-startX)/4.0;
         startX += deltaX;
         endX += deltaX;
      }
      else if (command.equals("UpButton")){
         double deltaY = (endY-startY)/4.0;
         startY -= deltaY;
         endY -= deltaY;
      }
      else if (command.equals("DownButton")){
         double deltaY = (endY-startY)/4.0;
         startY += deltaY;
         endY += deltaY;
      }
      else if (command.equals("ZoomOut")){
          double centerX = (startX + endX) / 2.0;
          double centerY = (startY + endY) / 2.0;
          
          // Adjust the distance from center to edge
          double deltaX = (endX - startX);
          double deltaY = (endY - startY);
          
          // Zoom out by increasing the distance from center to edge
          startX = centerX - deltaX;
          endX = centerX + deltaX;
          startY = centerY - deltaY;
          endY = centerY + deltaY;
          
          // Reduce the number of iterations dynamically
          iterationsFinal = Math.max(50, iterationsFinal / 2); // Reduce iterations by half
         
      }
      else if (command.equals("ZoomIn")){
          double centerX = (startX + endX) / 2.0;
          double centerY = (startY + endY) / 2.0;
          
          // Adjust the distance from center to edge
          double deltaX = (endX - startX) / 4.0;
          double deltaY = (endY - startY) / 4.0;
          
          // Zoom in by reducing the distance from center to edge
          startX = centerX - deltaX;
          endX = centerX + deltaX;
          startY = centerY - deltaY;
          endY = centerY + deltaY;
      }
      else if (command.equals("Fine")){
         iterationsFinal*=10;
      }
      else if (command.equals("Rough")){
         if (iterationsFinal / 10 > 5){
         
         
            iterationsFinal/=10;
         }
      }
      
      
      //System.out.println("Hello, World"); // Print "Hello, World" when button is clicked
      repaint();
   }
   
   
   
}
