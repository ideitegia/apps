package com.ideitegia.skoolan.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.prefetch.Prefetcher;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ideitegia.common.client.i18n.CommonConstants;
import com.ideitegia.common.client.i18n.CommonMessages;
import com.ideitegia.common.client.ui.PersonGrid;
import com.ideitegia.scheduler.client.SchedulePreview;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Idei_skoolan implements EntryPoint {

	/**
	 * Get the token for a given content widget.
	 * 
	 * @return the content widget token.
	 */
	public static String getContentWidgetToken(ContentWidget content) {
		return getContentWidgetToken(content.getClass());
	}

	/**
	 * Get the token for a given content widget.
	 * 
	 * @return the content widget token.
	 */
	public static <C extends ContentWidget> String getContentWidgetToken(
			Class<C> cwClass) {
		String className = cwClass.getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		return "!" + className;
	}
	
	/**
	 * The main application shell.
   	*/
	private SkoolanShell shell;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Get rid of scrollbars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area.
	    Window.enableScrolling(false);
	    Window.setMargin("0px");
	    
	    // Localization
	    ensureLocale();
		
		// Create the application shell.
//	    final SingleSelectionModel<ContentWidget> selectionModel = new SingleSelectionModel<ContentWidget>();
	    Set<ContentWidget> contentWidgets = new HashSet<ContentWidget>();
	    SimpleLayoutPanel selectedContent = new SimpleLayoutPanel();
	    shell = new SkoolanShell(selectedContent);
//	    shell.setContent(new Label("content"));
	    RootLayoutPanel.get().add(shell);

	    SplitLayoutPanel p = new SplitLayoutPanel();
	    p.addWest(obtainMainTree(selectedContent), 200);
	    p.add(selectedContent);
//	    p.add(new HTML("content"));
//	    p.add(selectedTreeItemContent);
	    
	    shell.setContent(p);
	    
	    // Always prefetch.
	    Prefetcher.start();

    
	}
	
	private void ensureLocale() {
		// search if exist locale in cookies
		boolean existLocale = false;
		// if not locale found, load user defined (or userAgent) locale
		if(!existLocale){
//			MetaElement metaElem = Document.get().createMetaElement();
//			metaElem.setName("gwt:property");
//			metaElem.setContent("locale=en");
//			getHeadElement().appendChild(metaElem);
		}
	}
	
	 private native HeadElement getHeadElement() /*-{
	    return $doc.getElementsByTagName("head")[0];
	  }-*/;

	private Widget obtainMainTree(SimpleLayoutPanel content){
	    StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
	    stackPanel.addStyleName("width-all");
//	    stackPanel.setPixelSize(200, 600);
	    stackPanel.add(createMainItem(content), createHeaderWidget("Centro educativo"), 3);
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Clases"), 3);
	    stackPanel.add(createSchedulerItem(content), createHeaderWidget("Marco horario"), 3);
//	    stackPanel.add(createContactsItem(), createHeaderWidget("Sustituciones"), 3);
	    stackPanel.add(createContactsItem(), createHeaderWidget("Configuracion"), 3);
	    
	    return stackPanel;
	    }
	private Widget createHeaderWidget(String text) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHeight("100%");
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
//		    hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		headerText.setStyleName("cw-StackPanelHeader");
		hPanel.add(headerText);
		return new SimplePanel(hPanel);
	}
	
	private Widget createSchedulerItem(final SimpleLayoutPanel content) {
		Tree treeItems = new Tree();
		treeItems.addTextItem("Previsualizar");
		treeItems.addTextItem("Ajuste manual");
		
		treeItems.addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> arg0) {
				if(arg0.getSelectedItem().getText().equals("Previsualizar")){
//					content.setWidget(new TreeClassRoom());
				} else if(arg0.getSelectedItem().getText().equals("Ajuste manual")){
					content.setWidget(new SchedulePreview());
				}
			}
		});
		
		return new ScrollPanel(treeItems);
	}
	
	private Widget createMainItem(final SimpleLayoutPanel content) {
		    Tree treeItems = new Tree();
		    
		    treeItems.addTextItem("Aulas");
		    treeItems.addTextItem("Grupos");
		    treeItems.addTextItem("Materias");
		    TreeItem departmentItem = treeItems.addTextItem("Departamentos");
		    addItem(departmentItem, null, "Dept. Historia");
		    addItem(departmentItem, null, "Dept. Matematika");
		    addItem(departmentItem, null, "Dept. Euskera");
		    addItem(departmentItem, null, "Dept. Natura");
		    addItem(departmentItem, null, "Dept. Fisika");
		    addItem(departmentItem, null, "Dept. Kimika");
		    addItem(departmentItem, null, "Dept. Ingelesa");
		    departmentItem.setState(false);
		    
		    TreeItem teacherItem = treeItems.addTextItem("Profesores");
		    addItem(teacherItem, null, "Profesor 1");
		    addItem(teacherItem, null, "Profesor 2");
		    teacherItem.setState(false);
		    
		    TreeItem alumnItem = treeItems.addTextItem("Alumnos");
		    addItem(alumnItem, null, "Alumno 1");
		    addItem(alumnItem, null, "Alumno 2");
		    alumnItem.setState(false);
		   
		    treeItems.addSelectionHandler(new SelectionHandler<TreeItem>() {
				@Override
				public void onSelection(SelectionEvent<TreeItem> arg0) {
					if(arg0.getSelectedItem().getText().equals("Aulas")){
						content.setWidget(new TreeClassRoom());
					} else if(arg0.getSelectedItem().getText().equals("Grupos")){
						content.setWidget(new TreeGroup());
					} else if(arg0.getSelectedItem().getText().equals("Materias")){
						content.setWidget(new TreeSubject());
					} else if(arg0.getSelectedItem().getText().equals("Departamentos")){
						content.setWidget(new TreeDepartmentGroup());
					} else if(arg0.getSelectedItem().getText().equals("Profesores")){
						content.setWidget(new TreeTeacherGroup());
					} else if(arg0.getSelectedItem().getText().equals("Alumnos")){
						content.setWidget(new PersonGrid());
					}
				}
			});
		    
		   return new ScrollPanel(treeItems);
		  }
	  private void addItem(TreeItem root, ImageResource image, String label) {
		    SafeHtmlBuilder sb = new SafeHtmlBuilder();
//		    sb.append(SafeHtmlUtils.fromTrustedString(AbstractImagePrototype.create(image).getHTML()));
		    sb.appendEscaped(" ").appendEscaped(label);
		    root.addItem(sb.toSafeHtml());
		  }
	  
	  private Widget createContactsItem() {
		    // Create a popup to show the contact info when a contact is clicked
		    HorizontalPanel contactPopupContainer = new HorizontalPanel();
		    contactPopupContainer.setSpacing(5);
//		    contactPopupContainer.add(new Image(images.defaultContact()));
		    final HTML contactInfo = new HTML();
		    contactPopupContainer.add(contactInfo);
		    final PopupPanel contactPopup = new PopupPanel(true, false);
		    contactPopup.setWidget(contactPopupContainer);

		    // Create the list of contacts
		    VerticalPanel contactsPanel = new VerticalPanel();
		    contactsPanel.setSpacing(4);
//		    String[] contactNames = constants.cwStackLayoutPanelContacts();
		    String[] contactNames = {"Mikel Laboa", "Kepa Junkera"};
//		    String[] contactEmails = constants.cwStackLayoutPanelContactsEmails();
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
}
