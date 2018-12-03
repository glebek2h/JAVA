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
    private int[] squaresX;
    private int[] squaresY;
    private ArrayList<int[]> arrayX;
    private ArrayList<int[]> arrayY;
    private int NUM = 0;

    private int countSquares;
    private Timer timer;
    private boolean left;
    private boolean right;
    private boolean getEnd;
    private boolean gameOver;
    private int count = 0;
    private int globalCount = 0;
    private int checkY = 304;

    Stick stick;
    LetterG letterG;
    public GameField()
    {

        arrayX = new ArrayList<>();
        arrayY = new ArrayList<>();

        squaresX = new int[MAX_SQUARES];
        squaresY = new int[MAX_SQUARES];

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
        System.out.println("GLOBAL_COUNT" + globalCount);
        countSquares = 5;
        //160+16+16
        /*for (int i = 0; i < countSquares; i++)
        {
            squaresX[i] = 176 - i * PIXEL_SIZE;
            squaresY[i] = 48;
        }*/
        stick = new Stick();
        timer = new Timer(300, this);
        timer.start();
    }

    public void move()
    {
        if(globalCount%2 ==0)
        stick.move(left,right);
        else
            letterG.move(left,right);
        right = false;
        left = false;
    }
    public void check()
    {
        if(globalCount%2==0)
        {
            squaresY = stick.getY();
            squaresX = stick.getX();
        }
        else
        {
            squaresY = letterG.getY();
            squaresX = letterG.getX();
        }

        for (int count = 0; count < arrayY.size(); count++)
        {
            for (int i = 0; i < countSquares; i++)
            {
                for (int j = 0; j < countSquares; j++)
                {
                    if (squaresY[i] == arrayY.get(count)[j] - 16 && squaresX[i] == arrayX.get(count)[j])
                        getEnd = true;
                }
            }
        }
        for (int i = 0; i < countSquares; i++)
        {
            if (squaresY[i] == 36)
                gameOver = true;
        }
        if (getEnd == true)
        {
            for (int i = 0; i < countSquares; i++)
            {
                squaresY[i] -= 16;
            }
        }
        if (squaresY[0] == 288 || squaresY[countSquares - 1] == 288)
        {
            getEnd = true;
        }
        int count;

        count = 0;
        for (int i = 0; i < arrayY.size(); i++)
        {
            for (int j = 0; j < countSquares; j++)
            {
                if (arrayY.get(i)[j] == 304)
                    count++;
            }
        }
        if (count == 20)
        {
            for (int i = 0; i < arrayY.size(); i++)
            {

                int temp[] = new int[countSquares];
                for (int j = 0; j < countSquares; j++)
                {
                    temp[j] = arrayY.get(i)[j] + 16;
                }
                arrayY.set(i, temp);
            }
        }

    }
     public void turn(int k)
     {
         if (globalCount % 2 == 0)
             stick.turn(k);
         else
             letterG.turn(k);
     }
    public void addNewFigure()
    {
        if (globalCount % 2 == 0)
        {
            arrayY.add(Arrays.copyOf(stick.getY(), countSquares));
            arrayX.add(Arrays.copyOf(stick.getX(), countSquares));
        } else
        {
            arrayY.add(Arrays.copyOf(letterG.getY(), countSquares));
            arrayX.add(Arrays.copyOf(letterG.getX(), countSquares));

        }
        arrayY.add(Arrays.copyOf(stick.getY(), countSquares));
        arrayX.add(Arrays.copyOf(stick.getX(), countSquares));
        globalCount++;
        if (globalCount % 2 == 0)
            stick = new Stick();
        else
            letterG = new LetterG();

        count = 0;

        getEnd = false;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
            if (getEnd == false && gameOver == false)
            {
                check();
                move();
                repaint();
            }
            else if(gameOver == false)
            {
                repaint();
                addNewFigure();
            }

    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(gameOver == false)
        {
            int[] squaresY = new int[countSquares] ;
            int[] squaresX  = new int[countSquares] ;
            if (globalCount % 2 == 0)
            {
                squaresY = stick.getY();
                squaresX = stick.getX();
            }
            else if(globalCount!=0)
            {
                squaresY = letterG.getY();
                squaresX = letterG.getX();
            }
            g.drawImage(back, 0, 0, this);
            for (int i = 0; i < countSquares; i++)
            {
                g.drawImage(square, squaresX[i], squaresY[i], this);
            }

            for (int i = 0; i < arrayY.size(); i++)
            {
                for (int j = 0; j < countSquares; j++)
                {
                    g.drawImage(square, arrayX.get(i)[j], arrayY.get(i)[j], this);
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
            }
        }
    }
}

