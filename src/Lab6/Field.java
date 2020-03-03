package Lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Field extends JPanel
{
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);

    private boolean paused;

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            repaint();
        }
    });

    public Field()
    {
        setBackground(Color.WHITE);
        repaintTimer.start();
        addMouseMotionListener(new MouseMotionHandler());
        addMouseListener(new MouseHandler());
    }

    public void addBall()
    {
        balls.add(new BouncingBall(this));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for(BouncingBall ball: balls)
        {
            ball.paint(canvas);
        }
    }

    public synchronized void pause()
    {
        paused = true;
    }

    public synchronized void canMove(BouncingBall ball) throws InterruptedException
    {
        if(paused)
        {
            wait();
        }
    }

    public synchronized void resume()
    {
        paused = false;
        notifyAll();
    }

    public class MouseHandler extends MouseAdapter
    {
        public void mouseClicked(MouseEvent ev)
        {

        }

        public void mousePressed(MouseEvent ev)
        {
            if(ev.getButton() == 1)
            {
                paused = true;
            }
        }

        public void mouseReleased(MouseEvent ev)
        {
            if(ev.getButton() == 1)
            {
                resume();
            }
        }
    }

    public class MouseMotionHandler implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
