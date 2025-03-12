package experience.automatisation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Resultats {
    private static final String CHEMIN_FICHIER = "experience/stockage/resultats.csv";
    private static FileWriter writer = null;
    
    private String algo;
    private int taille;
    private int desordre;
    private String repartition;
    private String generateur;

    public Resultats(String generateur, String algo, int taille, int desordre, String repartition) throws IOException {
        this.generateur=generateur;
        this.algo = algo;
        this.taille = taille;
        this.desordre = desordre;
        this.repartition = repartition;

        // Vérifier si le répertoire existe, sinon le créer
        File repertoire = new File("experience/stockage");
        if (!repertoire.exists()) {
            repertoire.mkdirs();
        }
        

        // Vérifier si le fichier existe déjà
        File fichier = new File(CHEMIN_FICHIER);
        boolean fichierExiste = fichier.exists();

        // Initialiser le FileWriter en mode append (ajout)
        if (writer == null) {
            writer = new FileWriter(fichier, true);

            // Si le fichier n'existe pas, ajouter l'en-tête
            if (!fichierExiste) {
                writer.write("Generateurs,Algorithme,Taille,Désordre,Répartition,Comparaisons,Temps,Affectations,Accès_aux_données\n");
            }
        }
    }

    public void enregistrerResultats(int nbComparaison, double tempsExecution, int nbreAffectation, int nbAccesMemoire) throws IOException {
        Locale locale = Locale.US;  
        String ligne = String.format(locale, "%s,%s,%d,%d,%s,%d,%.5f,%d,%d\n",
                                     generateur,algo, taille, desordre, repartition, nbComparaison, tempsExecution, nbreAffectation, nbAccesMemoire);
        writer.write(ligne);
               
    }

    // Fermer le fichier après utilisation (ex. : en fin de programme)
    public static void fermer() throws IOException {
        if (writer != null) {
            writer.close();
            writer = null;  // Remettre à null pour éviter une réouverture incorrecte
        }
    }
}
