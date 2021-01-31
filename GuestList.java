import java.util.*;

public class GuestList{
	
	private  Scanner sc = new Scanner(System.in);
	private int maxGuests = 10;
	private ArrayList<Guest> participant;
	private ArrayList<Guest> waitParticipant;
	
	
	//Constructori
	public GuestList() {
		this.maxGuests = 10;
		this.participant = new ArrayList<Guest>();
		this.waitParticipant = new ArrayList<Guest>();
	}
	
	public GuestList(int maxGuests) {
		this.maxGuests = maxGuests;
		this.participant = new ArrayList<Guest>(maxGuests);
		this.waitParticipant = new ArrayList<Guest>();
	}

	
	//Metode pentru verificare
	private int checkName(Guest aux) {
		
		for (Guest i : this.participant) {
		
			if (i.getfirstName().equals(aux.getfirstName())  && i.getlastName().equals(aux.getlastName()) ) 
				return this.participant.indexOf(i);
			}
				

		for (Guest i : this.waitParticipant) 
			if (i.getfirstName().equals(aux.getfirstName()) && i.getlastName().equals(aux.getlastName()))
				return this.waitParticipant.indexOf(i) + this.maxGuests;

	    throw new Exceptions("The name could not be found!");
	}
	
	private int checkPhone(Guest aux) {

		for (Guest i : this.participant) 
			if (i.getphone().equals(aux.getphone()))
				return this.participant.indexOf(i);
		
		for (Guest i : this.waitParticipant) 
			if (i.getphone().equals(aux.getphone()))
				return this.waitParticipant.indexOf(i) + this.maxGuests;

		throw new Exceptions("The phone could not be found!");
	}
	
	private int checkEmail(Guest aux) {

		for (Guest i : this.participant)
			if (i.getemail().equals(aux.getemail()))
				return this.participant.indexOf(i);
		
		for (Guest i : this.waitParticipant) 
			if (i.getemail().equals(aux.getemail()))
				return this.waitParticipant.indexOf(i) + this.maxGuests;
		
		throw new Exceptions("The e-mail could not be found!");
	}
	
	private int returnIndex (int aux) {
		String lastName;
		String firstName;
		String email;
		String phone;
	
		if (aux == 1) {
			System.out.println("Last name : ");
			lastName = sc.next();
			System.out.println("First name : ");
			firstName = sc.next();
			Guest auxGuest = new Guest(lastName, firstName);
			return this.checkName(auxGuest);
		}
		
		if (aux == 2) {
			System.out.println("E-mail : ");
			email = sc.next();
			Guest auxGuest = new Guest(email, 2);
			return this.checkEmail(auxGuest);
		}
		
		if (aux == 3) {
			System.out.println("Phone number : ");
			phone = sc.next();
			Guest auxGuest = new Guest(phone, 3);
			return this.checkPhone(auxGuest);
		}

		throw new Exceptions("That is not a good index!");
		
	}
	//Metode pentru cautare
	
	private  int howToCheck () {
		int aux;
		System.out.println("How do you want to search the participant ?\n"
				+ "1. Last name and first name\n"
				+ "2. E-mail\n"
				+ "3. Phone number");
		
		try {
			  aux = sc.nextInt();
			}
			catch(Exception e) {
				System.out.println("Are you sure that is a number ?");
				return - 1;
			}
		
		if (aux > 3 || aux < 1) {
			throw new Exceptions("Wrong input!");
		}else
			return aux;
	}
	
	public boolean searchParticipant() {
		
		int aux = this.howToCheck();
		int aux1 = this.returnIndex(aux);
		
		if (aux1 >= this.maxGuests) {
			System.out.print(this.waitParticipant.get(aux1 - this.maxGuests).getfirstName()
					+ " " + this.waitParticipant.get(aux1 - this.maxGuests).getlastName()
					+ " is on the waiting list at the index " + (aux1 - this.maxGuests + 1) + " !");
			return true;
		}
		
		if (aux1 < this.maxGuests && aux1 != -1) {
			System.out.println(this.participant.get(aux1).getfirstName() 
					+ " " + this.participant.get(aux1).getlastName() + " is on the participant list !");
			return true;
		}
		
		System.out.println("The participant was not found !");
		return false;
	}
		
	
	//Metoda adaugare
	public int addParticipant () {
		Guest aux = new Guest();
		
		if (this.checkName(aux) == -1 && this.checkPhone(aux) == -1 && this.checkEmail(aux) == -1) {
			
			if (this.participant.size() >= this.maxGuests) {
				
				this.waitParticipant.add(aux);
				System.out.println("You were signed up to the waiting list! Your index is " + (this.waitParticipant.indexOf(aux) + 1));
				return this.waitParticipant.indexOf(aux) + 1;
			}else {

				this.participant.add(aux);
				System.out.println("You were signed up to the event!");
				return 0;
			}
				
		}else {
			System.out.println("This person is already a participant!");
			return -1;
		}	
	}
	
	
	//Metoda stergere
	public boolean deleteParticipant() {
		
		int aux = this.howToCheck();
		int aux1 = this.returnIndex(aux);
		
		if (aux1 >= this.maxGuests) {
			System.out.println("The participant " + this.waitParticipant.get(aux1 - this.maxGuests).getfirstName() 
					+ " " + this.waitParticipant.get(aux1 - this.maxGuests).getlastName() 
					+ " was deleted from the waiting list");
			this.waitParticipant.remove(aux1 - this.maxGuests);
			return true;
		}
		
		if (aux1 < this.maxGuests && aux1 != -1) {
			System.out.println("The participant " + this.participant.get(aux1).getfirstName() 
					+ " " + this.participant.get(aux1).getlastName() + " was deleted");
			this.participant.remove(aux1);
			
			if (this.waitParticipant.size() > 0) {
				System.out.println("The participant " + this.waitParticipant.get(0).getfirstName() 
						+ " " + this.waitParticipant.get(0).getlastName() + " is now on the guest list");
				this.participant.add(this.waitParticipant.get(0));
				this.waitParticipant.remove(0);
			}
			return true;
		}
		
		System.out.println("The participant was not found !");
		return false;
	}
	
	
	//Metoda update	
	private  int howToCheckForUpdate () {
		int aux;
		System.out.println("What do you want to update ?\n"
				+ "1. Last name and first name\n"
				+ "2. E-mail\n"
				+ "3. Phone number");
		
		try {
			  aux = sc.nextInt();
			}
			catch(Exception e) {
				System.out.println("Are you sure that is a number ?");
				return - 1;
			}
		
		if (aux > 3 || aux < 1) {
			System.out.println("Wrong input !");
			return -1;
		}else
			return aux;
	}

	public boolean updateParticipant() {
		String lastName;
		String firstName;
		String email;
		String phone;
		
		int aux = this.howToCheck();
		int aux1 = this.returnIndex(aux);
		
		if (aux1 != -1) {
			int aux2 = this.howToCheckForUpdate();
			
			if (aux2 == 1) {
				System.out.println("Last name : ");
				lastName = sc.next();
				System.out.println("First name : ");
				firstName = sc.next();
				
				Guest auxGuest = new Guest(lastName, firstName);
				
				if (this.checkName(auxGuest) == -1)
					if (aux1 > this.maxGuests) 
						this.waitParticipant.get(aux1 - this.maxGuests).changeName(firstName, lastName);
					else
						this.participant.get(aux1).changeName(firstName, lastName);	
				else {
					System.out.println("This name is already on the list !");
					return true;
					}
			}
			
			if (aux2 == 2) {
				System.out.println("E-mail : ");
				email = sc.next();
				
				Guest auxGuest = new Guest(email, 2);
				
				if (this.checkEmail(auxGuest) == -1)
					if (aux1 > this.maxGuests) 
						this.waitParticipant.get(aux1 - this.maxGuests).changeEmail(email);
					else
						this.participant.get(aux1).changeEmail(email);
				else {
					System.out.println("This e-mail is already on the list !");
					return true;
					}
			}
			
			if (aux2 == 3) {
				System.out.println("Phone number : ");
				phone = sc.next();
				
				Guest auxGuest = new Guest(phone, 3);
				
				if (this.checkPhone(auxGuest) == -1)
					if (aux1 > this.maxGuests) 
						this.waitParticipant.get(aux1 - this.maxGuests).changePhone(phone);
					else
						this.participant.get(aux1).changeEmail(phone);
				else {
					System.out.println("This phone is already on the list !");
					return true;
					}
			}
			System.out.println("The participant was update !");
			return true;	
		}else {
		System.out.println("The participant was not found !");
		return false;
		}
	}
	
	
	//Metoda obtinere lista participare
	public void showGuestList() {	
		System.out.println("The Guest List : \n");
		for (Guest i : participant)
			System.out.println("\n" + (this.participant.indexOf(i) + 1) + "." + " Name : " + i.getlastName() + " " + i.getfirstName()
					+ "\n   E-mail : " + i.getemail()
					+ "\n   Phone number : " + i.getphone());
	}

	public ArrayList<Guest> getGuestList(){
		return this.participant;
	}

	//Metoda obtinerea lista de asteptare
	public void showWaitGuestList() {
		System.out.println("Waiting List :\n");
		for (Guest i : this.waitParticipant)
			System.out.println("\n" + (this.waitParticipant.indexOf(i) + 1) + "." + " Name : " + i.getlastName() + " " + i.getfirstName()
					+ "\n   E-mail : " + i.getemail()
					+ "\n   Phone number : " + i.getphone());
	}

	public ArrayList<Guest> getWaitGuestList() {
		return this.waitParticipant;
	}

	//Metoda obtinere locuri disponibile
	public int spaceLeftOnGuestList() {
		System.out.println("There are " + (this.maxGuests - this.participant.size()) + " tickets left");
		return this.maxGuests - this.participant.size();
	}

	//Metoda obtinere persoane participante
	public int participantOnGuestList() {
		System.out.println("There are " + this.participant.size() + " participants!");
		return this.participant.size();
	}
	
	//Metoda obtinere persoane asteptare
	public int participantOnWaitGuestList() {
		System.out.println("There are " + this.waitParticipant.size() + " guests on the waiting list!");
		return this.waitParticipant.size();
	}
	
	//Metoda obtinere nr total de persoane
	public int allParticipants() {
		System.out.println("There are " + (this.waitParticipant.size() + this.participant.size()) + " guests to the event!");
		return this.participant.size() + this.waitParticipant.size();
	}

	//Metoda cautare partiala
	private ArrayList<Guest> searchInList() {
		ArrayList<Guest> aux = new ArrayList<Guest>();
		String search;
		
		System.out.println("What do you want to search ?");
		sc.next();
		search = sc.nextLine();
		
		for (Guest i : this.participant)
			if (i.getemail().contains(search) || i.getfirstName().contains(search) || i.getlastName().contains(search) || i.getphone().contains(search))
				aux.add(i);	
		
		
		for (Guest i : this.waitParticipant)
			if (i.getemail().contains(search) || i.getfirstName().contains(search) || i.getlastName().contains(search) || i.getphone().contains(search))
				aux.add(i);
		
		return aux;
	}

	public void showSearchInList() {
		
		ArrayList<Guest> aux = this.searchInList();
		System.out.println("\nGuest that match:\n");
		for (Guest i : aux)
			System.out.println("\n" + (aux.indexOf(i) + 1) + "." + " Name : " + i.getlastName() + " " + i.getfirstName()
					+ "\n   E-mail : " + i.getemail()
					+ "\n   Phone number : " + i.getphone());
	}






}
