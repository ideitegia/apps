package com.ideitegia.skoolan.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TreeGroup extends Composite {
	
	// Annotation can be used to change the name of the associated xml file
	// @UiTemplate("HelloWidgetWorld.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, TreeGroup> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	VerticalPanel groupGrid;
	
	public TreeGroup() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		TextBox text = new TextBox();
		text.setValue("GROUP header");
		groupGrid.add(text);
		text = new TextBox();
		text.setValue("GROUP body");
		groupGrid.add(text);
		text = new TextBox();
		text.setValue("GROUP footer");
		groupGrid.add(text);
	}

}