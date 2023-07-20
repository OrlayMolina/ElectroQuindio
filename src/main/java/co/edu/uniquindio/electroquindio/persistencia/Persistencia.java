package co.edu.uniquindio.electroquindio.persistencia;

import co.edu.uniquindio.electroquindio.enumm.*;
import co.edu.uniquindio.electroquindio.modelo.Procesamiento;

import java.io.IOException;
import java.util.ArrayList;


public class Persistencia {

    public ArchivoUtil archivoUtil = new ArchivoUtil();

    public static final String rutaDispositivo = "C:\\Java proyectos\\ElectroQuindio\\src\\main\\java\\co\\edu\\uniquindio\\electroquindio\\archivos\\ProcesamientosFile.txt";

    public static final String rutaLog = "C:\\Java proyectos\\ElectroQuindio\\src\\main\\java\\co\\edu\\uniquindio\\electroquindio\\archivos\\SistemaLog.txt";

    public void guardarArchivoLog(String mensajeLog, int nivel, String accion){
        archivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, rutaLog);
    }

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
                    append(p.getMemoria()).append("\n");
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
                procesamiento.setCategoria(TipoCategoria.valueOf(linea.split("##")[1]));
                procesamiento.setMarca(TipoMarca.valueOf(linea.split("##")[2]));
                procesamiento.setModelo(linea.split("##")[3]);
                procesamiento.setTamanioPantalla(linea.split("##")[4]);
                procesamiento.setColor(TipoColor.valueOf(linea.split("##")[5]));
                procesamiento.setPrecio(Integer.parseInt(linea.split("##")[6]));
                procesamiento.setMarcaProcesador(TipoProcesador.valueOf(linea.split("##")[7]));
                procesamiento.setSistemaOperativo(TipoSistemaOperativo.valueOf(linea.split("##")[8]));
                procesamiento.setMemoria(linea.split("##")[9]);

                proces.add(procesamiento);

            }

            return proces;
        }

}
