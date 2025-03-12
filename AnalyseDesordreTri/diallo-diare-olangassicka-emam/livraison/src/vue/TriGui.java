package vue;

import javax.swing.*;

import model.*;

import java.awt.*;

/**
 * VueTri est la classe représentant l'interface graphique pour visualiser les étapes d'un algorithme de tri.
 */
public class TriGui extends JFrame  {
    public TriGui(ModelTri controlerTri) {
        this.setTitle("Algo de Tri");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenRect = ge.getMaximumWindowBounds();
        int screenWidth = screenRect.width;
        int screenHeight = screenRect.height;
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new BorderLayout());
        VueTri vueTri = new VueTri(controlerTri);
        this.add(vueTri, BorderLayout.CENTER);
        //this.pack();
        this.setVisible(true);

        setLayout(new BorderLayout());

    }
    
    
}
