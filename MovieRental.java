public class MovieRental {

	public static void main(String[] args) {
		//Create Customers
		Customer averageCustomer = new Customer("Malcolm", 39, 'N');
		Customer youngCustomer = new Customer("Emmanuel", 20, 'D');
		Customer oldCustomer = new Customer("Idris", 63, 'H');
		
		//Add Rentals To Middle Aged Customer - average case
		averageCustomer.addRental(new Rental("Black Panther", 2, 23));
		averageCustomer.addRental(new Rental("Get Out", 0, 16));
		averageCustomer.addRental(new Rental("Spiderverse", 0, 4));
		System.out.println(averageCustomer.printStatement());
		
		//Add Rentals To Old Customer - several movie types case
		oldCustomer.addRental(new Rental("Forrest Gump", 2, 10));
		oldCustomer.addRental(new Rental("The Godfather", 0, 14));
		oldCustomer.addRental(new Rental("Scarface", 0, 12));
		oldCustomer.addRental(new Rental("Uncut Gems", 1, 7));
		System.out.println(oldCustomer.printStatement());

		//Add Rentals To Young Customer - new release & adolescent case
		youngCustomer.addRental(new Rental("Pulp Fiction", 0, 11));
		youngCustomer.addRental(new Rental("Ex-Machina", 0, 19));
		youngCustomer.addRental(new Rental("Parasite", 1, 15));
		System.out.println(youngCustomer.printStatement());
	}

}
