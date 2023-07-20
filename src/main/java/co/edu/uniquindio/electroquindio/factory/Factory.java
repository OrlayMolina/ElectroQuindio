package co.edu.uniquindio.electroquindio.factory;

import co.edu.uniquindio.electroquindio.constantes.MensajeExceptionesConstante;
import co.edu.uniquindio.electroquindio.constantes.MensajeInformacionConstante;
import co.edu.uniquindio.electroquindio.controlador.DispositivoController;
import co.edu.uniquindio.electroquindio.exception.DispositivoElectronicoExisteException;
import co.edu.uniquindio.electroquindio.modelo.Almacen;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;
import co.edu.uniquindio.electroquindio.persistencia.Persistencia;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;

public class Factory {

    private MensajeInformacionConstante mensajeInformacion = new MensajeInformacionConstante();
    private MensajeExceptionesConstante mensajeError = new MensajeExceptionesConstante();
    private DispositivoController controller = new DispositivoController();
    private Almacen almacen = new Almacen();
    private Persistencia persistencia = new Persistencia();

    public static class SingletonHolder {
        private final static Factory eINSTANCE = new Factory();
    }

    public static Factory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public Factory() {
        cargarDatos();
    }

    public void cargarDatos(){
        this.almacen = new Almacen();

        try {
            ArrayList<Procesamiento> procesamiento;
            procesamiento = persistencia.cargarProcesamiento();
            getAlmacen().getListaProcesamientos().addAll(procesamiento);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Procesamiento crearDispositivo(Procesamiento proc) {
        Procesamiento procesamiento = null;

        try{
            procesamiento = getAlmacen().crearDispositivo(proc);
            persistencia.guardarDispositivo(getListProcesamientos());
            persistencia.guardarArchivoLog("Se ha creado un nuevo dispositivo en el sistema",2,mensajeInformacion.INFOMACION_DISPOSITIVO_GUARDADO);
        }catch(DispositivoElectronicoExisteException e){
            controller.mostrarMensaje("PRECAUCIÓN","Procesamiento duplicado.",
                    mensajeError.ERROR_REFERENCIA_DUPLICADA, Alert.AlertType.WARNING);
        }catch(IOException e){
            controller.mostrarMensaje("PRECAUCIÓN","Guardar procesamiento.",
                    mensajeError.ERROR_CREACION_DISPOSITIVO, Alert.AlertType.WARNING);
        }

        return procesamiento;
    }

    public boolean eliminarDispositivo(Procesamiento proc) {
        boolean bandera = false;

        try{
            bandera = getAlmacen().eliminarDispositivo(proc);
            persistencia.guardarDispositivo(getListProcesamientos());
            persistencia.guardarArchivoLog("Se ha eliminado el dispositivo correctamente",1,"Dispositivo se ha eliminado con éxito");
        }catch(IOException e){
            System.out.println("Ha ocurrido un error de archivo.");
        }

        return bandera;
    }

    public boolean actualizarDispositivo(Procesamiento proc) {
        boolean bandera = false;

        try{
            bandera = getAlmacen().actualizarDispositivo(proc);
            persistencia.guardarDispositivo(getListProcesamientos());
            persistencia.guardarArchivoLog("Se ha actualizado un dispositivo",1,"Dispositivo se ha actualizado con éxito");
        }catch(IOException e){
            System.out.println("Ha ocurrido un error de archivo.");
        }

        return bandera;
    }

    public Almacen getAlmacen() {
        return almacen;
    }


    public ArrayList<Procesamiento> getListProcesamientos() {
        return getAlmacen().getListaProcesamientos();
    }
}
