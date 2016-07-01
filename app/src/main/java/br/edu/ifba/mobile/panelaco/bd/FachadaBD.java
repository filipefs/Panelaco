package br.edu.ifba.mobile.panelaco.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 25/06/2016.
 */
public class FachadaBD extends SQLiteOpenHelper {

    private static FachadaBD instancia = null;

    public static FachadaBD criarInstancia(Context context){
        if(instancia == null){
            instancia = new FachadaBD(context);
        }

        return instancia;
    }

    public static FachadaBD getInstancia(){
        return instancia;
    }

    private static String NOME_BANCO = "Panelaco";
    private static int VERSAO_BANCO = 1;

    public FachadaBD(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_RECEITAS = "CREATE TABLE RECEITAS(CODIGO INTEGER PRIMARY KEY AUTOINCREMENT," +
            " NOME TEXT, INGREDIENTES TEXT, PREPARO TEXT)";


    private static String COMANDO_CRIACAO_TABELA_NUTRICAO = "CREATE TABLE NUTRICAO(CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NUTRIENTES TEXT, CALORIAS INT, COD_RECEITAS INTEGER, FOREIGN KEY(COD_RECEITAS) REFERENCES RECEITAS(CODIGO))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_RECEITAS);
        db.execSQL(COMANDO_CRIACAO_TABELA_NUTRICAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS RECEITAS");
        db.execSQL("DROP TABLE IF EXISTS NUTRICAO");
        onCreate(db);
    }

    public long inserirReceita(Receita receita){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", receita.getNome());
        valores.put("INGREDIENTES", receita.getIngredientes());
        valores.put("PREPARO", receita.getModoPreparo());

        long codigo = db.insert("RECEITAS", null, valores);

        return codigo;
    }

    public long inserirNutriente(InfoNutricional infoNutricional){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NUTRIENTES", infoNutricional.getNutrientes());
        valores.put("CALORIAS", infoNutricional.getCalorias());
        valores.put("COD_RECEITAS", infoNutricional.getCodReceitas());

        long codigo = db.insert("NUTRICAO", null, valores);

        return codigo;

    }

    public List<Receita> listarReceitas(){
        List<Receita> receitas = new ArrayList<Receita>();
        SQLiteDatabase db = getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, INGREDIENTES, PREPARO FROM RECEITAS";
        Cursor cursor = db.rawQuery(selecao, null);
        if(cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while (temProximo){
                Receita receita = new Receita();
                receita.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                receita.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                receita.setIngredientes(cursor.getString(cursor.getColumnIndex("INGREDIENTES")));
                receita.setModoPreparo(cursor.getString(cursor.getColumnIndex("PREPARO")));

                receitas.add(receita);

                temProximo = cursor.moveToNext();
            }
        }
        return receitas;
    }

    public int removerReceita(Receita receita){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("NUTRICAO", "COD_RECEITAS = "+receita.getCodigo(), null);

        return db.delete("RECEITAS", "CODIGO = " + receita.getCodigo(), null);

    }

    public InfoNutricional procurarNutrientes(long codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        InfoNutricional nutrientes = null;

        if(codigo != 0) {
            String selecao = "SELECT CODIGO, NUTRIENTES, CALORIAS FROM NUTRICAO WHERE COD_RECEITAS = " + codigo;
            Cursor cursor = db.rawQuery(selecao, null);
            nutrientes = new InfoNutricional();
            if (cursor.moveToFirst()) {
                do {
                    nutrientes.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                    nutrientes.setNutrientes(cursor.getString(cursor.getColumnIndex("NUTRIENTES")));
                    nutrientes.setCalorias(cursor.getInt(cursor.getColumnIndex("CALORIAS")));
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return nutrientes;

    }

    public long atualizarReceitas(Receita receita){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", receita.getNome());
        valores.put("INGREDIENTES", receita.getIngredientes());
        valores.put("PREPARO", receita.getModoPreparo());

        long codigo = db.update("RECEITAS", valores, "CODIGO = " + receita.getCodigo(), null);
        return codigo;
    }

    public long atualizarNutrientes(InfoNutricional nutrientes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NUTRIENTES", nutrientes.getNutrientes());
        valores.put("CALORIAS", nutrientes.getCalorias());

        long codigo = db.update("NUTRICAO", valores, "CODIGO = " + nutrientes.getCodigo(), null);
        return codigo;
    }

}
