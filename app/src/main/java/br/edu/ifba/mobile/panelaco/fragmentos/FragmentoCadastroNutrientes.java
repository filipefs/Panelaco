package br.edu.ifba.mobile.panelaco.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.panelaco.R;

/**
 * Created by Filipe on 24/06/2016.
 */
public class FragmentoCadastroNutrientes extends Fragment{

    private static FragmentoCadastroNutrientes instancia = null;

    public static FragmentoCadastroNutrientes getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroNutrientes();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nutrientes = null;
    private EditText calorias = null;
    private Button gravar = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragment_cadastro_receitas, vgroup, false);
        preparar();
        return tela;
    }

    private void preparar(){
        nutrientes = (EditText) tela.findViewById(R.id.nutrientes);
        calorias = (EditText) tela.findViewById(R.id.calorias);
        gravar = (Button) tela.findViewById(R.id.botaoGravar);
    }

}
