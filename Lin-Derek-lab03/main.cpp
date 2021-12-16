#include <iostream>
#include "arrayutil.h"

using namespace std;

/**
 * Main method that runs the test cases and check if
 * there is any length errors.
 * @return the exit code
 */
int main () {

    int test1[4] = {1, 2, 3, 4};
    int test2[5] = {-1, 175, 43, 100, 132};
    int test3[4] = {0, 5, 0, 5};

    cout << "getMax Test1: ";
    printArray(test1, sizeof(test1)/sizeof(test1[0]));
    cout << getMax(test1, sizeof(test1)/sizeof(test1[0])) << endl;
    cout << "getMean Test1: ";
    printArray(test1, sizeof(test1)/sizeof(test1[0]));
    cout << getMean(test1, sizeof(test1)/sizeof(test1[0])) << endl;
    cout << "getVar Test1: ";
    printArray(test1, sizeof(test1)/sizeof(test1[0]));
    cout << getVar(test1, sizeof(test1)/sizeof(test1[0])) << endl;
    cout << "sortArray Test1: ";
    printArray(test1, sizeof(test1)/sizeof(test1[0]));
    sortArray(test1, sizeof(test1)/sizeof(test1[0]));
    printArray(test1, sizeof(test1)/sizeof(test1[0]));

    cout << "\ngetMax Test2: ";
    printArray(test2, sizeof(test2)/sizeof(test2[0]));
    cout << getMax(test2, sizeof(test2)/sizeof(test2[0])) << endl;
    cout << "getMean Test2: ";
    printArray(test2, sizeof(test2)/sizeof(test2[0]));
    cout << getMean(test2, sizeof(test2)/sizeof(test2[0])) << endl;
    cout << "getVar Test2: ";
    printArray(test2, sizeof(test2)/sizeof(test2[0]));
    cout << getVar(test2, sizeof(test2)/sizeof(test2[0])) << endl;
    cout << "sortArray Test3: ";
    printArray(test2, sizeof(test2)/sizeof(test2[0]));
    sortArray(test2, sizeof(test2)/sizeof(test2[0]));
    printArray(test2, sizeof(test2)/sizeof(test2[0]));

    cout << "\ngetMax Test3: ";
    printArray(test3, sizeof(test3)/sizeof(test3[0]));
    cout << getMax(test3, sizeof(test3)/sizeof(test3[0])) << endl;
    cout << "getMean Test3: ";
    printArray(test3, sizeof(test3)/sizeof(test3[0]));
    cout << getMean(test3, sizeof(test3)/sizeof(test3[0])) << endl;
    cout << "getVar Test3: ";
    printArray(test3, sizeof(test3)/sizeof(test3[0]));
    cout << getVar(test3, sizeof(test3)/sizeof(test3[0])) << endl;
    cout << "sortArray Test3: ";
    printArray(test3, sizeof(test3)/sizeof(test3[0]));
    sortArray(test3, sizeof(test3)/sizeof(test3[0]));
    printArray(test3, sizeof(test3)/sizeof(test3[0]));

    return EXIT_SUCCESS;
}
