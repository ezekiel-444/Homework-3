package lms;

public class LMSTester {
    public static void main(String[] args) {
        // Create the LMS instance
        LMS iliauniLibrary = new LMS();

        // Create book instances
        Book starWars = new Book("Star Wars", "George Lucas");
        Book city = new Book("City", "Clifford Simak");

        // Add books to the library
        iliauniLibrary.addBook(starWars);
        iliauniLibrary.addBook(city);

        // Create student instances
        Student gocha = new Student("Gocha", "Lomidze", "12345677");
        Student saba = new Student("Saba", "Nadiradze", "3421325");

        // Borrow books
        iliauniLibrary.borrowBook(starWars, gocha);

        // Try to borrow an already borrowed book
        iliauniLibrary.borrowBook(starWars, saba); // This should not be allowed

        // Save the state to a file
        iliauniLibrary.saveState("libraryState.txt");

        // Print borrowed books information
        System.out.println("Borrowed books before loading state:");
        for (BorrowedBook borrowedBook : iliauniLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName());
        }

        // Load the state from the file
        iliauniLibrary.loadState("libraryState.txt");

        // Print borrowed books information after loading state
        System.out.println("Borrowed books after loading state:");
        for (BorrowedBook borrowedBook : iliauniLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName());
        }

        // Return a book
        iliauniLibrary.returnBook(starWars);

        // Print borrowed books information after returning a book
        System.out.println("Borrowed books after returning a book:");
        for (BorrowedBook borrowedBook : iliauniLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName());
        }
    }
}


