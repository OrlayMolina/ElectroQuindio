package co.edu.uniquindio.electroquindio.modelo;

import co.edu.uniquindio.electroquindio.constantes.MensajeInformacionConstante;
import co.edu.uniquindio.electroquindio.exception.DispositivoElectronicoExisteException;
import co.edu.uniquindio.electroquindio.interfaces.AlmacenInterface;

import java.util.ArrayList;

public class Almacen implements AlmacenInterface {

    private MensajeInformacionConstante mensajeInformacionConstante = new MensajeInformacionConstante();

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

    public boolean eliminarDispositivo(Procesamiento proc) {
        boolean bandera = false;

        if(proc != null) {
            getListaProcesamientos().remove(proc);
            bandera = true;
        }else {
            System.out.println("Procesamiento no existe");
        }
        return bandera;
    }

    public boolean actualizarDispositivo(Procesamiento proc) {
        Procesamiento procesamiento = new Procesamiento();
        boolean bandera = false;

        if(proc != null) {
            for(int i = 0; i < getListaProcesamientos().size(); i++){
                if(getListaProcesamientos().get(i).getReferencia().equals(proc.getReferencia())){
                    procesamiento.setReferencia(proc.getReferencia());
                    procesamiento.setCategoria(proc.getCategoria());
                    procesamiento.setMarca(proc.getMarca());
                    procesamiento.setModelo(proc.getModelo());
                    procesamiento.setTamanioPantalla(proc.getTamanioPantalla());
                    procesamiento.setColor(proc.getColor());
                    procesamiento.setPrecio(proc.getPrecio());
                    procesamiento.setMarcaProcesador(proc.getMarcaProcesador());
                    procesamiento.setSistemaOperativo(proc.getSistemaOperativo());
                    procesamiento.setMemoria(proc.getMemoria());

                    getListaProcesamientos().set(i, procesamiento);
                    bandera = true;
                }


            }
        }else {
            System.out.println("Procesamiento no existe");
        }
        return bandera;
    }


    public ArrayList<Procesamiento> getListaProcesamientos() {
        return listaProcesamientos;
    }

    public void setListaProcesamientos(ArrayList<Procesamiento> listaProcesamientos) {
        this.listaProcesamientos = listaProcesamientos;
    }
}
