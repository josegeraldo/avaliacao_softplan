package br.com.softplan.controle_financeiro.usecase;

import java.util.List;

import br.com.softplan.controle_financeiro.entidade.NotaFiscal;

/**
 * Classe com a geração de observação original
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class GeradorObservacaoSimples extends GeradorObservacao {

    public GeradorObservacaoSimples(final List<NotaFiscal> notasFiscais,
            final UnidadeMonetaria unidadeMonetaria, final FormatoDecimal formatoDecimal) {
        super(notasFiscais, unidadeMonetaria, formatoDecimal);
    }

	@Override
	public String getInformacaoNotaFiscal(final NotaFiscal notaFiscal) {
		final String informacao = String.format("%s", notaFiscal.getId());
		return informacao;
	}

    @Override
    public String getEncerramentoObservacao() {
        return ".";
    }
	

}
