package com.example.Tetris.Model;
import java.awt.*;

/**
 * Plansza Tetrisa o wymiarach 20x10.
 * @author Jakub Wójtowicz
 * @version 1.0
 */
public class Board {
    /**
     * Ilość kolumn i wierszy planszy.
     */
    private int Xsize = 10;
    private int Ysize = 20;
    /**
     * Plansza, która jest tablicą składająca się z obiektów klasy Squere (pojedyncze pole).
     */
    private Squere[][] board;

    /**
     * Metoda zwracająca planszę.
     * @return
     */
    public Squere[][] getBoard() {
        return board;
    }

    /**
     * Metoda ustawiająca planszę.
     * @param board
     */
    public void setBoard(Squere[][] board) {
        this.board = board;
    }

    /**
     * Metoda ustawiająca pojedyncze pole planszy.
     * @param row
     * @param col
     * @param isTetromino
     * @param color
     */
    public void setSquere(int row, int col, boolean isTetromino, Color color)
    {
        this.board[row][col].setIsTetromino(isTetromino);
        this.board[row][col].setColor(color);
    }

    /**
     * Metoda inicjalizująca planszę.
     */
    public Board()
    {
        EmptyBoardInitialize();
    }

    /**
     * Zapełnienie planszy pustymi polami.
     */
    private void EmptyBoardInitialize()
    {
        board = new Squere[Xsize][Ysize];
        for(int i = 0; i < Xsize; i++)
        {
            for(int j = 0; j < Ysize; j++)
            {
                board[i][j] = new Squere(false,new Color(0,0,0));
            }
        }
    }
}
