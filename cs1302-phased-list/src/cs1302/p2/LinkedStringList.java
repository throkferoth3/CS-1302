package cs1302.p2;

import cs1302.adt.Node;
import cs1302.adt.StringList;

/**
 * Child class of {@link BaseStringList} that creates a list using nodes to form
 * a linked list.
 */
public class LinkedStringList extends BaseStringList {

    /** The reference to the 1st node. */
    private Node head;

    /** Constructor that intializes the node and size values. */
    public LinkedStringList() {
        size = 0;
        head = new Node(null);
    }


    /**
     * Adds an item to the list at the specified index and shifts
     * everything at that index to the right in the form of a
     * linked list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) {
        if (index < 0 || index > size) { //checking for exceptions
            throw new IndexOutOfBoundsException("add: index is out of range (" + index + ")" );
        } else if (item == null) {
            throw new NullPointerException("add: item is " + null);
        } else if (item.equals("")) {
            throw new IllegalArgumentException("add: item is empty");
        } //if parameters are valid
        Node k = head;
        Node p = head;

        if (index == 0) {
            if (size == 0) {
                head.setItem(item);
            } else {
                k = new Node(item);
                k.setNext(head);
                head = k;
            }
        } else if (index == size) {
            while (k.hasNext()) {
                k = k.getNext();
            }
            k.setNext(new Node(item));
        } else {
            for (int i = 0; i < (index - 1); i++) {
                k = k.getNext();
            }
            for (int i = 0; i < index; i++) {
                p = p.getNext();
            }
            Node z = new Node(item);
            z.setNext(p);
            k.setNext(z);
        }
        size += 1;
        return true;
    }

    /**
     * This method returns the size of the list to 0 and clears everything in it.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        head = new Node(null);
        size = 0;
    }

    /**
     * Gets the item at the index of the linked list.
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

        Node k = head;
        for (int i = 0; i < index; i++) {
            k = k.getNext();
        }
        return (k.getItem());
    }

    /**
     * This method removes an item at the specified index of the linked list.
     * Inherits param, return, and/or exception comments.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("remove: index is out of range (" + index + ")" );
        }

        Node k = head;
        Node p = head;
        String returnItem;
        if (index == 0) {
            if (size == 1) {
                returnItem = head.getItem();
                head = new Node(null);
            } else {
                returnItem = head.getItem();
                head.setItem(null);
                head = head.getNext();
            }
        } else if (index == (size - 1)) {
            for (int i = 0; i < index; i++) {
                k = k.getNext();
            }
            returnItem = k.getItem();
            k.setItem(null);
        } else {
            for (int i = 0; i < index - 1; i++) {
                k = k.getNext();
                p = p.getNext();
            }
            p = p.getNext();
            returnItem = p.getItem();
            p.setItem(null);
            p = p.getNext();
            k.setNext(p);
        }
        size = size - 1;
        return returnItem;
    }

    /**
     * Returns a list of items from the start to the stop.
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

        StringList returnValue = new LinkedStringList();
        Node k = head;
        for (int i = 0; i < start; i++) {
            k = k.getNext();
        }

        for (int i = start; i < stop; i++) {
            returnValue.append(k.getItem());
            k = k.getNext();
        }
        return returnValue;
    }

}
