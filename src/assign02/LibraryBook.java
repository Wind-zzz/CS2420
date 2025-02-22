package assign02;

import java.lang.Object;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class LibraryBook extends Book {

    private int patron;
    private GregorianCalendar date = new GregorianCalendar();

    public LibraryBook(long isbn, String authorSurname, String authorOtherName, String title) {
        super(isbn, authorSurname, authorOtherName, title);
        this.patron = -1;
        this.date = new GregorianCalendar();
    }

    public int getPatron() {
        return this.patron;
    }

    public GregorianCalendar getDueDate() {
        return this.date;
    }

    public void checkIn() {
        this.patron = -1;
        this.date = null;
    }

    public void checkOut(int newPatron, GregorianCalendar newDate) {
        this.patron = newPatron;
        this.date = newDate;
    }
}
