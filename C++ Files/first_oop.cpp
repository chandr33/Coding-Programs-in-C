#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Circle {
	public:
		double compute_area(double r) {
			radius = r;
			cout<<radius<<endl;
			return 3.14*radius*radius;
		}

	private:
		double radius;
		//cout<<radius<<endl;
};

class Animal {
	//Every class has attributes such as height, weight and these are modeled as variables
	//Every class has Capabilities such as run,eat and these are modeled as functions
	private:
		int height;
		int weight;
		string name;

		static int numOfAnimals;//This variable's value is going to be shared by every object of type Animal that is ever created 
								//They basically act as Global Variables

	public:
		int getHeight() {return height;}
		int getWeight() {return weight;}
		string getName() {return name;}
		void setHeight(int cm){height = cm;}
		void setWeight(int kilo){weight = kilo;}
		void setName(string nm){name = nm;}
		void setAll(int,int,string);//Decalring a function prototype
		Animal(int,int,string);//Constructor
		~Animal();//Destructor
		Animal();//Function Overloading - Static polymorphism
		static int getNumOfAnimals() {return numOfAnimals;}
		void toString();//Function prototype
};

int Animal::numOfAnimals = 0;//Defining static variable
void Animal::setAll(int height, int weight, string name) {
	this -> height = height;//To refer to an object's specific height and not just a generic height
	//This is also done becuase at this point there are no Animal objects created
	this -> name = name;
	this -> weight = weight;
	Animal::numOfAnimals++;
}
Animal::Animal(int height, int weight, string name) {//Defining the constructor
	this -> height = height;
	this -> name = name;
	this -> weight = weight;
	Animal::numOfAnimals++;
}

Animal::~Animal() {
	cout<<"Animal "<<this->name<<" Destroyed "<<endl;
}
Animal::Animal() {//This is the overloaded constructor
	Animal::numOfAnimals++;
}
void Animal::toString() {
	cout<<this->name<<" is "<<this->height<<" cms tall and "<<this->weight<< "kgs in weight "<<endl;
}

class Dog : public Animal {//Inherit all the public variables and functiond from Animal class
	private:
		string sound = "Woof";
	public:
		void getSound() {cout<<sound<<endl;}

		Dog(int,int,string,string);

		Dog() : Animal() {};//This is calling the Dog superclass constructor, i.e. in this case is Animal

		void toString();//This is function overriding - Dynamic Polymorphism

};

Dog::Dog(int height,int weight,string name,string bark) : Animal(height,weight,name) //We are allowing the Animal or the base class contructor 
{																					//to handle all the arguments
	this -> sound = bark;//Sound doesn't exist in the animal class
}

void Dog::toString() {
	cout<<this->getName()<<" is "<<this->getHeight()<<" cms tall and "<<this->getWeight()<< "kgs in weight and says "<<this -> sound<<endl;
}
int main() {
	Circle cir;
	//cout<<cir.compute_area(1.5)<<endl;
	Animal fred;
	fred.setName("Fred");
	fred.setWeight(100);
	fred.setHeight(180);
	cout<<fred.getName()<<" is "<<fred.getHeight()<<" cms tall and "<<fred.getWeight()<<" kgs in weight "<<endl;

	Animal tom(36,15,"Tom");
	cout<<tom.getName()<<" is "<<tom.getHeight()<<" cms tall and "<<tom.getWeight()<<" kgs in weight "<<endl;

	Dog spot(38,16,"Spot","woof");
	cout<<"The number of animals are "<<Animal::getNumOfAnimals()<<endl;
	spot.getSound();
	tom.toString();
	spot.toString();
	//Destructor will be called before returning in a Queue mannerism ,i.e. Spot,Tom,Fred
	return 0;
}