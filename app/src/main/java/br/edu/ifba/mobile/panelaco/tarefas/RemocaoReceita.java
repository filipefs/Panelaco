package br.edu.ifba.mobile.panelaco.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.panelaco.bd.FachadaBD;
import br.edu.ifba.mobile.panelaco.bd.Receita;

/**
 * Created by Filipe on 01/07/2016.
 */
public class RemocaoReceita extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Receita receita = null;

    public RemocaoReceita(Context contexto, Receita receita){
        this.contexto = contexto;
        this.receita = receita;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(receita.getCodigo() != -1){
            if(FachadaBD.getInstancia().removerReceita(receita) == 0){
                mensagem = "Problemas de remoção";
            }else {
                mensagem = "Receita removida";
            }
        }else {
            mensagem = "Selecione uma receita";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }

}
