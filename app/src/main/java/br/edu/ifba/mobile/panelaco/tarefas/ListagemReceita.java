package br.edu.ifba.mobile.panelaco.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.panelaco.bd.FachadaBD;
import br.edu.ifba.mobile.panelaco.bd.Receita;

/**
 * Created by Filipe on 25/06/2016.
 */
public class ListagemReceita extends AsyncTask<Void, Void, List<Receita>> {

    private Context contexto = null;
    private ListView listaReceitas = null;

    public ListagemReceita(Context contexto, ListView listaReceitas){
        this.contexto = contexto;
        this.listaReceitas = listaReceitas;
    }

    @Override
    protected List<Receita> doInBackground(Void... params) {
        List<Receita> receitas = FachadaBD.getInstancia().listarReceitas();

        return receitas;
    }

    @Override
    protected void onPostExecute(List<Receita> receitas){
        if(receitas.isEmpty()){
            Toast.makeText(contexto, "Lista vazia. Cadastre uma receita!", Toast.LENGTH_LONG).show();
        }else {
            ArrayAdapter<Receita> adapter = new ArrayAdapter<Receita>(contexto, android.R.layout.simple_list_item_single_choice, receitas);
            listaReceitas.setAdapter(adapter);
        }
    }
}
