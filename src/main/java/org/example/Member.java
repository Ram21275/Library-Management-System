package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Member {
    private String phoneNumber;
    private String name;
    private int age;
    private double penaltyAmount;
    private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
    private ArrayList<Book> bookFine = new ArrayList<>();
    public Member(String phoneNumber, String name, int age) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.penaltyAmount = 0;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }
    public ArrayList<Book> getBookFine(){
            return bookFine;
    }
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void increasePenaltyAmount(double fine){
        this.penaltyAmount+=fine;
    }

    public boolean canBorrowBooks() {
        return borrowedBooks.size() < 2 && penaltyAmount == 0;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.decreaseAvailableCopies();
    }

    public void returnBook(Book book) {
        bookFine.add(book);
        borrowedBooks.remove(book);
    }
    public void addBookFine(Book book){
        bookFine.add(book);
    }
    public void decreaseBookFine(Book book){
            bookFine.remove(book);
    }
    public void setBookFine(ArrayList<Book> bookFine) {
        this.bookFine = bookFine;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
