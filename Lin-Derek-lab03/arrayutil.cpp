#include <iostream>
#include "arrayutil.h"

using namespace std;

/**
 * Finds the largest value in an array.
 * @param arr the array used for the function.
 * @param length the length of the array.
 * @return the max value.
 */
int getMax (int arr[], int length) {
    int largest = arr[0];

    for (int i = 0; i < length; i++) {
        if (largest < arr[i]) {
            largest = arr[i];
        }
    }

    return largest;
}

/**
 * Calculates the mean of the values in the array.
 * @return the mean of the values in the array.
 */
double getMean (int arr[], int length) {
    double sum = 0;
    for (int i = 0; i < length; i++) {
        sum += arr[i];
    }

    return (sum / length);
}

/**
 * Finds the population variance of the array.
 * @param arr the array used for the function.
 * @param length the length of the array.
 * @return the population variance.
 */
double getVar (int arr[], int length) {
    if (length < 1) {
        std::exit(EXIT_FAILURE);
    }
    double squaredTotal = 0;
    for (int i = 0; i < length; i++) {
        squaredTotal += (arr[i] * arr[i]);
    }
    double mean = getMean(arr, length);
    return (((1.0/length) * squaredTotal) - (mean * mean));
}

/**
 * Sorts the array from smallest to largest.
 * @param arr the array used for the function.
 * @param length the length of the array.
 */
void sortArray (int arr[], int length) {
    int largest = 0;
    while (length > 1) { //Sorts by repeatedly finding the largest value
        largest = getMax(arr, length);
        for (int i = 0; i < length; i++) {
            if (largest == arr[i]) {
                arr[i] = arr[length - 1];
                arr[length - 1] = largest;
            }
        }
        length--; //cuts off the last value
    }
}

/**
 * Prints the values of the array to the console.
 * @param arr the array used for the function.
 * @param length the length of the array.
 */
void printArray(int arr[], int length) {
    cout << "{";
    for (int i = 0; i < length; i++) {
        if (i == length - 1) {
            cout << arr[i] << "}" << endl;
        } else {
            cout << arr[i] << ", ";
        }
    }
}
