package br.edu.ifba.mobile.panelaco.fragmentos;

import android.support.v4.app.Fragment;;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.edu.ifba.mobile.panelaco.R;

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

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragment_lista_receitas, vgroup, false);

        preparar();

        return tela;
    }

    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaReceitas);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

}
