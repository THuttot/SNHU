#include "ItemTracker.h"
#include <iomanip>    // for setw, left, right
#include <fstream>    // for std::ifstream
#include <iostream>   // for std::cerr (error messages: https://cplusplus.com/reference/iostream/cerr/) 
#include <algorithm>  // to normalize case
#include <vector>

using namespace std;


// === Definition of loadFromInputFile ===
void ItemTracker::loadFromInputFile(const std::string& filename) {

	// 1. Try to open
	std::ifstream inFile(filename);
	if (!inFile) {
		std::cerr << "Failed to open " << filename << endl;
		return;
	}//end if()

	// 2. Read tokens and count, for each each word in file, skip whitespace and go until EOF

	std::string item;
	while (inFile >> item) {
	//normalize case
		for (char& fWord : item) {
			if (fWord >= 'A' && fWord <= 'Z') {
				fWord = fWord - 'A' + 'a';
			}
		}//end for()

		freqMap[item]++; //add to the frequency map for that word
	
	} // end while()
	
	// 3. Clean up
	inFile.close();

}// end loadFromInputFile

// === Definition of printAllFrequencies ===
void ItemTracker::printAllFrequencies() const {
	//nice formatting for the item and quantity
	cout << "Item        Quantity\n------------ --------\n";

	//determine bounds for name and count width
	const int NAME_WIDTH = 15; //how much room to give the item name
	const int COUNT_WIDTH = 1; // how much room to give the count

	for (const auto& pair : freqMap) {
		// pair.first  = the item name (e.g. "spinach")
		// pair.second = how many times it appeared (e.g. 4)
		cout
			<< left << setw(NAME_WIDTH) << pair.first // item name, left-aligned in 20 chars
			<< right << setw(COUNT_WIDTH) << pair.second  // count, right-aligned in 10 chars
			<< endl;
	}
}// end printAllFrequencies

// === Definition of printHistogram ===
void ItemTracker::printHistogram(char symbol) const {
	
	//nice formatting for the item and quantity
	cout << "Item        Quantity\n------------ --------\n";

	
	for (const auto& pair : freqMap) {
		const std::string& item = pair.first;
		int count =               pair.second;

		// print the item name
		cout << item;

		// pad with spaces so that he histogram always start at column 12
		int spaces = 12 - static_cast<int>(item.length());
		for (int i = 0; i < spaces; ++i) {
			cout << ' ';
		}

		//print the bar for symbols
		for (int i = 0; i < count; ++i) {
			cout << symbol;

		}
		//new line
		cout << endl;
	}
}//end printHistogram

// === Definition of writeBackupFile ===
void ItemTracker::writeBackupFile(const string& filename) const{
	//Open the output file
	ofstream out(filename);
	if (!out) {
		cerr << "Error: cannot open backup file "
			 << filename << " for writing." << endl;
		return; //leave if we cannot write
	
	}// end if


	//iterate over every item - count pair
	for (const auto &pair : freqMap) 
	{
		out << pair.first << " " << pair.second << endl;
	}
	//close and save file
	out.close();

}//end writeBackupFile

// === Definition of getFrequency ===
int ItemTracker::getFrequency(const string& item) const {
	auto it = freqMap.find(item);
	if (it != freqMap.end()) {
		return it->second;
	}
	return 0;
}