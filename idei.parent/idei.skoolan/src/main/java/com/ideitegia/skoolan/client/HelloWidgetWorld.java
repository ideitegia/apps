package com.ideitegia.skoolan.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class HelloWidgetWorld extends Composite {
	// Annotation not needed as we use the default but this allows to change the path
	@UiTemplate("HelloWidgetWorld.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, HelloWidgetWorld> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	ListBox listBox;

	@UiField
	Button submit;

	public HelloWidgetWorld(String... names) {
		// sets listBox
		initWidget(uiBinder.createAndBindUi(this));
		for (String name : names) {
			listBox.addItem(name);
		}
	}

	@UiHandler("submit")
	void handleClick(ClickEvent e) {
		Window.alert("Hello, UiBinder");
	}

}
