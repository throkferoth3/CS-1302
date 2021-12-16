#include <iostream>
#include <cstdlib>
#include <sstream>

using namespace std;

/**
 * This function gets a start time and duration from
 * the user, and then adds them together to find
 * an end time.
 *
 * @return int 0 if the program exits successfully.
 */
int main() {
    /// getting start time
    string startTime;
    cout << "Enter in a start time: ";
    getline(cin, startTime);

    /// getting duration
    string duration;
    cout << "  Enter in a duration: ";
    getline(cin, duration);

    /// adding together the times
    stringstream ss1(startTime);
    stringstream ss2(duration);
    int iTime;
    int iDuration;
    ss1 >> iTime;
    ss2 >> iDuration;

    /// getting the hours and minutes of the inputs
    int tHours = iTime / 100;
    int tMinutes = iTime - (tHours * 100);
    int dHours = iDuration / 100;
    int dMinutes = iDuration - (dHours * 100);

    /// adding the minutes
    if (dMinutes > 59) {
        dMinutes -= 60;
        dHours += 1;
    }
    tMinutes += dMinutes;
    while (tMinutes > 59) {
        tMinutes -= 60;
        tHours += 1;
    }

    /// adding the hours
    tHours += dHours;
    while (tHours >= 24) {
        tHours -= 24;
    }

    if (tMinutes >= 10) {
        if (tHours >= 10) {
            cout << "\t  End time is: " << tHours << tMinutes << endl;
        } else {
            cout << "\t  End time is: " << "0" << tHours << tMinutes << endl;
        }
    } else {
        if (tHours >= 10) {
            cout << "\t  End time is: " << tHours << "0" << tMinutes << endl;
        } else {
            cout << "\t  End time is: " << "0" << tHours << "0" << tMinutes << endl;
        }
    }

    return EXIT_SUCCESS;

}
