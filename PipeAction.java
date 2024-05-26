import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PipeAction implements ActionListener {
    ArrayList<Pipe> pipes;
    int boardHeight = 640;
    String action;
    PipeAction(ArrayList<Pipe> pipes, String action) {
        this.pipes = pipes;
        this.action = action;
    }
    ;

    public void placepipes() {
        //(0-1) * pipeHeight/2 -> (0-256)
        // 128
        // 0 - 128 - (0-256) --> 1/4 pipeHeight -> 3/4 pipeHeight
        if (!pipes.isEmpty()) {
            Pipe last_pipe = pipes.getLast();
            Pipe topPipe = new Pipe((int) last_pipe.x + 300, 0,"top");
            pipes.add(topPipe);
            Pipe bottomPipe = new Pipe( (int) last_pipe.x + 600, 0,"bot");
            bottomPipe.y = boardHeight-bottomPipe.height;
            pipes.add(bottomPipe);
        } else {
            Pipe topPipe = new Pipe( + 300, 0,"top");
            pipes.add(topPipe);
            Pipe bottomPipe = new Pipe(  600, 0,"bot");
            bottomPipe.y = boardHeight-bottomPipe.height;
            pipes.add(bottomPipe);
        }
    }
    public void deletepipes() {
        //(0-1) * pipeHeight/2 -> (0-256)
        // 128
        // 0 - 128 - (0-256) --> 1/4 pipeHeight -> 3/4 pipeHeight
        if (pipes.size() > 100) {
            for (int i = 0; i < pipes.size() - 100; i++) {
                if (pipes.get(i).x < 0) {
                    pipes.remove(pipes.get(i));
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (action.equals("place")) {
            placepipes();
        }
        if (action.equals("delete")) {
            deletepipes();
        }

    }
}
