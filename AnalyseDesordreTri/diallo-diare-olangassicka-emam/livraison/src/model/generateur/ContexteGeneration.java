package model.generateur;


public class ContexteGeneration {
    protected StrategieGeneration generator;

    public ContexteGeneration (StrategieGeneration generator){
        this.generator = generator;

    }


    public int[] generate() {
        if (this.generator !=null) {
            generator.generateList();
        }
        else{
            throw new IllegalArgumentException("vous n'avez pas de generateur definit ");
        }
        return generator.getList();

    }

    public void setGenerator(StrategieGeneration generator) {
        this.generator = generator;
    }
    public StrategieGeneration getGenerator() {
        return generator;
    }
    
}
