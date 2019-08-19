package br.com.softplan.sienge.usecase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.softplan.sienge.entidade.Composicao;
import br.com.softplan.sienge.entidade.Insumo;
import br.com.softplan.sienge.entidade.UnidadeMedida;

public class ServicoImpressaoComposicaoTest {

    private ServicoImpressaoComposicao servico;

    @Before
    public void instanciarServicoComposicao() {
        servico = new ServicoImpressaoComposicao();
    }

    @Test
    public void testeComposicaoSomenteInsumos() {

        Insumo insumo1 = new Insumo("1", "Insumo 1", UnidadeMedida.UNIDADE, new Double(1.5));
        Insumo insumo2 = new Insumo("2", "Insumo 2", UnidadeMedida.UNIDADE, new Double(2.5));

        Composicao composicao1 = new Composicao("3", "Composicao 1", UnidadeMedida.UNIDADE);
        composicao1.adicionarItem(insumo1, new Double(2));
        composicao1.adicionarItem(insumo2, new Double(4));

        servico.setComposicao(composicao1);

        assertEquals("3\tComposicao 1\tUN\t13,00", servico.imprimir());

    }

    @Test
    public void testeComposicaoComposta() {

        Insumo insumo1 = new Insumo("1", "Insumo 1", UnidadeMedida.UNIDADE, new Double(1.5));
        Insumo insumo2 = new Insumo("2", "Insumo 2", UnidadeMedida.UNIDADE, new Double(2.5));

        Composicao composicao1 = new Composicao("3", "Composicao 1", UnidadeMedida.UNIDADE);
        composicao1.adicionarItem(insumo1, new Double(2));
        composicao1.adicionarItem(insumo2, new Double(4));

        Composicao composicao2 = new Composicao("4", "Composicao 2", UnidadeMedida.METROS_QUADRADOS);
        composicao2.adicionarItem(insumo1, new Double(5));
        composicao2.adicionarItem(composicao1, new Double(7));

        servico.setComposicao(composicao2);

        assertEquals("4\tComposicao 2\tM2\t98,50", servico.imprimir());

    }
}
