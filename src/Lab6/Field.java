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

    private double xStart;
    private double yStart;
    private double xEnd;
    private double yEnd;
    private double startTime;
    private double endTime;
    private double totalTime;

    public class MouseHandler extends MouseAdapter
    {
        public void mouseClicked(MouseEvent ev)
        {

        }

        public void mousePressed(MouseEvent ev)
        {
            if(ev.getButton() == 1)
            {
                pause();
                xStart = ev.getX();
                yStart = ev.getY();
                startTime = System.currentTimeMillis();
            }
        }

        public void mouseReleased(MouseEvent ev)
        {
            if(ev.getButton() == 1)
            {
                xEnd = ev.getX();
                yEnd = ev.getY();
                endTime = System.currentTimeMillis();
                totalTime = endTime - startTime;
                resume();
                for(BouncingBall ball : balls)
                {
                    if (ball.x <= xStart + ball.radius && ball.x >= xStart - ball.radius && ball.y <= yStart + ball.radius && ball.y >= yStart - ball.radius)
                    {
                        ball.setSpeedX(0);
                        ball.setSpeedY(0);
                    }
                }
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
