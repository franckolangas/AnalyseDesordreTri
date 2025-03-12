package model.algo;

public interface StrategieTri {
    public void trier(int[] array); // Passe le contexte pour notifier les échanges
    public int getNbComparison();
    public int getNbAssignement();
    public int getNbDataAccess();
    public void resetStat();
    public void setPauseTime(int time);
    public int getPauseTime();
    public void setSortStatus(boolean b);
}
