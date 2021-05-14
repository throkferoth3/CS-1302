package cs1302.hw06;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 * This class contains methods related to {@code cs1302-hw06}.
 */
public class LambdaFun {

    /** Standard Input scanner. */
    private static Scanner input = new Scanner(System.in);

    /**
     * Main entry-point into the application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {

        String[] myStrings = new String[] {
            "CSCI",        "1302",    "is", "an", "awesome", "course!",
            "Lambda", "expressions", "scare", "me",     "but",       "I",
            "will",   "persevere"
        };

        Email[] inbox = new Email[] {
            new Email("bjb211@uga.edu", "yellowjacket@gatech.edu",
                      LocalDate.of(2019, 2, 4), "Go GA Tech!"),
            new Email("bjb211@uga.edu", "mom@aol.com",
                      LocalDate.of(2019, 2, 5), "Have a good day!"),
            new Email("bjb211@uga.edu", "steve@anyotherschool.edu",
                      LocalDate.of(2019, 2, 6), "I wish I would've chosen UGA"),
            new Email("bjb211@uga.edu", "student1@uga.edu",
                      LocalDate.of(2019, 2, 7), "Thanks for teaching us!"),
            new Email("bjb211@uga.edu", "yellowjacket@gatech.edu",
                      LocalDate.of(2019, 2, 8), "Go GA Tech!")
        };

        Predicate<String> p = e -> e.contains("a");
        System.out.println("Test 1(OriginalTest): outputs all strings that contain the letter a");
        LambdaFun.<String>printlnMatches(myStrings, p);

        Predicate<String> p2 = e -> e.contains("b");
        System.out.println("Test 2: outputs all strings that contain the letter b");
        LambdaFun.<String>printlnMatches(myStrings, p2);

        Predicate<String> p3 = e -> e.contains("c");
        System.out.println("Test 3: outputs all strings that contain the letter c");
        LambdaFun.<String>printlnMatches(myStrings, p3);

        Predicate<String> p4 = e -> (e.length() == 4);
        System.out.println("Test 3: outputs all strings that have a length of 4");
        LambdaFun.<String>printlnMatches(myStrings, p4);

        Predicate<Email> k = e -> (e.getSender() != "yellowjacket@gatech.edu");

        Function<Email, String> k1  = (e) -> ("[" + e.getContents() + "]");
        System.out.println("Test 4: outputs all emails not from @gatech.edu in brackets");
        LambdaFun.<Email>printlnMappedMatches(inbox, k, k1);

        Function<Email, String> k2  = (e) -> e.getSender();
        System.out.println("Test 5: outputs all emails senders who are not from @gatech.edu");
        LambdaFun.<Email>printlnMappedMatches(inbox, k, k2);

        Predicate<Email> k10 = e -> (e.getSender() == "yellowjacket@gatech.edu");

        Function<Email, String> k3  = (e) -> (e.getContents() + " " + e.getContents());
        System.out.println("Test 6: outputs all emails from @gatech.edu twice");
        LambdaFun.<Email>printlnMappedMatches(inbox, k10, k3);

    } // main

    /**
     * Prints the elements of the array that pass the test specified by the given predicate.
     * More formally, this method prints all elements {@code e} in the array referred to by
     * {@code t} such that {@code p.test(e)}. Each element will be printed on its own line.
     *
     * @param <T> the type of the array elements
     * @param t the specified array
     * @param p the specified predicate
     * @throws NullPointerException if the specified predicate is {@code null}
     */
    private static <T> void printlnMatches(T[] t, Predicate<T> p) {
        T e;
        for (int i = 0; i < t.length; i++) {
            e = t[i];
            if (p.test(e)) {
                System.out.println(e);
            }
        }
    } // printlnMatches

    /**
     * Prints the elements of the array that pass the test specified by the given predicate
     * using a string mapper. More formally, this method prints the string mapped elements
     * {@code f.apply(e)} in the array referred to by {@code t} for each {@code e} such that
     * {@code p.test(e)}. Each string mapped element will be printed on its own line.
     *
     * @param <T> the type of the array elements
     * @param t the specified array
     * @param p the specified predicate
     * @param f the specified string mapper
     * @throws NullPointerException if the specified predicate or string mapper is {@code null}
     */
    private static <T> void printlnMappedMatches(T[] t, Predicate<T> p, Function<T, String> f) {
        T e;
        for (int i = 0; i < t.length; i++) {
            e = t[i];
            if (p.test(e)) {
                System.out.println(f.apply(e));
            }
        }
    } // printlnMappedMatches

} // LambdaFun
