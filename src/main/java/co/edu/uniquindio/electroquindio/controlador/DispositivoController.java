package co.edu.uniquindio.electroquindio.controlador;

import co.edu.uniquindio.electroquindio.constantes.MensajeInformacionConstante;
import co.edu.uniquindio.electroquindio.enumm.*;
import co.edu.uniquindio.electroquindio.factory.Factory;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;
import co.edu.uniquindio.electroquindio.persistencia.Persistencia;
import co.edu.uniquindio.electroquindio.subcontrolador.DispositivoSubController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DispositivoController implements Initializable {

    private final ObservableList<TipoCategoria> listadoCategorias = FXCollections.observableArrayList();
    private final ObservableList<TipoMarca> listadoMarcas = FXCollections.observableArrayList();
    private final ObservableList<TipoColor> listadoColores = FXCollections.observableArrayList();
    private final ObservableList<TipoProcesador> listadoProcesadores = FXCollections.observableArrayList();
    private final ObservableList<TipoSistemaOperativo> listadoSistemaOperativo = FXCollections.observableArrayList();
    private final ObservableList<Procesamiento> listaProcesamientos = FXCollections.observableArrayList();

    private Procesamiento procesamiento;
    private DispositivoSubController subController;
    private Persistencia persistencia = new Persistencia();
    private MensajeInformacionConstante mensajeInformacion = new MensajeInformacionConstante();

    @FXML
    private Button btnSalir;

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
    void cerrarAplicacion(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void actualizarDispositivo(ActionEvent event) {
        actualizarProcesamientos();
    }

    @FXML
    void eliminarDispositivo(ActionEvent event) {
        eliminarProcesamientos();
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
            listaProcesamientos.add(procesamiento);
            tableDispositivos.refresh();
            mostrarMensaje("GUARDAR","Creación de procesamiento.",
                    mensajeInformacion.INFOMACION_DISPOSITIVO_ACTUALIZADO, Alert.AlertType.INFORMATION);
            persistencia.guardarArchivoLog("Se guardado un dispositivo correctamente", 1, "La acción se ejecuto desde el método guardarDispositvo de DispositivoController.");

        }
    }

    public void eliminarProcesamientos(){
        boolean bandera = false, mensaje;
        if(procesamiento != null){
            mensaje = mostrarMensajeConfirmacion("¿Está seguro que desea eliminar el procesamiento?.");
            if(mensaje){
                bandera = subController.eliminarDispositivo(procesamiento);
                if(bandera){
                    listaProcesamientos.remove(procesamiento);
                    // Procesamiento va a ser igual al null.
                    procesamiento = null;
                    tableDispositivos.getSelectionModel().clearSelection();
                    mostrarMensaje("ELIMINACIÓN","Eliminación de procesamiento",
                            "El procesamiento se ha eliminado correctamente", Alert.AlertType.INFORMATION);
                }else {
                    mostrarMensaje("ELIMINACIÓN","Eliminación de procesamiento.",
                            "El procesamiento no se pudo eliminar.", Alert.AlertType.WARNING);
                }
            }
        }

    }

    public void actualizarProcesamientos(){
        Procesamiento proc = new Procesamiento();
        boolean bandera = false;

        String referencia = txReferencia.getText();
        TipoCategoria tipoCategoria = cmbCategoria.getSelectionModel().getSelectedItem();
        TipoMarca marca = cmbMarca.getSelectionModel().getSelectedItem();
        String modelo = txModelo.getText();
        String tamanioPantalla = txPantalla.getText();
        TipoColor color = cmbColor.getSelectionModel().getSelectedItem();
        int precio = Integer.parseInt(txPrecio.getText());
        TipoProcesador procesador = cmbProcesador.getSelectionModel().getSelectedItem();
        TipoSistemaOperativo sistemaOperativo = cmbSistemaOperativo.getSelectionModel().getSelectedItem();
        String memoria = txMemoria.getText();

        proc.setCategoria(tipoCategoria);
        proc.setReferencia(referencia);
        proc.setMarca(marca);
        proc.setModelo(modelo);
        proc.setTamanioPantalla(tamanioPantalla);
        proc.setColor(color);
        proc.setPrecio(precio);
        proc.setMarcaProcesador(procesador);
        proc.setSistemaOperativo(sistemaOperativo);
        proc.setMemoria(memoria);

        if(procesamiento != null){
            bandera = subController.actualizarDispositivo(proc);
            if(bandera){
                tableDispositivos.refresh();
                mostrarMensaje("ACTUALIZACIÓN","Actualización de procesamiento.",
                        "El procesamiento se actualizó correctamente.", Alert.AlertType.INFORMATION);
                tableDispositivos.refresh();
            }else {
                mostrarMensaje("ACTUALIZACIÓN","Actualización de procesamiento.",
                        "El procesamiento no se pudo actualizar.", Alert.AlertType.WARNING);
            }
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
        listadoMarcas.add(TipoMarca.MAC);
        listadoMarcas.add(TipoMarca.MOTOROLA);
        listadoMarcas.add(TipoMarca.NOKIA);
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
        listadoProcesadores.add(TipoProcesador.APPLE_16);
        listadoProcesadores.add(TipoProcesador.HUAWEI_KIRIN);
        listadoProcesadores.add(TipoProcesador.INTEL);
        listadoProcesadores.add(TipoProcesador.MOTOROLA_MEDIATEK);
        listadoProcesadores.add(TipoProcesador.NOKIA_MEDIATEK);
        listadoProcesadores.add(TipoProcesador.SAMSUNG_EXYNOS);
        cmbProcesador.setItems(listadoProcesadores);
    }

    public void mostrarSistemaOperativo(){
        listadoSistemaOperativo.add(TipoSistemaOperativo.ANDROID);
        listadoSistemaOperativo.add(TipoSistemaOperativo.IOS);
        listadoSistemaOperativo.add(TipoSistemaOperativo.LINUX);
        listadoSistemaOperativo.add(TipoSistemaOperativo.MAC_OS);
        listadoSistemaOperativo.add(TipoSistemaOperativo.WINDOWS);
        cmbSistemaOperativo.setItems(listadoSistemaOperativo);
    }


    @FXML
    public void filtroEnumMarca(){
        filtro();
    }

    public void filtro(){


        TipoCategoria tipoCategoria = cmbCategoria.getSelectionModel().getSelectedItem();
        if(tipoCategoria.equals(TipoCategoria.COMPUTADOR)){
            listadoProcesadores.clear();

            listadoProcesadores.add(TipoProcesador.AMD);
            listadoProcesadores.add(TipoProcesador.INTEL);
            cmbProcesador.setItems(listadoProcesadores);

            listadoMarcas.clear();
            listadoMarcas.add(TipoMarca.ACER);
            listadoMarcas.add(TipoMarca.ASUS);
            listadoMarcas.add(TipoMarca.HEWLETT_PACKARD);
            listadoMarcas.add(TipoMarca.LENOVO);
            listadoMarcas.add(TipoMarca.MAC);
            cmbMarca.setItems(listadoMarcas);

            listadoSistemaOperativo.clear();

            listadoSistemaOperativo.add(TipoSistemaOperativo.LINUX);
            listadoSistemaOperativo.add(TipoSistemaOperativo.MAC_OS);
            listadoSistemaOperativo.add(TipoSistemaOperativo.WINDOWS);
            cmbSistemaOperativo.setItems(listadoSistemaOperativo);


        }

        if(tipoCategoria.equals(TipoCategoria.CELULAR)){
            listadoProcesadores.clear();

            listadoProcesadores.add(TipoProcesador.APPLE_16);
            listadoProcesadores.add(TipoProcesador.HUAWEI_KIRIN);
            listadoProcesadores.add(TipoProcesador.MOTOROLA_MEDIATEK);
            listadoProcesadores.add(TipoProcesador.NOKIA_MEDIATEK);
            listadoProcesadores.add(TipoProcesador.SAMSUNG_EXYNOS);
            cmbProcesador.setItems(listadoProcesadores);

            listadoMarcas.clear();

            listadoMarcas.add(TipoMarca.APPLE);
            listadoMarcas.add(TipoMarca.HUAWEI);;
            listadoMarcas.add(TipoMarca.MOTOROLA);
            listadoMarcas.add(TipoMarca.NOKIA);
            listadoMarcas.add(TipoMarca.SAMSUNG);
            cmbMarca.setItems(listadoMarcas);

            listadoSistemaOperativo.clear();

            listadoSistemaOperativo.add(TipoSistemaOperativo.ANDROID);
            listadoSistemaOperativo.add(TipoSistemaOperativo.IOS);;
            cmbSistemaOperativo.setItems(listadoSistemaOperativo);
        }
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

    public ObservableList<Procesamiento> getProcesamientos() {
        listaProcesamientos.addAll(subController.obtenerProcesamientos());
        return listaProcesamientos;
    }

    private void mostrarInformacionSerie(Procesamiento proc) {
        if(proc != null) {

            cmbCategoria.setValue(proc.getCategoria());
            cmbMarca.setValue(proc.getMarca());
            txModelo.setText(proc.getModelo());
            txReferencia.setText(proc.getReferencia());
            txPantalla.setText(proc.getTamanioPantalla());
            cmbColor.setValue(proc.getColor());
            txPrecio.setText(String.valueOf(proc.getPrecio()));
            cmbProcesador.setValue(proc.getMarcaProcesador());
            cmbSistemaOperativo.setValue(proc.getSistemaOperativo());
            txMemoria.setText(proc.getMemoria());
        }
    }

    private void inicializarProcesamientosView() {

        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colReferencia.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colPantalla.setCellValueFactory(new PropertyValueFactory<>("tamanioPantalla"));
        colProcesador.setCellValueFactory(new PropertyValueFactory<>("marcaProcesador"));
        colSistemaOperativo.setCellValueFactory(new PropertyValueFactory<>("sistemaOperativo"));
        colMemoria.setCellValueFactory(new PropertyValueFactory<>("memoria"));

        tableDispositivos.getItems().clear();
        tableDispositivos.setItems(getProcesamientos());

        tableDispositivos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            procesamiento = (Procesamiento) newSelection;

            mostrarInformacionSerie(procesamiento);

        });

    }

    public boolean mostrarMensajeConfirmacion(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    /**
     *
     */
    public void datosIniciales(){
        Factory factory = Factory.getInstance();
        subController = new DispositivoSubController(factory);
        new DispositivoController();
        inicializarProcesamientosView();
        mostrarCategoria();
        mostrarMarcas();
        mostrarColor();
        mostrarMarcarProcesamiento();
        mostrarSistemaOperativo();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datosIniciales();
    }
}
