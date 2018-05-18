#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

int main() {
	cout << "Hello World" << endl;
	const double PI = 3.14156926355;
	char myGrade = 'A';
	bool isHappy = true;
	cout << "Printing character size (# of bytes)" <<sizeof(myGrade)<<endl;
	cout << "4 / 5: " <<PI<<endl;
	int greetOption = 3;
	switch(greetOption) {
		case(1):
			cout<<"Hello"<<endl;
			break;
		case(2):
			cout<<"Bonjour"<<endl;
			break;
		default:
			cout<<"Hi"<<endl;
	}
	int largestNum = (6 > 3) ? 6 : 3;
	cout<<largestNum<<endl;
	int favNum[5];
	int badNums[5] = {1,2,3,4,5};
	int rand_num = rand()%100 + 1;//Genrates random number between 1 and 100;
	string numGuessed;
	int convertNumGuessed = 0;
	do {
		cout<<"Guess between 1 and 10: ";
		getline(cin, numGuessed);
		convertNumGuessed = stoi(numGuessed);
		cout<<convertNumGuessed<<endl;

	}while(convertNumGuessed != 4);

	string dogString = "dog";
	string catString = "cat";
	cout<<dogString.compare(catString)<<endl;
	cout<<catString.compare(dogString)<<endl;
	cout<<catString.compare(catString)<<endl;
	string wholename;
	getline(cin,wholename);
	string firstName = wholename.assign(wholename,0,6);
	cout<<firstName<<endl;
	int lastNameIndex = wholename.find("handra",0);//Enter the findex you want to start searching from, i.e.0th
	cout<<lastNameIndex<<endl;						//Similarly you can use insert replace and erase on string variables by providing indices as arguments
	return 0;
}