#include <iostream>
#include <vector>

using namespace std;
//All members of the class are implicitly declared private

/*
This class will have two functions ,i.e. one to populate the input vector and the other to implement the Kadane's Algorithm
*/
class Kadane_Algorithm {
	int size; 
	public:
		Kadane_Algorithm();
		vector<int> populate_vector();//This populates and returns the vector
		int maxSum(std::vector<int> v);//This returns the max contiguous sum
};

Kadane_Algorithm::Kadane_Algorithm() {
	int s;
	cout<<"Enter the size of the array"<<endl;
	cin>>s;
	this -> size = s;
	cout<<"The size of the array is "<<this->size<<endl;
}
vector<int> Kadane_Algorithm::populate_vector() {
	vector<int> result;
	int number;
	cout<<"Now start entering the numbers"<<endl;
	for (int i = 0; i < this->size; i++)
	{
		cin>>number;
		result.push_back(number);
	}
	return result;
}

int Kadane_Algorithm::maxSum(std::vector<int> input_vector) {
	int max = input_vector.at(0);
	int max_ending_here = 0;
	std::vector<int>::iterator i,j;
	int sum = 0;
	for (i = input_vector.begin(); i != input_vector.end(); i++)
	{
		max_ending_here += *i;
		if (max_ending_here < 0)
		{
			max_ending_here = 0;
		}
		else if (max < max_ending_here)
		{
			max = max_ending_here;
		}
	}

	return max;
}

int main() {
	Kadane_Algorithm * ptr = new Kadane_Algorithm();
	std::vector<int> v = ptr -> populate_vector();
	int result = ptr -> maxSum(v);
	cout<<"The largest sum is "<<result<<endl;
	return 0;
}