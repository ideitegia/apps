package com.ideitegia.scheduler.client;

import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.HorizontalPanelDropController;
import com.allen_sauer.gwt.dnd.client.drop.VerticalPanelDropController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SchedulePreview extends Composite {
	
	// Annotation can be used to change the name of the associated xml file
	// @UiTemplate("HelloWidgetWorld.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, SchedulePreview> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	VerticalPanel previewPanel;
	
	
	
	 private static final int COLUMNS = 7;

	  private static final String CSS_DEMO_INSERT_PANEL_EXAMPLE = "demo-InsertPanelExample";

	  private static final String CSS_DEMO_INSERT_PANEL_EXAMPLE_COLUMN_COMPOSITE = "demo-InsertPanelExample-column-composite";

	  private static final String CSS_DEMO_INSERT_PANEL_EXAMPLE_CONTAINER = "demo-InsertPanelExample-container";

	  private static final String CSS_DEMO_INSERT_PANEL_EXAMPLE_HEADING = "demo-InsertPanelExample-heading";

	  private static final String CSS_DEMO_INSERT_PANEL_EXAMPLE_WIDGET = "demo-InsertPanelExample-widget";

	  private static final int ROWS = 4;

	  private static final int SPACING = 2;

	
	
	public SchedulePreview() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
//		TextBox text = new TextBox();
//		text.setValue("SCHEDULE PREVIEW header");
//		previewPanel.add(text);
//		text = new TextBox();
//		text.setValue("SCHEDULE PREVIEW body");
//		previewPanel.add(text);
//		text = new TextBox();
//		text.setValue("SCHEDULE PREVIEW footer");
//		previewPanel.add(text);
		
		previewPanel.getElement().getStyle().setProperty("width", "100%");
		
		
		
		
		
		 // text area to log drag events as they are triggered
	    final HTML eventTextArea = new HTML();
//	    eventTextArea.addStyleName(CSS_DEMO_EVENT_TEXT_AREA);
//	    eventTextArea.setSize(boundaryPanel.getOffsetWidth() + "px", "10em");

	    // instantiate shared drag handler to listen for events
	    final DemoDragHandler demoDragHandler = new DemoDragHandler(eventTextArea);
//	    dragController.addDragHandler(demoDragHandler);

		
		
		
		addStyleName(CSS_DEMO_INSERT_PANEL_EXAMPLE);
	    int count = 0;

	    // use the boundary panel as this composite's widget
	    AbsolutePanel boundaryPanel = new AbsolutePanel();
	    boundaryPanel.setSize("100%", "100%");
//	    setWidget(boundaryPanel);
	    previewPanel.add(boundaryPanel);

	    // initialize our column drag controller
	    PickupDragController columnDragController = new PickupDragController(boundaryPanel, false);
	    columnDragController.setBehaviorMultipleSelection(false);
//	    columnDragController.addDragHandler(demoDragHandler);

	    // initialize our widget drag controller
	    PickupDragController widgetDragController = new PickupDragController(boundaryPanel, false);
	    widgetDragController.setBehaviorMultipleSelection(false);
	    widgetDragController.addDragHandler(demoDragHandler);

	    // initialize horizontal panel to hold our columns
	    HorizontalPanel horizontalPanel = new HorizontalPanel();
//	    horizontalPanel.addStyleName(CSS_DEMO_INSERT_PANEL_EXAMPLE_CONTAINER);
	    horizontalPanel.getElement().getStyle().setProperty("width", "100%");
	    horizontalPanel.setSpacing(SPACING);
	    boundaryPanel.add(horizontalPanel);

	    // initialize our column drop controller
	    HorizontalPanelDropController columnDropController = new HorizontalPanelDropController(
	        horizontalPanel);
	    columnDragController.registerDropController(columnDropController);

	    for (int col = 1; col <= COLUMNS; col++) {
	      // initialize a vertical panel to hold the heading and a second vertical
	      // panel
	      VerticalPanel columnCompositePanel = new VerticalPanel();
//	      columnCompositePanel.addStyleName(CSS_DEMO_INSERT_PANEL_EXAMPLE_COLUMN_COMPOSITE);
	      columnCompositePanel.getElement().getStyle().setProperty("width", "100%");

	      // initialize inner vertical panel to hold individual widgets
	      VerticalPanel verticalPanel = new VerticalPanelWithSpacer();
//	      verticalPanel.addStyleName(CSS_DEMO_INSERT_PANEL_EXAMPLE_CONTAINER);
	      verticalPanel.getElement().getStyle().setProperty("width", "100%");
	      verticalPanel.setSpacing(SPACING);
	      horizontalPanel.add(columnCompositePanel);

	      // initialize a widget drop controller for the current column
	      VerticalPanelDropController widgetDropController = new VerticalPanelDropController(
	          verticalPanel);
	      widgetDragController.registerDropController(widgetDropController);

	      // Put together the column pieces
	      Label heading = new Label("Day " + col);
//	      heading.addStyleName(CSS_DEMO_INSERT_PANEL_EXAMPLE_HEADING);
	      heading.getElement().getStyle().setProperty("background-color", DemoDragHandler.GREEN);
	      columnCompositePanel.add(heading);
	      columnCompositePanel.add(verticalPanel);

	      // make the column draggable by its heading
	      columnDragController.makeDraggable(columnCompositePanel, heading);

	      for (int row = 1; row <= ROWS; row++) {
	        // initialize a widget
	        HTML widget = new HTML("Signature&nbsp;#" + ++count);
//	        widget.addStyleName(CSS_DEMO_INSERT_PANEL_EXAMPLE_WIDGET);
	        widget.getElement().getStyle().setProperty("background-color", DemoDragHandler.BLUE);
	        
	        widget.setHeight(Random.nextInt(4) + 2 + "em");
	        verticalPanel.add(widget);

	        // make the widget draggable
	        widgetDragController.makeDraggable(widget);
	      }
	    }

		
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Shared drag handler which display events as they are received by the various drag controllers.
	 */
	public final class DemoDragHandler implements DragHandler {

	  /**
	   * CSS blue.
	   */
	  public static final String BLUE = "#4444BB";

	  /**
	   * CSS green.
	   */
	  public static final String GREEN = "#44BB44";

	  /**
	   * CSS red.
	   */
	  public static final String RED = "#BB4444";

	  /**
	   * Text area where event messages are shown.
	   */
	  private final HTML eventTextArea;

	  DemoDragHandler(HTML dragHandlerHTML) {
	    eventTextArea = dragHandlerHTML;
	  }

	  /**
	   * Log the drag end event.
	   *
	   * @param event the event to log
	   */
	  @Override
	  public void onDragEnd(DragEndEvent event) {
	    log("onDragEnd: " + event, RED);
	  }

	  /**
	   * Log the drag start event.
	   *
	   * @param event the event to log
	   */
	  @Override
	  public void onDragStart(DragStartEvent event) {
	    log("onDragStart: " + event, GREEN);
	  }

	  /**
	   * Log the preview drag end event.
	   *
	   * @param event the event to log
	   * @throws VetoDragException exception which may be thrown by any drag handler
	   */
	  @Override
	  public void onPreviewDragEnd(DragEndEvent event) throws VetoDragException {
	    log("<br>onPreviewDragEnd: " + event, BLUE);
	  }

	  /**
	   * Log the preview drag start event.
	   *
	   * @param event the event to log
	   * @throws VetoDragException exception which may be thrown by any drag handler
	   */
	  @Override
	  public void onPreviewDragStart(DragStartEvent event) throws VetoDragException {
	    log("onPreviewDragStart: " + event, BLUE);
	  }

	  public void clear() {
	    eventTextArea.setHTML("");
	  }

	  public void log(String text, String color) {
	    eventTextArea.setHTML(eventTextArea.getHTML()
	        + (eventTextArea.getHTML().length() == 0 ? "" : "<br>") + "<span style='color: " + color
	        + "'>" + text + "</span>");
	  }
	}

	
}