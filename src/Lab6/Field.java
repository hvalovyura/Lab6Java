package Lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JFrame
{
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);

    private boolean paused;

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Field()
    {
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void painComponent(Graphics g)
    {
        super.paintComponents(g);
        Graphics2D canvas = (Graphics2D) g;
        for(BouncingBall ball: balls)
        {
            ball.paint(canvas);
        }
    }
}
