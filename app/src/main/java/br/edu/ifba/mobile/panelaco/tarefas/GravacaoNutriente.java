package br.edu.ifba.mobile.panelaco.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.panelaco.bd.FachadaBD;
import br.edu.ifba.mobile.panelaco.bd.InfoNutricional;

/**
 * Created by Filipe on 26/06/2016.
 */
public class GravacaoNutriente extends AsyncTask<Void, Void, String> {

    private InfoNutricional infoNutricional = null;
    private Context contexto = null;

    public GravacaoNutriente(Context contexto, InfoNutricional infoNutricional){
        this.infoNutricional = infoNutricional;
        this.contexto = contexto;
    }




    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        long codigo = -1;
        if(codigo == -1){
            codigo = FachadaBD.getInstancia().inserirNutriente(infoNutricional);
        }else{
            codigo = FachadaBD.getInstancia().atualizarNutrientes(infoNutricional);
        }

        if(codigo > 0){
            mensagem = "Informação nutricional guardada com sucesso";
        }else {
            mensagem = "Erro ao gravar informação nutricional";
        }


        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
