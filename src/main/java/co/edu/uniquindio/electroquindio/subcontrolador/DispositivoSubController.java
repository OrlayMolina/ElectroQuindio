package co.edu.uniquindio.electroquindio.subcontrolador;

import co.edu.uniquindio.electroquindio.factory.Factory;
import co.edu.uniquindio.electroquindio.modelo.Almacen;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;

import java.util.ArrayList;

public class DispositivoSubController {

    private Factory factory;
    private Almacen almacen;

    public DispositivoSubController(Factory factory) {
        this.factory = factory;
        almacen = factory.getAlmacen();
    }

    public Procesamiento crearDispositivo(Procesamiento proc) {
        return factory.crearDispositivo(proc);
    }
}
