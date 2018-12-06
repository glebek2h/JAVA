import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Tetris
 * Created by fpm.kazachin on 06.12.2018 17:34
 */

public class ScoreWindow extends JPanel implements Runnable,ActionListener
{
    JFrame frame;
    Image back;
    Timer timer;
    Graphics g;
    @Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        frame = new JFrame("Score");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocation(720,400);
        frame.setSize(200,200);
        frame.setVisible(true);
        setFocusable(true);

        ImageIcon ImageIconBack = new ImageIcon("back2.png");
        back = ImageIconBack.getImage();
        g = frame.getGraphics();


        timer = new Timer(10, this);
        timer.start();

        System.out.println("Привет из побочного потока!");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.drawImage(back,0,0,this);
        g.drawString("Всего добавлено фигур :"+Integer.toString(GameField.Num),0,50);
        g.drawString("Заработанные Очки :"+Integer.toString(GameField.score),0,60);

    }

    /*@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(back,0,0,this);
        g.drawString(Integer.toString(GameField.scoreInfo()),90,30);
    }*/
}
