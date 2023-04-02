package com.example.Tetris.View;
import com.example.Tetris.Model.Game;
import com.example.Tetris.Model.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Główne okno aplikacji.
 * Łączy panele planszy, gry oraz wyswietla napisy.
 * @author Jakub Wójtowicz
 * @version 1.0
 */
public class GUI extends JFrame {
    /**
     * Widok planszy.
     */
    private Playfield playfield;
    /**
     * Napis wyświetlający aktualną ilość zdobytych punktów.
     */
    private JLabel labelScore;
    /**
     * Napis wyświetlający informacje o końcu gry.
     */
    private JLabel labelGameOver;

    /**
     * Metoda tworząca GUI aplikacji.
     * Ustawia planszę gry, ramkę, napisy oraz parametry okna programu.
     * @param game
     */
    public GUI(Game game)
    {
        this.setName("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600 ,755);
        this.setLocation(300,50);
        this.setLayout(null);
        playfield = new Playfield();
        Frame frame = new Frame();

        playfield.setLocation(60,60);
        frame.setLocation(30,30);

        this.add(playfield);
        this.add(frame);

        this.addKeyListener(game);

        labelScore = new JLabel("Score: ");
        labelScore.setFont(new Font("Arial", Font.PLAIN, 30));
        labelScore.setBounds(435, 50, 200, 50);
        this.add(labelScore);

        labelGameOver = new JLabel("Game over!");
        labelGameOver.setFont(new Font("Arial", Font.PLAIN, 35));
        labelGameOver.setBounds(395, 110, 400, 100);
        labelGameOver.setForeground(Color.ORANGE);
        this.add(labelGameOver);
        labelGameOver.setVisible(false);

        this.setVisible(true);

    }

    /**
     * Metoda służąca do odświeżenia widoku planszy.
     * @param board
     * @param score
     */
    public void Refresh(Board board, int score)
    {
        this.playfield.setBoard(board);
        this.playfield.repaint();
        this.labelScore.setText("Score: " + score);
    }

    /**
     * Metoda służąca do ustawienia napisu informującego o końcu gry.
     * @param text
     * @param visible
     */
    public void setGameOverLabel(String text, boolean visible)
    {
        this.labelGameOver.setText(text);
        this.labelGameOver.setVisible(visible);
    }
}
