package co.edu.uniquindio.electroquindio.main;

import co.edu.uniquindio.electroquindio.persistencia.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Persistencia persistencia = new Persistencia();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DispositivoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 761, 542);
        stage.setTitle("ElectroQuindío");
        stage.setScene(scene);
        stage.show();

        persistencia.guardarArchivoLog("Se ha cargado la aplicación", 1, "Se ha cargado la aplicación exitosamente.");

    }

    public static void main(String[] args) {
        launch();
    }
}