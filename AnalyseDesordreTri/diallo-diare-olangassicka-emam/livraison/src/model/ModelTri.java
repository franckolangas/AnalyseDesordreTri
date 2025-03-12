package model;

import controler.AbstractModeleEcoutable;
import controler.EcouteurModele;
import model.algo.ContexteTri;
import model.algo.ThreadTri;
import model.generateur.ContexteGeneration;

public class ModelTri extends AbstractModeleEcoutable implements EcouteurModele{

    protected  ContexteTri contexteTri;
    protected ContexteGeneration contexteGeneration;
    protected int[] array ;
    // protected int arraySize;
    // protected int delaisAnnimation = 0;


    public ModelTri(ContexteTri contexteTri,ContexteGeneration contexteGeneration) {
        this.contexteTri = contexteTri;
        this.contexteGeneration = contexteGeneration;
        this.array = contexteGeneration.generate();
        
        
    }
    
    public void startSort() {
        contexteTri.getStrategieTri().resetStat();
        contexteTri.resetExecutionTime();
        ThreadTri threadT = new ThreadTri(contexteTri, array, this);
        new Thread(threadT).start();
        
    }

    public void startExperimentation(){
        contexteTri.trierListe(array);
        System.out.println(contexteTri.getExecutionTime());
    }

    public int[] getArray() {
        return array;
        
    }
    public void resetData(){
        getContexteTri().getStrategieTri().resetStat();
        getContexteTri().resetExecutionTime();
        fireChange();
    }

    public ContexteGeneration getContexteGeneration() {
        return contexteGeneration;
    }

    public ContexteTri getContexteTri() {
        return contexteTri;
        
    }

    public void setArray(int[] array) {
        this.array = array;
        fireChange();
    }

    public void clearArray(){
        int []vide  = {};
        this.array = vide;
        fireChange();
    }

    public void setContexteGeneration(ContexteGeneration contexteGeneration) {
        this.contexteGeneration = contexteGeneration;
        fireChange();
    }

    public void setContexteTri(ContexteTri contexteTri) {
        this.contexteTri = contexteTri;
        fireChange();
    }

    public double getExecutionTime(){
        return contexteTri.getExecutionTime();
    }

    public int getNbAssignement() {
        return contexteTri.getStrategieTri().getNbAssignement();
    }

    public int getNbComparison() {
        return contexteTri.getStrategieTri().getNbComparison();
    }

    public int getNbDataAccess(){
        return contexteTri.getStrategieTri().getNbDataAccess();
    }

    public void setPauseTime(int time){
        contexteTri.getStrategieTri().setPauseTime(time);
        fireChange();
    }
    public void stopSort(){
        this.contexteTri.getStrategieTri().setSortStatus(false);
        fireChange();
    }
    public void continuSort(){
        this.contexteTri.getStrategieTri().setSortStatus(true);
        fireChange();
    }
    

    @Override
    public void somethingHasChanged(Object source) {
        this.fireChange();

    }
}
