package br.com.softplan.sienge.entidade;

public class Item {

    private String codigo;

    private String descricao;

    private UnidadeMedida unidadeMedida;

    private Double valorUnitario;

    public Item() {
        super();
    }

    public Item(final String codigo, final String descricao, final UnidadeMedida unidadeMedida) {
        super();
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
    }

    public Item(final String codigo, final String descricao, final UnidadeMedida unidadeMedida,
            final Double valorUnitario) {
        super();
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.valorUnitario = valorUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(final UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(final Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Item) {
            Item item = ( Item ) obj;
            return this.getCodigo().equals(item.getCodigo());
        }

        return false;
    }
}
