package com.ideitegia.skoolan.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TreeTeacherGroup extends Composite {
	
	// Annotation can be used to change the name of the associated xml file
	// @UiTemplate("HelloWidgetWorld.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, TreeTeacherGroup> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	VerticalPanel teacherGroupGrid;
	
	public TreeTeacherGroup() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		TextBox text = new TextBox();
		text.setValue("TEACHER LIST header");
		teacherGroupGrid.add(text);
		text = new TextBox();
		text.setValue("TEACHER LIST body");
		teacherGroupGrid.add(text);
		text = new TextBox();
		text.setValue("TEACHER LIST footer");
		teacherGroupGrid.add(text);
	}

}