package ca.bcit.comp2522.lab7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a bookstore containing a collection of literature.
 * The BookStore class manages a collection of literature, allowing various operations
 * such as sorting, filtering, and retrieving statistical information.
 *
 * @author Brownie Tran
 * @author Raymond Yang
 * @author Andrew Hwang
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
        store.items.sort(Comparator.comparingInt(o -> o.getTitle().length()));

        store.printBooks(item -> item.getYearPublished()<1950);

    }

    public void printBooks(BookFilter filter)
    {
        for(T item : items)
        {
            if(filter.filter(item))
            {
                System.out.println(item);
            }
        }
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
         * the literature titles in the store.
         *
         * @return the average title length
         */
        double averageTitleLength()
        {
            int[] totalLength = {0};

            items.forEach((final T item) -> totalLength[0] += item.getTitle().length());
            return (double) totalLength[0] / items.size();
        }

        public void sortByTitle()
        {
            items.sort(Comparator.comparing(T::getTitle));
        }

        public double getAverageTitleLength()
        {
            int totalLength = 0;
            for(T item : items)
            {
                totalLength += item.getTitle().length();
            }
            return(items.size()>0) ? (double)totalLength/items.size() : 0;
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
        System.out.println("BooksStore: " + name);
        items.forEach((final T item) -> System.out.println(item.getTitle() + ": in stock"));
    }

    /*
     * Prints all novel titles in uppercase.
     */
    private void printAllTitles()
    {
        items.forEach((final T item) -> System.out.println(item.getTitle().toUpperCase()));
    }

    /*
     * Prints the titles of all novels that contain the specified substring.
     *
     * @param title the substring to search for within the novel titles (case-insensitive)
     */
    private void printBookTitle(final String title)
    {
        items.forEach((final T item) -> {
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
        items.forEach((final T item) -> System.out.println(item.getTitle()));
    }

    /*
     * Prints the titles of novels published within the decade starting at the specified year.
     *
     * @param decade the starting year of the decade range
     */
    private void printGroupByDecade(final int decade)
    {
        items.forEach((final T item) -> {
            if(item.getYearPublished() >= decade && item.getYearPublished() <= decade + DECADE) {
                System.out.println(item.getTitle());
            }
        });
    }

    /*
     * Finds and prints the title of the novel with the longest title.
     *
     * <p>If multiple novels share the same longest title length, the first encountered is printed.</p>
     */
    private void getLongest() {

        List<T> longestNovelTitle = new ArrayList<T>();
        longestNovelTitle.add(items.getFirst());

        items.forEach((final T item) -> {
            if (Comparator.comparingInt((T item1) -> item1.getTitle().length()).compare(item, longestNovelTitle.getFirst()) > 0) {
                longestNovelTitle.set(0, item);
            }
        });
        System.out.println(longestNovelTitle.getFirst().getTitle());
    }


    /*
     * Determines whether there is any novel published in the specified year.
     *
     * @param year the publication year to search for
     * @return {@code true} if at least one novel was published in the given year; {@code false} otherwise
     */
    private boolean isThereABookWrittenIn(final int year)
    {
        boolean[] result = {false};
        items.forEach((final  T item) -> {
            if (item.getYearPublished() == year) {
                result[0] = true;
            }
        });
        return result[0];
    }

    /*
     * Counts how many novels have titles that contain the specified word.
     *
     * @param word the word to search for within the novel titles (case-insensitive)
     * @return the count of novels whose titles contain the specified word
     */
    private int howManyBooksContain(final String word)
    {
        int[] booksContainingWord = {NONE};

        items.forEach((final T item) -> {
            if(item.getTitle().toLowerCase().contains(word.toLowerCase())) {
                booksContainingWord[0]++;
            }
        });

        return booksContainingWord[0];
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
        int[] booksBetweenYears = {NONE};

        items.forEach((final T item) -> {
            if (item.getYearPublished() >= first && item.getYearPublished() <= last) {
                booksBetweenYears[0]++;
            }
        });
        return (booksBetweenYears[0] / (double)(items.size())) * DEC_TO_PERCENT_FACTOR;
    }

    /*
     * Retrieves the oldest novel in the collection.
     *
     * @return the {@link Novel} with the earliest publication year
     */
    private T getOldestBook()
    {
        List<T> oldestBook = new ArrayList<T>();
        oldestBook.add(items.getFirst());

        items.forEach((final T item) -> {
            if(item.getYearPublished() < oldestBook.getFirst().getYearPublished()) {
                oldestBook.set(0, item);
            }
        });
        return oldestBook.getFirst();
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
        items.forEach((final T item) -> {
            if(item.getTitle().length() == titleLength) {
                booksThisLength.add(item);
            }
        });
        return booksThisLength;
    }

    /*
     * Adds novels from the store to a List.
     *
     * @param novelCollection The List of Novels to add to
     */
    private void addNovelsToCollection(final List<? super Novel> novelCollection)
    {
        items.forEach((final T item) -> {
            if (item instanceof Novel) {
                novelCollection.add((Novel) item);
            }
        });
    }
}
