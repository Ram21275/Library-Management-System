package org.example;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class Book {
    private int bookId;
    private String bookTitle;
    private String author;
    private int totalCopies;
    private int availableCopies;
    private Instant issuedTime;
    private Instant returnTime;


    Book(int bId, String bt, String aut, int c){
        this.bookId = bId;
        this.bookTitle = bt;
        this.author = aut;
        this.totalCopies = c;
        this.availableCopies = c;
    }

    public String getAuthor(){
        return author;
    }
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return bookTitle;
    }

    public int getTotalCopies(){
        return totalCopies;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public Instant getIssuedTime() {
        return issuedTime;
    }
    public Instant getReturnTime() {
        return returnTime;
    }
    public void setIssuedTime(Instant issuedTime) {
        this.issuedTime = issuedTime;
    }

    public void setReturnTime(Instant returnTime) {
        this.returnTime = returnTime;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void increaseAvailableCopies() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

}
