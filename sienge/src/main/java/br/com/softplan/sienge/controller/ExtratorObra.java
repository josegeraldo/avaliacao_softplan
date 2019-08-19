package br.com.softplan.sienge.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.softplan.sienge.entidade.Item;
import br.com.softplan.sienge.entidade.Obra;
import br.com.softplan.sienge.usecase.ConstrutorObra;
import br.com.softplan.sienge.usecase.ServicoImpressaoObra;

/**
 * Classe que extrai informações da obra no arquivo e exibe em saída padrão
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class ExtratorObra {

    private List<ItemJSON> listItensJSON = new LinkedList<>();

    public void extrairItens(final String caminhoArquivo) {

        File file = new File(caminhoArquivo);
        try (
                InputStream stream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(stream);) {

            StringBuilder conteudoArquivo = new StringBuilder();
            char[] buff = new char[4096];
            int lidos = 0;
            while ((lidos = reader.read(buff)) > -1) {
                conteudoArquivo.append(buff, 0, lidos);
            }

            Gson gson = new Gson();
            listItensJSON.addAll(
                    gson.fromJson(conteudoArquivo.toString(), new TypeToken<List<ItemJSON>>() {}.getType()));
        }
        catch (IOException e) {
            throw new Error("Erro ao carregar o arquivo " + caminhoArquivo, e);
        }
            
    }

    public Obra construirObra() {

        final ConstrutorObra construtor = new ConstrutorObra();

        for (final ItemJSON itemJSON : listItensJSON) {

            final String tipoItem = itemJSON.getTipoItem();
            final String codigoItem = itemJSON.getCodigoItem();
            final String descricaoItem = itemJSON.getDescricaoItemComposicao();
            final String descricaoUnidadeMedidaItem = itemJSON.getUnidadeItem();
            final String valorUnitarioStr = itemJSON.getValorUnitario();
            final Double valorUnitarioItem = "".equals(valorUnitarioStr) ? null
                    : new BigDecimal(valorUnitarioStr.replace(".", "").replace(",", ".")).doubleValue();
            final Double quantidade = new BigDecimal(
                    itemJSON.getQuantidadeComposicao().replace(".", "").replace(",", ".")).doubleValue();

            final Item item = construtor.construirItem(tipoItem, codigoItem, descricaoItem,
                    descricaoUnidadeMedidaItem, valorUnitarioItem);

            final String codigoComposicao = itemJSON.getCodigoComposicao();
            final String descricaoComposicao = itemJSON.getDescricaoComposicao();
            final String descricaoUnidadeMedidaComposicao = itemJSON.getUnidadeComposicao();

            construtor.construirComposicao(codigoComposicao,
                    descricaoComposicao, descricaoUnidadeMedidaComposicao, null, item, quantidade);
        }
        
        return construtor.getObra();
    }

    public static void main(final String[] args) {

        ExtratorObra extrator = new ExtratorObra();
        extrator.extrairItens(args[0]);
        final Obra obra = extrator.construirObra();

        ServicoImpressaoObra servicoImpressaoObra = new ServicoImpressaoObra(obra);
        System.out.println(servicoImpressaoObra.imprimir());
    }
}
