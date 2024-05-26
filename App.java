import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
       int boardWidth =1200;
       int boardHeight = 640;
       JFrame frame = new JFrame("Flappy Plane");
       frame.setVisible(true);
       frame.setSize(boardWidth, boardHeight);
       frame.setLocationRelativeTo(null);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       FlappyPlane flappyPlane = new FlappyPlane();
       frame.add (flappyPlane);
       frame.pack();
       flappyPlane.requestFocus();
       frame.setVisible(true);
    }
}
