package br.com.softplan.controle_financeiro.usecase;

import java.util.List;

import br.com.softplan.controle_financeiro.entidade.NotaFiscal;

public enum GeradorObservacaoEnum {

	SIMPLES
	{
		@Override
        public GeradorObservacao getGeradorObservacao(final List<NotaFiscal> notasFiscais,
                final UnidadeMonetaria unidadeMonetaria, final FormatoDecimal formatoDecimal) {
            return new GeradorObservacaoSimples(notasFiscais, unidadeMonetaria, formatoDecimal);
		}
	}, 
	VALORES_COM_TOTAIS
	{
		@Override
        public GeradorObservacao getGeradorObservacao(final List<NotaFiscal> notasFiscais,
                final UnidadeMonetaria unidadeMonetaria, final FormatoDecimal formatoDecimal) {
            return new GeradorObservacaoValoresComTotal(notasFiscais, unidadeMonetaria, formatoDecimal);
		}
	};
	
    public abstract GeradorObservacao getGeradorObservacao(final List<NotaFiscal> notasFiscais,
            final UnidadeMonetaria unidadeMonetaria, final FormatoDecimal formatoDecimal);
	
}
