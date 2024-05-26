import javax.swing.*;
import java.awt.*;

public class Plane {
    public double x;
    public double y;
    public int width = 50;
    public int height = 50;
    public double veloccityX = 2;
    public double gravity = 1.5;
    public double veloccityY = gravity;
    Image img = new ImageIcon(getClass().getResource("plane2.PNG")).getImage();;

    public void jump() {

        y -= 35;
    }

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

