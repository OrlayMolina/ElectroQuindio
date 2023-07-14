package co.edu.uniquindio.electroquindio.modelo;

import co.edu.uniquindio.electroquindio.enumm.TipoProcesador;
import co.edu.uniquindio.electroquindio.enumm.TipoSistemaOperativo;

public class Procesamiento extends DispositivoElectrico{

    private TipoProcesador marcaProcesador;

    private TipoSistemaOperativo sistemaOperativo;

    private String memoria;

    public Procesamiento() {

    }

    public TipoProcesador getMarcaProcesador() {
        return marcaProcesador;
    }

    public void setMarcaProcesador(TipoProcesador marcaProcesador) {
        this.marcaProcesador = marcaProcesador;
    }

    public TipoSistemaOperativo getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(TipoSistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }
}
