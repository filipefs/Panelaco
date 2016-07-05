package br.edu.ifba.mobile.panelaco.bd;

/**
 * Created by Filipe on 26/06/2016.
 */
public class InfoNutricional {


    private long codigo = -1;
    private String nutrientes;
    private int calorias;
    private long codReceitas;

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    public void setNutrientes(String nutrientes) {
        this.nutrientes = nutrientes;
    }

    public long getCodReceitas() {
        return codReceitas;
    }

    public void setCodReceitas(long codReceitas) {
        this.codReceitas = codReceitas;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "InfoNutricional{" +
                "codigo=" + codigo +
                ", nutrientes='" + nutrientes + '\'' +
                ", calorias=" + calorias +
                ", codReceitas=" + codReceitas +
                '}';
    }
}
