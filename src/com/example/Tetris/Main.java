package com.example.Tetris;

import com.example.Tetris.Controller.Tetris;

/**
 * Punkt wejsciowy aplikacji gry Tetris.
 * Gra zostala zrealizowana z wykorzystaniem struktury MVC.
 * @author Jakub Wojtowicz
 * @version 1.0
 */
public class Main
{
    /**
     * Metoda wejściowa aplikacji. Służy do utworzenia i rozpoczęcia nowej gry Tetris.
     * @param args
     */
    public static void main(String[] args)
    {
        //Utworzenie nowej gry
        Tetris tetris = new Tetris();
        //Uruchomienie gry
        tetris.MainLoop();
    }
}

