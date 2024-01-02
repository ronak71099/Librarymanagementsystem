package librarymangement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isCheckedOut;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkout() {
        if (!isCheckedOut) {
            isCheckedOut = true;
            System.out.println("Book checked out successfully: " + title);
        } else {
            System.out.println("Book is already checked out: " + title);
        }
    }

    public void returnBook() {
        if (isCheckedOut) {
            isCheckedOut = false;
            System.out.println("Book returned successfully: " + title);
        } else {
            System.out.println("Book is not checked out: " + title);
        }
    }
}

class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the library: " + book.getTitle());
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public void checkoutBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book != null && member != null) {
            book.checkout();
            member.borrowBook(book);
        } else {
            System.out.println("Book or member not found.");
        }
    }

    public void returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book != null && member != null) {
            book.returnBook();
            member.returnBook(book);
        } else {
            System.out.println("Book or member not found.");
        }
    }

    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
}

public class lms {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("B001", "Introduction to Java", "John Doe");
        Book book2 = new Book("B002", "Data Structures", "Jane Smith");

        Member member1 = new Member("M001", "Alice");
        Member member2 = new Member("M002", "Bob");

        library.addBook(book1);
        library.addBook(book2);

        library.registerMember(member1);
        library.registerMember(member2);

        library.checkoutBook("B001", "M001");
        library.checkoutBook("B002", "M002");


        System.out.println("Books borrowed by Alice after returning: " + member1.getBorrowedBooks().size());
        System.out.println("Books borrowed by Bob after returning: " + member2.getBorrowedBooks().size());

        library.returnBook("B001", "M001");
        library.returnBook("B002", "M002");

        // Display borrowed books after returning
        System.out.println("Books borrowed by Alice after returning: " + member1.getBorrowedBooks().size());
        System.out.println("Books borrowed by Bob after returning: " + member2.getBorrowedBooks().size());
    }
}
