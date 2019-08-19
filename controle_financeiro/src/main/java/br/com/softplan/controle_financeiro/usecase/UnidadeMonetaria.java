package br.com.softplan.controle_financeiro.usecase;

public enum UnidadeMonetaria {

	REAL
	{
		@Override
		public String getSimboloMonetario() {
			return "R$";
		}
		
	};
	
	public abstract String getSimboloMonetario();
	
}
