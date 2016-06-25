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
            " NOME TEXT, INGREDIENTES TEXT, PREPARO TEXT, NUTRIENTES TEXT, CALORIAS INT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_RECEITAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long inserir(Receita receita){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", receita.getNome());
        valores.put("INGREDIENTES", receita.getIngredientes());
        valores.put("PREPARO", receita.getModoPreparo());
        valores.put("NUTRIENTES", receita.getNutrientes());
        valores.put("CALORIAS", receita.getCalorias());

        long codigo = db.insert("RECEITAS", null, valores);

        return codigo;
    }

    public List<Receita> listarReceitas(){
        List<Receita> receitas = new ArrayList<Receita>();
        SQLiteDatabase db = getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, CALORIAS FROM RECEITAS";
        Cursor cursor = db.rawQuery(selecao, null);
        if(cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while (temProximo){
                Receita receita = new Receita();
                receita.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                receita.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                receita.setIngredientes(cursor.getString(cursor.getColumnIndex("INGREDIENTES")));
                receita.setModoPreparo(cursor.getString(cursor.getColumnIndex("PREPARO")));
                receita.setNutrientes(cursor.getString(cursor.getColumnIndex("NUTRIENTES")));
                receita.setCalorias(cursor.getInt(cursor.getColumnIndex("CALORIAS")));

                receitas.add(receita);

                temProximo = cursor.moveToNext();
            }
        }
        return receitas;
    }

}