package DataTypes;

import java.util.Objects;

// the Symbol datatype is unique to this language (afaik)
// it allows for algebraic mathematical operations
// as such, 2a + a will return 3a
// symbols do not have a value, i will implement this at a later stage for evaluation purposes
public class Symbol {
    private final String name;
    private Number coefficient; // number superclass handles this elegantly
    private final boolean isFloat; // use flag to handle data types

    // we have 2 constructors here, one for each of the data types, int and float currently
    // will consider adding vectors and matrices to the data structures
    public Symbol(Integer coefficient, String name) {
        validateName(name); // moved outside constructor
        this.name = name;
        this.coefficient = coefficient;
        this.isFloat = false;
    }

    public Symbol(Float coefficient, String name) {
        validateName(name); // moved outside constructor
        this.name = name;
        this.coefficient = coefficient;
        this.isFloat = true;
    }

    // this solution handles everything very nicely
    public void setCoefficient(Number coefficient) {
        if (isFloat && !(coefficient instanceof Float)) {
            throw new IllegalArgumentException("Expected a float coefficient");
        } else if (!isFloat && !(coefficient instanceof Integer)) {
            throw new IllegalArgumentException("Expected integer coefficient");
        }
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public Number getCoefficient() {
        return coefficient;
    }

    // this method should handle both addition and subtraction
    public Symbol add(Symbol other) { // you can add Symbol objects algebraically
        if (!(Objects.equals(this.name, other.name))) {
            // i will implement this at a later stage,
            throw new IllegalArgumentException("Cannot add symbols with different names"); // this is not good, will change
        } // i don't know if this could work with a switch, i could try if it's needed
        if (this.isFloat && !other.isFloat) { // can handle mismatches more elegantly than with errors
            return new Symbol(this.coefficient.floatValue() + other.coefficient.floatValue(), this.name);
        } else if (!this.isFloat && other.isFloat) {
            return new Symbol(this.coefficient.intValue() + other.coefficient.intValue(), this.name);
        } else if (this.isFloat && other.isFloat) {
            return new Symbol(this.coefficient.floatValue() + other.coefficient.floatValue(), this.name);
        } else if (!this.isFloat && !other.isFloat) {
            return new Symbol(this.coefficient.intValue() + other.coefficient.intValue(), this.name);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.coefficient.toString() + this.name;
    }

    // a Symbol must be defined by a single, lowercase letter (really can't imagine why you would need more than 26 symbols)
    // we do this because a Vector and a Matrix (both Matrix) are to be defined by capital letters
    private void validateName(String name) { // doesn't need to return anything
        if (!name.matches("[a-z]")) { // check regex for alphabet
            throw new IllegalArgumentException("Symbol name must be a single lowercase alphabetical character.");
        }
    }
}
