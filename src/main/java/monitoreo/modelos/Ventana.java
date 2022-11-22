package monitoreo.modelos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import monitoreo.modelos.impl.*;
//import monitoreo.modelos.interfaces.ITipoServicio;

//import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class Ventana extends Application {

    private Mapa mapa;
    //private GraphicsOverlay graphicsOverlay;

    @Override
    public void start(Stage stage) throws Exception {

        // Crea una fachada par el Mapa
        FachadaMapa facade = new FachadaMapa(stage);
        //facade.getMapaBase().imprimeCoordenadasActual();
        this.mapa = facade.getMapa();

        // Crea la imagen para el botón
        Image img = new Image("https://upload-icon.s3.us-east-2.amazonaws.com/uploads/icons/png/4498062351543238871-512.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(40);
        view.setPreserveRatio(true);

        // Crea el botón de Ventana nueva
        Button btnNuevo = new Button();
        btnNuevo.setGraphic( view );
        btnNuevo.setText("Nuevo");
        facade.getStackPane().getChildren().add(btnNuevo);
        facade.getStackPane().setAlignment(btnNuevo, Pos.BOTTOM_CENTER);
        facade.getStackPane().setMargin(btnNuevo, new Insets(10, 10, 10, 10));
        btnNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                muestraNuevaVentana();
            }
        });

        // Agrega el punto
        PuntoMonitoreoBuilder puntoBuilder = new PuntoMonitoreoBuilder("Inicio del día");
        puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);
        puntoBuilder.withUbicacion(-12.054901, -77.085470);
        Punto puntoInicial = puntoBuilder.build();

        puntoBuilder = new PuntoMonitoreoBuilder("Fin del día");
        //puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.DIAMOND, 0xFF000000, 20);
        puntoBuilder.withUbicacion(-12.055901, -77.086470);
        Punto puntoFinal = puntoBuilder.build();


        facade.addGraphicsOverlay(puntoInicial.getGrafico());
        facade.addGraphicsOverlay(puntoFinal.getGrafico());

        // Dibuja el mapa en la ventana
        facade.stackAddMapView();
        facade.mostrarVentana();
    }

    public void muestraNuevaVentana() {
        Stage stage = new Stage();
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.setTitle("Sistema de Monitoreo de Vehiculos NUEVO");
        stage.setWidth(800);
        stage.setHeight(700);

        //  Clonacion de MapaBase
        Mapa mapaBase2 = (Mapa)mapa.copiar();

        mapaBase2.imprimeCoordenadasActual();
        stackPane.getChildren().add(mapaBase2.getMapView());

        stage.show();
    }


}
