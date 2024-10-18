package src;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class LibraryManagement {
    private static Library<Book> bookLibrary = new Library<>();
    private static Library<Member> memberLibrary = new Library<>();

    private static final String BOOKS_FILE = "books.dat";
    private static final String MEMBERS_FILE = "members.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        loadLibraries();

        while (running) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Display All Members");
            System.out.println("7. Save & Exit");

            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addBook(sc);
                case 2 -> addMember(sc);
                case 3 -> borrowBook(sc);
                case 4 -> returnBook(sc);
                case 5 -> displayBooks();
                case 6 -> displayMembers();
                case 7 -> {
                    saveLibraries();
                    running = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addBook(Scanner sc) {
        System.out.println("Enter title: ");
        String title = sc.nextLine();
        System.out.println("Enter author: ");
        String author = sc.nextLine();
        System.out.println("Enter ISBN: ");
        String ISBN = sc.nextLine();
        System.out.println("Enter publication year: ");
        int year = sc.nextInt();
        sc.nextLine();

        Book book = new Book(title, author, ISBN, year);
        bookLibrary.addItem(book);
        System.out.println("Book added!");
    }

    private static void addMember(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter member ID: ");
        String memberId = sc.nextLine();

        Member member = new Member(name, memberId);
        memberLibrary.addItem(member);
        System.out.println("Member added!");
    }

    private static void borrowBook(Scanner sc) {
        System.out.println("Enter member ID: ");
        String memberId = sc.nextLine();
        Member member = findMember(memberId);

        if (member == null) {
            System.out.println("Member not found!");
            return;
        }

        System.out.println("Enter ISBN of the book to borrow: ");
        String ISBN = sc.nextLine();
        Book book = findBook(ISBN);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        member.borrowBook(book);
        System.out.println("Book borrowed!");
    }

    private static void returnBook(Scanner sc) {
        System.out.println("Enter member ID: ");
        String memberId = sc.nextLine();
        Member member = findMember(memberId);

        if (member == null) {
            System.out.println("Member not found!");
            return;
        }

        System.out.println("Enter ISBN of the book to return: ");
        String ISBN = sc.nextLine();
        Book book = findBook(ISBN);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        member.returnBook(book);
        System.out.println("Book returned!");
    }

    private static void displayBooks() {
        System.out.println("Books in Library:");
        System.out.println(bookLibrary);
    }

    private static void displayMembers() {
        System.out.println("Members in Library:");
        System.out.println(memberLibrary);
    }

    private static Book findBook(String ISBN) {
        return bookLibrary.getAllItems().stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst()
                .orElse(null);
    }

    private static Member findMember(String memberId) {
        return memberLibrary.getAllItems().stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    private static void saveLibraries() {
        try {
            bookLibrary.saveToFile(BOOKS_FILE);
            memberLibrary.saveToFile(MEMBERS_FILE);
            System.out.println("Libraries saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving libraries: " + e.getMessage());
        }
    }

    private static void loadLibraries() {
        try {
            bookLibrary.loadFromFile(BOOKS_FILE);
            memberLibrary.loadFromFile(MEMBERS_FILE);
            System.out.println("Libraries loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading libraries: " + e.getMessage());
        }
    }
}