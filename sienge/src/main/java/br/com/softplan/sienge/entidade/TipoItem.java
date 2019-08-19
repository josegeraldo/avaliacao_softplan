package br.com.softplan.sienge.entidade;

public enum TipoItem {

    INSUMO("INSUMO")
    {

    @Override
        public Item criarItem(final String codigo, final String descricao, final UnidadeMedida unidadeMedida,
                final Double valorUnitario) {
            return new Insumo(codigo, descricao, unidadeMedida, valorUnitario);
        }
        
    },
    COMPOSICAO("COMPOSICAO")
    {

        @Override
        public Item criarItem(final String codigo, final String descricao, final UnidadeMedida unidadeMedida,
                final Double valorUnitario) {
            return new Composicao(codigo, descricao, unidadeMedida);
        }
        
    };
    
    public abstract Item criarItem(final String codigo, final String descricao,
            final UnidadeMedida unidadeMedida, final Double valorUnitario);

    private String descricao;

    private TipoItem(final String descricao) {
        this.descricao = descricao;
    }

    public static TipoItem getTipoItemPorDescricao(final String descricao) {
        for( final TipoItem tipoItem : TipoItem.values() ) {
            if (tipoItem.descricao.equals(descricao)) {
                return tipoItem;
            }
        }

        return null;
    }
}
