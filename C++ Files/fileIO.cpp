#include <iostream>
#include <fstream>

using namespace std;

int main() {
	string quote = "You can do it. Never give up!";
	ofstream write("Follow_Quote.txt");//Create an output file stream to create this file if it doesn't exist
	if (!write)//If the stream did not open
	{
		cout<<"Error opening file"<<endl;
		return -1;
	}
	else {
		write << quote << endl;
		write.close();
	}
	ofstream write2("Follow_Quote.txt",iostream::app);
	//Open a stream to append to the file
	//iostream::binary - Treats file as binary
	//iostream::in - Open a file to read input
	//iostream::out - Open a file to write output
	if (!write2)
	{
		cout<<"Error opening file"<<endl;
		return -1;
	}
	else {
		write2 << "\n - Rochak Chandra" <<endl;
		write2.close();
	}

	char letter;
	ifstream reader("Follow_Quote.txt");
	if (!reader)
	{
		cout<<"Error opening file"<<endl;
		return -1;
	}
	else {
		for (int i = 0; i != reader.eof(); i++)
		{
			reader.get(letter);
			cout<<letter;
		}
		cout<<endl;
		reader.close();
	}	

	int number = 0;
	try {
		if (number != 0)
		{
			cout<<2/number<<endl;
		}
		throw(number);	
	}
	catch (int num){
		cout<<"Number is not valid"<<endl;
	}

	return 0;
}