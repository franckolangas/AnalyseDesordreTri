package model.algo;


public class ContexteTri {
    public StrategieTri strategy;
    private double tempsExecutuion;
    private String TimerUnit;
    // private int nbAssignement;
    // private int nbComparison;

    public ContexteTri(StrategieTri strategy) {
        this.strategy = strategy;
        this.tempsExecutuion = 0;
        this.TimerUnit = "";
        
        // this.nbComparison = this.strategy.getNbComparison();
        // this.nbAssignement = this.strategy.getNbAssignement();


    }

    /**
     * Méthode pour trier la liste en utilisant la stratégie définie.
     * @param array Le tableau à trier.
     */
    public void trierListe(int[] array) {
        // Démarrer le chronomètre
        long startTime = System.nanoTime();
        strategy.trier(array); // Utilisation de la stratégie de tri
        // Arrêter le chronomètre
        long endTime = System.nanoTime();
        long elapsedNano = endTime - startTime;
        double elapsedMs = elapsedNano / 1_000_000_000.0;

        // Si le temps dépasse 1 milliseconde, afficher en secondes, sinon en millisecondes
        if (elapsedMs > 1) {
            double elapsedSec = elapsedNano / 1_000_000_000.0;
            tempsExecutuion = elapsedSec;
            TimerUnit = " secondes";
        } else {
            tempsExecutuion = elapsedMs;
            TimerUnit = " millisecondes";
        }

        System.out.println("Temps d'exécution : "  + tempsExecutuion + TimerUnit);
        System.out.println("Le nombre de comparaisons est : " + strategy.getNbComparison());
        System.out.println("Le nombre de permutations est : " + strategy.getNbAssignement());
    }

    public void resetExecutionTime(){
        this.tempsExecutuion=0;
    }

    /**
     * Méthode pour définir une nouvelle stratégie de tri.
     * @param strategy La stratégie à utiliser.
     */
    public void setStrategy(StrategieTri strategy) {
        this.strategy = strategy;
    }
    public StrategieTri getStrategieTri(){
        return strategy;
    }
    
    public double getExecutionTime() {
        return tempsExecutuion;
    }
    
    public String getTimerUnit() {
        return TimerUnit;
    }
    

    
}
