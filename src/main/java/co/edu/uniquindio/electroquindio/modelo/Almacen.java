package co.edu.uniquindio.electroquindio.modelo;

import co.edu.uniquindio.electroquindio.exception.DispositivoElectronicoExisteException;

import java.util.ArrayList;

public class Almacen {

    private ArrayList<Procesamiento> listaProcesamientos = new ArrayList<Procesamiento>();

    public Almacen(){

    }

    public boolean validarPorReferencia(Procesamiento proc){

        for (Procesamiento p : listaProcesamientos) {
            if (p.getReferencia().equals(proc.getReferencia())) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarSiExiste(Procesamiento proc) throws RuntimeException {
        if(validarPorReferencia(proc)){
            throw new DispositivoElectronicoExisteException();
        }
        return false;
    }

    public Procesamiento crearDispositivo(Procesamiento proc){
        Procesamiento procesamiento;

        boolean existeProcesamiento = verificarSiExiste(proc);
        if(existeProcesamiento)
            throw new DispositivoElectronicoExisteException();
        else{
            procesamiento = new Procesamiento();
            procesamiento.setCategoria(proc.getCategoria());
            procesamiento.setMarca(proc.getMarca());
            procesamiento.setModelo(proc.getModelo());
            procesamiento.setReferencia(proc.getReferencia());
            procesamiento.setColor(proc.getColor());
            procesamiento.setTamanioPantalla(proc.getTamanioPantalla());
            procesamiento.setPrecio(proc.getPrecio());
            procesamiento.setMarcaProcesador(proc.getMarcaProcesador());
            procesamiento.setSistemaOperativo(proc.getSistemaOperativo());
            procesamiento.setMemoria(proc.getMemoria());

            getListaProcesamientos().add(procesamiento);
        }

        return procesamiento;
    }



    public ArrayList<Procesamiento> getListaProcesamientos() {
        return listaProcesamientos;
    }

    public void setListaProcesamientos(ArrayList<Procesamiento> listaProcesamientos) {
        this.listaProcesamientos = listaProcesamientos;
    }
}
