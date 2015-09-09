/**
 * 
 */
package mocking.matching;

import java.math.BigDecimal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * This class is aimed to be used as a method argument in the mock expectations.
 * 
 * @author Xavier Pigeon
 */
public class Argument {

    private String description;

    private BigDecimal ratio;

    private Integer quantity;

    /**
     * Default constructor.
     */
    public Argument() {}

    /**
     * Constructor.
     * 
     * @param description
     *            a textual description
     * @param ratio
     *            a ratio
     * @param quantity
     *            a quantity
     */
    public Argument(String description, BigDecimal ratio, Integer quantity) {
        this.description = description;
        this.ratio = ratio;
        this.quantity = quantity;
    }

    /**
     * Getter for the field description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the field description.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the field ratio.
     * 
     * @return the ratio
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * Setter for the field ratio.
     * 
     * @param ratio
     *            the ratio to set
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * Getter for the field quantity.
     * 
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Setter for the field quantity.
     * 
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description, ratio, quantity);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Argument) {
            Argument that = (Argument) object;
            return Objects.equal(this.description, that.description) && Objects.equal(this.ratio, that.ratio)
                    && Objects.equal(this.quantity, that.quantity);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("description", description).add("ratio", ratio)
                .add("quantity", quantity).toString();
    }
}