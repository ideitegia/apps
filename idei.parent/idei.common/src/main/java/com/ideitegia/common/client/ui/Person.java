package com.ideitegia.common.client.ui;




public class Person implements Comparable<Person> {
	private int id;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String address;
//	private Category Category;
	private int age;
	private Integer birthday;
	private String firstName;
	private String lastName;

	public Person() {

	}

	public Person(int id, String name, String firstSurname, String secondSurname) {
		this.id = id;
		this.firstName = name;
		this.lastName = firstSurname;
		this.secondSurname = secondSurname;
	}
	
	 @Override
	    public int compareTo(Person o) {
	      return (o == null || o.firstName == null) ? -1 : -o.firstName.compareTo(firstName);
	    }

	    @Override
	    public boolean equals(Object o) {
	      if (o instanceof Person) {
	        return id == ((Person) o).id;
	      }
	      return false;
	    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstSurname() {
		return firstSurname;
	}
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}
	public String getSecondSurname() {
		return secondSurname;
	}
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}
		
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Category getCategory() {
//		return Category;
//	}
//
//	public void setCategory(Category category) {
//		Category = category;
//	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getBirthday() {
		return birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


//	public class Category {
//
//	    private final String displayName;
//
//	    private Category(String displayName) {
//	      this.displayName = displayName;
//	    }
//
//	    public String getDisplayName() {
//	      return displayName;
//	    }
//	  }
	
}

