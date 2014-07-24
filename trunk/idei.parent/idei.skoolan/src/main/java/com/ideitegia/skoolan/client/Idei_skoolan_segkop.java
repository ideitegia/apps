package com.ideitegia.skoolan.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ideitegia.entity.client.model.Alumn;
import com.ideitegia.entity.client.model.Person;
import com.ideitegia.entity.client.model.Teacher;
import com.ideitegia.skoolan.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Idei_skoolan_segkop implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private HTMLPanel selectedTreeItemContent;
	
    private TreeClassRoom classRoom;
	private TreeGroup group;
	private TreeSubject subject;
	private TreeTeacherGroup teacherGroup;
	private TreeAlumnGroup alumnGroup;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send to server");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
//		RootPanel.get("nameFieldContainer").add(nameField);
//		RootPanel.get("sendButtonContainer").add(sendButton);
//		RootPanel.get("errorLabelContainer").add(errorLabel);		
		
		
		Alumn alumn = new Alumn();
		alumn.setPerson(new Person());
		alumn.getPerson().setName("Alumn");
		Label alumnLabel = new Label("Hello " + alumn.getPerson().getName());
	    
	    Teacher teacher = new Teacher();
	    teacher.setPerson(new Person());
		teacher.getPerson().setName("Teacher");
	    Label teacherLabel = new Label("Hello " + teacher.getPerson().getName());
	    
		
	    
	    
		 // Create a Split Panel
	    SplitLayoutPanel splitPanel = new SplitLayoutPanel(5);
	    splitPanel.ensureDebugId("cwSplitLayoutPanel");
	    splitPanel.getElement().getStyle().setProperty("border", "3px solid #e7e7e7");
	    splitPanel.getElement().getStyle().setProperty("background-color", "#000000");
	    splitPanel.setHeight("100%");
	    splitPanel.setWidth("100%");

	    
	    splitPanel.addNorth(new Label("NORTH"), 50);
//	    splitPanel.addSouth(new Label("SOUTH"), 50);
	    splitPanel.addEast(new Label("EAST"), 100);
//	    splitPanel.addWest(new Label("WEST"), 100);
//	    splitPanel.addNorth(new Label("NORTH 2"), 50);
//	    splitPanel.addSouth(new Label("SOUTH 2"), 50);
	    
	    
	    // Add text all around.
//	    splitPanel.addNorth(new Label(constants.cwSplitLayoutPanelNorth1()), 50);
//	    splitPanel.addSouth(new Label(constants.cwSplitLayoutPanelSouth1()), 50);
//	    splitPanel.addEast(new Label(constants.cwSplitLayoutPanelEast()), 100);
//	    splitPanel.addWest(new Label(constants.cwSplitLayoutPanelWest()), 100);
//	    splitPanel.addNorth(new Label(constants.cwSplitLayoutPanelNorth2()), 50);
//	    splitPanel.addSouth(new Label(constants.cwSplitLayoutPanelSouth2()), 50);

	    // Add scrollable text to the center.
//	    String centerText = constants.cwSplitLayoutPanelCenter();
//	    for (int i = 0; i < 3; i++) {
//	      centerText += " " + centerText;
//	    }
	    Label centerLabel = new Label("centerText");
	    ScrollPanel centerScrollable = new ScrollPanel(centerLabel);
	    splitPanel.add(centerScrollable);
	    
	    
	    
		
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.addStyleName("width-all");
		mainPanel.setSpacing(0);	    
//		RootPanel.get("content").add(mainPanel);
//		RootPanel.get("content").add(splitPanel);
		
		// Create a three-pane layout with splitters. 
	    SplitLayoutPanel p = new SplitLayoutPanel();
	    p.addWest(obtainMainTree(), 200);
//	    p.add(new HTML("content"));
	    p.add(selectedTreeItemContent);
	    p.addSouth(new HTML("south"), 5);

	    // Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
	    // resize events on the window to ensure that its children are informed of
	    // possible size changes.
	    RootLayoutPanel rp = RootLayoutPanel.get();
	    rp.add(p);

	    HorizontalPanel headerPanel = new HorizontalPanel();
	    final Label headerText1 = new Label();
	    headerText1.setText("Skoolan aplikazioa");
	    final Label headerText2 = new Label();
	    headerText2.setText("Text 2");
	    final Label headerText3 = new Label();
	    headerText3.setText("Text 3");
	    headerPanel.add(headerText1);
	    headerPanel.add(headerText2);
	    headerPanel.add(headerText3);
	    headerPanel.add(alumnLabel);
	    headerPanel.add(teacherLabel);
//	    headerPanel.setHeight("100px");
	    headerPanel.addStyleName("header");
	    
//	    mainPanel.add(headerPanel);
		
//	    HorizontalPanel hPanel = new HorizontalPanel();
//	    mainPanel.add(hPanel);
//	    hPanel.setSpacing(0);
	    mainPanel.add(splitPanel);
	    
	    // right side tree content
//	    hPanel.add(obtainMainTree());
	    
//	    splitPanel.addWest(obtainMainTree(), 100);
	    
	    // left side content
//	    VerticalPanel vPanel = new VerticalPanel();
//	    vPanel.setSpacing(0);	    
//	    vPanel.add(nameField);
//	    vPanel.add(sendButton);
//	    vPanel.add(errorLabel);
	    
	    
	    
//	    HelloWidgetWorld helloWorld =
//	    	      new HelloWidgetWorld("able", "baker", "charlie");
//	    	        vPanel.add(helloWorld);
//	    	        vPanel.add(new MyHTMLTable());
	    	    

//	    hPanel.add(vPanel);
	    
	    
	    selectedTreeItemContent = new HTMLPanel("");

	    classRoom = new TreeClassRoom();
	    group = new TreeGroup();
	    subject = new TreeSubject();
	    teacherGroup = new TreeTeacherGroup();
	    alumnGroup = new TreeAlumnGroup();
	    
	    classRoom.setVisible(false);
	    group.setVisible(false);
	    subject.setVisible(false);
	    teacherGroup.setVisible(false);
	    alumnGroup.setVisible(false);
	
	    selectedTreeItemContent.add(classRoom);
		selectedTreeItemContent.add(group);
		selectedTreeItemContent.add(subject);
		selectedTreeItemContent.add(teacherGroup);
		selectedTreeItemContent.add(alumnGroup);
	    
//	    hPanel.add(selectedTreeItemContent);
//		splitPanel.addEast(selectedTreeItemContent, 100);
	    
//		RootPanel.get("treeNodeContent").add(nameField);
//		RootPanel.get("treeNodeContent").add(sendButton);
//		RootPanel.get("treeNodeContent").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		
	    
	    
	    
	    
//	    RootPanel.get("treeContent").add(stackPanel);
		
	}
	
	private Widget obtainMainTree(){

//		VerticalPanel vPanel = new VerticalPanel();
//	    vPanel.setSpacing(4);
//	 // Add some content to the panel
//	    for (int i = 1; i < 3; i++) {
//	      vPanel.add(new HorizontalPanel());
//	    }
//	    
//	    RootPanel.get("treeContent").add(vPanel);
	    
	    StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
	    stackPanel.setPixelSize(200, 600);
//		stackPanel.setSize("200px", "100%");

	    // Add the Mail folders.
//	    Widget mailHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelMailHeader());
//	    stackPanel.add(createMailItem(images), mailHeader, 4);

//	    Centro educativo / Edificio (pueden )
	    stackPanel.add(createMainItem(), createHeaderWidget("Centro educativo"), 4);

//	    curso lectivo
//	    stackPanel.add(createContactsItem(), createHeaderWidget("BI"), 4);
	    
	    
//	    aulas
//	    stackPanel.add(createFiltersItem(), createHeaderWidget("Aulas"), 4);
//	    grupos
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Grupos"), 4);
//	    materias
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Materias"), 4);
//	    profesores
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Profesores"), 4);
//	    alumnos
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Alumnos"), 4);
//	    clases
	    stackPanel.add(createContactsItem(), createHeaderWidget("Clases"), 4);
	    
	    
//	    marco horario
	    stackPanel.add(createContactsItem(), createHeaderWidget("Marco horario"), 4);
//	    recreos
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Grupos"), 4);
//	    sustituciones
	    stackPanel.add(createContactsItem(), createHeaderWidget("Sustituciones"), 4);
//	    ausencias
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Grupos"), 4);
//	    vacaciones
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Grupos"), 4);
//	    festivos
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Grupos"), 4);
	    
	    return stackPanel;
	}
	
	
	 /**
	   * The constants used in this Content Widget.
	   */
	  public static interface CwConstants extends Constants {
	    String[] cwStackLayoutPanelContacts();

	    String[] cwStackLayoutPanelContactsEmails();

	    String cwStackLayoutPanelContactsHeader();

	    String cwStackLayoutPanelDescription();

	    String[] cwStackLayoutPanelFilters();

	    String cwStackLayoutPanelFiltersHeader();

	    String[] cwStackLayoutPanelMailFolders();

	    String cwStackLayoutPanelMailHeader();

	    String cwStackLayoutPanelName();
	  }

	  /**
	   * Specifies the images that will be bundled for this example.
	   * 
	   * We will override the leaf image used in the tree. Instead of using a blank
	   * 16x16 image, we will use a blank 1x1 image so it does not take up any
	   * space. Each TreeItem will use its own custom image.
	   */
//	  public interface Images extends Tree.Resources {
//	    ImageResource contactsgroup();
//
//	    ImageResource defaultContact();
//
//	    ImageResource drafts();
//
//	    ImageResource filtersgroup();
//
//	    ImageResource inbox();
//
//	    ImageResource mailgroup();
//
//	    ImageResource sent();
//
//	    ImageResource templates();
//
//	    ImageResource trash();
//
//	    /**
//	     * Use noimage.png, which is a blank 1x1 image.
//	     */
//	    @Override
//	    @Source("noimage.png")
//	    ImageResource treeLeaf();
//	  }

	  /**
	   * An instance of the constants.
	   */
//	  private final CwConstants constants = null;

	  /**
	   * Initialize this example.
	   */
//	  @Override
//	  public Widget onInitialize() {
//	    // Get the images.
//	    Images images = (Images) GWT.create(Images.class);
//
//	    // Create a new stack layout panel.
//	    StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
//	    stackPanel.setPixelSize(200, 400);
//
//	    // Add the Mail folders.
//	    Widget mailHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelMailHeader(), images.mailgroup());
//	    stackPanel.add(createMailItem(images), mailHeader, 4);
//
//	    // Add a list of filters.
//	    Widget filtersHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelFiltersHeader(), images.filtersgroup());
//	    stackPanel.add(createFiltersItem(), filtersHeader, 4);
//
//	    // Add a list of contacts.
//	    Widget contactsHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelContactsHeader(), images.contactsgroup());
//	    stackPanel.add(createContactsItem(images), contactsHeader, 4);
//
//	    // Return the stack panel.
//	    stackPanel.ensureDebugId("cwStackLayoutPanel");
//	    return stackPanel;
//	  }

	  /**
	   * Create the list of Contacts.
	   * 
	   * @param images the {@link Images} used in the Contacts
	   * @return the list of contacts
	   */
	  private Widget createContactsItem() {
	    // Create a popup to show the contact info when a contact is clicked
	    HorizontalPanel contactPopupContainer = new HorizontalPanel();
	    contactPopupContainer.setSpacing(5);
//	    contactPopupContainer.add(new Image(images.defaultContact()));
	    final HTML contactInfo = new HTML();
	    contactPopupContainer.add(contactInfo);
	    final PopupPanel contactPopup = new PopupPanel(true, false);
	    contactPopup.setWidget(contactPopupContainer);

	    // Create the list of contacts
	    VerticalPanel contactsPanel = new VerticalPanel();
	    contactsPanel.setSpacing(4);
//	    String[] contactNames = constants.cwStackLayoutPanelContacts();
	    String[] contactNames = {"Mikel Laboa", "Kepa Junkera"};
//	    String[] contactEmails = constants.cwStackLayoutPanelContactsEmails();
	    String[] contactEmails = {"mikel@laboa.eus", "kepa@junkera.eus"};
	    for (int i = 0; i < contactNames.length; i++) {
	      final String contactName = contactNames[i];
	      final String contactEmail = contactEmails[i];
	      final Anchor contactLink = new Anchor(contactName);
	      contactsPanel.add(contactLink);

	      // Open the contact info popup when the user clicks a contact
	      contactLink.addClickHandler(new ClickHandler() {
	        @Override
	        public void onClick(ClickEvent event) {
	          // Set the info about the contact
	          SafeHtmlBuilder sb = new SafeHtmlBuilder();
	          sb.appendEscaped(contactName);
	          sb.appendHtmlConstant("<br><i>");
	          sb.appendEscaped(contactEmail);
	          sb.appendHtmlConstant("</i>");
	          contactInfo.setHTML(sb.toSafeHtml());

	          // Show the popup of contact info
	          int left = contactLink.getAbsoluteLeft() + 14;
	          int top = contactLink.getAbsoluteTop() + 14;
	          contactPopup.setPopupPosition(left, top);
	          contactPopup.show();
	        }
	      });
	    }
	    return new SimplePanel(contactsPanel);
	  }

	  /**
	   * Create the list of filters for the Filters item.
	   * 
	   * @return the list of filters
	   */
	  private Widget createFiltersItem() {
	    VerticalPanel filtersPanel = new VerticalPanel();
	    filtersPanel.setSpacing(4);
//	    for (String filter : constants.cwStackLayoutPanelFilters()) {
	    filtersPanel.add(new CheckBox("filter 1"));
	    filtersPanel.add(new CheckBox("filter 2"));
	    filtersPanel.add(new CheckBox("filter 3"));
//	    }
	    return new SimplePanel(filtersPanel);
	  }

	  /**
	   * Create a widget to display in the header that includes an image and some
	   * text.
	   * 
	   * @param text the header text
	   * @param image the {@link ImageResource} to add next to the header
	   * @return the header widget
	   */
	  private Widget createHeaderWidget(String text) {
	    // Add the image and text to a horizontal panel
	    HorizontalPanel hPanel = new HorizontalPanel();
	    hPanel.setHeight("100%");
	    hPanel.setSpacing(0);
	    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
//	    hPanel.add(new Image(image));
	    HTML headerText = new HTML(text);
	    headerText.setStyleName("cw-StackPanelHeader");
	    hPanel.add(headerText);
	    return new SimplePanel(hPanel);
	  }

	  /**
	   * Create the {@link Tree} of Mail options.
	   * 
	   * @param images the {@link Images} used in the Mail options
	   * @return the {@link Tree} of mail options
	   */
	  private Widget createMainItem() {
	    Tree mailPanel = new Tree();
//	    TreeItem mailPanelRoot = mailPanel.addTextItem("foo@example.com");
//	    String[] mailFolders = constants.cwStackLayoutPanelMailFolders();
//	    addItem(mailPanelRoot, null, mailFolders[0]);
//	    addItem(mailPanelRoot, null, mailFolders[1]);
//	    addItem(mailPanelRoot, null, mailFolders[2]);
//	    addItem(mailPanelRoot, null, mailFolders[3]);
//	    addItem(mailPanelRoot, null, mailFolders[4]);
	    
	    mailPanel.addTextItem("Aulas");
	    mailPanel.addTextItem("Grupos");
	    mailPanel.addTextItem("Materias");
	    
	    TreeItem teacherItem = mailPanel.addTextItem("Profesores");
	    addItem(teacherItem, null, "Profesor 1");
	    addItem(teacherItem, null, "Profesor 2");
	    teacherItem.setState(false);
	    
	    TreeItem alumnItem = mailPanel.addTextItem("Alumnos");
	    addItem(alumnItem, null, "Alumno 1");
	    addItem(alumnItem, null, "Alumno 2");
	    alumnItem.setState(false);
	    
//	    addItem(mailPanelRoot, null, "Aulas");
//	    addItem(mailPanelRoot, null, "Grupos");
//	    addItem(mailPanelRoot, null, "Materias");
//	    addItem(mailPanelRoot, null, "Profesores");
//	    addItem(mailPanelRoot, null, "Alumnos");
	    
//	    aulas
//	    grupos
//	    materias
//	    profesores
//	    alumnos
	    
//	    clases
//	    recreos
//	    sustituciones
//	    ausencias
//	    vacaciones
//	    festivos
	   
	    
//	    mailPanelRoot.setState(true);
	    
	    mailPanel.addSelectionHandler(new SelectionHandler<TreeItem>() {
			
			@Override
			public void onSelection(SelectionEvent<TreeItem> arg0) {
//				Window.alert(arg0.getSelectedItem().getText());
//				if (arg0.getSelectedItem().getChildCount()>0){
//					arg0.getSelectedItem().setState(true);
//				}
				
				for(int i=0; i<selectedTreeItemContent.getWidgetCount(); i++){
					selectedTreeItemContent.getWidget(i).setVisible(false);
				}
				
				if(arg0.getSelectedItem().getText().equals("Aulas") || selectedTreeItemContent == null){
					classRoom.setVisible(true);
				} else if(arg0.getSelectedItem().getText().equals("Grupos")){
					group.setVisible(true);
				} else if(arg0.getSelectedItem().getText().equals("Materias")){
					subject.setVisible(true);
				} else if(arg0.getSelectedItem().getText().equals("Profesores")){
					teacherGroup.setVisible(true);
				} else if(arg0.getSelectedItem().getText().equals("Alumnos")){
					alumnGroup.setVisible(true);
				}
				
			}

		});
	    
	    return mailPanel;
	  }
	  
	  /**
	   * Add a {@link TreeItem} to a root item.
	   * 
	   * @param root the root {@link TreeItem}
	   * @param image the icon for the new child item
	   * @param label the label for the child icon
	   */
	  private void addItem(TreeItem root, ImageResource image, String label) {
	    SafeHtmlBuilder sb = new SafeHtmlBuilder();
//	    sb.append(SafeHtmlUtils.fromTrustedString(AbstractImagePrototype.create(image).getHTML()));
	    sb.appendEscaped(" ").appendEscaped(label);
	    root.addItem(sb.toSafeHtml());
	  }
	
}
