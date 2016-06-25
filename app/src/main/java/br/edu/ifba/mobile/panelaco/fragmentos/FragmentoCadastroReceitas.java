package br.edu.ifba.mobile.panelaco.fragmentos;

import android.support.v4.app.Fragment;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import br.edu.ifba.mobile.panelaco.R;

/**
 * Created by Filipe on 24/06/2016.
 */
public class FragmentoCadastroReceitas extends Fragment {

    private static FragmentoCadastroReceitas instancia = null;

    public static FragmentoCadastroReceitas getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroReceitas();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nome = null;
    private EditText ingredientes = null;
    private EditText modoPreparo = null;
    private Button proximo = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragment_cadastro_receitas, vgroup, false);
        preparar();
        return tela;
    }

    private void preparar(){
        nome = (EditText) tela.findViewById(R.id.nome);
        ingredientes = (EditText) tela.findViewById(R.id.ingredientes);
        modoPreparo = (EditText) tela.findViewById(R.id.preparo);
        proximo = (Button) tela.findViewById(R.id.botaoProximo);
        proximo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TabHost host = (TabHost) getActivity().findViewById(android.R.id.tabhost);
                host.setCurrentTab(1);
            }
        });
    }

}
