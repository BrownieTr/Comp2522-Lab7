package ca.bcit.comp2522.lab7;


@FunctionalInterface
public interface BookFilter
{
    boolean filter(Book book);
}
