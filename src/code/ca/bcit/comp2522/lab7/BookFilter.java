package ca.bcit.comp2522.lab7;


@FunctionalInterface
public interface BookFilter<T extends Literature>
{
    boolean filter(final T book);

}
