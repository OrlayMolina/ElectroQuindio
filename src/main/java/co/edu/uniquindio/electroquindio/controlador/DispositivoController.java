package co.edu.uniquindio.electroquindio.controlador;

import co.edu.uniquindio.electroquindio.enumm.*;
import co.edu.uniquindio.electroquindio.factory.Factory;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;
import co.edu.uniquindio.electroquindio.subcontrolador.DispositivoSubController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DispositivoController implements Initializable {

    private final ObservableList<TipoCategoria> listadoCategorias = FXCollections.observableArrayList();
    private final ObservableList<TipoMarca> listadoMarcas = FXCollections.observableArrayList();
    private final ObservableList<TipoColor> listadoColores = FXCollections.observableArrayList();
    private final ObservableList<TipoProcesador> listadoProcesadores = FXCollections.observableArrayList();
    private final ObservableList<TipoSistemaOperativo> listadoSistemaOperativo = FXCollections.observableArrayList();

    private DispositivoSubController subController;

    @FXML
    private Button btnActualizarDispositivo;

    @FXML
    private Button btnCrearDispositivo;

    @FXML
    private Button btnEliminarDispositivo;

    @FXML
    private Button btnLimpiarDatosDispositivo;

    @FXML
    private ComboBox<TipoCategoria> cmbCategoria;

    @FXML
    private ComboBox<TipoColor> cmbColor;

    @FXML
    private ComboBox<TipoMarca> cmbMarca;

    @FXML
    private ComboBox<TipoProcesador> cmbProcesador;

    @FXML
    private ComboBox<TipoSistemaOperativo> cmbSistemaOperativo;

    @FXML
    private TableColumn<Procesamiento, TipoCategoria> colCategoria;

    @FXML
    private TableColumn<Procesamiento, TipoMarca> colMarca;

    @FXML
    private TableColumn<Procesamiento, String> colMemoria;

    @FXML
    private TableColumn<Procesamiento, String> colModelo;

    @FXML
    private TableColumn<Procesamiento, String> colPantalla;

    @FXML
    private TableColumn<Procesamiento, TipoProcesador> colProcesador;

    @FXML
    private TableColumn<Procesamiento, String> colReferencia;

    @FXML
    private TableColumn<Procesamiento, TipoProcesador> colSistemaOperativo;

    @FXML
    private TableView<Procesamiento> tableDispositivos;

    @FXML
    private TextField txMemoria;

    @FXML
    private TextField txModelo;

    @FXML
    private TextField txPantalla;

    @FXML
    private TextField txPrecio;

    @FXML
    private TextField txReferencia;

    @FXML
    void actualizarDispositivo(ActionEvent event) {

    }

    @FXML
    void crearDispositivo(ActionEvent event) {
        guardarDispositivo();
    }

    public void guardarDispositivo(){
        Procesamiento procesamiento;
        Procesamiento proc = new Procesamiento();

        TipoCategoria tipoCategoria = cmbCategoria.getSelectionModel().getSelectedItem();
        TipoMarca marca = cmbMarca.getSelectionModel().getSelectedItem();
        String modelo = txModelo.getText();
        String referencia = txReferencia.getText();
        String tamanioPantalla = txPantalla.getText();
        TipoColor color = cmbColor.getSelectionModel().getSelectedItem();
        int precio = Integer.parseInt(txPrecio.getText());
        TipoProcesador procesador = cmbProcesador.getSelectionModel().getSelectedItem();
        TipoSistemaOperativo sistemaOperativo = cmbSistemaOperativo.getSelectionModel().getSelectedItem();
        String memoria = txMemoria.getText();

        proc.setCategoria(tipoCategoria);
        proc.setMarca(marca);
        proc.setModelo(modelo);
        proc.setReferencia(referencia);
        proc.setTamanioPantalla(tamanioPantalla);
        proc.setColor(color);
        proc.setPrecio(precio);
        proc.setMarcaProcesador(procesador);
        proc.setSistemaOperativo(sistemaOperativo);
        proc.setMemoria(memoria);

        procesamiento = subController.crearDispositivo(proc);

        if(procesamiento != null){
            System.out.println("Todo esta bien.");
        }else {
            System.out.println("f");
        }
    }

    public void mostrarCategoria(){
        listadoCategorias.add(TipoCategoria.CELULAR);
        listadoCategorias.add(TipoCategoria.COMPUTADOR);
        cmbCategoria.setItems(listadoCategorias);
    }

    public void mostrarMarcas(){
        listadoMarcas.add(TipoMarca.ACER);
        listadoMarcas.add(TipoMarca.APPLE);
        listadoMarcas.add(TipoMarca.ASUS);
        listadoMarcas.add(TipoMarca.HUAWEI);
        listadoMarcas.add(TipoMarca.HEWLETT_PACKARD);
        listadoMarcas.add(TipoMarca.LENOVO);
        listadoMarcas.add(TipoMarca.MOTOROLA);
        listadoMarcas.add(TipoMarca.MOTOROLA);
        listadoMarcas.add(TipoMarca.SAMSUNG);
        cmbMarca.setItems(listadoMarcas);
    }

    public void mostrarColor(){
        listadoColores.add(TipoColor.AZUL);
        listadoColores.add(TipoColor.AZUL_METALIZADO);
        listadoColores.add(TipoColor.BLANCO);
        listadoColores.add(TipoColor.DORADO);
        listadoColores.add(TipoColor.NEGRO);
        listadoColores.add(TipoColor.NEGRO_METALIZADO);
        listadoColores.add(TipoColor.PLATEADO);
        listadoColores.add(TipoColor.ROJO);

        cmbColor.setItems(listadoColores);
    }

    public void mostrarMarcarProcesamiento(){
        listadoProcesadores.add(TipoProcesador.AMD);
        listadoProcesadores.add(TipoProcesador.INTEL);
        cmbProcesador.setItems(listadoProcesadores);
    }

    public void mostrarSistemaOperativo(){
        listadoSistemaOperativo.add(TipoSistemaOperativo.ANDROID);
        listadoSistemaOperativo.add(TipoSistemaOperativo.MAC_OS);
        cmbSistemaOperativo.setItems(listadoSistemaOperativo);
    }

    /**
     *
     */
    public void datosIniciales(){
        Factory factory = Factory.getInstance();
        subController = new DispositivoSubController(factory);
        new DispositivoController();

        mostrarCategoria();
        mostrarMarcas();
        mostrarColor();
        mostrarMarcarProcesamiento();
        mostrarSistemaOperativo();
    }



    @FXML
    void eliminarDispositivo(ActionEvent event) {

    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        cmbCategoria.setValue(null);
        cmbMarca.setValue(null);
        txModelo.setText("");
        txReferencia.setText("");
        txPantalla.setText("");
        cmbColor.setValue(null);
        txPrecio.setText("");
        cmbProcesador.setValue(null);
        cmbSistemaOperativo.setValue(null);
        txMemoria.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datosIniciales();
    }
}
