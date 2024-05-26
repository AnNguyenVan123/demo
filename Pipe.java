import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.util.Scanner;

public class Pipe {
    Random rand = new Random();
    double x;
    double y;
    int width = 50;
    String type;
    int height = 50 + rand.nextInt(400);
    Image img;

    Image bottompipeImage = new ImageIcon(getClass().getResource("bot.png")).getImage();
    boolean passed = false;

    Pipe(int pipe_x, int pipe_y, String type) {

        if (type.equals("top")) {
            this.img = new ImageIcon(getClass().getResource("top.png")).getImage();
        }
        if (type.equals("bot")) {
            this.img = new ImageIcon(getClass().getResource("bot.png")).getImage();
        }

        this.x = pipe_x;
        this.y = pipe_y;
    }
}
