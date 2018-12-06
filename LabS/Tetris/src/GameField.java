import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tetris
 * Created by fpm.kazachin on 19.11.2018 8:42
 */

public class GameField extends JPanel implements ActionListener
{
    private final int SIZE = 320;
    private final int PIXEL_SIZE = 16;
    private final int TOTAL_PIXELS = 400;
    private final int MAX_SQUARES = 5;
    private Image square;
    private Image back;
    private Image gameover;

    private ArrayList<Integer> squaresX;
    private ArrayList<Integer> squaresY;

    private ArrayList<ArrayList<Integer>> arrayX;
    private ArrayList<ArrayList<Integer>> arrayY;
    private int NUM = 0;

    private int countSquares;
    private Timer timer;
    private boolean left;
    private boolean right;
    private boolean down;
    private boolean getEnd;
    private boolean gameOver;
    private int count = 1;
    private int globalCount = 1;

    Stick stick;
    LetterG letterG;
    LetterL letterL;
    public GameField()
    {

        arrayX = new ArrayList<>();
        arrayY = new ArrayList<>();

        squaresX = new ArrayList<>();
        squaresY = new ArrayList<>();

        ImageIcon ImageIconSquare = new ImageIcon("square.png");
        square = ImageIconSquare.getImage();
        ImageIcon ImageIconBack = new ImageIcon("back.png");
        back = ImageIconBack.getImage();
        ImageIcon ImageIconGameover = new ImageIcon("gameover.png");
        gameover = ImageIconGameover.getImage();

        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame()
    {
        countSquares = 5;
        stick = new Stick();
        timer = new Timer(600, this);
        timer.start();
    }

    public void move()
    {

        if (globalCount == 1)
            stick.move(left, right);
        if (globalCount == 2)
            letterG.move(left, right);
        if (globalCount == 3)
            letterL.move(left, right);

        right = false;
        left = false;
        down = false;
    }
    public void check()
    {
        setArrays();

        for (int j = 0; j < arrayX.size(); j++)
        {
            for (int i = 0; i < squaresX.size(); i++)
            {

                for (int k = 0; k < arrayX.get(j).size(); k++)
                {
                    if (squaresY.get(i).intValue() == arrayY.get(j).get(k).intValue() - 16 && squaresX.get(i).intValue() == arrayX.get(j).get(k).intValue())
                    {
                        getEnd = true;
                    }
                }
            }
        }


        if (squaresY.get(0) == 304 || squaresY.get(squaresY.size() - 1) == 304)
        {
            getEnd = true;
        }

        for (int y = 304; y >=48 ;)
        {
            int count1 = 0;
            for (int i = 0; i < arrayY.size(); i++)
            {
                for (int j = 0; j < arrayY.get(i).size(); j++)
                {
                    if (arrayY.get(i).get(j) == y)
                        count1++;
                }
            }
            if (count1 >= 20)
            {
                //удаляем
                for (int k = 0; k < arrayY.size(); k++)
                {
                    for (int j = 0; j < arrayY.get(k).size(); j++)
                    {
                        if (arrayY.get(k).get(j) == y)
                        {
                            arrayY.get(k).remove(j);
                            arrayX.get(k).remove(j);
                            j--;

                        }
                    }
                }
                //cдвигаем вниз оставшиеся
                for (int k = 0; k < arrayY.size(); k++)
                {
                    for (int j = 0; j < arrayY.get(k).size(); j++)
                    {
                        if( arrayY.get(k).get(j)<=y)
                        arrayY.get(k).set(j, arrayY.get(k).get(j) + 16);
                    }
                }
            }
            y -= 16;
        }

        //gameover
        for (int i = 0;arrayY.size()!=0&& i < arrayY.get(arrayY.size()-1).size(); i++)
        {
            if (arrayY.get(arrayY.size()-1).get(i) == 48)
                gameOver = true;
        }
    }
     public void turn(int k)
     {
         if (globalCount == 1)
             stick.turn(k);
         if (globalCount == 2)
             letterG.turn(k);
         if (globalCount == 3)
             letterL.turn(k);
     }
    public void addNewFigure()
    {
        System.out.println("GLOBAL_COUNT" + globalCount);
        arrayY.add(new ArrayList<>(squaresY));
        arrayX.add(new ArrayList<>(squaresX));
        for (int i = 0; i < arrayY.size() ; i++)
        {
            for (int j = 0; j < arrayY.get(i).size(); j++)
            {
                System.out.println("Y:"+i +": "+arrayY.get(i).get(j) + " X"+i +": "+arrayX.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
        globalCount++;
        if(globalCount == 4)
        globalCount = 1;
        if (globalCount == 1)
        {
            stick = new Stick();
        }
        if (globalCount == 2)
        {
            letterG = new LetterG();
        }
        if (globalCount == 3)
        {
            letterL = new LetterL();
        }


        count = 1;
        getEnd = false;
    }

    public void setArrays()
    {
        if(globalCount == 1)
        {
            squaresY = stick.getY();
            squaresX = stick.getX();
        }
        if(globalCount == 2)
        {
            squaresY = letterG.getY();
            squaresX = letterG.getX();
        }
        if(globalCount == 3)
        {
            squaresY = letterL.getY();
            squaresX = letterL.getX();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
            if (getEnd == false && gameOver == false)
            {

                check();
                if (getEnd == false)
                move();
                setArrays();
            }
            else if(gameOver == false)
            {
                addNewFigure();
            }
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(gameOver == false)
        {
            setArrays();
            g.drawImage(back, 0, 0, this);
            for (int i = 0; i < countSquares; i++)
            {
                g.drawImage(square, squaresX.get(i), squaresY.get(i), this);
            }
            for (int i = 0; i < arrayY.size(); i++)
            {
                for (int j = 0; j < arrayY.get(i).size(); j++)
                {
                    g.drawImage(square, arrayX.get(i).get(j), arrayY.get(i).get(j), this);
                }
            }
        }
        else g.drawImage(gameover, 0, 0, this);
    }
    class FieldKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT)
            {
                left = true;
                right = false;
            }
            if (key == KeyEvent.VK_RIGHT )
            {
                right = true;
                left = false;
            }
            if(key == KeyEvent.VK_SPACE)
            {
                turn(count);
                count++;
                if(count == 5)
                    count = 1;
            }
            /*if(key == KeyEvent.VK_DOWN)
            {
                down = true;
            }*/

        }
    }
}

