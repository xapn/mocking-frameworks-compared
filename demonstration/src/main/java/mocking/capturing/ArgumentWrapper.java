/**
 * 
 */
package mocking.capturing;

import java.time.LocalDate;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * This class is aimed to be used as a method argument passed to a dependency
 * object that also uses a wrapped object to be checked.
 * 
 * @author Xavier Pigeon
 */
public class ArgumentWrapper<T> {

    private LocalDate date;

    private T argument;

    /**
     * Default constructor.
     */
    public ArgumentWrapper() {}

    /**
     * Constructor.
     * 
     * @param date
     *            the date
     * @param argument
     *            the wrapped argument
     */
    public ArgumentWrapper(LocalDate date, T argument) {
        this.date = date;
        this.argument = argument;
    }

    /**
     * Getter for the field date.
     * 
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter for the field date.
     * 
     * @param date
     *            the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for the field argument.
     * 
     * @return the argument
     */
    public T getArgument() {
        return argument;
    }

    /**
     * Setter for the field argument.
     * 
     * @param argument
     *            the argument to set
     */
    public void setArgument(T argument) {
        this.argument = argument;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date, argument);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ArgumentWrapper) {
            ArgumentWrapper<T> that = (ArgumentWrapper) object;
            return Objects.equal(this.date, that.date) && Objects.equal(this.argument, that.argument);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("date", date).add("argument", argument).toString();
    }
}