package monitoreo.modelos.interfaces;

import com.esri.arcgisruntime.mapping.view.Graphic;

public interface IGrafico {
 
    void mover(Integer x, Integer Y);

    Graphic getGrafico();
}
