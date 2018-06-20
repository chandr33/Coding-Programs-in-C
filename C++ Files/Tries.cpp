#include <iostream>

using namespace std;

const int SIZE = 26;

typedef struct TrieNode {
	struct TrieNode * children[SIZE];
	bool isEndOfWord;
}Trie;

Trie * newNode() {
	Trie * new_node = new Trie();
	new_node -> isEndOfWord = false;
	for (int i = 0; i < SIZE; i++)
		new_node -> children[i] = NULL;
	return new_node;
}

void insert(Trie * root,string key) {
	int index;
	Trie * root_ref = root;
	for (int i = 0; key[i] != '\0'; i++)
	{
		index = key[i] - 'a';//Set index as alphabet's Number
		if (!root_ref -> children[index])//If the characteer is not present
			root_ref -> children[index] = newNode();//Set the children as the reference to the new node and mark the new node's End Of Word as false
		root_ref = root_ref -> children[index];
	}
	root_ref -> isEndOfWord = true;
}

bool search(Trie * root, string key) {
	Trie * root_ref = root;
	int index;
	for (int i = 0; key[i] != '\0'; ++i)
	{
		index = key[i] - 'a';
		if (!root_ref -> children[index])
			return false;
		root_ref = root_ref -> children[index];
	}
	if (root_ref -> isEndOfWord)
		return true;
	return false;
}

bool isEmpty(Trie * root) {
	for (int i = 0; i < 26; i++) {
		if (root -> children[i])
			return false;
	}
	return true;
}

bool delete_util(Trie * root, string key, int i, int length) {
	int index = key[i] - 'a';
	if (i == length) {
		if (!isEmpty(root)) {//If the last node leads upto some other words
			root -> isEndOfWord = false;//Set the end of word for the last node as False
			return true;
		}
		return false;//If the last node was empty
	}
	if (delete_util(root -> children[index],key,i+1,length))//If the last node was not empty
		return true;
	else {
		root -> children[index] = NULL;//Delete the current child
		if (isEmpty(root))//See if there are any else Non NULL Child Nodes present
			return false;
	}
	return true;
}

void delete_node(Trie * root, string key) {
	int i = 0;
	int deleted = 0;
	if (key.length() > 0) {
		if (delete_util(root,key,i,key.length()))
			deleted = 1;
	}
	if (deleted)
		cout<<"Deleted"<<endl;
	else
		cout<<"Not found"<<endl;
}

int main() {
	string keys[] = {"the","a","there","answer","any","by","bye","their"};
	int n = sizeof(keys)/sizeof(keys[0]);
	Trie * root = newNode();
	for (int i = 0; i < n; i++)	
		insert(root,keys[i]);

	delete_node(root,"a");
	search(root,"any") ? cout<<"Present"<<endl : cout<<"Not Found"<<endl;
	return 0;
}