package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;
import cs1302.oracle.OracleStringList;
import cs1302.oracle.FancyOracleStringList;

public class Driver {
    public static void main(String[] args) {
        FancyStringList ls;

        // To test what the output is for your code, you can use ArrayStringList or LinkedStringList:
//	 ls = new FancyOracleStringList(); // uncomment to run the test cases using the oracle.
//	 ls = new ArrayStringList(); // uncomment to run the test cases using your array implementation
	 ls  = new LinkedStringList(); // uncomment to run the test cases using your linked implementation.

               FancyStringList list = new LinkedStringList();
        ls.add(0, "a");
        ls.add(1, "b");
        ls.add(2, "c");
        System.out.println(ls);

        ls.append("5");

        list.add(0, "1");
        list.add(1, "2");
        list.add(2, "3");
        list.prepend(ls);
        System.out.println(list);
        System.out.println(list.reverse());
        list.clear();

    } // main

} // Driver
