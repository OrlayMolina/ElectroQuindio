package co.edu.uniquindio.electroquindio.factory;

import co.edu.uniquindio.electroquindio.exception.DispositivoElectronicoExisteException;
import co.edu.uniquindio.electroquindio.modelo.Almacen;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;

import java.util.ArrayList;

public class Factory {

    private Almacen almacen;

    public static class SingletonHolder {
        private final static Factory eINSTANCE = new Factory();
    }

    public static Factory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public Factory() {

    }

    public Procesamiento crearDispositivo(Procesamiento proc) {
        Procesamiento procesamiento = null;

        try{
            procesamiento = getAlmacen().crearDispositivo(proc);
        }catch(DispositivoElectronicoExisteException e){
            System.out.println("Ya existe un dispositivo electrónico registrado con esa Referencia.");
        }

        return procesamiento;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
}
