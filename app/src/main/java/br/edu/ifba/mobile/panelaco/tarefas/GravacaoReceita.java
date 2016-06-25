package br.edu.ifba.mobile.panelaco.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.panelaco.bd.FachadaBD;
import br.edu.ifba.mobile.panelaco.bd.Receita;

/**
 * Created by Filipe on 25/06/2016.
 */
public class GravacaoReceita extends AsyncTask<Void, Void, String> {

    private Receita receitas = null;
    private Context contexto = null;

    public GravacaoReceita(Context contexto, Receita receita){
        this.contexto = contexto;
        this.receitas = receita;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        long codigo = -1;
        codigo = FachadaBD.getInstancia().inserir(receitas);

        if(codigo > 0){
            mensagem = "Receita gravada com sucesso!";
        }else {
            mensagem = "Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
