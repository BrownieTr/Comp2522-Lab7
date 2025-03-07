package ca.bcit.comp2522.lab7;

/**
 * Represents a novel.
 *
 * @author Giorgio Donatelli
 * @author Brownie Tran
 * @version 1.0
 */
class Novel extends Literature
{
    private final String title;
    private final int yearPublished;

    /**
     * Constructs a new novel.
     *
     * @param title the title of the novel
     * @param yearPublished the year the book was published
     * @throws IllegalArgumentException if the title is invalid
     */
    public Novel(final String title,
                 final int yearPublished)
    {
        validateString(title);
        validatePubYear(yearPublished);

        this.title = title;
        this.yearPublished = yearPublished;
    }

    public Novel() {
        this.title = null;
        this.yearPublished = 0;
    }

    /**
     * Gets the title of the novel.
     *
     * @return the title of the novel
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

    @Override
    public String toString()
    {
        return title;
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
