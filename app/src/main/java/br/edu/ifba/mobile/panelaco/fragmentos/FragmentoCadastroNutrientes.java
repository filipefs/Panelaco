package br.edu.ifba.mobile.panelaco.fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.edu.ifba.mobile.panelaco.R;
import br.edu.ifba.mobile.panelaco.bd.FachadaBD;
import br.edu.ifba.mobile.panelaco.bd.InfoNutricional;
import br.edu.ifba.mobile.panelaco.bd.Receita;
import br.edu.ifba.mobile.panelaco.tarefas.GravacaoNutriente;

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
    private Spinner listaReceitas = null;
    private long codigoSelecionado;

    private InfoNutricional infoNutricional = null;
    private Receita receita = null;


    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragment_cadastro_nutrientes, vgroup, false);
        preparar();
        return tela;
    }

    private void preparar(){
        nutrientes = (EditText) tela.findViewById(R.id.nutrientes);
        calorias = (EditText) tela.findViewById(R.id.calorias);
        listaReceitas = (Spinner) tela.findViewById(R.id.receitaNutriente);
        List<Receita> receitas =  FachadaBD.getInstancia().listarReceitas();
        ArrayAdapter<Receita> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, receitas);
        listaReceitas.setAdapter(adapter);


        listaReceitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Receita receitaSelecionada = (Receita)parent.getItemAtPosition(position);
                setCodigoSelecionado(receitaSelecionada.getCodigo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gravar = (Button) tela.findViewById(R.id.gravarNutrientes);

        gravar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                GravacaoNutriente gravacao = new GravacaoNutriente(getContexto(), getNutrientes());
                gravacao.execute();
                limparCampos();

            }
        });



    }

    public long getCodigoSelecionado() {
        return codigoSelecionado;
    }

    public void setCodigoSelecionado(long codigoSelecionado) {
        this.codigoSelecionado = codigoSelecionado;
    }


    public Context getContexto(){
        return this.getContext();
    }

    public InfoNutricional getNutrientes(){

        if(infoNutricional == null){
            infoNutricional = new InfoNutricional();
        }
        infoNutricional.setNutrientes(nutrientes.getText().toString());
        infoNutricional.setCalorias(Integer.valueOf(calorias.getText().toString()));
        infoNutricional.setCodReceitas(getCodigoSelecionado());

        return infoNutricional;
    }

    public void exibirNutrientesSelecionados(){
        receita = FragmentoListaReceitas.getInstancia().getReceitaSelecionada();

        if(receita != null){
            if(receita.getCodigo() == -1){
                limparCampos();
            }else {
                infoNutricional = FachadaBD.getInstancia().procurarNutrientes(receita.getCodigo());
                carregarCampos();
                //receita = null;
            }
        }else{
            limparCampos();
        }

    }

    public void limparCampos(){
        nutrientes.setText("");
        calorias.setText("");
    }

    public void carregarCampos(){
        nutrientes.setText(infoNutricional.getNutrientes());
        calorias.setText(infoNutricional.getCalorias() + "");
    }

}
