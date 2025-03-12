package model.algo;

import java.util.Arrays;

import model.generateur.*;

public class Main {
    public static void main(String[] args) {
        ContexteTri contexteTri = new ContexteTri(new TimSort());
        ContexteGeneration contexteGene = new ContexteGeneration(new Generator0(11, 1, Repartition.ALEATOIRE));
        int array[] = contexteGene.generate();
        System.out.println(Arrays.toString(array));
        System.out.println("********************************************************************");
        contexteTri.trierListe(array);
        System.out.println(contexteTri.getStrategieTri().getNbAssignement());
    }
}
