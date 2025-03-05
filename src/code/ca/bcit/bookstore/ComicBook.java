package ca.bcit.bookstore;

/**
 * Represents a comic book.
 *
 * @author Giorgio Donatelli
 * @author Brownie Tran
 * @version 1.0
 */
class ComicBook extends Literature
{
    private final String title;
    private final int yearPublished;

    /**
     * Constructs a new comic book.
     *
     * @param title the title of the comic book
     * @param yearPublished the year the book was published
     * @throws IllegalArgumentException if the title is invalid
     */
    ComicBook(final String title,
              final int yearPublished)
    {
        validateString(title);
        validatePubYear(yearPublished);

        this.title = title;
        this.yearPublished = yearPublished;
    }

    /**
     * Gets the title of the comic book.
     *
     * @return the title of the comic book
     */
    @Override
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the year of publication.
     *
     * @return the year of publication
     */
    @Override
    public int getYearPublished()
    {
        return yearPublished;
    }

    /*
     * Validates that the provided object is not null.
     *
     * @param str the object to validate
     * @throws IllegalArgumentException if the object is invalid
     */
    private static void validateString(final String str)
    {
        if(str == null || str.isBlank())
        {
            throw new IllegalArgumentException("String cannot be null or blank");
        }
    }

    /*
     * Validates a publication year.
     *
     * @param year the year to validate
     * @throws IllegalArgumentException if the year is invalid
     */
    private static void validatePubYear(final int year)
    {
        if(year < Literature.MIN_PUB_YEAR || year > Literature.CUR_YEAR)
        {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
    }
}
