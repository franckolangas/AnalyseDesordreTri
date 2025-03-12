package model.generateur;



public class Generator0 extends AbstractGenerator {

    public Generator0(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    @Override
    protected void remplirListe() {
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }
    }
    
}
