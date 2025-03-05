package ca.bcit.bookstore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a bookstore containing a collection of literature.
 * The BookStore class manages a collection of literature, allowing various operations
 * such as sorting, filtering, and retrieving statistical information.
 *
 * @author Giorgio Donatelli
 * @author Brownie Tran
 * @version 1.0
 */
class BookStore<T extends Literature>
{
    private static final int DEC_TO_PERCENT_FACTOR = 100;
    private static final int DECADE = 9;
    private static final int NONE = 0;

    private final String name;
    private final List<T> items = new ArrayList<>();

    /**
     * The entry point of the application.
     *
     * <p>Creates a {@link BookStore} instance.</p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args)
    {
        final BookStore<Literature> store;

        store = new BookStore<>("Bookland");

        store.addItem(new Novel("War and Peace", 1984));
        store.addItem(new ComicBook("Spider-Man", 1990));
        store.addItem(new Magazine("National Geographic", 2006));

        store.printItems();

        store.items.sort(new Comparator<>() {
            @Override
            public int compare(final Literature o1, final Literature o2) {
                return Integer.compare(o1.getTitle().length(), o2.getTitle().length());
            }
        });
    }

    /**
     * Class for handling info about the BookStore.
     */
    static class BookStoreInfo
    {
        /**
         * Displays info about the BookStore.
         *
         * @param storeName the name of the store
         * @param itemCount the number of items in the store
         */
        public void displayInfo(final String storeName,
                                final int itemCount)
        {
            System.out.println("BookStore: " + storeName + ", Items: " + itemCount);
        }
    }

    /**
     * Class for computing statistics about the literature
     * in the BookStore.
     */
    class NovelStatistics
    {
        /**
         * Calculates the average length of all
         * the literatu re titles in the store.
         *
         * @return the average title length
         */
        double averageTitleLength()
        {
            int totalLength;

            totalLength = 0;

            for(final T item : items)
            {
                totalLength += item.getTitle().length();
            }
            return (double) totalLength / items.size();
        }
    }

    /**
     * Constructs a BookStore with the specified name.
     *
     * @param name the name of the BookStore
     * @throws IllegalArgumentException if the name is invalid
     */
    BookStore(final String name)
    {
        validateString(name);

        this.name = name;
    }

    /*
     * Validates that the provided object is not null.
     *
     * @param str the object to validate
     * @throws IllegalArgumentException if the object is null
     */
    private static void validateString(final String str)
    {
        if(str == null || str.isBlank())
        {
            throw new IllegalArgumentException("String cannot be null or blank");
        }
    }

    /*
     * Adds a new piece of literature to the bookstore.
     *
     * @param item the literature to add
     */
    private void addItem(final T item)
    {
        items.add(item);
    }

    /*
     * Prints all literature in the bookstore.
     */
    private void printItems()
    {
        for(final T item : items)
        {
            System.out.println(item.getTitle());
        }
    }

    /*
     * Prints all novel titles in uppercase.
     */
    private void printAllTitles()
    {
        for(final T item : items)
        {
            System.out.println(item.getTitle().toUpperCase());
        }
    }

    /*
     * Prints the titles of all novels that contain the specified substring.
     *
     * @param title the substring to search for within the novel titles (case-insensitive)
     */
    private void printBookTitle(final String title)
    {
        items.forEach(item -> {
            if(item.getTitle().toLowerCase().contains(title.toLowerCase()))
            {
                System.out.println(item.getTitle());
            }
        });
    }

    /*
     * Sorts the novels in alphabetical order (case-insensitive) by title and prints each title.
     */
    private void printTitlesInAlphaOrder()
    {
        items.sort(Comparator.comparing(Literature::getTitle, String::compareToIgnoreCase));
        items.forEach(item -> System.out.println(item.getTitle()));
    }

    /*
     * Prints the titles of novels published within the decade starting at the specified year.
     *
     * @param decade the starting year of the decade range
     */
    private void printGroupByDecade(final int decade)
    {
        for(final T item : items)
        {
            if(item.getYearPublished() >= decade && item.getYearPublished() <= decade + DECADE)
            {
                System.out.println(item.getTitle());
            }
        }
    }

    /*
     * Finds and prints the title of the novel with the longest title.
     *
     * <p>If multiple novels share the same longest title length, the first encountered is printed.</p>
     */
    private void getLongest()
    {
        T longestNovelTitle = items.getFirst();
        for(final T item : items)
        {
            if(item.getTitle().length() > longestNovelTitle.getTitle().length())
            {
                longestNovelTitle = item;
            }
        }
        System.out.println(longestNovelTitle.getTitle());
    }

    /*
     * Determines whether there is any novel published in the specified year.
     *
     * @param year the publication year to search for
     * @return {@code true} if at least one novel was published in the given year; {@code false} otherwise
     */
    private boolean isThereABookWrittenIn(final int year)
    {
        for(final T item : items)
        {
            if(item.getYearPublished() == year)
            {
                return true;
            }
        }
        return false;
    }

    /*
     * Counts how many novels have titles that contain the specified word.
     *
     * @param word the word to search for within the novel titles (case-insensitive)
     * @return the count of novels whose titles contain the specified word
     */
    private int howManyBooksContain(final String word)
    {
        int booksContainingWord = NONE;
        for(final T item : items)
        {
            if(item.getTitle().toLowerCase().contains(word.toLowerCase()))
            {
                booksContainingWord++;
            }
        }
        return booksContainingWord;
    }

    /*
     * Calculates the percentage of novels published between the specified years (inclusive).
     *
     * @param first the starting year of the range (inclusive)
     * @param last the ending year of the range (inclusive)
     * @return the percentage of novels published within the specified range
     */
    private double whichPercentWrittenBetween(final int first, final int last)
    {
        int booksBetweenYears = NONE;
        for(final T item : items)
        {
            if(item.getYearPublished() >= first && item.getYearPublished() <= last)
            {
                booksBetweenYears++;
            }
        }
        return (booksBetweenYears / (double)(items.size())) * DEC_TO_PERCENT_FACTOR;
    }

    /*
     * Retrieves the oldest novel in the collection.
     *
     * @return the {@link Novel} with the earliest publication year
     */
    private T getOldestBook()
    {
        T oldestBook = items.getFirst();
        for(final T item : items)
        {
            if(item.getYearPublished() < oldestBook.getYearPublished())
            {
                oldestBook = item;
            }
        }
        return oldestBook;
    }

    /*
     * Retrieves a list of novels whose title length matches the specified value.
     *
     * @param titleLength the exact length that the novel titles should match
     * @return an {@code ArrayList} of novels with titles of the specified length
     */
    private ArrayList<T> getBooksThisLength(final int titleLength)
    {
        final ArrayList<T> booksThisLength = new ArrayList<>();
        for(final T item : items)
        {
            if(item.getTitle().length() == titleLength)
            {
                booksThisLength.add(item);
            }
        }
        return booksThisLength;
    }

    /*
     * Adds novels from the store to a List.
     *
     * @param novelCollection The List of Novels to add to
     */
    private void addNovelsToCollection(final List<? super Novel> novelCollection)
    {
        for (final T item : items)
        {
            if (item instanceof Novel)
            {
                novelCollection.add((Novel) item);
            }
        }
    }
}
