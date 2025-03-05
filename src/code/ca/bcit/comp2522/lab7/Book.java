package ca.bcit.comp2522.lab7;

/**
 * Represents a Book with a title, publishing year, and an author.
 * With methods for comparing two books, printing its details, and getting its title reversed.
 *
 * @author Mohammad Sadeghi
 * @author Brownie Tran
 * @version 1.0
 */
public class Book implements Comparable<Book>, Printable, Reversible
{
    private final int MAX_TITLE_LENGTH = 100;
    private final int CURRENT_YEAR = 2025;
    private final int EARLIEST_PUBLISHED_YEAR = 1;

    private final String title;
    private final Date   yearPublished;
    private final Author author;

    /**
     * Constructs a Book with a title, publishing year, and an author.
     *
     * @param title title of  book
     * @param yearPublished publishing year
     * @param author author of book
     */
    Book(final String title,
         final Date yearPublished,
         final Author author)
    {
        validateTitle(title);
        validateAuthor(author);
        validateYearPublished(yearPublished);

        this.title         = title;
        this.author        = author;
        this.yearPublished = yearPublished;
    }

    /*
     * Validates the title of the book on criteria:
     * 1. Cannot not be null
     * 2. Cannot not be blank
     * 3. Length must be less than MAX_TITLE_LENGTH
     *
     * @param title given title
     */
    private void validateTitle(final String title)
    {
        if(title == null ||
           title.isBlank() ||
           title.length() > MAX_TITLE_LENGTH)
        {
            throw new IllegalArgumentException("Title cannot be null or blank and must be less than " + MAX_TITLE_LENGTH);
        }
    }

    /*
     * Validates the author on criteria:
     * 1. Cannot be null
     *
     * @param author
     */
    private void validateAuthor(final Author author)
    {
        if(author == null)
        {
            throw new IllegalArgumentException("Author cannot be null");
        }
    }

    /*
     * Validates the author of the book on criteria:
     * 1. Must be between year 1 and CURRENT_YEAR
     *
     * @param yearPublished given year of book being published
     */
    private void validateYearPublished(final Date yearPublished)
    {
        if(yearPublished == null ||
           yearPublished.getYear() > CURRENT_YEAR ||
           yearPublished.getYear() < EARLIEST_PUBLISHED_YEAR)
        {
            throw new IllegalArgumentException("Year cannot be null or and must be between 1 and " + CURRENT_YEAR);
        }
    }

    /**
     * Compare two books, older books are "larger".
     *
     * @param o the object to be compared.
     * @return positive if this is the older book,
     * 0 if same publishing year, and negative if the other book is older
     */
    @Override
    public int compareTo(final Book o)
    {
        return this.yearPublished.getYear() - o.yearPublished.getYear();
    }

    /**
     * Prints the details of the book.
     * Prints in the format of Title: (title) published by (author) on (yearPublished)
     */
    @Override
    public void display()
    {
        System.out.println("Title: " + title +
                           " published by: ");
        author.display();
        System.out.println("on " + yearPublished.toString());
    }

    /**
     * Returns the title of the book backwards.
     *
     * @return title of book backwards
     */
    @Override
    public String backward()
    {
        final StringBuilder title;
        title = new StringBuilder();

        title.append(this.title);

        return title.reverse().toString();
    }
}
