package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Library> lib = new ArrayList<>();
    public static ArrayList<Member> mem = new ArrayList<>();
    public static ArrayList<Book> books = new ArrayList<>();
    public static FastReader in = new FastReader();
    public static Library l1 = new Library();

    static void library(){

        System.out.println("------------------------------");
        System.out.println("1. Register a member");
        System.out.println("2. Remove a member");
        System.out.println("3. Add a book");
        System.out.println("4. Remove a book");
        System.out.println("5. View all members along with their books and fines to be paid");
        System.out.println("6. View all books");
        System.out.println("7. Back");
        System.out.println("------------------------------");
        int ch = in.nextInt();

        if(ch==7){
            System.out.println("------------------------------");
            main(null);
        }
        else if(ch==1){
            System.out.print("Name: ");
            String n = in.nextLine();
            System.out.print("Age: ");
            int a = in.nextInt();
            System.out.print("Phone Number: ");
            String ph = in.nextLine();
            l1.registerMember(ph,n,a);
            System.out.println("------------------------------");
            System.out.println("Member Added Successfully!");
            library();
        }
        else if(ch==2){
            System.out.print("Phone Number: ");
            String ph = in.nextLine();
            l1.removeMember(ph);
            System.out.println("------------------------------");
            System.out.println("Member Removed Successfully!");
            library();
        }
        else if(ch==3){
            System.out.print("Book ID: ");
            int bookid = in.nextInt();
            System.out.print("Title: ");
            String title = in.nextLine();
            System.out.print("Author: ");
            String auth = in.nextLine();
            System.out.print("Copy: ");
            int copy = in.nextInt();
            l1.addBook(bookid,title,auth,copy);
            lib.add(l1);
            System.out.println("------------------------------");
            System.out.println("Book Added Successfully!");
            library();
        }
        else if(ch==4){
            System.out.print("Book ID: ");
            int bookid = in.nextInt();
            l1.removeBook(bookid);
            System.out.println("------------------------------");
            System.out.println("Book Removed Successfully!");
            library();
        }
        else if(ch==5){
            l1.listMembers();
            library();
        }
        else if(ch==6){
            l1.listBooks();
            library();
        }
        else{
            System.out.println("Invalid Input....");
            System.out.println("Try Again");
            library();
        }

    }
    static void memberCheck(){

        System.out.print("Phone Number: ");
        String ph = in.nextLine();
        Member a = l1.getMemberByPhoneNumber(ph);
        System.out.println("------------------------------");
        if(a!=null){
            l1.loginAsMember(ph);
            System.out.println("Welcome "+a.getName());
            member(ph,a);
        }
        else{
            System.out.println("Sorry Member Not Found :(");
            main(null);
        }
    }
    static void member(String phoneNumber, Member a){

        System.out.println("------------------------------");
        System.out.println("1. List Available Books");
        System.out.println("2. List My Books");
        System.out.println("3. Issue book");
        System.out.println("4. Return book");
        System.out.println("5. Pay Fine");
        System.out.println("6. Back");
        System.out.println("------------------------------");
        int ch = in.nextInt();
        ArrayList<Book> temp = new ArrayList<>();

        if(ch==6){
            System.out.println("------------------------------");
            main(null);
        }
        else if(ch==1){
            l1.listBooks();
            member(phoneNumber,a);
        }
        else if(ch==2){
            l1.listMemberBooks();
            member(phoneNumber,a);
        }
        else if(ch==3){
            System.out.print("Book ID: ");
            int bookid = in.nextInt();
            l1.issueBook(bookid);
            member(phoneNumber,a);
        }
        else if(ch==4){
            System.out.print("Book ID: ");
            int bookid = in.nextInt();
            l1.returnBook(bookid);
            member(phoneNumber,a);
        }
        else if(ch==5){
            l1.calculateFine();
            member(phoneNumber,a);
        }
        else{
            System.out.println("Invalid Input....");
            System.out.println("Try Again");
            member(phoneNumber,a);
        }
    }
    public static void main(String[] args) {
        System.out.println("Library Portal Initialized....");
        System.out.println("------------------------------");
        System.out.println("1. Enter as a librarian");
        System.out.println("2. Enter as a member");
        System.out.println("3. Exit");
        System.out.println("------------------------------");
        int choice = in.nextInt();
        if(choice==3){
            System.out.println("------------------------------");
            System.out.println("Thanks for visiting!");
            System.out.println("------------------------------");
            System.exit(0);
        }
        else if(choice==1){
            library();
        }
        else if(choice==2){
            memberCheck();
        }
        else{
            System.out.println("wrong input try again!");
            main(null);

        }
    }
}