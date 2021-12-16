#include <iostream>
#include <cstdlib>
#include <sstream>

using namespace std;

/**
 * This function checks if a number is prime.
 * It also is a modification of the c++ primality
 * code from https://en.m.wikipedia.org/wiki/Primality_test.
 *
 * @return Returns true if it is prime and false if not.
 * @param k The number in question.
 */
bool isPrime(int k) {
    /// Checking to see if k is divisible by 3 or 2
    if (k % 2 == 0 || k % 3 == 0) {
        return false;
    }

    /// Checking if divisible by other numbers up to sqrt(k)
    for (int i = 5; i * i <= k; i += 6) {
        if (k % i == 0 || k % (i + 2) == 0) {
            return false;
        }
    }
    return true;
}

/**
 * This function finds the sum of the first n prime numbers
 * that the user inputs.
 *
 * @return 0 if program exits successfully.
 */
int main() {
    string str;
    int inputNumber;
    int n = 1;
    int primesFound = 2;
    unsigned long long int primeSum = 5;
    int tempPrime;

    /// getting input
    cout << "Enter integer: ";
    getline(cin, str);

    stringstream ss(str);
    ss >> inputNumber;

    /// finding prime sum
    if (inputNumber == 1) {
        primeSum = 2;
    } else if (inputNumber == 2) {
        primeSum = 5;
    } else {
        while (primesFound < inputNumber){
            tempPrime = ((6 * n) - 1);
            if (isPrime(tempPrime)) {
                primeSum += tempPrime;
                primesFound += 1;
            }
            if (primesFound < inputNumber) {
                tempPrime = ((6 * n) + 1);
                if (isPrime(tempPrime)) {
                    primeSum += tempPrime;
                    primesFound += 1;
                }
            }
            n++;
        }
    }

    cout << "    Prime sum: " << primeSum << endl;
    return EXIT_SUCCESS;
}
