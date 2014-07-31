package com.ideitegia.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AlumnGrid extends Composite {
	
	interface MyUiBinder extends UiBinder<Widget, AlumnGrid> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	public AlumnGrid() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
	}
		
}