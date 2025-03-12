package controler;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModeleEcoutable implements ModeleEcoutable{
    
    private final List<EcouteurModele> listeners = new ArrayList<>();
    
    // Méthode pour enregistrer un écouteur
    @Override
    public void addModelListener(EcouteurModele listener) {
        listeners.add(listener);
    }
    @Override
    public void removeModelListener(EcouteurModele l) {
        this.listeners.remove(l);
        
    }
    
    // Méthode pour notifier les écouteurs lorsque le modèle change
    public void fireChange() {
        for (EcouteurModele ecouteurModele : listeners) {
            ecouteurModele.somethingHasChanged(this);
        }
    }
    
    public boolean hasListening() {
		return listeners.size() > 0;
	}
}
