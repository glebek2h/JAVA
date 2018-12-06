import javax.swing.*;
import java.awt.*;

/**
 * Tetris
 * Created by fpm.kazachin on 19.11.2018 8:41
 */

public class MainWindow extends JFrame
{
    public MainWindow()
    {
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320,342);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
        setBackground(Color.GREEN);
    }

    public static void main(String[] args)
    {
        MainWindow mw = new MainWindow();
        ScoreWindow sw = new ScoreWindow();
        sw.run();
    }

}
