package experience;

import java.io.IOException;

import experience.automatisation.Resultats;
import model.ModelTri;
import model.algo.ContexteTri;
import model.algo.HeapSort;
import model.algo.InsertionSort;
import model.algo.QuickSort;
import model.algo.RadixSort;
import model.algo.TimSort;
import model.algo.TriBulle;
import model.algo.MergeSort;
import model.generateur.AbstractGenerator;
import model.generateur.ContexteGeneration;
import model.generateur.Generator0;
import model.generateur.Generator1;
import model.generateur.Repartition;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 5) {
            System.out.println("Usage: java -jar tri.jar <generateur> <algorithme> <taille> <desordre> <repartition>");
            return;
        }
        String generateur = args[0];
        String algo = args[1];
        int taille = Integer.parseInt(args[2]);
        int desordre = Integer.parseInt(args[3]);
        String repartitionStr = args[4];

        
        // Sélection de l'algorithme
        ContexteTri contexteTri;
        switch (algo.toLowerCase()) {
            case "tri_rapide":
                contexteTri = new ContexteTri(new QuickSort());
                break;
            case "tri_a_bulle":
                contexteTri = new ContexteTri(new TriBulle());
                break;
            case "tri_fusion":
                contexteTri = new ContexteTri(new MergeSort());
                break;
            case "tri_insertion":
                contexteTri = new ContexteTri(new InsertionSort());
            break; 
            case "tri_par_tas":
                contexteTri = new ContexteTri(new HeapSort());
            break;
            case "tri_par_base":
                contexteTri = new ContexteTri(new RadixSort());
            break;
            case "tri_hybride":
                contexteTri = new ContexteTri(new TimSort());
            break;    
            default:
                System.out.println("Erreur : Algorithme inconnu");
                return;
        }

        //Selection du generateur
        AbstractGenerator generator = null;

        switch (generateur) {
            case "Generator0":
                generator = new Generator0(taille, desordre / 100, null);
            break;
            case "Generator1":
                generator = new Generator1(taille, desordre / 100, null);
            break;
            default:
                System.out.println("generateur inconnu");
            break;
        }

       //Selection de la repartition
       switch (repartitionStr) {
        case "DEBUT":
            generator.setRepartition(Repartition.DEBUT);
        break;
        case "FIN":
            generator.setRepartition(Repartition.FIN);
        break;
        case "MILIEU":
            generator.setRepartition(Repartition.MILIEU);
        break;
        case "ALEATOIRE":
            generator.setRepartition(Repartition.ALEATOIRE);
        break;
        default:
            System.out.println("repartition inconnue");
            break;
       }
        

        // Création des contextes
        ContexteGeneration contexteGeneration = new ContexteGeneration(generator);
        ModelTri modelTri = new ModelTri(contexteTri,contexteGeneration);
        Resultats resultats = new Resultats(generateur,algo, taille, desordre, repartitionStr);
        // Lancer le tri
        modelTri.startExperimentation();

        int nbComparaison = modelTri.getNbComparison();
        System.out.println(modelTri.getNbComparison());
        double tempsExecution = modelTri.getExecutionTime();
        System.out.println(modelTri.getExecutionTime());
        int nbreAffectation = modelTri.getNbAssignement();
        int nbAccesDonnee = modelTri.getNbDataAccess();
        resultats.enregistrerResultats(nbComparaison, tempsExecution, nbreAffectation, nbAccesDonnee);
        
        Resultats.fermer();
        System.out.println("Fin du programme");
    }
}
