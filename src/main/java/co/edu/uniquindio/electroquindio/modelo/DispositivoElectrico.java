package co.edu.uniquindio.electroquindio.modelo;

import co.edu.uniquindio.electroquindio.enumm.TipoCategoria;
import co.edu.uniquindio.electroquindio.enumm.TipoColor;
import co.edu.uniquindio.electroquindio.enumm.TipoMarca;

public abstract class DispositivoElectrico {

    // atributos privados
    private TipoCategoria tipoCategoria;

    private TipoMarca marca;

    private String modelo;

    private String referencia;

    private TipoColor color;

    private String tamanioPantalla;

    private int precio;


    //métodos públicos
    public DispositivoElectrico(){

    }

    public TipoCategoria getCategoria() {
        return tipoCategoria;
    }

    public void setCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public TipoMarca getMarca() {
        return marca;
    }

    public void setMarca(TipoMarca marca) {
        this.marca = marca;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public TipoColor getColor() {
        return color;
    }

    public void setColor(TipoColor color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTamanioPantalla() {
        return tamanioPantalla;
    }

    public void setTamanioPantalla(String tamanioPantalla) {
        this.tamanioPantalla = tamanioPantalla;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}
