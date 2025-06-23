// CS210_Final_Project_Hutto.cpp : This file contains the 'main' function. Program execution begins and ends there.
// Talan Hutt
//6/18/2025

#include <iostream>
#include "ItemTracker.h"
using namespace std;

int main()
{
	ItemTracker tracker;
	tracker.loadFromInputFile("produce.txt");
	tracker.writeBackupFile("frequency.dat");
    
   int choice = 0;
   do {
       // 1) Display the menu
       std::cout
           << "\n=== Corner Grocer Menu ===\n"
           << "1) Lookup one item’s frequency\n"
           << "2) Show all item frequencies\n"
           << "3) Show histogram\n"
           << "4) Exit\n"
           << "Enter choice (1 through 4): ";

       // 2) Read & validate
       cin >> choice;
       if (cin.fail()) {
           cin.clear();                // clear the error flag
           cin.ignore(1000, '\n');     // discard bad input
           cout << "Please enter a number between 1 and 4.\n";
           continue;                        // back to the top of the loop
       }

       //act on the choice 
       switch (choice) {
       case 1: {
            //lookup one item
           string item;
           cout << "Enter item name: ";
           cin >> item;
           // normalize user input to lowercase
           for (char& c : item) {
               if (c >= 'A' && c <= 'Z') {
                   c = c - 'A' + 'a';
               }
           }

           int freq = tracker.getFrequency(item);
           cout << endl << item << " appears " << freq << " times" << endl;
           break; // exit this case
       }
       case 2:
           //show all
           tracker.printAllFrequencies();
           break;
       case 3: 
           //histogram
           tracker.printHistogram('*');
           break;
       case 4:
           //exit message
           cout << "Goodbye!\n";
           break;
       default:
           //out-of-range- number
           cout << "Invalid choice: please pick 1-4. " << endl;
       }//end switch(choice)
   } while (choice != 4);
   return 0;
}// end main()