package bankingmanagement;

public class Customer {
	private final String customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public Customer(
			String customerID,
			String firstName,
			String lastName,
			String email,
			String phoneNumber) {
		
		if (customerID == null || customerID.isBlank()) {
		    throw new IllegalArgumentException("Invalid customer ID.");
		}
		
		if (firstName == null || firstName.isBlank()) {
		    throw new IllegalArgumentException("Invalid first name.");
		}
		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException("Invalid last name.");
		}
		
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Invalid email address.");
		}
		
		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new IllegalArgumentException("Invalid phone number.");
		}
		
		this.customerID=customerID;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.phoneNumber=phoneNumber;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		
		if(firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException("Invalid first name.");
		}

		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		
		if(lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException("Invalid last name.");
		}
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
		if( email == null || email.isBlank()) {
			throw new IllegalArgumentException("Invalid email.");
		}
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if(phoneNumber == null || phoneNumber.isBlank()) {
			throw new IllegalArgumentException("Invalid phone number.");
		}
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerID() {
		return customerID;
	}
	
	
	
}
