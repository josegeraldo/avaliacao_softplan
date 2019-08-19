package br.com.softplan.sienge.entidade;

import java.util.HashMap;
import java.util.Map;

public class Composicao extends Item {

    /**
     * itens da composicao e suas respectivas quantidades na composicao
     */
    private Map<Item, Double> itens = new HashMap<>();

    public Composicao() {

    }

    public Composicao(final String codigo, final String descricao, final UnidadeMedida unidadeMedida) {
        super(codigo, descricao, unidadeMedida);
    }

    public void adicionarItem(final Item item, final Double quantidade) {
        this.itens.put(item, quantidade);
    }

    public Double getValorComposicao() {

        Double valor = new Double(0);
        for (final Map.Entry<Item, Double> entrada : itens.entrySet()) {

            final Item item = entrada.getKey();
            final Double valorItem = ((item instanceof Composicao) ? ((Composicao) item).getValorComposicao()
                    : item.getValorUnitario()) * entrada.getValue();

            valor += valorItem;
        }

        return valor;
    }
}
