# SpellCheck
Implementation of a Spellchecker backed by a hash-table using linear probing
Implements a spelling checker by using a hash table. 
Includes a driver program that prompts you to type a word and checks for misspelled words. 
If the word is spelled correctly outputs “no mistakes found”. 
For misspelled words it will list any words in the dictionary that are obtainable by applying any of the following rules:
a. Add one character to the beginning
b. Add one character to the end
c. Remove one character to the beginning 
d. Remove one character from the end
e. Exchange adjacent characters
