package br.com.softplan.controle_financeiro.usecase;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import br.com.softplan.controle_financeiro.entidade.NotaFiscal;

public class GeradorObservacaoSimplesTest {

    @Test
    public void testeMultiplasNotas() {

        NotaFiscal nota1 = new NotaFiscal(1);
        NotaFiscal nota2 = new NotaFiscal(2);
        NotaFiscal nota3 = new NotaFiscal(3);
        NotaFiscal nota4 = new NotaFiscal(4);
        NotaFiscal nota5 = new NotaFiscal(5);
        final List<NotaFiscal> notasFiscais = new LinkedList<>(Arrays.asList(new NotaFiscal[] { nota1, nota2, nota3, nota4, nota5 }));
        
        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.SIMPLES.getGeradorObservacao(notasFiscais,
                UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5.",
                observacao);
    }

    @Test
    public void testeNotaUnica() {

        NotaFiscal nota1 = new NotaFiscal(1);
        final List<NotaFiscal> notasFiscais = new LinkedList<>(
                Arrays.asList(new NotaFiscal[] { nota1 }));

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.SIMPLES.getGeradorObservacao(notasFiscais,
                UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("Fatura da nota fiscal de simples remessa: 1.", observacao);
    }

    @Test
    public void testeDuasNotas() {

        NotaFiscal nota1 = new NotaFiscal(1);
        NotaFiscal nota2 = new NotaFiscal(2);
        final List<NotaFiscal> notasFiscais = new LinkedList<>(
                Arrays.asList(new NotaFiscal[] { nota1, nota2 }));

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.SIMPLES.getGeradorObservacao(notasFiscais,
                UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("Fatura das notas fiscais de simples remessa: 1 e 2.", observacao);
    }

    @Test
    public void testeNenhumaNota() {

        final List<NotaFiscal> notasFiscais = new LinkedList<>();

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.SIMPLES.getGeradorObservacao(notasFiscais,
                UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("", observacao);
    }

    @Test
    public void testeNotasFiscaisNula() {

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.SIMPLES.getGeradorObservacao(null,
                UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("", observacao);
    }
}
