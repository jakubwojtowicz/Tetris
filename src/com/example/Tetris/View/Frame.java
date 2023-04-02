package com.example.Tetris.View;

import javax.swing.*;
import java.awt.*;

/** Ramka otaczającą planszę w widoku aplkacji.
 *@author Wójtowicz Jakub
 *@version 1.0
 */
public class Frame extends JPanel {
    /**
     * Ustawia wielkość oraz widoczność ramki.
     */
    public Frame()
    {
        this.setSize(360,690);
        this.setVisible(true);
    }

    /**
     * Metoda rysująca ramkę.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));

        for(int i = 0; i < 12; i ++)
        {
            for(int j = 0; j< 22; j++)
            {
                if(i == 0 || i == 11 || j == 0 || j ==21) {
                    g2D.setColor(new Color(100, 100, 100));
                    g2D.fillRect(i * 30, j * 30, 30, 30);
                    g2D.setColor(new Color(0, 0, 0));
                    g2D.drawRect(i * 30, j * 30, 30, 30);
                }
            }
        }
    }
}
