package org.example;

import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class Library {
    private ArrayList<Member> members;
    private ArrayList<Book> books;
    private Member loggedInMember;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(int bid, String title, String author, int totalCopies) {
        for(int i = 0; i < totalCopies; i++){
            books.add(new Book(bid, title, author, totalCopies));
            bid++;
        }
    }

    public void removeBook(int bookId) {
        books.removeIf(book -> book.getBookId() == bookId);
    }

    public void registerMember(String phoneNumber, String name, int age) {
        members.add(new Member(phoneNumber, name, age));
    }

    public void removeMember(String phoneNumber) {
        members.removeIf(member -> member.getPhoneNumber().equals(phoneNumber));
    }

    public Member getMemberByPhoneNumber(String phoneNumber) {
        for (Member member : members) {
            if (member.getPhoneNumber().equals(phoneNumber)) {
                return member;
            }
        }
        return null;
    }

    public void listMemberBooks(){
//        loggedInMember = getMemberByPhoneNumber(phoneNumber);
        ArrayList<Book> books = loggedInMember.getBorrowedBooks();
        for (Book book : books) {
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Total Copies: " + book.getTotalCopies());
                System.out.println("Available Copies: " + book.getAvailableCopies());
                System.out.println();
        }

    }

    public void calculateFine() {
//        loggedInMember = getMemberByPhoneNumber(phoneNumber);
//        long totalFine = 0;
        for(Book book: loggedInMember.getBookFine()){
            loggedInMember.decreaseBookFine(book);
        }
        if(loggedInMember.getPenaltyAmount()>0){
            System.out.println("Your fine of " + loggedInMember.getPenaltyAmount()+" has been submitted");
            loggedInMember.setPenaltyAmount(0);
        }
        else{
            System.out.println("You have no fine");
        }
    }

    public void loginAsMember(String phoneNumber) {
        loggedInMember = getMemberByPhoneNumber(phoneNumber);
    }

    public void issueBook(int bookId) {

        if (loggedInMember == null) {
            System.out.println("Please log in as a member.");
            return;
        }
        if (getBookById(bookId) == null) {
            System.out.println("Book not found.");
            return;
        }

        Book book = getBookById(bookId);
        book.setIssuedTime(Instant.now());
        System.out.println(book.getIssuedTime());

        if (!loggedInMember.canBorrowBooks()) {
            System.out.println("Cannot borrow more books or you have penalties to clear.");
            return;
        }

        loggedInMember.borrowBook(book);
        System.out.println("------------------------------");
        System.out.println("Book issued successfully.");

    }

    public void returnBook(int bookId) {
//        setTemp(loggedInMember.getBorrowedBooks());
        if (loggedInMember == null) {
            System.out.println("Please log in as a member.");
            return;
        }
        if (getBookById(bookId) == null) {
            System.out.println("Book not found.");
            return;
        }
        Book book = getBookById(bookId);
        book.setReturnTime(Instant.now());
        System.out.println(book.getReturnTime());
        long daysBetween = Duration.between(book.getIssuedTime(), book.getReturnTime()).getSeconds();
        System.out.println(daysBetween);
        if(daysBetween>=10){
            double fine = (daysBetween-10) * 3;
            loggedInMember.increasePenaltyAmount(fine);
            loggedInMember.addBookFine(book);
            book.increaseAvailableCopies();
            loggedInMember.returnBook(book);
            System.out.println("Book returned successfully."+" You have a fine of "+loggedInMember.getPenaltyAmount()+" for "+(daysBetween-10));
        }
        System.out.println("------------------------------");
        if (loggedInMember.getBorrowedBooks().contains(book)) {
            book.increaseAvailableCopies();
            loggedInMember.returnBook(book);
            System.out.println("Book returned successfully.No fine");
        } else {
            System.out.println("You did not borrow this book.");
        }
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Total Copies: " + book.getTotalCopies());
            System.out.println("Available Copies: " + book.getAvailableCopies());
            System.out.println();
        }
    }

    public void listMembers() {
        for (Member member : members) {
            System.out.println("Name: " + member.getName());
            System.out.println("Phone Number: " + member.getPhoneNumber());
            System.out.println("Age: " + member.getAge());
            System.out.println("Penalty Amount: " + member.getPenaltyAmount());
        }
    }

    private Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
