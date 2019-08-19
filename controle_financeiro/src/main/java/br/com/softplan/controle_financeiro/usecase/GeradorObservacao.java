package br.com.softplan.controle_financeiro.usecase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import br.com.softplan.controle_financeiro.entidade.NotaFiscal;

/**
 * Classe que possui o método template geraObservacao As subclasses implementam
 * as partes que compõem o algoritmo do método Caso uma classe queira redefinir
 * comportamento fora do template, é possível sobrescrever o método
 * geraObservacao
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public abstract class GeradorObservacao {

    protected static Properties props;
    static {
        InputStream stream = GeradorObservacao.class.getResourceAsStream("/messages.properties");
        if (stream == null) {
            throw new Error("Arquivo messages.properties não está presente na aplicação.");
        }

        props = new Properties();
        try {
            props.load(stream);
        }
        catch (IOException e) {
            throw new Error("Erro ao carregar o arquivo messages.properties", e);
        }

    }

    protected List<NotaFiscal> notasFiscais = new LinkedList<>();
	protected UnidadeMonetaria unidadeMonetaria;
	protected FormatoDecimal formatoDecimal;
	
	public GeradorObservacao() {
		super();
	}
	
    public GeradorObservacao(final List<NotaFiscal> notasFiscais, final UnidadeMonetaria unidadeMonetaria,
            final FormatoDecimal formatoDecimal) {
        super();
        if (notasFiscais != null) {
            this.notasFiscais.addAll(notasFiscais);
        }
        this.unidadeMonetaria = unidadeMonetaria;
        this.formatoDecimal = formatoDecimal;
    }

    public String getCabecalhoObservacao() {
	    final StringBuilder cabecalho = new StringBuilder();

        if (this.notasFiscais.size() > 0) {
            final String keyPrefix = this.getClass().getName().toLowerCase();
            final String keySuffix = (this.notasFiscais.size() == 1 ? "mensagem_nota_fiscal_unica"
                    : "mensagem_nota_fiscal_multipla");
            cabecalho.append(String.format("%s: ", props.get(String.format("%s.%s", keyPrefix, keySuffix))));
        }
        return cabecalho.toString();
	}

	public String geraObservacao() {
		
		final StringBuilder observacao = new StringBuilder();
		
		if( !notasFiscais.isEmpty() ) {
		
			observacao.append( this.getCabecalhoObservacao() );
			
            Iterator<NotaFiscal> notaFiscalIterator = notasFiscais.iterator();
            final NotaFiscal notaFiscal = notaFiscalIterator.next();
            observacao.append(this.getInformacaoNotaFiscal(notaFiscal));

            while (notaFiscalIterator.hasNext()) {
				
                final NotaFiscal proximaNotaFiscal = notaFiscalIterator.next();
                
                final String separador = (notaFiscalIterator.hasNext() ? ", " : " e ");

                observacao.append(String.format("%s%s", separador, this.getInformacaoNotaFiscal(proximaNotaFiscal)));

			}
            
            observacao.append(this.getEncerramentoObservacao());
		}
		
		
		return observacao.toString();
	}
	
	public void setNotasFiscais(final List<NotaFiscal> notasFiscais) {
        if (notasFiscais != null) {
            this.notasFiscais.addAll(notasFiscais);
        }

	}
	
	public void setUnidadeMonetaria(final UnidadeMonetaria unidadeMonetaria) {
		this.unidadeMonetaria = unidadeMonetaria;
	}
	
	public void setFormatoDecimal(final FormatoDecimal formatoDecimal) {
		this.formatoDecimal = formatoDecimal;
	}

    public abstract String getInformacaoNotaFiscal(NotaFiscal notaFiscal);

    public abstract String getEncerramentoObservacao();
}
