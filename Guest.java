import java.util.*;

public class Guest {
	
	private Scanner sc = new Scanner(System.in);
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	
	public String getlastName () {
		return this.lastName;
	}
	
	public String getfirstName () {
		return this.firstName;
	}
	
	public String getemail() {
		return this.email;
	}
	
	public String getphone() {
		return this.phone;
	}
	
	public Guest (String lastName, String firstName, String email, String phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
	}
	
 	public Guest () {
		System.out.println("Last Name : ");
		this.lastName = sc.nextLine();
		
		System.out.println("First Name : ");
		this.firstName = sc.nextLine();
		
		System.out.println("E-mail : ");
		this.email = sc.nextLine();
		
		System.out.println("Phone Number : ");
		this.phone = sc.nextLine();
	}
	
	public Guest(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public Guest(String emailPhone, int op) {
		if (op == 2) 
			this.email = emailPhone;
		else 
			this.phone = emailPhone;	
	}


	public void changeName (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void changeEmail (String email) {
		this.email = email;
	}
	
	public void changePhone (String phone) {
		this.phone = phone;
	}

}
