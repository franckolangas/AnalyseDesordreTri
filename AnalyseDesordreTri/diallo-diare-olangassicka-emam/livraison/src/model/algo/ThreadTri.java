package model.algo;

import model.ModelTri;

public class ThreadTri implements Runnable{
    protected ContexteTri strategieTri;
    protected int[] array ;
   // protected ControlerTri controlerTri;
    
    public ThreadTri (ContexteTri strategieTri,int[] array, ModelTri controlerTri){
        this.strategieTri = strategieTri;
        this.array = array;
        //this.controlerTri = controlerTri;
    }

    @Override
    public void run() {
        this.strategieTri.trierListe(array);
    }
    
}
