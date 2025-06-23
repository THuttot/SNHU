#pragma once
#include <map>
#include <string>
using namespace std;
class ItemTracker {
private:

	map<string, int> freqMap; //holds counts for each item

public:

	void loadFromInputFile(const string& filename); //load txt file
	void writeBackupFile(const string& filename) const; //write to to backup file
	int getFrequency(const string& filename) const; //return the frequency of desired item
	void printAllFrequencies() const; // return each unique item and their frequency in the txt doc
	void printHistogram(char symbol) const; // return each item and their frequency in histogram format

};