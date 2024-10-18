package src;
import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Transaction(Book book, Member member, LocalDate borrowDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "book=" + book +
                ", member=" + member +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

    // Getters and Setters 

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    

    
}
