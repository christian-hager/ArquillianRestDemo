package de.nordiccoding.demos;

/**
 * @author Christian Hager // nordiccoding.blogspot.com
 */
public class Contact {

	public Contact(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	Long id;

	String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
