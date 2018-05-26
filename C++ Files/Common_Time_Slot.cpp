#include <iostream>
#include <vector>

using namespace std;


//This function returns the earliest intersection time with duration of the given SlotsA and SlotsB. 
vector<int> meetingPlanner( const vector<vector<int>>& slotsA, const vector<vector<int>>& slotsB, int dur) 
{
	int found = 0;
	vector<int> common_time;
	vector<vector<int>>::const_iterator i = slotsA.begin();
	vector<vector<int>>::const_iterator j = slotsB.begin();

	vector<int>::const_iterator col_A;
	vector<int>::const_iterator col_B;

	while ((!found) && (i != slotsA.end()) && (j != slotsB.end())) {
		col_A = i -> begin();
		col_B = j -> begin();
		if (*(col_A+1)-(*col_A) >= dur)//If A has Time slot greater than or equal to duration
		{
			if (*(col_B+1)-(*col_B) >= dur) {//If B has Time slot greater than or equal to duration
				bool starting = (*col_A >= *col_B) ? true : false;
				if (!starting)
				{
					if ((starting + dur) <= *(col_A+1)) {
						found = 1;
						common_time.push_back(*col_B);
						common_time.push_back((*col_B + dur));
					}
				}
				else if(starting) {
					if ((*col_A + dur) <= *(col_B+1)) {
						found = 1;
						common_time.push_back(*col_A);
						common_time.push_back(*col_A + dur);
					}
				}
			}
		}
		if (*(col_A + 1) > *(col_B+1))
			j++;
		else
			i++;
	}
	return common_time;
}

int main() {
	//B = [[1,10]]
	//A = [[2,3],[5,7]]
	//Duration = 2

	vector<vector<int>> B;
	vector<int> element1_B;
	element1_B.push_back(1);
	element1_B.push_back(10);
	B.push_back(element1_B);

	vector<vector<int>> A;
	vector<int> element1_A;
	element1_A.push_back(2);
	element1_A.push_back(3);

	vector<int> element2_A;
	element2_A.push_back(5);
	element2_A.push_back(7);
	A.push_back(element1_A);
	A.push_back(element2_A);

	vector<int> result = meetingPlanner(A,B,2);
	if (result.empty() == true)
		cout<<"No common Time Slot"<<endl;
	else
		cout<<result.at(0)<<" "<<result.at(1)<<endl;

	return 0;
}