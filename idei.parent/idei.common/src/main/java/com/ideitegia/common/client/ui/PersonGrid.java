package com.ideitegia.common.client.ui;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.ideitegia.common.client.i18n.CommonMessages;

public class PersonGrid extends ResizeComposite {

	interface MyUiBinder extends UiBinder<Widget, PersonGrid> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	private CommonMessages messages = GWT.create(CommonMessages.class);
	
	@UiField(provided = true)
	DataGrid<Person> dataGrid;

	@UiField(provided = true)
	SimplePager pager;
	
	private ListDataProvider<Person> dataProvider;

	public ListDataProvider<Person> getDataProvider() {
		if(dataProvider==null){
			dataProvider = new ListDataProvider<Person>();
		}
		return dataProvider;
	}
	
	public PersonGrid() {
		initialize();
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	 private void initialize(){
		  
		 /*
		  * Set a key provider that provides a unique key for each contact. If key is
		  * used to identify contacts when fields (such as the name and address)
		  * change.
		  */
		 dataGrid = new DataGrid<Person>(15);
		 dataGrid.setWidth("100%");
		 
		 /*
		  * Do not refresh the headers every time the data is updated. The footer
		  * depends on the current data, so we do not disable auto refresh on the
		  * footer.
		  */
		 dataGrid.setAutoHeaderRefreshDisabled(true);
		 
		 // Set the message to display when the table is empty.
		 dataGrid.setEmptyTableWidget(new Label(messages.dataGridEmpty()));
		 
		 // Attach a column sort handler to the ListDataProvider to sort the list.
		 loadPersonList();
		 ListHandler<Person> sortHandler = new ListHandler<Person>(getDataProvider().getList());
		 dataGrid.addColumnSortHandler(sortHandler);
		 
		 // Create a Pager to control the table.
		 SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		 pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		 pager.setDisplay(dataGrid);
		 
		 // Add a selection model so we can select cells.
		 final SelectionModel<Person> selectionModel = new MultiSelectionModel<Person>(KEY_PROVIDER);
		 dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager .<Person> createCheckboxManager());
		 
		 // Initialize the columns.
		 initTableColumns(selectionModel, sortHandler);
		 
		 // Add the CellList to the adapter in the database.
		 dataProvider.addDataDisplay(dataGrid);
	  }
	
	
	 private void loadPersonList() {
		 List<Person> list = getDataProvider().getList();
		if(list==null){
			Window.alert("list is null");
			list = new LinkedList<Person>();
		}
		list.add(new Person(1, "Mikel", "Apaolaza", "Azkuna"));
		list.add(new Person(2, "Kurton", "Monire", "Domor"));
		list.add(new Person(3, "Nahikari", "Maguren", ""));
		list.add(new Person(4, "Izaro", "Ameztar", ""));
		list.add(new Person(5, "Aimar", "Anitua", ""));
		list.add(new Person(6, "Olatz", "Madariaga", ""));
		list.add(new Person(7, "Olatz", "Madariaga", ""));
		list.add(new Person(8, "Olatz", "Madariaga", ""));
		list.add(new Person(9, "Olatz", "Madariaga", ""));
		list.add(new Person(10, "Olatz", "Madariaga", ""));
		list.add(new Person(11, "Olatz", "Madariaga", ""));
		list.add(new Person(12, "Olatz", "Madariaga", ""));
		list.add(new Person(13, "Olatz", "Madariaga", ""));
		list.add(new Person(14, "Olatz", "Madariaga", ""));
		list.add(new Person(15, "Olatz", "Madariaga", ""));
		list.add(new Person(16, "Olatz", "Madariaga", ""));
		list.add(new Person(17, "Olatz", "Madariaga", ""));
		list.add(new Person(18, "Olatz", "Madariaga", ""));
		list.add(new Person(19, "Olatz", "Madariaga", ""));
		list.add(new Person(20, "Olatz", "Madariaga", ""));
		list.add(new Person(21, "Olatz", "Madariaga", ""));
		list.add(new Person(22, "Olatz", "Madariaga", ""));
		list.add(new Person(23, "Olatz", "Madariaga", ""));
		list.add(new Person(24, "Olatz", "Madariaga", ""));
		list.add(new Person(25, "Olatz", "Madariaga", ""));
		list.add(new Person(26, "Olatz", "Madariaga", ""));
		list.add(new Person(27, "Olatz", "Madariaga", ""));
		list.add(new Person(28, "Olatz", "Madariaga", ""));
		
		
	}


	/**
     * The key provider that provides the unique ID of a person.
     */
    public static final ProvidesKey<Person> KEY_PROVIDER = new ProvidesKey<Person>() {
      @Override
      public Object getKey(Person item) {
        return item == null ? null : item.getId();
      }
    };
	
	 private void initTableColumns(final SelectionModel<Person> selectionModel, ListHandler<Person> sortHandler) {
		    // Checkbox column. This table will uses a checkbox column for selection.
		    // Alternatively, you can call dataGrid.setSelectionEnabled(true) to enable
		    // mouse selection.
		    Column<Person, Boolean> checkColumn = new Column<Person, Boolean>(new CheckboxCell(true, false)) {
		          @Override
		          public Boolean getValue(Person object) {
		            // Get the value from the selection model.
		            return selectionModel.isSelected(object);
		          }
		        };
		    dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		    dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);

		    // First name.
		    Column<Person, String> firstNameColumn = new Column<Person, String>(new EditTextCell()) {
		          @Override
		          public String getValue(Person object) {
		            return object.getFirstName();
		          }
		        };
		    firstNameColumn.setSortable(true);
		    sortHandler.setComparator(firstNameColumn, new Comparator<Person>() {
		      @Override
		      public int compare(Person o1, Person o2) {
		        return o1.getFirstName().compareTo(o2.getFirstName());
		      }
		    });
		    dataGrid.addColumn(firstNameColumn, messages.dataGridColumnFirstName());
		    firstNameColumn.setFieldUpdater(new FieldUpdater<Person, String>() {
		      @Override
		      public void update(int index, Person object, String value) {
		        // Called when the user changes the value.
		        object.setFirstName(value);
		        refreshDisplays();
		      }
		    });
		    dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);

		    // Last name.
		    Column<Person, String> lastNameColumn =
		        new Column<Person, String>(new EditTextCell()) {
		          @Override
		          public String getValue(Person object) {
		            return object.getLastName();
		          }
		        };
		    lastNameColumn.setSortable(true);
		    sortHandler.setComparator(lastNameColumn, new Comparator<Person>() {
		      @Override
		      public int compare(Person o1, Person o2) {
		        return o1.getLastName().compareTo(o2.getLastName());
		      }
		    });
		    dataGrid.addColumn(lastNameColumn, messages.dataGridColumnLastName());
		    lastNameColumn.setFieldUpdater(new FieldUpdater<Person, String>() {
		      @Override
		      public void update(int index, Person object, String value) {
		        // Called when the user changes the value.
		        object.setLastName(value);
		        refreshDisplays();
		      }
		    });
		    dataGrid.setColumnWidth(lastNameColumn, 20, Unit.PCT);

		    // Age.
		    Column<Person, Number> ageColumn = new Column<Person, Number>(new NumberCell()) {
		      @Override
		      public Number getValue(Person object) {
		        return object.getAge();
		      }
		    };
		    ageColumn.setSortable(true);
		    sortHandler.setComparator(ageColumn, new Comparator<Person>() {
		      @Override
		      public int compare(Person o1, Person o2) {
		        return o1.getBirthday().compareTo(o2.getBirthday());
		      }
		    });
		    Header<String> ageFooter = new Header<String>(new TextCell()) {
		      @Override
		      public String getValue() {
		        List<Person> items = dataGrid.getVisibleItems();
		        if (items.size() == 0) {
		          return "";
		        } else {
		          int totalAge = 0;
		          for (Person item : items) {
		            totalAge += item.getAge();
		          }
		          return "Avg: " + totalAge / items.size();
		        }
		      }
		    };
		    dataGrid.addColumn(ageColumn, new SafeHtmlHeader(SafeHtmlUtils.fromSafeConstant(messages.dataGridColumnAge())), ageFooter);
		    dataGrid.setColumnWidth(ageColumn, 7, Unit.EM);

		    // Category.
//		    final Category[] categories = ContactDatabase.get().queryCategories();
//		    List<String> categoryNames = new ArrayList<String>();
//		    for (Category category : categories) {
//		      categoryNames.add(category.getDisplayName());
//		    }
//		    SelectionCell categoryCell = new SelectionCell(categoryNames);
//		    Column<Person, String> categoryColumn = new Column<Person, String>(categoryCell) {
//		      @Override
//		      public String getValue(Person object) {
//		        return object.getCategory().getDisplayName();
//		      }
//		    };
//		    dataGrid.addColumn(categoryColumn, constants.cwDataGridColumnCategory());
//		    categoryColumn.setFieldUpdater(new FieldUpdater<Person, String>() {
//		      @Override
//		      public void update(int index, Person object, String value) {
//		        for (Category category : categories) {
//		          if (category.getDisplayName().equals(value)) {
//		            object.setCategory(category);
//		          }
//		        }
//		        refreshDisplays();
//		      }
//		    });
//		    dataGrid.setColumnWidth(categoryColumn, 130, Unit.PX);

		    // Address.
		    Column<Person, String> addressColumn = new Column<Person, String>(new TextCell()) {
		      @Override
		      public String getValue(Person object) {
		        return object.getAddress();
		      }
		    };
		    addressColumn.setSortable(true);
		    sortHandler.setComparator(addressColumn, new Comparator<Person>() {
		      @Override
		      public int compare(Person o1, Person o2) {
		        return o1.getAddress().compareTo(o2.getAddress());
		      }
		    });
		    dataGrid.addColumn(addressColumn, messages.dataGridColumnAddress());
		    dataGrid.setColumnWidth(addressColumn, 60, Unit.PCT);
		  }
	 
	 public void refreshDisplays() {
		    dataProvider.refresh();
		  }
	
}

