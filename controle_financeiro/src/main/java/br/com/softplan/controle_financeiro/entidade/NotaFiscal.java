package br.com.softplan.controle_financeiro.entidade;

/**
 * Classe que representa a entidade Nota Fiscal
 * 
 * @author José Geraldo de Sousa Junior
 *
 */
public class NotaFiscal {

	private Integer id;
	private Double valor;
	
	public NotaFiscal() {
		super();
	}
	
	public NotaFiscal(final Integer id, final Double valor) {
		this.id = id;
		this.valor = valor;
	}
	
	public NotaFiscal(final Integer id) {
        this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(final Double valor) {
		this.valor = valor;
	}
}
