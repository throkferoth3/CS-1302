package cs1302.p2;

import cs1302.adt.FancyStringList;
import cs1302.adt.StringList;

/**
 * An abstract class that implements StringList. It acts as a general parent
 * to 2 other classes. It contains the basic parts of all lists.
 */
public abstract class BaseStringList implements FancyStringList {

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

    /**
     * This is the add method that inserts a StringList to the
     * desired index.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, StringList items) {
        if (items == null) {
            throw new NullPointerException("add: items is " + null);
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("add: index is out of range (" + index + ")");
        } else if (index > size()) {
            throw new IndexOutOfBoundsException("add: index is out of range (" + index + ")");
        }

        if (!items.isEmpty()) {
            StringList itemsCopy = items.slice(0, items.size());
            for (int i = 0; i < itemsCopy.size(); i++) {
                add(index + i, itemsCopy.get(i));
            }
        }
        return true;
    }

    /**
     * This is the append method that adds a StringList to the end of the list using
     * the add method.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean append(StringList items) {
        if (items == null) {
            throw new NullPointerException("append: items is " + null);
        }

        StringList itemsCopy = items.slice(0, items.size());
        for (int i = 0; i < itemsCopy.size(); i++) {
            if (!itemsCopy.get(i).equals("")) {
                add(size, itemsCopy.get(i));
            }
        }
        return true;
    }

    /**
     * This is the contains method that checks to see if the list
     * contains the desired string at or after the supplied index.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean contains(int start, String target) {
        if (start < 0 || start >= size) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (i >= start) {
                    if (get(i).equals(target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This is the indexOf method that finds the index of the
     * supplied string and returns that index or returns -1 if
     * it is not found.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public int indexOf(int start, String target) {
        for (int i = 0; i < size; i++) {
            if (i >= start && get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is the preppend method that adds a StringList to the start of the list using
     * the add method.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(StringList items) {
        if (items == null) {
            throw new NullPointerException("prepend: items is " + null);
        }

        StringList itemsCopy = items.slice(0, items.size());
        for (int i = 0; i < itemsCopy.size(); i++) {
            if (!itemsCopy.get(i).equals("")) {
                add(0 + i, itemsCopy.get(i));
            }
        }
        return true;
    }
}
