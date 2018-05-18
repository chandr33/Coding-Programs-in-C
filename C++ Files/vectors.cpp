#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

int main() {
	vector<int> v;//Initializing vector of type int with size 10
	int arr[5] = {1,2,3,4,5};
	/*v.insert(v.begin(),arr,arr+3);
	vector<int> :: iterator i;
	i = v.begin() + 2;
	cout<<*i<<endl;*/
	vector<int> :: iterator i;

	for (int j = 0; j < 5; ++j)
	{
		v.push_back(j);
	}
	v.insert(v.begin()+5,2);
	v.push_back(0);
	cout<<v.at(6)<<endl;
	//TODO - Learn about iterators in vectors
	//Vectors basically act as dynamic arrays(Arraylist in Java), and give you more functionalities than normal arrays.
	return 0;	
}