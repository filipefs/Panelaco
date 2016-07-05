package br.edu.ifba.mobile.panelaco.fragmentos;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.panelaco.R;
import br.edu.ifba.mobile.panelaco.bd.Receita;
import br.edu.ifba.mobile.panelaco.tarefas.ListagemReceita;
import br.edu.ifba.mobile.panelaco.tarefas.RemocaoReceita;

/**
 * Created by Filipe on 24/06/2016.
 */
public class FragmentoListaReceitas extends Fragment {

    private static FragmentoListaReceitas instancia = null;

    public static FragmentoListaReceitas getInstancia(){
        if(instancia == null){
            instancia = new FragmentoListaReceitas();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    private Receita receita = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragment_lista_receitas, vgroup, false);

        preparar();

        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){
        super.onCreateOptionsMenu(menu, inflador);

        inflador.inflate(R.menu.menu_panelaco, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        long id =item.getItemId();

        if(id != AdapterView.INVALID_ROW_ID){
            if(id == R.id.cadastro_remover){
                RemocaoReceita remocao = new RemocaoReceita(this.getContext(), this.getReceitaSelecionadaExclusao());
                remocao.execute();
                atualizar();
            }
        }

        return super.onOptionsItemSelected(item);
    }



    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaReceitas);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar(){
        ListagemReceita listagemReceita = new ListagemReceita(this.getContext(), lista);
        listagemReceita.execute();
    }

    public Receita getReceitaSelecionadaExclusao(){

        int posicao = lista.getCheckedItemPosition();

        if (posicao != ListView.INVALID_POSITION){
            receita = (Receita) lista.getItemAtPosition(posicao);
        }

        return receita;
    }

    public Receita getReceitaSelecionada(){

        int posicao = lista.getCheckedItemPosition();

        if (posicao != ListView.INVALID_POSITION && receita == null){
            receita = (Receita) lista.getItemAtPosition(posicao);
        }

        return receita;
    }

    public void limparReceita(){
        receita = null;
    }

}
