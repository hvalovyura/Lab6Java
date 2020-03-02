package Lab6;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    private Field field = new Field();

    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
