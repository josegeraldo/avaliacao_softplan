package br.com.softplan.sienge.usecase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.softplan.sienge.entidade.Composicao;
import br.com.softplan.sienge.entidade.Insumo;
import br.com.softplan.sienge.entidade.Obra;
import br.com.softplan.sienge.entidade.UnidadeMedida;

public class ServicoImpressaoObraTest {

    private ServicoImpressaoObra servico;

    @Before
    public void instanciarServicoComposicao() {
        servico = new ServicoImpressaoObra();
    }

    @Test
    public void testeObraComposicoesSimples() {

        Insumo insumo1 = new Insumo("1", "Insumo 1", UnidadeMedida.UNIDADE, new Double(1.5));
        Insumo insumo2 = new Insumo("2", "Insumo 2", UnidadeMedida.UNIDADE, new Double(2.5));

        Insumo insumo3 = new Insumo("3", "Insumo 3", UnidadeMedida.UNIDADE, new Double(3.5));
        Insumo insumo4 = new Insumo("4", "Insumo 4", UnidadeMedida.UNIDADE, new Double(4.5));

        Composicao composicao1 = new Composicao("5", "Composicao 1", UnidadeMedida.UNIDADE);
        composicao1.adicionarItem(insumo1, new Double(2));
        composicao1.adicionarItem(insumo2, new Double(4));

        Composicao composicao2 = new Composicao("6", "Composicao 2", UnidadeMedida.METROS_QUADRADOS);
        composicao2.adicionarItem(insumo3, new Double(2));
        composicao2.adicionarItem(insumo4, new Double(4));

        Obra obra = new Obra();
        obra.getComposicoes().add(composicao1);
        obra.getComposicoes().add(composicao2);

        servico.setObra(obra);

        assertEquals("5\tComposicao 1\tUN\t13,00\n6\tComposicao 2\tM2\t25,00", servico.imprimir());

    }

    @Test
    public void testeObraComposicoesCompostas() {

        Insumo insumo1 = new Insumo("1", "Insumo 1", UnidadeMedida.UNIDADE, new Double(1.5));
        Insumo insumo2 = new Insumo("2", "Insumo 2", UnidadeMedida.UNIDADE, new Double(2.5));

        Insumo insumo3 = new Insumo("3", "Insumo 3", UnidadeMedida.UNIDADE, new Double(3.5));
        Insumo insumo4 = new Insumo("4", "Insumo 4", UnidadeMedida.UNIDADE, new Double(4.5));

        Composicao composicao1 = new Composicao("5", "Composicao 1", UnidadeMedida.UNIDADE);
        composicao1.adicionarItem(insumo1, new Double(2));
        composicao1.adicionarItem(insumo2, new Double(4));

        Composicao composicao2 = new Composicao("6", "Composicao 2", UnidadeMedida.METROS_QUADRADOS);
        composicao2.adicionarItem(insumo3, new Double(2));
        composicao2.adicionarItem(insumo4, new Double(4));
        composicao2.adicionarItem(composicao1, new Double(2));

        Obra obra = new Obra();
        obra.getComposicoes().add(composicao1);
        obra.getComposicoes().add(composicao2);

        servico.setObra(obra);

        assertEquals("5\tComposicao 1\tUN\t13,00\n6\tComposicao 2\tM2\t51,00", servico.imprimir());

    }
}
