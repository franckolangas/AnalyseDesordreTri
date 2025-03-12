package model.generateur;

import java.util.*;

public class GeneratorKendallTau extends AbstractGenerator {

    public GeneratorKendallTau(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    @Override
    protected void remplirListe() {
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }

    }

    public static int calculeDistanceDesordre(List<Integer> permActuelle, List<Integer> permSuivante) {
        if (permActuelle.size() != permSuivante.size()) {
            throw new IllegalArgumentException("Les deux permutations doivent avoir la même taille.");
        }
        if (!contiennentMemeElements(permActuelle, permSuivante)) {
            throw new IllegalArgumentException("Les permutations doivent contenir les mêmes éléments.");
        }

        int distanceKendallTau = 0;
        int n = permActuelle.size();

        Map<Integer, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            positionMap.put(permSuivante.get(i), i);

        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (positionMap.get(permActuelle.get(i)) > positionMap.get(permActuelle.get(j))) {
                    distanceKendallTau++;
                }
            }
        }

        return distanceKendallTau;
    }

    private static boolean contiennentMemeElements(List<Integer> list1, List<Integer> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    @Override
    public List<Integer> generateurDesordre() {
        if (liste.isEmpty()) {
            throw new IllegalStateException("La liste est vide. Veuillez générer une liste d'abord.");
        }

        int kendallTauCible = (int) Math.round((taille * (taille - 1) / 2) * quantite);
        int kendallTauActuel = calculeDistanceDesordre(listeInitiale, liste);
        Random random = new Random();

        int maxIterations = 10 * taille;
        int iterations = 0;

        System.out.println("🎯 Distance Kendall-Tau cible : " + kendallTauCible);
        System.out.println("📌 Distance Kendall-Tau actuelle avant swaps : " + kendallTauActuel);

        while (Math.abs(kendallTauActuel - kendallTauCible) > 1 && iterations < maxIterations) {
            int index1 = random.nextInt(taille);
            int index2;
            do {
                index2 = random.nextInt(taille);
            } while (index1 == index2);

            // Swap et recalcul
            Collections.swap(liste, index1, index2);
            int newKendallTau = calculeDistanceDesordre(listeInitiale, liste);

            // Vérifier si le swap rapproche de la cible
            if (Math.abs(newKendallTau - kendallTauCible) < Math.abs(kendallTauActuel - kendallTauCible)) {
                kendallTauActuel = newKendallTau;
            } else {
                // Annuler le swap s'il ne réduit pas Kendall-Tau
                Collections.swap(liste, index1, index2);
            }

            iterations++;
        }

        System.out.println("✅ Distance Kendall-Tau finale : " + kendallTauActuel);
        return liste;
    }

}
