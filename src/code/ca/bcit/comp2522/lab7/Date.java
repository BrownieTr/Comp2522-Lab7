package ca.bcit.comp2522.lab7;

/**
 * Represents a date, with the capability of calculating the day of week.
 *
 * @author Felix Nguyen
 * @author Justin Cardas
 * @author Armaan Brar
 * @author Mohammad Sadeghi
 * @author Brownie Tran
 * @version 25.01
 */
public class Date implements Printable
{
    private static final int EARLIEST_SUPPORTED_YEAR;
    private static final int CURRENT_YEAR;

    private static final int FIRST_MONTH;
    private static final int MONTHS_IN_YEAR;

    private static final int FIRST_DAY;
    private static final int DAYS_IN_JAN;
    private static final int DAYS_IN_FEB;
    private static final int DAYS_IN_MAR;
    private static final int DAYS_IN_APR;
    private static final int DAYS_IN_MAY;
    private static final int DAYS_IN_JUN;
    private static final int DAYS_IN_JUL;
    private static final int DAYS_IN_AUG;
    private static final int DAYS_IN_SEP;
    private static final int DAYS_IN_OCT;
    private static final int DAYS_IN_NOV;
    private static final int DAYS_IN_DEC;
    private static final int LEAP_DAY;


    private static final int LEAP_YEAR_CYCLE;
    private static final int LEAP_YEAR_EXCEPTION_CYCLE;
    private static final int LEAP_YEAR_EXCEPTION_EXCEPTION_CYCLE;

    private static final int DAYS_IN_WEEK;

    private static final int JAN_WEEKDAY_CODE;
    private static final int FEB_WEEKDAY_CODE;
    private static final int MAR_WEEKDAY_CODE;
    private static final int APR_WEEKDAY_CODE;
    private static final int MAY_WEEKDAY_CODE;
    private static final int JUN_WEEKDAY_CODE;
    private static final int JUL_WEEKDAY_CODE;
    private static final int AUG_WEEKDAY_CODE;
    private static final int SEP_WEEKDAY_CODE;
    private static final int OCT_WEEKDAY_CODE;
    private static final int NOV_WEEKDAY_CODE;
    private static final int DEC_WEEKDAY_CODE;

    private static final int TWO_THOUSAND;
    private static final int EIGHTEEN_HUNDRED;
    private static final int ONE_HUNDRED;
    private static final int TWELVE;
    private static final int SIX;
    private static final int FOUR;
    private static final int TWO;
    private static final int ZERO;

    static
    {
        // year range
        EARLIEST_SUPPORTED_YEAR = 1700;
        CURRENT_YEAR            = 2025;

        // month range
        FIRST_MONTH    = 1;
        MONTHS_IN_YEAR = 12;

        // day ranges
        FIRST_DAY   = 1;
        DAYS_IN_JAN = 31;
        DAYS_IN_FEB = 28;
        DAYS_IN_MAR = 31;
        DAYS_IN_APR = 30;
        DAYS_IN_MAY = 31;
        DAYS_IN_JUN = 30;
        DAYS_IN_JUL = 31;
        DAYS_IN_AUG = 31;
        DAYS_IN_SEP = 30;
        DAYS_IN_OCT = 31;
        DAYS_IN_NOV = 30;
        DAYS_IN_DEC = 31;
        LEAP_DAY    = 1;

        // leap year cycles
        LEAP_YEAR_CYCLE                     = 4;
        LEAP_YEAR_EXCEPTION_CYCLE           = 100;
        LEAP_YEAR_EXCEPTION_EXCEPTION_CYCLE = 400;

        // values for calculating week day
        DAYS_IN_WEEK = 7;

        JAN_WEEKDAY_CODE = 1;
        FEB_WEEKDAY_CODE = 4;
        MAR_WEEKDAY_CODE = 4;
        APR_WEEKDAY_CODE = 0;
        MAY_WEEKDAY_CODE = 2;
        JUN_WEEKDAY_CODE = 5;
        JUL_WEEKDAY_CODE = 0;
        AUG_WEEKDAY_CODE = 3;
        SEP_WEEKDAY_CODE = 6;
        OCT_WEEKDAY_CODE = 1;
        NOV_WEEKDAY_CODE = 4;
        DEC_WEEKDAY_CODE = 6;

        TWO_THOUSAND     = 2000;
        EIGHTEEN_HUNDRED = 1800;
        ONE_HUNDRED      = 100;
        TWELVE           = 12;
        SIX              = 6;
        FOUR             = 4;
        TWO              = 2;
        ZERO             = 0;
    }

    private final int    day;
    private final int    month;
    private final int    year;
    private final String dayOfWeek;

    /**
     * Constructs a Date object. Validates whether the given parameters
     * constitute a valid date (throwing IllegalArgumentException if invalid).
     *
     * @param day   an int for the day
     * @param month an int for the month
     * @param year  an int for the year
     */
    public Date(final int day,
                final int month,
                final int year)
    {
        validateYear(year);
        validateMonth(month);
        validateDay(year, month, day);
        this.day       = day;
        this.month     = month;
        this.year      = year;
        this.dayOfWeek = calculateDayOfWeek(year, month, day);
    }

    /*
        Checks that the year is no earlier than the earliest supported year,
        and no later than the current year.
         Throws IllegalArgumentException if invalid.
     */
    private static void validateYear(final int year)
    {
        if(year < EARLIEST_SUPPORTED_YEAR)
        {
            throw new IllegalArgumentException("Year must be >=" + EARLIEST_SUPPORTED_YEAR);
        }
        if(year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year must be <=" + CURRENT_YEAR);
        }
    }

    /*
        Checks that the month is valid (January to December).
        Throws IllegalArgumentException if invalid.
     */
    private static void validateMonth(final int month)
    {
        if(month < FIRST_MONTH)
        {
            throw new IllegalArgumentException("Month must be >=" + FIRST_MONTH);
        }
        if(month > MONTHS_IN_YEAR)
        {
            throw new IllegalArgumentException("Month must be <=" + MONTHS_IN_YEAR);
        }
    }

    /*
        Checks that the day is valid given the year and month,
        considering leap years. Throws IllegalArgumentException if invalid.
     */
    private static void validateDay(final int year,
                                    final int month,
                                    final int day)
    {
        final int numberOfDays;
        numberOfDays = calculateNumberOfDays(year, month);
        if(day < FIRST_DAY)
        {
            throw new IllegalArgumentException("Day must be >=" + FIRST_DAY);
        }
        if(day > numberOfDays)
        {
            throw new IllegalArgumentException("Day must be <=" + numberOfDays);
        }
    }

    /*
        Calculates how many days are in a month of a particular year,
        considering leap years.
     */
    private static int calculateNumberOfDays(final int year,
                                             final int month)
    {
        return switch(month)
        {
            case 1 -> DAYS_IN_JAN;
            case 2 -> isLeapYear(year)
                      ? DAYS_IN_FEB + LEAP_DAY
                      : DAYS_IN_FEB;
            case 3 -> DAYS_IN_MAR;
            case 4 -> DAYS_IN_APR;
            case 5 -> DAYS_IN_MAY;
            case 6 -> DAYS_IN_JUN;
            case 7 -> DAYS_IN_JUL;
            case 8 -> DAYS_IN_AUG;
            case 9 -> DAYS_IN_SEP;
            case 10 -> DAYS_IN_OCT;
            case 11 -> DAYS_IN_NOV;
            case 12 -> DAYS_IN_DEC;
            default -> throw new IllegalStateException("Unexpected value: " + month);
        };
    }

    /*
        Checks whether a year is a leap year.
    */
    private static boolean isLeapYear(final int year)
    {
        if(year % LEAP_YEAR_CYCLE != 0)
        {
            return false;
        }
        if(year % LEAP_YEAR_EXCEPTION_CYCLE != 0)
        {
            return true;
        }
        return year % LEAP_YEAR_EXCEPTION_EXCEPTION_CYCLE == 0;
    }

    /*
        Calculate what day of week a date is.

        ------ FORMULA ------

        To get the day of the week, do the following
        seven steps for dates in the 1900s:

        e.g. October 31, 1977:

        step 1: calculate the number of twelves in 77:
            6
        step 2: calculate the remainder from step 1:
              77 - 12*6
            = 77 - 72
            = 5
        step 3: calculate the number of fours in step 2:
            5/4 = 1.25, so
            1
        step 4: add the day of the month to each step above:
              31 + 6 + 5 + 1
            = 43
        step 5: add the month code (for jfmamjjasond: 144025036146):
            for october it is 1:
            43 + 1 = 44
        step 6: add the previous five numbers:
            (44) and mod by 7:
            44%7 = 2 (44/7 = 6 remainder 2)
        step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6;
            our 2 means Oct 31 1977 was monday

        Extra notes:
        a) for January/February dates in leap years, add 6 at the start
        b) for all dates in the 2000s, add 6 at the start
        c) for all dates in the 1800s, add 2 at the start
     */
    private static String calculateDayOfWeek(final int year,
                                             final int month,
                                             final int day)
    {
        final int JAN;
        final int FEB;

        final int yearLastTwoDigits;

        final int valueStep0;
        final int valueStep1;
        final int valueStep2;
        final int valueStep3;
        final int valueStep4;
        final int valueStep5;
        final int valueStep6;

        JAN = 1;
        FEB = 2;

        yearLastTwoDigits = year % ONE_HUNDRED;

        valueStep0 = yearLastTwoDigits +
                     (isLeapYear(year) && (month == JAN || month == FEB)
                      ? SIX
                      : ZERO) +
                     (year - yearLastTwoDigits == TWO_THOUSAND
                      ? SIX
                      : ZERO) +
                     (year - yearLastTwoDigits == EIGHTEEN_HUNDRED
                      ? TWO
                      : ZERO);
        valueStep1 = valueStep0 / TWELVE;
        valueStep2 = yearLastTwoDigits % TWELVE;
        valueStep3 = valueStep2 / FOUR;
        valueStep4 = valueStep1 + valueStep2 + valueStep3 + day;
        valueStep5 = valueStep4 + switch(month)
        {
            case 1 -> JAN_WEEKDAY_CODE;
            case 2 -> FEB_WEEKDAY_CODE;
            case 3 -> MAR_WEEKDAY_CODE;
            case 4 -> APR_WEEKDAY_CODE;
            case 5 -> MAY_WEEKDAY_CODE;
            case 6 -> JUN_WEEKDAY_CODE;
            case 7 -> JUL_WEEKDAY_CODE;
            case 8 -> AUG_WEEKDAY_CODE;
            case 9 -> SEP_WEEKDAY_CODE;
            case 10 -> OCT_WEEKDAY_CODE;
            case 11 -> NOV_WEEKDAY_CODE;
            case 12 -> DEC_WEEKDAY_CODE;
            default -> throw new IllegalStateException("Unexpected value: " + month);
        };
        valueStep6 = valueStep5 % DAYS_IN_WEEK;
        return switch(valueStep6)
        {
            case 0 -> "saturday";
            case 1 -> "sunday";
            case 2 -> "monday";
            case 3 -> "tuesday";
            case 4 -> "wednesday";
            case 5 -> "thursday";
            case 6 -> "friday";
            default -> throw new IllegalStateException("Unexpected value: " + valueStep6);
        };
    }

    /**
     * Gets the year.
     *
     * @return the year as an int
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Gets the month.
     *
     * @return the month as an int
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Gets the day.
     *
     * @return the day as an int
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Gets the week day
     *
     * @return the week day as a String (e.g. "monday")
     */
    public String getDayOfWeek()
    {
        return dayOfWeek;
    }

    /**
     * Gets the date in YYYY-MM-DD format.
     *
     * @return the date as a String
     */
    public String getYyyyMmDd()
    {
        return String.format("%d-%d-%d",
                             this.year,
                             this.month,
                             this.day);
    }

    /**
     * Converts the date represented by this object into a readable string format.
     *
     * <p>The format of the returned string is: <br>
     * {@code [DayOfWeek], [MonthName] [Day], [Year]}</p>
     *
     * <p>For example:</p>
     * <ul>
     *   <li>{@code "Monday, January 1, 2023"}</li>
     *   <li>{@code "Friday, December 25, 2020"}</li>
     * </ul>
     *
     * <p>The month is converted from its numeric representation into its full English name.
     * If the numeric value for the month is not between 1 and 12, an
     * {@link IllegalStateException} is thrown.</p>
     *
     * @return a human-readable string representation of the date.
     * @throws IllegalStateException if the month value is outside the range of 1 to 12.
     */
    @Override
    public String toString()
    {
        final String        monthName;
        final StringBuilder humanReadableDate;

        monthName = switch(this.month)
        {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> throw new IllegalStateException("Unexpected value: " + this.month);
        };

        humanReadableDate = new StringBuilder();
        humanReadableDate.append(this.dayOfWeek);
        humanReadableDate.append(", ");
        humanReadableDate.append(monthName);
        humanReadableDate.append(" ");
        humanReadableDate.append(this.day);
        humanReadableDate.append(", ");
        humanReadableDate.append(this.year);

        return humanReadableDate.toString();
    }

    @Override
    public void display()
    {

    }
}