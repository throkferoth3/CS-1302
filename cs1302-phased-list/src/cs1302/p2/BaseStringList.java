package cs1302.p2;

import cs1302.adt.StringList;

/**
 * An abstract class that implements StringList. It acts as a general parent
 * to 2 other classes. It contains the basic parts of all lists.
 */
public abstract class BaseStringList implements StringList {

    /** The size of the list. */
    protected int size;

    /**
     * Constructs an object of type BaseStringList.
     */
    public BaseStringList() {
    }

   /**
     * This is the append method that adds an item to the end of the list using
     * the add method.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean append(String item) {
        if (item == null) {
            throw new NullPointerException("append: item is " + null);
        } else if (item.equals("")) {
            throw new IllegalArgumentException("append: item is empty");
        }

        if (size == 0) {
            add(0, item);
        } else {
            add(size, item);
        }
        return true;
    }

    /**
     * This is the isEmpty method that checks the size of the list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method creates a new string using the items in the list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String makeString(String start, String sep, String end) {
        String returnString = start;

        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                returnString += (get(i) + sep);
            }
            returnString += get(size - 1);
        }
        returnString += end;
        return returnString;
    }

    /**
     * This is the preppend method that adds an item to the start of the list using
     * the add method.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(String item) {
        if (item == null) {
            throw new NullPointerException("prepend: item is " + null);
        } else if (item.equals("")) {
            throw new IllegalArgumentException("prepend: item is empty");
        }
        add(0, item);
        return true;
    }

    /**
     * This gives the size of the list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This creates a string using makeString to show all items in the list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return makeString("[", ", ", "]");
    }

}
