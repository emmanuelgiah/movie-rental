public class MovieRental {

	public static void main(String[] args) {
		//Create Customer
		String myName = "Emmanuel";
		Customer custom = new Customer(myName);
		//Create Movie
		Movie mov0 = new Movie("Inception", 1);
		Movie mov1 = new Movie("Batman", 2);
		Movie mov2 = new Movie("The Revenant", 0);
		//Create Rental
		Rental movieRental0 = new Rental(mov0, 5);
		Rental movieRental1 = new Rental(mov1, 7);
		Rental movieRental2 = new Rental(mov2, 3);
		Rental movieRental3 = new Rental("The Joker", 0, 10);
		//Add Rental
		custom.addRental(movieRental0);
		custom.addRental(movieRental1);
		custom.addRental(movieRental2);
		custom.addRental(movieRental3);
		System.out.println(custom.printStatement());
		System.out.println(custom.statementToXML());
	}

}
