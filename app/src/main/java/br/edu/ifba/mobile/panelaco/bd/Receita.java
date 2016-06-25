package br.edu.ifba.mobile.panelaco.bd;

/**
 * Created by Filipe on 25/06/2016.
 */
public class Receita {

    private long codigo;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private String nutrientes;
    private int calorias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    public void setNutrientes(String nutrientes) {
        this.nutrientes = nutrientes;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "codigo=" + codigo +
                ", nome='" + nome +
                ", calorias=" + calorias +
                '}';
    }
}
