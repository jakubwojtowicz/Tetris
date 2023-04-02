package com.example.Tetris.Model;
import java.awt.*;

/**
 * Pojedyncze pole planszy oraz tetromino.
 * @author Jakub Wójtowicz
 * @version 1.0
 */
public class Squere {
    /**
     * Czy pole jest zajęte przez tetromino.
     */
    private boolean isTetromino;
    /**
     * Kolor pola.
     */
    private Color color;

    /**
     * Metoda ustawiająca pole.
     * @param isTetromino
     * @param color
     */
    public Squere(boolean isTetromino, Color color)
    {
        this.isTetromino = isTetromino;
        this.color = color;
    }

    /**
     * Metoda zwracająca informacje czy pole jest zajęte przez tetromino.
     * @return isTetromino
     */
    public boolean getIsTetromino()
    {
        return isTetromino;
    }

    /**
     * Metoda ustawiająca cczy pole jest tetromino.
     * @param isTetromino
     */
    public void setIsTetromino(boolean isTetromino)
    {
        this.isTetromino = isTetromino;
    }

    /**
     * Metoda zwracająca kolor tetromino.
     * @return kolor
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Metoda ustawiająca kolor tetromino.
     * @param color
     */
    public void setColor (Color color)
    {
        this.color = color;
    }
}
