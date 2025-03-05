package ca.bcit.comp2522.lab7;


/**
 * Represents an Author, which extends Person and implements the Printable interface.
 * An Author has a specific genre they write in.
 *
 * @author Mohammad Sadeghi
 * @author Brownie Tran
 * @version 1.0
 */
public class Author extends Person implements Printable
{
    private final int MAX_CHARACTER = 30;

    private final String genre;

    /**
     * Constructs an Author object with specified birth date, death date, name, and genre.
     *
     * @param dateOfBirth The birth date of the author.
     * @param dateOfDeath The death date of the author (null if still alive).
     * @param name The full name of the author.
     * @param genre The genre the author writes in.
     * @throws IllegalArgumentException if the genre is null, empty, or exceeds the max character limit.
     */
    public Author(final Date dateOfBirth,
                  final Date dateOfDeath,
                  final Name name,
                  final String genre)
    {
        super(dateOfBirth, dateOfDeath, name);
        validateGenre(genre);
        this.genre = genre;
    }

    /**
     * Validates the genre input to ensure it meets constraints.
     *
     * @param genre The genre to validate.
     * @throws IllegalArgumentException if genre is null, empty, or exceeds max character limit.
     */
    private void validateGenre(final String genre)
    {
        if(genre == null || genre.isEmpty())
        {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        } else if(genre.length() > MAX_CHARACTER)
        {
            throw new IllegalArgumentException("Genre cannot have more than 30 characters");
        }
    }

    /**
     * Return genre as a String
     *
     * @return genre
     */
    protected String getGenre()
    {
        return genre;
    }

    /**
     * Displays the author's information, including their genre.
     */
    @Override
    public void display()
    {
        super.display();
        final StringBuilder sb = new StringBuilder();
        sb.append("Author's genre: ");
        sb.append(genre);
        System.out.println(sb.toString());
    }
}
