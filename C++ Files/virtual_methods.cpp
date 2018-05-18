#include <iostream>
#include <vector>

using namespace std;//This allows us to use methods and functions in the std library

class Animal{
	public:
		void getFamily() {cout<<"We are animals"<<endl;}
		virtual void getClass() {cout<<"I am an animal"<<endl;}//This method is marked virtual as we anticipate the sub class of animals will have 
					//a function written to overwrite this function
};

class Dog: public Animal {
public:
	void getClass() {cout<<"I am a dog"<<endl;}
};

class GermanShepherd : public Dog {
	public:
		void getClass() {cout<<"I am a German Shepherd"<<endl;}
		void getDerived() {cout<<"I am an animal and dog"<<endl;}
};

void whatClassAreYou(Animal *animal) {
	animal -> getClass();
}

int main() {
	Animal *animal = new Animal;
	Dog *dog = new Dog;

	animal -> getClass();
	dog -> getClass();
	whatClassAreYou(animal);
	whatClassAreYou(dog);

	Dog spot;
	GermanShepherd max;
	//The base class can call derived class methods as long as they are defined in the base class
	Animal *ptrDog = &spot;
	Animal *GShepherd = &max;

	ptrDog -> getFamily();
	ptrDog -> getClass();
	GShepherd -> getFamily();
	GShepherd -> getClass();
	 
	return 0;
}