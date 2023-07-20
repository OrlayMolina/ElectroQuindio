package co.edu.uniquindio.electroquindio.interfaces;

import co.edu.uniquindio.electroquindio.modelo.Procesamiento;

import java.util.ArrayList;

public interface AlmacenInterface {

    boolean validarPorReferencia(Procesamiento procesamiento);

    boolean verificarSiExiste(Procesamiento proc) throws RuntimeException;

    Procesamiento crearDispositivo(Procesamiento proc);

    boolean eliminarDispositivo(Procesamiento proc);

    boolean actualizarDispositivo(Procesamiento proc);

    ArrayList<Procesamiento> getListaProcesamientos();

    void setListaProcesamientos(ArrayList<Procesamiento> listaProcesamientos);
}
