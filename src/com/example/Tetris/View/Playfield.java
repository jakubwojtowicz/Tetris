package com.example.Tetris.View;
import com.example.Tetris.Model.Board;
import javax.swing.*;
import java.awt.*;

/**
 * Klasa, która rysuje planszę gry.
 *@author Wójtowicz Jakub
 *@version 1.0
 */
public class Playfield extends JPanel
{
    /**
     * Wysokość i szerokość planszy.
     */
    private int xSize = 10;
    private int ySize = 20;
    /**
     * Wielkość pojedynczego pola.
     */
    private int squereSize = 30;
    /**
     * Kolor pojedynczego pola.
     */
    private Color BackgroundColor = new Color(0,0,0);
    /**
     * Plansza gry.
     */
    private Board board;

    /**
     * Metoda ustawiająca planszę gry do wyświetlenia.
     * @param board
     */
    public void setBoard(Board board) {this.board = board;}

    /**
     * Metoda ustawiająca wielkość planszy, widoczność oraz inicjalizująca planszę.
     */
    public Playfield()
    {
        this.setSize(xSize*squereSize,ySize*squereSize);
        this.setVisible(true);
        board = new Board();
    }

    /**
     * Metoda rysująca aktualną planszę.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(1));

        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                if(board.getBoard()[i][j].getIsTetromino() == true)
                {
                    g2D.setColor(board.getBoard()[i][j].getColor());
                }
                else
                {
                    g2D.setColor(this.BackgroundColor);
                }
                g2D.fillRect(i*squereSize, j*squereSize, squereSize, squereSize);
                g2D.setColor(new Color(0,0,0));
                g2D.drawRect(i*squereSize, j*squereSize, squereSize, squereSize);
            }
        }
    }
}

