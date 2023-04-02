package com.example.Tetris.Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Klasa, która implementuje logikę gry oraz obsługuje sterowanie za pomocą klawiatury.
 *@author Wójtowicz Jakub
 *@version 1.0
 */
public class Game implements KeyListener {
    /**
     * Plansza gry.
     */
    private Board board;
    /**
     * Metoda zwracająca aktualny stan planszy.
     * @return plansza
     */
    public Board getBoard() {return this.board;}
    /**
     * Aktualnie spadające tetromino w grze.
     */
    private Tetromino currentTetromino;
    /**
     * Poprzednia pozycja tetromino.
     */
    private Point oldTetrominoPostion;
    /**
     * Aktualna pozycja tetromino.
     */
    private Point newTetrominoPostion;
    /**
     * Ilość zdobytych punktów.
     */
    private int score;
    /**
     * Metoda zwracająca ilość punktów.
     * @return ilość punktów
     */
    public int getScore(){return this.score;}
    /**
     * Zmienna określająca, czy tetromino zostało zatrzymane.
     */
    private boolean isTetrominoStopped;
    /**
     * Metoda zwracająca informację, czy tetromino zostało zatrzymane.
     */
    public boolean IsTetrominoStopped()
    {
        return this.isTetrominoStopped;
    }
    /**
     * Normalna prędkość spadania tetromino.
     */
    private int normalSpeed;
    /**
     * Zwiekszona predkosc spadania tetromino.
     */
    private int highSpeed;
    /**
     * Ustawiona prędkość spadania tetromino.
     */
    private int speed;
    /**
     * Metoda zwracająca aktualną prędkość spadania tetromino.
     * @return prędkość
     */
    public int getSpeed() {return speed;}

    /**
     * Tworzy nową planszę oraz ustawia wartości początkowe parametrów gry.
     */
    public Game()
    {
        board = new Board();
        score = 0;
        normalSpeed = 500;
        highSpeed = 50;
        speed = normalSpeed;
    }
    /**
     * Tworzy nowe tetromino.
     */
    public void NewTetromino() {
        this.currentTetromino = new Tetromino();
        isTetrominoStopped = false;
        oldTetrominoPostion = new Point(3,-currentTetromino.getheight());
        newTetrominoPostion = new Point(3,-currentTetromino.getheight());

    }
    /**
     *  Metoda realizująca spadanie tetromino o jeden poziom.
     *  Sprawdza, czy tetromino zostało zastopowane oraz usuwa linie.
     */
    public void Step()
    {
        if(isTetrominoStopped() == true)
        {
            ClearLines();
            isTetrominoStopped = true;
        }
        else
        {
            oldTetrominoPostion = (Point) newTetrominoPostion.clone();
            newTetrominoPostion.y += 1;
            DeleteTetromino();
            InsertTetromino();
        }
    }

    /**
     * Metoda sprawdzająca, czy tetromino zostało zatrzymane na swojej drodze.
     * @return true -> tetromino zastopowane; false -> tetromino nie zostało zastopowane
     */
    private boolean isTetrominoStopped()
    {
        //jezeli tetromino jest na ostatnim poziomie
        if(newTetrominoPostion.y + currentTetromino.getheight() -1 == 19)
        {
            return true;
        }
        //dla wszystkich pol z tetromino sprawdzane jest polozenie pola wzgledem aktualnej planszy
        for (int x = 0; x < currentTetromino.getShape().length; x++) {
            for (int y = 0; y < currentTetromino.getShape()[0].length; y++) {

                int boardY = newTetrominoPostion.y + y + 1;
                int boardX = newTetrominoPostion.x + x;

                //jezeli sprawdzane pole poza planszą
                if(boardY < 0 || boardX >= 10 || boardY > 19 || boardX < 0)
                {
                    continue;
                }

                //jezeli sprawdzane pole stanowi tetromino
                if(y+1 < currentTetromino.getShape()[0].length) {
                    if (currentTetromino.getShape()[x][y + 1].getIsTetromino() == true) {
                        continue;
                    }
                }

                //jezeli rozaptrywane pole tetromino to blok
                if(currentTetromino.getShape()[x][y].getIsTetromino() == true)
                {
                    //jezeli tetromino dotyka innego bloku na planszy
                    if (board.getBoard()[boardX][boardY].getIsTetromino() == true)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Metoda usuwająca tetromino z planszy.
     */
    private void DeleteTetromino()
    {
        for(int i = 0; i < currentTetromino.getShape().length; i++)
        {
            for(int j = 0; j < currentTetromino.getShape()[i].length; j++)
            {
                if(currentTetromino.getShape()[i][j].getIsTetromino() == true) {
                    if(j+oldTetrominoPostion.y > -1 && i + oldTetrominoPostion.x < 10) {
                        if (board.getBoard()[i + oldTetrominoPostion.x][j + oldTetrominoPostion.y].getIsTetromino() == true) {
                            board.setSquere(i + oldTetrominoPostion.x, j + oldTetrominoPostion.y, false, new Color(0,0,0));
                        }
                    }
                }
            }
        }
    }

    /**
     * Metoda wstawiająca tetromino na planszę.
     */
    private void InsertTetromino()
    {
        for(int i = 0; i < currentTetromino.getShape().length; i++)
        {
            for(int j = 0; j < currentTetromino.getShape()[i].length; j++)
            {
                if(currentTetromino.getShape()[i][j].getIsTetromino() == true) {
                    if (j + newTetrominoPostion.y > -1 && i + newTetrominoPostion.x < 10) {
                        board.setSquere(i + newTetrominoPostion.x, j + newTetrominoPostion.y, true, currentTetromino.getColor());
                    }
                }
            }
        }
    }

    /**
     * Metoda sprawdzająca, czy któryś wiersz planszy jest zapełniony przez bloki i wywołuje metodę czyszczenia linii.
     */
    private void ClearLines()
    {
        for(int row = 0; row< 20; row++)
        {
            if(CheckLine(row) == true)
            {
                ClearLine(row);
            }
        }
    }

    /**
     * Metoda sprawdzająca czy pojedyncza linia na planszy jest zapełniona przez bloki.
     * @param row
     * @return true -> linia jest zapełniona;
     *         false -> linia nie jest zapełniona
     */
    private boolean CheckLine(int row)
    {
        for(int col = 0; col< 10; col++)
        {
            if (board.getBoard()[col][row].getIsTetromino() != true)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Metoda czyszcząca pojedynczą linię planszy.
     * @param row
     */
    private void ClearLine(int row)
    {
        score += 1;
        normalSpeed -= 10; //acceleration
        for(int col = 0; col < 10; col ++)
        {
            board.setSquere(col,row, false, new Color(0,0,0));
        }
        Gravity(row);
    }

    /**
     * Realizuje spadek bloków o jeden stopień ponad wyczyszczoną linią.
     * @param clearedLine
     */
    private void Gravity(int clearedLine)
    {
        for(int row = clearedLine; row > 0; row--)
        {
            for (int col = 0; col < 10; col++) {
                if(row - 1 > 0 && row -1 < 20) {
                    board.setSquere(col, row, board.getBoard()[col][row - 1].getIsTetromino(), board.getBoard()[col][row - 1].getColor());
                }
            }
        }
    }

    /**
     * Metoda sprawdzająca, czy koniec gry.
     * @return true -> koniec gry;
     *         false -> nie koniec gry
     */
    public boolean IsGameOver()
    {
        if(isTetrominoStopped == true)
        {
            for(int i =0; i< 10; i++)
            {
                if(board.getBoard()[i][0].getIsTetromino()==true)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metoda czyszcząca całą planszę.
     */
    public void ClearBoard()
    {
        board = new Board();
    }

    /**
     * Metoda resetująca wynik gry.
     */
    public void ResetScore()
    {
        score = 0;
    }

    /**
     * Porusza tetromino o jeden blok w lewo.
     */
    private void MoveTetrominoLeft()
    {
        if(newTetrominoPostion.x > 0 && !CheckLeftMove())
        {
            oldTetrominoPostion = (Point) newTetrominoPostion.clone();
            newTetrominoPostion.x -= 1;
            DeleteTetromino();
            InsertTetromino();
        }
    }

    /**
     * Sprawdza, czy tetromino może przesunąć się w lewo.
     * @return true -> może się przesunąć; false -> nie może się przesunąć
     */
    private boolean CheckLeftMove()
    {
        for (int y = 0; y < currentTetromino.getheight(); y++)
        {
            //jezeli rozaptrywane pole tetromino to blok
            if(currentTetromino.getShape()[0][y].getIsTetromino() == true)
            {
                if(newTetrominoPostion.x - 1 > -1 && newTetrominoPostion.y+y < 20 &&  newTetrominoPostion.y+y > 0)
                {
                    // jezeli tetromino jest zablokowane przez inne tetromino
                    if (board.getBoard()[newTetrominoPostion.x -1][newTetrominoPostion.y+y].getIsTetromino() == true)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Porusza tetromino o jeden blok w prawo.
     */
    private void MoveTetrominoRight()
    {
        if(newTetrominoPostion.x + currentTetromino.getWidth()  < 10 && !CheckRightMove()) {
            oldTetrominoPostion = (Point) newTetrominoPostion.clone();
            newTetrominoPostion.x += 1;
            DeleteTetromino();
            InsertTetromino();
        }
    }

    /**
     * Sprawdza, czy tetromino może przesunąć się w prawo.
     * @return true -> może się przesunąć; false -> nie może się przesunąć
     */
    private boolean CheckRightMove()
    {
        for (int y = 0; y < currentTetromino.getheight(); y++)
        {
            //jezeli rozaptrywane pole tetromino to blok
            if(currentTetromino.getShape()[currentTetromino.getWidth()-1][y].getIsTetromino() == true)
            {
                if(newTetrominoPostion.x + currentTetromino.getWidth() < 10 && newTetrominoPostion.y+y < 20 &&  newTetrominoPostion.y+y > 0)
                {
                    // jezeli tetromino jest zablokowane przez inne tetromino
                    if (board.getBoard()[newTetrominoPostion.x +currentTetromino.getWidth()][newTetrominoPostion.y+y].getIsTetromino() == true)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Obsługuje naciskanie przycisków klawiatury.
     * @param e event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                MoveTetrominoLeft();
                break;
            case KeyEvent.VK_RIGHT:
                MoveTetrominoRight();
                break;
            case KeyEvent.VK_SPACE:
                if(IsGameOver())
                {
                    ClearBoard();
                    ResetScore();
                }
                else
                {
                    speed = highSpeed;
                }
                break;
            case KeyEvent.VK_UP:
                oldTetrominoPostion = (Point) newTetrominoPostion.clone();
                DeleteTetromino();
                currentTetromino.rotateRight();
                InsertTetromino();
                while (newTetrominoPostion.x + currentTetromino.getWidth() > 10) {
                    MoveTetrominoLeft();
                }

                break;
            case KeyEvent.VK_DOWN:
                oldTetrominoPostion = (Point) newTetrominoPostion.clone();
                DeleteTetromino();
                currentTetromino.rotateLeft();
                InsertTetromino();
                while (newTetrominoPostion.x + currentTetromino.getWidth() > 10) {
                    MoveTetrominoLeft();
                }
                break;
        }
    }

    /**
     * Obsługuje opuszcenie przcisku klawiatury.
     * @param e event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_SPACE:
                speed = normalSpeed;
                break;
        }
    }
}
