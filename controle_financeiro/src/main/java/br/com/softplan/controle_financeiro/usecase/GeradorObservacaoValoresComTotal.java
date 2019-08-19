package br.com.softplan.controle_financeiro.usecase;

import java.util.List;

import br.com.softplan.controle_financeiro.entidade.NotaFiscal;

/**
 * Classe com a geração de observação em novo formato solicitado
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class GeradorObservacaoValoresComTotal extends GeradorObservacao {

	public GeradorObservacaoValoresComTotal() {
		super();
	}

    public GeradorObservacaoValoresComTotal(final List<NotaFiscal> notasFiscais,
            final UnidadeMonetaria unidadeMonetaria, final FormatoDecimal formatoDecimal) {
        super(notasFiscais, unidadeMonetaria, formatoDecimal);
    }

	@Override
	public String getInformacaoNotaFiscal(final NotaFiscal notaFiscal) {
		final String simboloMonetario = this.unidadeMonetaria.getSimboloMonetario();
        final Double valorNotaFiscal = notaFiscal.getValor();
        final String valorFormatado = this.formatoDecimal.getFormatoDecimal(valorNotaFiscal);
        final String informacao = String.format("%s cujo valor é %s %s", notaFiscal.getId(), simboloMonetario,
                valorFormatado);
		return informacao;
	}

    @Override
    public String getEncerramentoObservacao() {
        final String simboloMonetario = this.unidadeMonetaria.getSimboloMonetario();
        Double valorTotal = new Double(0);
        for( final NotaFiscal notaFiscal : notasFiscais ) {
            valorTotal += notaFiscal.getValor();
        }
        final String valorFormatado = this.formatoDecimal.getFormatoDecimal(valorTotal);
        return String.format(". Total = %s %s.", simboloMonetario, valorFormatado);
    }
	
	
}
