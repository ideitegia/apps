/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ideitegia.skoolan.client.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPanel extends Composite {
	private TextBox textBoxUsername;
	private TextBox textBoxPassword;
	
	public LoginPanel() {
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblLoginToYour = new Label("Sign in to your account");
		lblLoginToYour.setStyleName("gwt-Label-Login");
		verticalPanel.add(lblLoginToYour);
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		flexTable.setWidth("345px");
		
		Label lblUsername = new Label("Username:");
		lblUsername.setStyleName("gwt-Label-Login");
		flexTable.setWidget(0, 0, lblUsername);
		
		textBoxUsername = new TextBox();
		flexTable.setWidget(0, 1, textBoxUsername);
		
		Label lblPassword = new Label("Password:");
		lblPassword.setStyleName("gwt-Label-Login");
		flexTable.setWidget(1, 0, lblPassword);
		
		textBoxPassword = new TextBox();
		textBoxPassword.setDirection(Direction.RTL);
		flexTable.setWidget(1, 1, textBoxPassword);
		
		CheckBox chckbxRememberMeOn = new CheckBox("Remember me on this computer");
		chckbxRememberMeOn.setStyleName("gwt-Login-CheckBox");
		flexTable.setWidget(2, 1, chckbxRememberMeOn);
		
		Button btnSignIn = new Button("Sign In");
		btnSignIn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (textBoxUsername.getText().length() == 0
						|| textBoxPassword.getText().length() == 0) {
						Window.alert("Username or password is empty."); 
					}
			}
		});
		flexTable.setWidget(3, 1, btnSignIn);
	}
}
