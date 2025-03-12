package vue;

import model.*;
import model.algo.*;
import model.generateur.*;
import controler.EcouteurModele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

import javax.swing.event.*;
import java.util.HashMap;
import java.util.Map;

public class VueTri extends JPanel implements EcouteurModele {

    // private ContexteTri strategyTri;
    private ModelTri controller;
    private JFrame frame;
    private JPanel controlPanel;
    private JPanel graphPanel;
    private JPanel statPanel;
    // Ajout des champs de saisie pour la quantité et le degré de répartition
    private JTextField tailleField;
    private JTextField degreField;

    // private Dimension screenSize;

    protected int nbAssignement;
    protected int nbComparison;
    protected int nbDataAccess;

    private JLabel nbComparisonLabel;
    private JLabel nbAssignementLabel;
    private JLabel nbDataAccessLabel;
    private JLabel timeExecutionLabel;

    // Ajout des composants pour la sélection de la répartition et du générateur
    private JComboBox<String> generateurCombo;
    private ButtonGroup repartitionGroup;

    private JRadioButton debutButton;
    private JRadioButton millieuButton;
    private JRadioButton aleatoireButton;
    private JRadioButton finButton;

    // Ajout de la liste déroulante pour les algorithmes de tri
    private JComboBox<String> algoCombo;
    private Map<String, StrategieTri> algosMap;

    /**
     * Constructeur pour initialiser l'interface utilisateur avec la stratégie de
     * tri.
     *
     * @param strategyTri La stratégie utilisée pour configurer le tri.
     *                    Elle détermine l'ordre et la logique utilisée pour trier
     *                    les éléments.
     */
    public VueTri(ModelTri controlerTri) {
        this.controller = controlerTri;
        this.nbAssignement = controller.getNbAssignement();
        this.nbComparison = controller.getNbComparison();
        this.controller.addModelListener(this);

        setLayout(new BorderLayout());

        // Panneau pour les contrôles
        controlPanel = new JPanel();
        // controlPanel.setPreferredSize(new Dimension(0, 90));

        // Panneau pour les statistiques
        statPanel = new JPanel();

        nbComparisonLabel = new JLabel();
        nbAssignementLabel = new JLabel();
        nbDataAccessLabel = new JLabel();
        timeExecutionLabel = new JLabel();

        statPanel.add(nbComparisonLabel);
        statPanel.add(nbAssignementLabel);
        statPanel.add(nbDataAccessLabel);
        statPanel.add(timeExecutionLabel);

        // Panneau pour le graphique
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                //repaint();
                afficherGraphique(g);
                updateStatPanel();
                // controlPanel.repaint();

            }
        };


        JScrollPane scrollPane = new JScrollPane(graphPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);

        // add(graphPanel, BorderLayout.CENTER);
        generateurCombo = new JComboBox<>(
                new String[] { "Generator0", "Generator1", "GenerateurBlocTrier", "GenerateurEntropie" });
        controlPanel.add(generateurCombo);

        JLabel tailleLabel = new JLabel("Taille:");
        tailleField = new JTextField("500");
        tailleField.setPreferredSize(new Dimension(55, 20));
        controlPanel.add(tailleLabel);
        controlPanel.add(tailleField);

        JLabel degreLabel = new JLabel("Quantité de desordre:");
        degreField = new JTextField("0.7");
        degreField.setPreferredSize(new Dimension(30, 20));
        controlPanel.add(degreLabel);
        controlPanel.add(degreField);

        // Initialiser la map des algorithmes de tri disponibles et des générateurs
        algosMap = new HashMap<>();
        // Ajouter les algorithmes de tri disponibles ici (exemple)
        algosMap.put("QuickSort", new QuickSort());
        algosMap.put("InsertionSort", new model.algo.InsertionSort()); // Exemple fictif
        algosMap.put("MergeSort", new model.algo.MergeSort()); // Exemple fictif
        algosMap.put("BubbleSort", new model.algo.TriBulle()); // Exemple fictif
        algosMap.put("TimSort", new model.algo.TimSort());
        algosMap.put("HeapSort", new model.algo.HeapSort());
        algosMap.put("RadixSort", new model.algo.RadixSort());

        algoCombo = new JComboBox<>(algosMap.keySet().toArray(new String[0]));
        controlPanel.add(algoCombo);

        repartitionGroup = new ButtonGroup();
        debutButton = new JRadioButton("Début");
        millieuButton = new JRadioButton("Milieu");
        finButton = new JRadioButton("Fin");
        aleatoireButton = new JRadioButton("Aléatoire", true);

        repartitionGroup.add(debutButton);
        repartitionGroup.add(millieuButton);
        repartitionGroup.add(finButton);
        repartitionGroup.add(aleatoireButton);

        controlPanel.add(debutButton);
        controlPanel.add(millieuButton);
        controlPanel.add(finButton);
        controlPanel.add(aleatoireButton);

        JLabel speedLabel = new JLabel("vitesse: ");
        JSlider speedBar = new JSlider(JSlider.HORIZONTAL, 0, 500, 30);
        // speedBar.setValue(0); // Valeur initiale
        // progressBar.setStringPainted(true); // Afficher le texte dans la barre
        speedBar.setPreferredSize(new Dimension(100, 50)); // Définir la taille de la barre
        controlPanel.add(speedLabel);
        controlPanel.add(speedBar);

        speedBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = speedBar.getValue(); // Obtenir la valeur actuelle du slider
                speedLabel.setText("vitesse: " + value); // Mettre à jour l'étiquette
                controller.setPauseTime(value);

            }
        });

        JButton generateButton = new JButton("Générer");
        JButton startButton = new JButton("Démarrer le Tri");
        JButton resetButton = new JButton("Arreter le Tri");
        controlPanel.add(generateButton);
        controlPanel.add(startButton);
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.SOUTH);
        add(statPanel, BorderLayout.NORTH);
        // les evenement

        // selection de l'algo de tri
        algoCombo.addActionListener(e -> {
            String selectedAlgo = (String) algoCombo.getSelectedItem();
            if (selectedAlgo != null) {
                controlerTri.setContexteTri(new ContexteTri(algosMap.get(selectedAlgo)));
            }
        });

        generateurCombo.addItemListener(e -> {
            // String selectedGenerator = (String) generateurCombo.getSelectedItem();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedGenerator = (String) generateurCombo.getSelectedItem();

                if ("GenerateurEntropie".equals(selectedGenerator)) {
                    setEnabledButton(false);
                    degreLabel.setText("Entropie:");
                } else if ("GenerateurBlocTrier".equals(selectedGenerator)) {
                    setEnabledButton(false);
                    degreLabel.setText("Quantité de desordre:");
                } else {
                    setEnabledButton(true);
                    degreLabel.setText("Quantité de desordre:");
                }
            }
        });

        generateButton.addActionListener(e -> {
            controller.resetData();
            //controller.continuSort();
            try {
                // Récupérer les valeurs saisies par l'utilisateur
                int taille = Integer.parseInt(tailleField.getText());
                double quantiteDesordre = Double.parseDouble(degreField.getText());
                // double entropieCible = Double.parseDouble(degreField.getText());

                // Identifier la répartition sélectionnée
                Repartition repartition = Repartition.DEBUT;
                if (millieuButton.isSelected()) {
                    repartition = Repartition.MILIEU;
                } else if (finButton.isSelected()) {
                    repartition = Repartition.FIN;
                } else if (aleatoireButton.isSelected()) {
                    repartition = Repartition.ALEATOIRE;
                }

                // Créer un générateur de liste avec les paramètres saisis
                String selectedGenerator = (String) generateurCombo.getSelectedItem();
                if ("Generator0".equals(selectedGenerator)) {
                    controlerTri.setContexteGeneration(
                            new ContexteGeneration(new Generator0(taille, quantiteDesordre, repartition)));

                } else if ("Generator1".equals(selectedGenerator)) {
                    controlerTri.setContexteGeneration(
                            new ContexteGeneration(new Generator1(taille, quantiteDesordre, repartition)));

                } else if ("GenerateurBlocTrier".equals(selectedGenerator)) {
                    controlerTri.setContexteGeneration(
                            new ContexteGeneration(new GeneratorBlocTrie(taille, quantiteDesordre)));
                } else if ("GenerateurEntropie".equals(selectedGenerator)) {
                    controlerTri.setContexteGeneration(
                            new ContexteGeneration(new GeneratorEntropie(taille, quantiteDesordre)));

                }
                //repaint();
                int[] generatedArray = controlerTri.getContexteGeneration().generate();
                controlerTri.setArray(generatedArray);
                // System.out.println("liste initiale :"+Arrays.toString(
                // controlerTri.getArray()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Veuillez saisir des valeurs valides pour la quantité et le degré de répartition.",
                        "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }
        });

        startButton.addActionListener(e -> {
            //controller.continuSort();
            if (controlerTri.getArray().length == 0) {
                JOptionPane.showMessageDialog(frame, "Veuillez générer un tableau avant de démarrer le tri.", "Erreur",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // generateButton.setEnabled(false);
            controlerTri.startSort();
            // generateButton.setEnabled(true);

        });

        resetButton.addActionListener(e -> {
            //tailleField.setText("");
            //degreField.setText("");
            //repartitionGroup.clearSelection();
            controller.stopSort();
           // controller.resetData();
            //controller.clearArray();

        });

    }

    public void setEnabledButton(Boolean b) {
        debutButton.setEnabled(b);
        finButton.setEnabled(b);
        millieuButton.setEnabled(b);
        aleatoireButton.setEnabled(b);
    }

    public void updateStatPanel() {
        nbComparisonLabel.setText("Nombre de comparaisons: " + controller.getNbComparison());
        nbAssignementLabel.setText("Nombre d'affectations: " + controller.getNbAssignement());
        nbDataAccessLabel.setText("Nombre d'acces aux données: " + controller.getNbDataAccess());
        timeExecutionLabel.setText("Temps d'execution: " + controller.getExecutionTime() + " "
                + controller.getContexteTri().getTimerUnit());
    }

    protected void afficherGraphique(Graphics g) {
        // Nettoyage de l'arrière-plan
        super.paintComponent(g);
        somethingHasChanged(g);
        // IMPORTANT : Ne pas appeler graphPanel.repaint() ici !
        //graphPanel.repaint();
        // Conversion en Graphics2D pour pouvoir utiliser setStroke
        Graphics2D g2d = (Graphics2D) g;

        int[] array = controller.getArray();
        int size = array.length;
        if (size == 0) {
            return;
        }else{

            int panelWidth = graphPanel.getWidth();
            int panelHeight = graphPanel.getHeight();
    
            // On définit le nombre maximal de barres à afficher en fonction de la largeur
            // du panel
            int maxBars = panelWidth;
            int barsToDisplay = size > panelWidth ? maxBars : size;
    
            int barWidth = panelWidth / barsToDisplay;
            int totalGraphWidth = barWidth * barsToDisplay;
            int xOffset = (panelWidth - totalGraphWidth) / 2;
    
            Color fillColor = new Color(86, 130, 3);
    
            // Définition de l'épaisseur de la bordure souhaitée
            int borderThickness = 1;
            // On détermine s'il y a assez de place pour dessiner la bordure :
            // si la largeur de la barre est trop petite, on n'affichera pas la bordure.
            boolean drawBorder = barWidth > borderThickness;
    
            if (drawBorder) {
                g2d.setStroke(new BasicStroke(borderThickness));
            }
    
            // Cas sans regroupement
            if (barsToDisplay == size) {
                for (int i = 0; i < size; i++) {
                    int barHeight = array[i] * panelHeight / size;
                    int x = xOffset + i * barWidth;
                    int y = panelHeight - barHeight;
    
                    // Remplissage de la barre
                    g2d.setColor(fillColor);
                    g2d.fillRect(x, y, barWidth, barHeight);
    
                    // Dessin de la bordure si la barre est assez large
                    if (drawBorder) {
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, barWidth, barHeight);
                    }
                }
            }
            // Cas avec regroupement des données
            else {
                barsToDisplay = size > panelWidth ? maxBars : size;
                int groupSize = size / barsToDisplay;
                for (int i = 0; i < barsToDisplay; i++) {
                    int sum = 0;
                    for (int j = i * groupSize; j < (i + 1) * groupSize && j < size; j++) {
                        sum += array[j];
                    }
                    int average = sum / groupSize;
                    int barHeight = average * panelHeight / size;
                    int x = xOffset + i * barWidth;
                    int y = panelHeight - barHeight;
    
                    g2d.setColor(fillColor);
                    g2d.fillRect(x, y, barWidth, barHeight);
    
                    if (drawBorder) {
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, barWidth, barHeight);
                    }
                }
            }

        }

    }

    @Override
    public void somethingHasChanged(Object source) {
        graphPanel.repaint();
    }

}

// package vue;

// import javax.swing.*;
// import java.awt.*;
// import controler.*;
// import model.ControlerTri;
// import model.algo.*;

// public class VueTri extends JPanel implements EcouteurModele {
// private ControlerTri controlerTri;
// private JComboBox<String> algoComboBox;
// private JComboBox<String> dataComboBox;
// private JButton startButton, resetButton, generateButton;
// private JPanel graphPanel, controlPanel;

// public VueTri(ControlerTri controler) {
// this.controlerTri = controler;
// this.controlerTri.addModelListener(this);

// setLayout(new BorderLayout());

// // Panneau pour le graphique
// graphPanel = new JPanel() {
// @Override
// protected void paintComponent(Graphics g) {
// super.paintComponent(g);
// drawArray(g);
// }
// };
// graphPanel.setPreferredSize(new Dimension(600, 300));
// add(graphPanel, BorderLayout.CENTER);

// // Panneau pour les contrôles
// controlPanel = new JPanel();
// controlPanel.setLayout(new FlowLayout());

// algoComboBox = new JComboBox<>(new String[]{"QuickSort", "MergeSort",
// "BubbleSort"});
// dataComboBox = new JComboBox<>(new String[]{"Aléatoire", "Presque trié",
// "Inverse"});

// generateButton = new JButton("Générer");
// startButton = new JButton("Démarrer");
// resetButton = new JButton("Réinitialiser");

// controlPanel.add(new JLabel("Algorithme :"));
// controlPanel.add(algoComboBox);
// controlPanel.add(new JLabel("Données :"));
// controlPanel.add(dataComboBox);
// controlPanel.add(generateButton);
// controlPanel.add(startButton);
// controlPanel.add(resetButton);

// add(controlPanel, BorderLayout.SOUTH);
// }

// private void drawArray(Graphics g) {
// int[] array = controlerTri.getArray();
// if (array != null) {
// int width = getWidth();
// int height = getHeight();
// int barWidth = width / array.length;
// for (int i = 0; i < array.length; i++) {
// int barHeight = (int) ((double) array[i] / array.length * height);
// g.fillRect(i * barWidth, height - barHeight, barWidth, barHeight);
// }
// }
// }

// @Override
// public void somethingHasChanged(Object source) {
// graphPanel.repaint();
// }
// }
