package com.example.Tetris.Model;

import java.awt.*;
import java.util.Random;

/**
 * Pojedyncze tetromino zawierające losowy kształt oraz kolor.
 * @author Jakub Wójtowicz
 * @version 1.0
 */
public class Tetromino {
    /**
     * Typ tetromino.
     * Typy zaimplementowane w grze:
     * 'I','O','T','S','Z','J','L'.
     */
    private char type;
    /**
     * Kształt pojedynczego tetromino.
     * Jest to tablica pojedycznych pól.
     */
    private Squere[][] shape;
    /**
     * Wysokość i szerokość tetromino.
     */
    private int height;
    private int width;

    /**
     * Metoda zwracająca kształt tetromino.
     * @return shape
     */
    public Squere[][] getShape() {return this.shape;}

    /**
     * Metoda zwracająca wysokość tetromino.
     * @return wysokość
     */
    public int getheight() {return this.height;}

    /**
     * Metoda zwracająca szerokość tetromino.
     * @return szerokość
     */
    public int getWidth() {return this.width;}

    /**
     * Kolor tetromino.
     * Zaimplementowane kolory:
     * czerwony, zielony, niebieski, żółty, różowy, jasny niebieski, brązowy.
     */
    private Color color;

    /**
     * Metoda zwracająca kolor tetromino.
     * @return
     */
    public Color getColor() {return this.color;}

    /**
     * Tablica, z której losowany jest typ tetromino.
     */
    private char [] tetrominoTypes = {'I','O','T','S','Z','J','L'};
    /**
     * Tablica, z której losowany jest kolor tetromino.
     */
    private Color [] tetrominoColors = {new Color(255,0,0), new Color(0,255,0),
            new Color(0,0,255), new Color(255,255,0),
            new Color(255,0,255), new Color(0,255,255),
            new Color(255,165,0)};

    /**
     * Tworzy nowe tetromino.
     */
    public Tetromino() {
        RandomTetromino();
    }

    /**
     * Losuje kolor i typ tetromino oraz tworzy jego kształt.
     */
    private void RandomTetromino()
    {
        Random rand = new Random();
        this.type = tetrominoTypes[rand.nextInt(7)];
        this.color = tetrominoColors[rand.nextInt(7)];
        CreateShape();
    }

    /**
     * Metoda tworząca kształt tetromino w zależności od jego typu.
     */
    private void CreateShape()
    {
        switch(type)
        {
            case 'I':
                shape = new Squere[][]{
                        {new Squere(true, color)},
                        {new Squere(true, color)},
                        {new Squere(true, color)},
                        {new Squere(true, color)},

                };
                height = 1;
                width = 4;
                break;

            case 'O':
                shape = new Squere[][]{
                        {new Squere(true, color), new Squere(true, color)},
                        {new Squere(true, color), new Squere(true, color)}

                };
                height = 2;
                width = 2;
                break;
            case 'T':
                shape = new Squere[][]{
                        {new Squere(true, color), new Squere(true, color), new Squere(true, color)},
                        {new Squere(false, color), new Squere(true, color), new Squere(false, color)}
                };
                height = 3;
                width = 2;
                break;
            case 'S':
                shape = new Squere[][]{
                        {new Squere(false, color), new Squere(true, color), new Squere(true, color)},
                        {new Squere(true, color), new Squere(true, color), new Squere(false, color)}
                };
                height = 3;
                width = 2;
                break;
            case 'Z':
                shape = new Squere[][]{
                        {new Squere(true, color), new Squere(true, color), new Squere(false, color)},
                        {new Squere(false, color), new Squere(true, color), new Squere(true, color)}
                };
                height = 3;
                width = 2;
                break;
            case 'J':
                shape = new Squere[][]{
                        {new Squere(false, color), new Squere(true, color)},
                        {new Squere(false, color), new Squere(true, color)},
                        {new Squere(true, color), new Squere(true, color)}
                };
                height = 2;
                width = 3;
                break;
            case 'L':
                shape = new Squere[][]{
                        {new Squere(true, color), new Squere(false, color)},
                        {new Squere(true, color), new Squere(false, color)},
                        {new Squere(true, color), new Squere(true, color)}
                };
                height = 2;
                width = 3;
                break;
        }

    }

    /**
     * Metoda obracająca tetromino w lewo.
     */
    public void rotateLeft()
    {
        int m = shape.length;
        int n = shape[0].length;
        Squere[][] rotated = new Squere[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n-j-1][i] = shape[i][j];
            }
        }
        shape = rotated.clone();
        int width_copy = width;
        width = height;
        height = width_copy;
    }

    /**
     * Metoda obracjąca tetromino w prawo.
     */
    public void rotateRight()
    {
        int m = shape.length;
        int n = shape[0].length;
        Squere[][] rotated = new Squere[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m-1-i] = shape[i][j];
            }
        }
        shape = rotated.clone();
        int width_copy = width;
        width = height;
        height = width_copy;
    }
}
