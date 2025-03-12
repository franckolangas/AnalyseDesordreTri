package model.generateur;

public class Generator1 extends AbstractGenerator {

    public Generator1(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    @Override
    protected void remplirListe() {
        for (int i = taille - 1; i >= 0; i--) {
            liste.add(i);
        }
    }

}
