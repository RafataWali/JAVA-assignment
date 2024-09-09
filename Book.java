
    //Define Book class to store the book's informations
public class Book{
    private String title;       //book's name
    private String author;      //author name
    private int physicalCopies; //number of copies
    private boolean hasEbook;   //has ebook or not
    
    //Install books informations
    public Book(String title, String author, int physicalCopies, boolean hasEbook) {
        this.title = title;
        this.author = author;
        this.physicalCopies = physicalCopies;
        this.hasEbook = hasEbook;
    }

    //getter method, get class
    public String getTitle()   {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getPhysicalCopies() {
        return physicalCopies;
    }
    public boolean hasEbook() {
        return hasEbook;
    }

    //Setter method for adjust 
    public void purchasePhysicalCopy() {
        if (physicalCopies > 0) {
            physicalCopies--;
        } else {
            System.out.println("No physical copies available.");
        }
    }
    public void returnPhysicalCopy() {
        physicalCopies++;
    }
}

