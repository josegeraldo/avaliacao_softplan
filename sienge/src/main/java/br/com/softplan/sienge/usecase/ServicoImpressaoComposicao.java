package br.com.softplan.sienge.usecase;

import br.com.softplan.sienge.controller.FormatoDecimal;
import br.com.softplan.sienge.entidade.Composicao;

/**
 * Classe de serviço de impressão de informações de composição
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class ServicoImpressaoComposicao {

    private Composicao composicao;

    public ServicoImpressaoComposicao() {
        super();
    }

    public ServicoImpressaoComposicao(final Composicao composicao) {
        super();
        this.composicao = composicao;
    }

    public String imprimir() {
        
        final String formatoImpressao = "%s\t%s\t%s\t%s";
        
        return String.format(formatoImpressao, composicao.getCodigo(), composicao.getDescricao(),
                composicao.getUnidadeMedida().getDescricao(),
                FormatoDecimal.BRASIL.getFormatoDecimal(composicao.getValorComposicao()));
    }

    public Composicao getComposicao() {
        return composicao;
    }

    public void setComposicao(final Composicao composicao) {
        this.composicao = composicao;
    }
}
