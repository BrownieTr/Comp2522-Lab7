package ca.bcit.comp2522.lab7;

/**
 * A class representing a generic piece of literature.
 *
 * @author Giorgio Donatelli
 * @author Brownie Tran
 * @version 1.0
 */
abstract class Literature {
    static final int MIN_PUB_YEAR = 0;
    static final int CUR_YEAR = 2025;

    /**
     * Gets the title of the literature.
     *
     * @return the title of the literature
     */
    public abstract String getTitle();

    /**
     * Gets the year of publication.
     *
     * @return the year of publication
     */
    public abstract int getYearPublished();

    /**
     * Prints the title in String
     *
     * @return
     */
    public abstract String toString();
}
