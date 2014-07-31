package com.ideitegia.common.client.i18n;

import java.util.Date;

import com.google.gwt.i18n.client.Messages;

public interface CommonMessages extends Messages {

	@DefaultMessage("apptitle")
	String appTitle();

	@DefaultMessage("''{0}'' no es un símbolo válido.")
	String invalidSymbol(String symbol);

	@DefaultMessage("Última actualización: {0,date,medium} {0,time,medium}")
	String lastUpdate(Date timestamp);
	
	@DefaultMessage("No se han encontrado resultados")
	String dataGridEmpty();

	@DefaultMessage("Dirección")
	String dataGridColumnAddress();

	@DefaultMessage("Edad")
	String dataGridColumnAge();

	@DefaultMessage("Nombre")
	String dataGridColumnFirstName();

	@DefaultMessage("Apellido")
	String dataGridColumnLastName();


}