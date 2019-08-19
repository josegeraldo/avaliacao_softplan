package br.com.softplan.sienge.entidade;

public enum UnidadeMedida {

    METROS_QUADRADOS("M2"), UNIDADE("UN"), METROS_CUBICOS("M3"), CHP("CHP"), CHI("CHI");

    private String descricao;

    private UnidadeMedida(final String descricao) {
        this.descricao = descricao;
    }

    public static UnidadeMedida getUnidadeMedidaPorDescricao(final String descricao) {
        for (final UnidadeMedida unidadeMedida : UnidadeMedida.values()) {
            if (unidadeMedida.descricao.equals(descricao)) {
                return unidadeMedida;
            }
        }

        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
}
