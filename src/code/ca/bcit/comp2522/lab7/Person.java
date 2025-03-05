package ca.bcit.comp2522.lab7;
/**
 * Creates a person with a name, date of birth and date of death (if deceased).
 * Has methods for comparing two people based on date of birth, printing details
 * and getting full name backwards.
 *
 * @author Mohammad Sadeghi
 * @author Brownie Tran
 * @version 1.0
 */
public class Person implements Comparable<Person>, Printable, Reversible
{

    private final Date dateOfBirth;
    private final Date dateOfDeath;
    private final Name name;

    /**
     * Constructs a Person with a name, date of birth
     * and a date of death if they are dead.
     * Date of birth and name cannot be null, but date of death can be.
     *
     * @param dateOfBirth given date of birth
     * @param dateOfDeath given date of death
     * @param name        given name
     */
    public Person(final Date dateOfBirth,
                  final Date dateOfDeath,
                  final Name name)
    {
        validateDateOfBirth(dateOfBirth);
        validateName(name);

        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.name        = name;
    }

    /*
     * Validate date of birth on criteria:
     * 1. Cannot be null
     *
     * @param dateOfBirth given date of birth
     */
    private void validateDateOfBirth(final Date dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
    }

    /*
     * Validate name on criteria:
     * 1. Cannot be null
     *
     * @param name given name.
     */
    private void validateName(final Name name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Name of birth cannot be null");
        }
    }

    /**
     * Compares two people based on their date of birth.
     * Older people are "larger"
     *
     * @param o the object to be compared.
     * @return positive if this person older, 0 if they are equal
     * and negative if the other person is older
     */
    @Override
    public int compareTo(final Person o)
    {
        if(this.dateOfBirth.getYear() != o.dateOfBirth.getYear())
        {
            return this.dateOfBirth.getYear() - o.dateOfBirth.getYear();
        }
        if(this.dateOfBirth.getMonth() != o.dateOfBirth.getMonth())
        {
            return this.dateOfBirth.getMonth() - o.dateOfBirth.getMonth();
        }
        return this.dateOfBirth.getDay() - o.dateOfBirth.getDay();
    }

    /**
     * Display Person's full name, date of birth and date of death (if deceased).
     */
    @Override
    public void display()
    {
        final StringBuilder details;
        details = new StringBuilder();

        details.append("Full Name: ");
        details.append(name.getFullName());
        details.append("\nDate of Birth: ");
        details.append(dateOfBirth.getYyyyMmDd());

        if(dateOfDeath != null)
        {
            details.append(" Date of Death: ");
            details.append(dateOfDeath.getYyyyMmDd());
        }
        System.out.println(details.toString());
    }

    /**
     * Prints the person's full name backwards.
     *
     * @return Full name of this person backwards
     */
    @Override
    public String backward()
    {
        final StringBuilder fullName;
        fullName = new StringBuilder();

        fullName.append(this.name.getFullName());

        return fullName.reverse().toString();
    }
}
