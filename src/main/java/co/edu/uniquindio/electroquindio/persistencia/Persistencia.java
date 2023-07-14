package co.edu.uniquindio.electroquindio.persistencia;

import co.edu.uniquindio.electroquindio.enumm.*;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;

import java.io.IOException;
import java.util.ArrayList;


public class Persistencia {

    public ArchivoUtil archivoUtil = new ArchivoUtil();

    public static final String rutaDispositivo = "C:\\Java proyectos\\ElectroQuindio\\src\\main\\java\\co\\edu\\uniquindio\\electroquindio\\archivos\\ProcesamientosFile.txt";

    public void guardarDispositivo(ArrayList<Procesamiento> listaProcesamientos) throws IOException {

        StringBuilder contenido = new StringBuilder();

        for (Procesamiento p : listaProcesamientos) {
            contenido.append(p.getReferencia()).append("##").
                    append(p.getCategoria()).append("##").
                    append(p.getMarca()).append("##").
                    append(p.getModelo()).append("##").
                    append(p.getTamanioPantalla()).append("##").
                    append(p.getColor()).append("##").
                    append(p.getPrecio()).append("##").
                    append(p.getMarcaProcesador()).append("##").
                    append(p.getSistemaOperativo()).append("##").
                    append(p.getMemoria()).append("##").append("\n");
        }

        archivoUtil.guardarArchivo(rutaDispositivo, contenido.toString(), false);
    }

        public ArrayList<Procesamiento> cargarProcesamiento() throws IOException{

            ArrayList<Procesamiento> proces = new ArrayList<>();
            ArrayList<String> contenido = archivoUtil.leerArchivo(rutaDispositivo);

            String linea;

            for (String s : contenido) {
                linea = s;
                Procesamiento procesamiento = new Procesamiento();

                procesamiento.setReferencia(linea.split("##")[0]);
                procesamiento.setCategoria(TipoCategoria.valueOf(linea.split("##")[0]));
                procesamiento.setMarca(TipoMarca.valueOf(linea.split("##")[0]));
                procesamiento.setModelo(linea.split("##")[0]);
                procesamiento.setTamanioPantalla(linea.split("##")[0]);
                procesamiento.setColor(TipoColor.valueOf(linea.split("##")[0]));
                procesamiento.setPrecio(Integer.parseInt(linea.split("##")[0]));
                procesamiento.setMarcaProcesador(TipoProcesador.valueOf(linea.split("##")[0]));
                procesamiento.setSistemaOperativo(TipoSistemaOperativo.valueOf(linea.split("##")[0]));
                procesamiento.setMemoria(linea.split("##")[0]);

                proces.add(procesamiento);

            }

            return proces;
        }

}
