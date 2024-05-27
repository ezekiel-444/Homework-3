package lms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LMS {

    List<Book> books = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                return;
            }
        }
        books.add(book);
    }

    public void removeBook(Book book) {
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                books.remove(b);
                return;
            }
        }
    }

    public void borrowBook(Book book, Student student) {
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                for (BorrowedBook borrowedBook : borrowedBooks) {
                    if (borrowedBook.getBook().getTitle().equals(book.getTitle())) {
                        System.out.println("Book already borrowed: " + book.getTitle());
                        return;
                    }
                }
                borrowedBooks.add(new BorrowedBook(book, student));
                return;
            }
        }
        System.out.println("Book not found in the library: " + book.getTitle());
    }

    public void returnBook(Book book) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().getTitle().equals(book.getTitle())) {
                borrowedBooks.remove(borrowedBook);
                return;
            }
        }
        System.out.println("Book not found in borrowed books: " + book.getTitle());
    }

    public void saveState(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(new File(filePath), StandardCharsets.UTF_8);

            // Write book information
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor());
            }

            // Write an empty line
            writer.println();

            // Write borrowed book information
            for (BorrowedBook borrowedBook : borrowedBooks) {
                writer.println(borrowedBook.getBook().getTitle() + "," + borrowedBook.getBook().getAuthor() + "," + borrowedBook.getStudent().getName() + "," + borrowedBook.getStudent().getSurname() + "," + borrowedBook.getStudent().getPersonalNumber());
            }

            writer.close();
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the state.");
            e.printStackTrace();
        }
    }

    public void loadState(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Clear current state
            students.clear();
            books.clear();
            borrowedBooks.clear();

            // Load book information
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break; // Empty line indicates end of books

                String[] parts = line.split(",");
                books.add(new Book(parts[0], parts[1]));
            }

            // Load borrowed book information
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break; // Empty line indicates end of borrowed books

                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1]);
                Student student = new Student(parts[2], parts[3], parts[4]);
                borrowedBooks.add(new BorrowedBook(book, student));
            }

            scanner.close();
            System.out.println("Loaded");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading the state.");
            e.printStackTrace();
        }
    }
}


