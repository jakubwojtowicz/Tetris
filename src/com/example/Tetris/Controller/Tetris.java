package com.example.Tetris.Controller;
import com.example.Tetris.Model.Game;
import com.example.Tetris.View.GUI;

/** Klasa, która łączy UI oraz logikę gry Tetris, a także inicjuje główną pętlę programu.
 *@author Wójtowicz Jakub
 *@version 1.0
 */
public class Tetris {
    /**
     * com.example.Tetris.Controller.Model gry.
     */
    private Game game = new Game(); /*com.example.Tetris.Controller.Model*/
    /**
     * Graficzny interfejs użytkownika.
     */
    private GUI gui = new GUI(game); /*com.example.Tetris.Controller.View*/
    /**
     * Zmienna, w której zapisywana jest wartość poprzedniego czasu.
     * Używana jest do realizacji spadania Tetromino z określoną częstotliwością.
     */
    private int counterStartTime;

    /**
     * Glowna petla gry Tetris.
     * Pętla zawiera dwie pętle, które oznaczają jedną grę oraz spadek jednego tetromino.
     */
    public void MainLoop()
    {
        //Pętla całej gry
        while(true)
        {
            //Pętla jednej gry
            while (!game.IsGameOver())
            {
                gui.setGameOverLabel("Game over!", false);
                game.NewTetromino();
                counterStartTime = (int) System.currentTimeMillis();
                //Pętla spadania pojedynczego tetromino
                while (!game.IsTetrominoStopped())
                {
                    if ((int) System.currentTimeMillis() - counterStartTime >= game.getSpeed())
                    {
                        game.Step();
                        counterStartTime = (int) System.currentTimeMillis();
                    }
                    gui.Refresh(game.getBoard(), game.getScore());
                }
            }
            gui.setGameOverLabel("Game over!", true);
        }
    }
}
