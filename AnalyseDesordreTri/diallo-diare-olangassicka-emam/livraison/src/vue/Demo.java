package vue;
    
import model.ModelTri;
import model.algo.*;
import model.generateur.ContexteGeneration;
import model.generateur.*;
import model.generateur.Repartition;

public class Demo {
        
    public static void main(String[] args) {
        
        ModelTri controler = new ModelTri(new ContexteTri(new QuickSort()), new ContexteGeneration(new Generator0(300,0.7,Repartition.ALEATOIRE)));
        new TriGui(controler);
    }
}
