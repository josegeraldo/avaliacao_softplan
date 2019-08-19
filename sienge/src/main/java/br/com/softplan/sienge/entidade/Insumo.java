package br.com.softplan.sienge.entidade;

public class Insumo extends Item {

    public Insumo() {
        super();
    }

    public Insumo(final String codigo, final String descricao, final UnidadeMedida unidadeMedida,
            final Double valorUnitario) {
        super(codigo, descricao, unidadeMedida, valorUnitario);
    }

}
