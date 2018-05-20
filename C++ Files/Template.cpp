#include <iostream>
#include <vector>

using namespace std;


class Test_Vector {
	static int x;
	public: class Test_Iterator {
				public:
					int Test(){ return x;}
			};
};

int Test_Vector::x = 3;

template <typename T>
class Square : public T {
	public:
		int calc() {return T::Test() * T::Test();}
};
int main() {
	Square<Test_Vector::Test_Iterator> * sqr = new Square<Test_Vector::Test_Iterator>();
	cout<<sqr->calc()<<endl;
	return 0;
}