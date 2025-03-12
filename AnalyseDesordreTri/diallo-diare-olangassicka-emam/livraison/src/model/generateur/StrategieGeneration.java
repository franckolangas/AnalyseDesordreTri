package model.generateur;

import java.util.List;

public interface StrategieGeneration {
    // methode qui generera le desordre dans la liste 
    public List<Integer> generateList();
    // Méthode pour obtenir la liste desordonnee générée sous forme de tableau
    int[] getList();
    // methode pour obtenir la liste avant desordre 
    public List<Integer> getListeInitiale();
}
