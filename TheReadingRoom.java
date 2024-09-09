import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheReadingRoom {
    public static void main(String[] args) {
        //create a array list to store object data
        List<Book> books = new ArrayList<>();
        //create a array list to cart
        List<Book> cart = new ArrayList<>();
        Scanner scanner = new Scanner (System.in);
        boolean running = true;
        //add book information(data) to object
        books.add(new Book("Absolute Java", "Savitch", 5, true));
        books.add(new Book("JAVA: How to Program", "Deitel and Deitel", 0, true));
        books.add(new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false));
        books.add(new Book("Java Software Solutions", "Lewis and Lotfus", 5, false));
        books.add(new Book("JAVA Program Design", "Cohoon and Davidson", 1, true));
        
      
        
        while (running) {
            //display menue
            System.out.println("=".repeat(48));
            System.out.println("Well come to the Reading Room !");
            System.out.println("=".repeat(48));
            System.out.println("Chose an option:");
            System.out.println("1. Add a book to shoppong cart");
            System.out.println("2. View shopping cart");
            System.out.println("3. Remove a book from shopping cart");
            System.out.println("4. Checkout");
            System.out.println("5. List all books");
            System.out.println("6. Quit");
            System.out.println("Please Select:  ");
            
            //input 
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                addBookToCart(books, cart, scanner);
                    break;
                case 2:
                viewCart(cart);
                    break;
                case 3:
                removeBookFromCart(cart, scanner);
                    break;
                case 4:
                checkout(cart);
                    break;
                case 5:
                listAllBooks(books);
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();

    }


    //add book to cart
    public static void addBookToCart(List<Book> books, List<Book> cart, Scanner scanner) {
        System.out.println("Enter a key word to search for a book: ");
        String keyword = scanner.nextLine().toLowerCase();

        List<Book> matchedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword) ) {
                matchedBooks.add(book);
            }
        }
        if (matchedBooks.isEmpty()){
            System.out.println("No books found with that keyword."); 
        } else {
            System.out.println("The folowing books were found: ");
            for (int i = 0; i<matchedBooks.size(); i++) {
                System.out.println((i+1) + ". " + matchedBooks.get(i).getTitle());
            }
            System.out.println(matchedBooks.size() + 1 + ". Cancel");

            System.out.println("please select: ");
            int bookSlecetion = scanner.nextInt();
            scanner.nextLine();

            if (bookSlecetion > 0  && bookSlecetion <= matchedBooks.size()) {
                Book selectedBook = matchedBooks.get(bookSlecetion - 1);
                System.out.println("Do you want to buy this as an ebook (yes/no)?: ");
                String ebookChoice = scanner.nextLine().toLowerCase();
                if (ebookChoice.equals("yes") && selectedBook.hasEbook()) {
                    cart.add(selectedBook);
                    System.out.println("E-book of \"" + selectedBook.getTitle() + "\" has been added to your cart.");
                } else if (!ebookChoice.equals("yes")) {
                    if (selectedBook.getPhysicalCopies() > 0){
                        selectedBook.purchasePhysicalCopy();
                        cart.add(selectedBook);
                        System.out.println("Physica copy of \"" + selectedBook.getTitle() + "\" has been added to your cart.");
                    } else{
                        System.out.println("Sorry! There is no physical copy of the book.");
                    }                     
                }
            } else if (bookSlecetion != matchedBooks.size() + 1) {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //view cart
    public static void viewCart(List<Book> cart){
        if (cart.isEmpty()){
            System.out.println("Your cart is empty.");
        }else {
            System.out.println("Your shopping cart contains the following book(s):");
            for (int i = 0; i < cart.size(); i++) {
                Book book = cart.get(i);
                String type = book.hasEbook() ? "E-book" : "Physical copy";
                System.out.println((i + 1) + "." + book.getTitle() + book.getAuthor() + "|" + type);
            }
        } 
    }

    //remove book from cart
    public static void removeBookFromCart(List<Book> cart, Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            viewCart(cart);
            System.out.println("Which book would you like to remove (enter number)?");
            int index = scanner.nextInt();
            scanner.nextLine();
    
            
            if (index > 0 && index <= cart.size()) {
               
                Book bookToRemove = cart.remove(index - 1);
                if (!bookToRemove.hasEbook()) {
                    bookToRemove.returnPhysicalCopy();
                }
                System.out.println("Item removed from shopping cart.");
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }
    



    //checkout
    public static void checkout(List<Book> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. No checkout possible.");
        } else {
            double totalPrice = 0.0;
            for (Book book : cart) {
                totalPrice += book.hasEbook() ? 8.0 : 50.0;
            }
            System.out.println("Total due: $" + totalPrice);
            cart.clear();
            System.out.println("Checkout complete. Thank you for your purchase!");
        }
    }
    //List all books
    public static void listAllBooks(List<Book> books) {
        System.out.println("The following books are available:");
        for (Book book : books){
            String ebookAvaliability = book.hasEbook() ? "ebook available" : "no ebook available";
            System.out.println(book.getTitle() + book.getPhysicalCopies() + " copies, " + ebookAvaliability);
        
        }

    }
}
