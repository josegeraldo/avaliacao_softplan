package br.com.softplan.sienge.entidade;

import java.util.LinkedList;
import java.util.List;

public class Obra {

    /**
     * composicoes da obra
     */
    private List<Composicao> composicoes = new LinkedList<>();

    public Obra() {
    }


    public List<Composicao> getComposicoes() {
        return this.composicoes;
    }

}
