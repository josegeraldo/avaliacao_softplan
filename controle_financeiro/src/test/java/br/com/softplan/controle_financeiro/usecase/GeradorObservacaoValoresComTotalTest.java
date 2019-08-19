package br.com.softplan.controle_financeiro.usecase;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import br.com.softplan.controle_financeiro.entidade.NotaFiscal;

public class GeradorObservacaoValoresComTotalTest {

    @Test
    public void testeMultiplasNotas() {

        NotaFiscal nota1 = new NotaFiscal(1, new Double(10));
        NotaFiscal nota2 = new NotaFiscal(2, new Double(35));
        NotaFiscal nota3 = new NotaFiscal(3, new Double(5));
        NotaFiscal nota4 = new NotaFiscal(4, new Double(1500));
        NotaFiscal nota5 = new NotaFiscal(5, new Double(0.3));
        final List<NotaFiscal> notasFiscais = new LinkedList<>(
                Arrays.asList(new NotaFiscal[] { nota1, nota2, nota3, nota4, nota5 }));

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.VALORES_COM_TOTAIS
                .getGeradorObservacao(notasFiscais, UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals(
                "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = R$ 1.550,30.",
                observacao);
    }

    @Test
    public void testeMultiplasNotasValoresGrandes() {

        NotaFiscal nota1 = new NotaFiscal(1, new Double(100000000));
        NotaFiscal nota2 = new NotaFiscal(2, new Double(35));
        NotaFiscal nota3 = new NotaFiscal(3, new Double(5));
        NotaFiscal nota4 = new NotaFiscal(4, new Double(1500));
        NotaFiscal nota5 = new NotaFiscal(5, new Double(0.3));
        final List<NotaFiscal> notasFiscais = new LinkedList<>(
                Arrays.asList(new NotaFiscal[] { nota1, nota2, nota3, nota4, nota5 }));

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.VALORES_COM_TOTAIS
                .getGeradorObservacao(notasFiscais, UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals(
                "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 100.000.000,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = R$ 100.001.540,30.",
                observacao);
    }

    @Test
    public void testeNotaUnica() {

        NotaFiscal nota1 = new NotaFiscal(1, new Double(10));
        final List<NotaFiscal> notasFiscais = new LinkedList<>(
                Arrays.asList(new NotaFiscal[] { nota1 }));

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.VALORES_COM_TOTAIS
                .getGeradorObservacao(notasFiscais, UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals(
                "Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00. Total = R$ 10,00.",
                observacao);
    }

    @Test
    public void testeDuasNotas() {

        NotaFiscal nota1 = new NotaFiscal(1, new Double(10));
        NotaFiscal nota2 = new NotaFiscal(2, new Double(20));
        final List<NotaFiscal> notasFiscais = new LinkedList<>(
                Arrays.asList(new NotaFiscal[] { nota1, nota2 }));

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.VALORES_COM_TOTAIS
                .getGeradorObservacao(notasFiscais, UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals(
                "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00 e 2 cujo valor é R$ 20,00. Total = R$ 30,00.",
                observacao);
    }

    @Test
    public void testeNenhumaNota() {

        final List<NotaFiscal> notasFiscais = new LinkedList<>();

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.VALORES_COM_TOTAIS
                .getGeradorObservacao(notasFiscais, UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("", observacao);
    }

    @Test
    public void testeNotasFiscaisNula() {

        GeradorObservacao geradorObservacao = GeradorObservacaoEnum.VALORES_COM_TOTAIS
                .getGeradorObservacao(null, UnidadeMonetaria.REAL, FormatoDecimal.BRASIL);

        final String observacao = geradorObservacao.geraObservacao();
        assertEquals("", observacao);
    }
}
