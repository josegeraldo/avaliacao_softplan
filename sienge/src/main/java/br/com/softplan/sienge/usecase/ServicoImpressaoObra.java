package br.com.softplan.sienge.usecase;

import java.util.Iterator;

import br.com.softplan.sienge.entidade.Composicao;
import br.com.softplan.sienge.entidade.Obra;

/**
 * Classe de serviço de impressão de informações da obra
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class ServicoImpressaoObra {

    private ServicoImpressaoComposicao servicoImpressaoComposicao = new ServicoImpressaoComposicao();
    private Obra obra;

    public ServicoImpressaoObra () {
        super();
    }

    public ServicoImpressaoObra(final Obra obra) {
        this.obra = obra;
    }

    public String imprimir() {
        final StringBuilder impressao = new StringBuilder();

        for (final Iterator<Composicao> composicaoIt = obra.getComposicoes().iterator(); composicaoIt
                .hasNext();) {
            servicoImpressaoComposicao.setComposicao(composicaoIt.next());

            impressao.append(servicoImpressaoComposicao.imprimir());

            if (composicaoIt.hasNext()) {
                impressao.append("\n");
            }
        }

        return impressao.toString();
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(final Obra obra) {
        this.obra = obra;
    }
}
