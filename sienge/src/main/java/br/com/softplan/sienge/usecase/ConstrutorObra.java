package br.com.softplan.sienge.usecase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.softplan.sienge.entidade.Composicao;
import br.com.softplan.sienge.entidade.Item;
import br.com.softplan.sienge.entidade.Obra;
import br.com.softplan.sienge.entidade.TipoItem;
import br.com.softplan.sienge.entidade.UnidadeMedida;

/**
 * Classe que dirige a construção de objetos de insumo e de composição da obra
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class ConstrutorObra {

    /**
     * itens já processados na analise do arquivo da obra
     */
    private Map<String, Item> itensCadastrados = new HashMap<>();

    final Obra obra = new Obra();

    public Item construirItem(final String tipoItemDescricao, final String codigo, final String descricao,
            final String descricaoUnidadeMedida, final Double valorUnitario) {
        
        if (!itensCadastrados.containsKey(codigo)) {
            final UnidadeMedida unidadeMedida = UnidadeMedida
                    .getUnidadeMedidaPorDescricao(descricaoUnidadeMedida);

            TipoItem tipoItem = TipoItem.getTipoItemPorDescricao(tipoItemDescricao);
            Item item = tipoItem.criarItem(codigo, descricao, unidadeMedida, valorUnitario);

            itensCadastrados.put(codigo, item);
        }

        return itensCadastrados.get(codigo);
    }

    public Composicao construirComposicao(final String codigo, final String descricao,
            final String descricaoUnidadeMedida, final Double valorUnitario, final Item item, final Double quantidade) {

        final Composicao composicao = (Composicao) this.construirItem("COMPOSICAO", codigo, descricao,
                descricaoUnidadeMedida, valorUnitario);
        composicao.adicionarItem(item, quantidade);

        final List<Composicao> composicoesObra = obra.getComposicoes();
        if (!composicoesObra.contains(composicao)) {
            composicoesObra.add(composicao);
        }

        return composicao;
    }

    public Obra getObra() {
        return this.obra;
    }
}
