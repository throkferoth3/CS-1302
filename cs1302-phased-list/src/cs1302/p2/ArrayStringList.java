package cs1302.p2;

import cs1302.adt.StringList;

/**
 * Child class of {@link BaseStringList} that makes a list with arrays.
 */
public class ArrayStringList extends BaseStringList {

    /** This is the main array. */
    private String[] items;

    /**
     * This is a constructor that initializes the array to size 6 and
     * sets size to 0.
     */
    public ArrayStringList() {
        super();
        size = 0;
        items = new String[6];
    }

    /**
     * This adds an item to the array index and shifts any existing
     * values on that index or to the right of it, to the right.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) {
        arrayCheck();
        if (index < 0 || index > size) { //checking for exceptions
            throw new IndexOutOfBoundsException("add: index is out of range (" + index + ")" );
        } else if (item == null) {
            throw new NullPointerException("add: item is " + null);
        } else if (item.equals("")) {
            throw new IllegalArgumentException("add: item is empty");
        } else { //if parameters are valid
            if (items[index] == null) { //checks if index is empty
                items[index] = item;
                size += 1;
            } else {
                String[] tempArray1 = new String[items.length];
                String[] tempArray2 = new String[items.length];
                for (int i = 0; i < index; i++) { //splitting items into 2 arrays
                    tempArray1[i] = items[i];
                }
                for (int i = index; i < items.length; i++) {
                    tempArray2[i] = items[i];
                }

                items = new String[tempArray1.length]; //inserting item
                for (int i = 0; i < index; i++) {
                    items[i] = tempArray1[i];
                }
                items[index] = item;
                for (int i = (index + 1); i < items.length; i++) {
                    items[i] = tempArray2[i - 1];
                }
                size += 1;
            }
        }
        return true;
    }

    /**
     * This gets rid of all values in the list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        items = new String[items.length];
        size = 0;
    }

    /**
     * This returns the value of the item at the index.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("get: index is out of range (" + index + ")" );
        }
        return items[index];
    }

    /**
     * This removes the value of the item at the specified index
     * and shifts all values to the right of it, to the left.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {
        arrayCheck();
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("remove: index is out of range (" + index + ")" );
        }

        String removedString = items[index];
        String[] tempArray = new String[items.length];
        for (int i = index; i < size + 1; i++) {
            tempArray[i] = items[i + 1];
            items[i] = null;
        }
        for (int i = index; i < items.length; i++) {
            items[i] = tempArray[i];
        }
        size -= 1;
        return removedString;
    }

    /**
     * This returns the list of the items from the start to the stop.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public StringList slice(int start, int stop) {
        if (start > stop) {
            throw new IndexOutOfBoundsException("slice: start " +
            "(" + start + ") > stop (" + stop + ")");
        } else if (start < 0) {
            throw new IndexOutOfBoundsException("slice: index is out of range (" + start + ")");
        } else if (stop > size()) {
            throw new IndexOutOfBoundsException("slice: index is out of range (" + stop + ")");
        }

        StringList returnValue = new ArrayStringList();
        for (int i = start; i < stop; i++) {
            returnValue.append(items[i]);
        }
        return returnValue;
    }


    /**
     * This method checks to see if the size of the list is approaching the
     * the array length. If it is, it creates another array with the the same list
     * but 1.5x the size.
     */
    public void arrayCheck() {
        if (items.length - size <= 1) {

            String[] tempArray = new String[(int) Math.round(items.length * 1.5)];

            for (int i = 0; i < items.length; i++) {
                tempArray[i] = items[i];
            }

            items = new String[tempArray.length];

            for (int i = 0; i < items.length; i++) {
                items[i] = tempArray[i];
            }

        }
    }

}
