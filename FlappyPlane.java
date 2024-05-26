import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
class FlappyPlane extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 1200;
    int boardHeight = 640;
    Image BackgroundImg = new ImageIcon(getClass().getResource("screen.jpg")).getImage();
    Plane plane = new Plane(boardWidth / 15, boardHeight / 2);
    ArrayList<Pipe> pipes = new ArrayList<Pipe>();;
    Timer gameLoop;
    Timer placepipesTimer;
    Timer deletepipesTimer;
    boolean gameOver = false;
    boolean started=false;
    double score = 0;
    FlappyPlane() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);
        placepipesTimer = new Timer(100, new PipeAction(pipes, "place"));
        deletepipesTimer = new Timer(5, new PipeAction(pipes, "delete"));
        gameLoop = new Timer(1000 /60, this);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        g.drawImage(BackgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(plane.img, (int) plane.x, (int)plane.y, plane.width, plane.height, null);
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, (int) pipe.x,(int) pipe.y, pipe.width, pipe.height, null);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 32));
        }
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
            g.drawString("Press Enter + Space to replay",boardWidth/2-200,boardHeight/2);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move() {
        if (plane.x < boardWidth - 300) {
            plane.x += plane.veloccityX;
        }
        plane.y += plane.veloccityY;
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x -= plane.veloccityX;
            if (!pipe.passed && plane.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 100;
            }
            if (collision(plane, pipe)) {
                gameOver = true;
            }
        }
        if (plane.y > boardHeight) {
            gameOver = true;
        }
    }
    public boolean collision(Plane plane, Pipe pipe) {
        return plane.x <pipe.x + pipe.width &&
                plane.x + plane.width > pipe.x &&
                plane.y < pipe.y + pipe.height &&
                plane.y + plane.height > pipe.y ;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placepipesTimer.stop();
            deletepipesTimer.stop();
            gameLoop.stop();
            started = false;
        }
        if (!started){
            placepipesTimer.stop();
            deletepipesTimer.stop();
            gameLoop.stop();
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(started) {
                if(plane.y-35>0) {
                    plane.jump();
                }
            }
            else if(!gameOver) {
                    started = true;
                    placepipesTimer.start();
                    deletepipesTimer.start();
                    gameLoop.start();
            }
        }
        if(e.getKeyCode() ==KeyEvent.VK_LEFT){
            if(plane.x-20>50) {
                plane.x -= 20;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
               plane.y +=8 ;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
              started = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(gameOver) {
                plane = new Plane(boardWidth / 15, boardHeight / 2);
                score =0 ;
                pipes.clear();
                gameOver = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

