package com.ideitegia.skoolan.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ideitegia.entity.client.model.Alumn;
import com.ideitegia.entity.client.model.Person;

public class TreeAlumnGroup extends Composite {
	
	// Annotation can be used to change the name of the associated xml file
	// @UiTemplate("HelloWidgetWorld.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, TreeAlumnGroup> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	VerticalPanel alumnGroupGrid;
	
	public TreeAlumnGroup() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		
		
		Alumn alumn = new Alumn();
		alumn.setPerson(new Person());
		TextBox text = new TextBox();
		alumn.getPerson().setName("Ikaslea: Oinatz");
		text.setValue(alumn.getPerson().getName());
		alumnGroupGrid.add(text);
		text = new TextBox();
		alumn = new Alumn();
		alumn.setPerson(new Person());
		alumn.getPerson().setName("Ikaslea: Aimar");
		text.setValue(alumn.getPerson().getName());
		alumnGroupGrid.add(text);
		text = new TextBox();
		alumn = new Alumn();
		alumn.setPerson(new Person());
		alumn.getPerson().setName("Ikaslea: Ange");
		text.setValue(alumn.getPerson().getName());
		alumnGroupGrid.add(text);
	}

}