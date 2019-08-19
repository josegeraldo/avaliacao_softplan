package br.com.softplan.controle_financeiro.usecase;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public enum FormatoDecimal {

	BRASIL
	{

		@Override
        public String getFormatoDecimal(final Double valor) {
            DecimalFormat df = new DecimalFormat("###,##0.00");
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator(',');
            dfs.setGroupingSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            return df.format(valor);

		}
		
	};
	
    public abstract String getFormatoDecimal(final Double valor);
}
