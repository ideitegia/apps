
package com.ideitegia.skoolan.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

/**
 * Application shell for Showcase sample.
 */
public class SkoolanShell extends ResizeComposite {

  interface SkoolanShellUiBinder extends UiBinder<Widget, SkoolanShell> {
  }

  /**
   * The text color of the selected tab.
   */
//  private static final String SELECTED_TAB_COLOR = "#333333";

  /**
   * The unique ID assigned to the next callback.
   */
//  private static int nextCallbackId = 0;

  private static SkoolanShellUiBinder uiBinder = GWT.create(
      SkoolanShellUiBinder.class);

  /**
   * The panel that holds the content.
   */
  @UiField
  SimpleLayoutPanel contentPanel;

  /**
   * The container around the links at the top of the app.
   */
  @UiField
  TableElement linkCell;

  /**
   * The container around locale selection.
   */
  @UiField
  TableCellElement localeSelectionCell;
  @UiField
  Button locale_es;
  @UiField
  Button locale_eus;
  @UiField
  Button locale_en;
  
  @UiField
  FlowPanel contentButtons;
  
  @UiField
  Anchor homeLink;
  
  @UiField
  Anchor favoritesLink;
  
  @UiField
  Anchor aboutLink;
  
  /**
   * The current {@link ContentWidget} being displayed.
   */
  private ContentWidget content;

  /**
   * The handler used to handle user requests to view raw source.
   */
  private HandlerRegistration contentSourceHandler;

  /**
   * The widget that holds CSS or source code for an example.
   */
  private HTML contentSource = new HTML();

  private SimpleLayoutPanel selectedContent;
  
  
  public SimpleLayoutPanel getSelectedContent() {
	return selectedContent;
}



public void setSelectedContent(SimpleLayoutPanel selectedContent) {
	this.selectedContent = selectedContent;
}



/**
   * Construct the {@link SkoolanShell}.
   *
   * @param treeModel the treeModel that backs the main menu
   */
  public SkoolanShell(TreeViewModel treeModel) {
	  
    // Initialize the ui binder.
    initWidget(uiBinder.createAndBindUi(this));
    initializeLocaleBox();
    contentSource.getElement().getStyle().setBackgroundColor("#eee");
    contentSource.getElement().getStyle().setMargin(10.0, Unit.PX);
    contentSource.getElement().getStyle().setProperty("border", "1px solid #c3c3c3");
    contentSource.getElement().getStyle().setProperty("padding", "10px 2px");

    // In RTL mode, we need to set some attributes.
    if (LocaleInfo.getCurrentLocale().isRTL()) {
      localeSelectionCell.setAlign("left");
      linkCell.setPropertyString("align", "left");
    }

    // Default to no content.
    contentPanel.ensureDebugId("contentPanel");
    setContent(null);
  }

  
  
  public SkoolanShell(SimpleLayoutPanel selectedContent) {
	  
	  this.selectedContent = selectedContent;
	  
	  // Initialize the ui binder.
	    initWidget(uiBinder.createAndBindUi(this));
	    initializeLocaleBox();
	    contentSource.getElement().getStyle().setBackgroundColor("#eee");
	    contentSource.getElement().getStyle().setMargin(10.0, Unit.PX);
	    contentSource.getElement().getStyle().setProperty("border", "1px solid #c3c3c3");
	    contentSource.getElement().getStyle().setProperty("padding", "10px 2px");

	    // In RTL mode, we need to set some attributes.
	    if (LocaleInfo.getCurrentLocale().isRTL()) {
	      localeSelectionCell.setAlign("left");
	      linkCell.setPropertyString("align", "left");
	    }

	    
//	    home = new Anchor();
//	    favorites = new Anchor();
//	    about = new Anchor();
	    contentButtons.getElement().getStyle().setProperty("background-color", "#c3c3c3");
	    contentButtons.getElement().getStyle().setProperty("text-align", "right");
	    homeLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
//	          Window.alert("Home click event");
	        	getSelectedContent().setWidget(null);
	        }
	      });
	    favoritesLink.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	          Window.alert("Favorites click event");
	        }
	      });
//	    aboutLink.addClickHandler(new ClickHandler() {
//	        public void onClick(ClickEvent event) {
//	          Window.alert("About click event");
//	        }
//	      });
	    

	    // Default to no content.
	    contentPanel.ensureDebugId("contentPanel");
	    setContent(null);
}

/**
   * Returns the currently displayed content. (Used by tests.)
   */
  public ContentWidget getContent() {
    return content;
  }

  /**
   * Get the main menu used to select examples.
   *
   * @return the main menu
   */
//  public CellTree getMainMenu() {
//    return mainMenu;
//  }

  /**
   * Set the content to display.
   *
   * @param content the content
   */
  public void setContent(Widget content) {
	  contentPanel.setWidget(content);
  }
  public void setContent(final ContentWidget content) {
    // Clear the old handler.
    if (contentSourceHandler != null) {
      contentSourceHandler.removeHandler();
      contentSourceHandler = null;
    }

    this.content = content;
    if (content == null) {
      contentPanel.setWidget(null);
      return;
    }


    // Handle user requests for raw source.
    contentSourceHandler = content.addValueChangeHandler(
        new ValueChangeHandler<String>() {
          public void onValueChange(ValueChangeEvent<String> event) {
            // Select the file in the list box.
//            String filename = event.getValue();
//            int index = content.getRawSourceFilenames().indexOf(filename);
//            tabSourceList.setSelectedIndex(index + 1);

            // Show the file.
//            showSourceFile();
          }
        });

    // Show the widget.
    showExample();
  }

  /**
   * Initialize the {@link ListBox} used for locale selection.
   */
  private void initializeLocaleBox() {
    final String cookieName = LocaleInfo.getLocaleCookieName();
    final String queryParam = LocaleInfo.getLocaleQueryParam();
    if (cookieName == null && queryParam == null) {
      // if there is no way for us to affect the locale, don't show the selector
      localeSelectionCell.getStyle().setDisplay(Display.NONE);
      return;
    }
    String currentLocale = LocaleInfo.getCurrentLocale().getLocaleName();
    if (currentLocale.equals("default")) {
      currentLocale = "en";
    }
    
  }

  /**
   * Show a example.
   */
  private void showExample() {
    if (content == null) {
    	Window.alert("content is null");
      return;
    }

    // Set the highlighted tab.
//    tabExample.getElement().getStyle().setColor(SELECTED_TAB_COLOR);
//    tabStyle.getElement().getStyle().clearColor();
//    tabSource.getElement().getStyle().clearColor();

    contentPanel.setWidget(content);
  }
  
  @UiHandler("aboutLink")
  void onAboutClicked(ClickEvent event) {
    // When the 'About' item is selected, show the AboutDialog.
    // Note that showing a dialog box does not block -- execution continues
    // normally, and the dialog fires an event when it is closed.
    AboutDialog dlg = new AboutDialog();
    dlg.show();
    dlg.center();
  }

	@UiHandler("locale_es")
	void onLocaleEsClicked(ClickEvent event) {
		changeLocale("es");
	}

	@UiHandler("locale_eus")
	void onLocaleEusClicked(ClickEvent event) {
		changeLocale("eus");
	}

	@UiHandler("locale_en")
	void onLocaleEnClicked(ClickEvent event) {
		changeLocale("en");
	}

	private void changeLocale(String localeValue) {
		NodeList<Element> tags = Document.get().getElementsByTagName(MetaElement.TAG);
		boolean done = false;
		for (int i = 0; i < tags.getLength(); i++) {
			MetaElement metaTag = ((MetaElement) tags.getItem(i));
			if (metaTag.getName().equals("gwt:property")) {
				metaTag.setContent(localeValue);
				done = true;
				Window.Location.reload();
			}
		}
		if (!done) {
			String url = GWT.getHostPageBaseURL();
			String newUrl = url + "?locale=" + localeValue;
			Window.Location.replace(newUrl);
			
//			MetaElement metaElem = Document.get().createMetaElement();
//			metaElem.setName("gwt:property");
//			metaElem.setContent(localeValue);	
//			getHeadElement().appendChild(metaElem);
//			Window.Location.reload();

		}
	}

	
	 private native HeadElement getHeadElement() /*-{
	    return $doc.getElementsByTagName("head")[0];
	  }-*/;

	  /**
	   * Inject the GWT theme style sheet based on the RTL direction of the current
	   * locale.
	   */
//	  private void injectThemeStyleSheet() {
//	    // Choose the name style sheet based on the locale.
//	    String styleSheet = "gwt/" + THEME + "/" + THEME;
//	    styleSheet += LocaleInfo.getCurrentLocale().isRTL() ? "_rtl.css" : ".css";
//
//	    // Load the GWT theme style sheet
//	    String modulePath = GWT.getModuleBaseURL();
//	    LinkElement linkElem = Document.get().createLinkElement();
//	    linkElem.setRel("stylesheet");
//	    linkElem.setType("text/css");
//	    linkElem.setHref(modulePath + styleSheet);
//	    getHeadElement().appendChild(linkElem);
//	  }
	
	
	
	
	
}
