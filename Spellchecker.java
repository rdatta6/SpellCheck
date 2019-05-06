package com.company;
import java.util.*;
import java.io.*;

public class Spellchecker {

    private static String[] reader() {//Rip the dictionary from the dict.txt file
        File file = new File("/Users/rohandatta/Downloads/dict.txt");
        try {
            Scanner scan = new Scanner(file);
            List<String> words = new ArrayList<>();
            while (scan.hasNextLine()) {
                words.add(scan.nextLine());
            }
            System.out.println(words.size());
            return words.toArray(new String[words.size()]);
        } catch (IOException I) {
            System.out.println("File not Found");
        }
        String[] error = {"Not Found"};
        return error;
    }

    public static class LPHash<Key extends Comparable<Key>, Value> {
        private int N;
        private int M;
        private Key[] keys; //array for keys
        private Value[] vals;//array for values

        public LPHash(int cap) {
            this.M = cap;
            this.keys = (Key[]) new Comparable[M];
            this.vals = (Value[]) new Comparable[M]; //initialize arrays
        }

        private Integer hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % M;
        }

        private void resize(int cap) {
            LPHash<Key, Value> t;
            t = new LPHash<Key, Value>(cap);
            for (int i = 0; i < M; i++){
                if (keys[i] != null)
                {t.put(keys[i], vals[i]);}}
            keys = t.keys;
            vals = t.vals;
            M = t.M; //if the table is not large enough, copy the current table to a larger table
        }

        public void put(Key key, Value val) {
            if (N >= M / 2) {resize(2 * M);}  // double M (see text)
            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % M) {//check to make sure there isnt anything in the hashed index
                if (keys[i].equals(key)) {//if there is, update it
                    vals[i] = val;
                    return;
                }
            }
                keys[i] = key;//else, just assign the value to the empty slot
                vals[i] = val;
                N++;
        }

        public Value get(Key key) {
            for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
                if (keys[i].equals(key)) {
                    return vals[i]; //obtain the value at the hashed index
                }
            }
            return null;
        }

        public void SpellChecker(Key key){
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        List<String> Suggestions = new ArrayList<>();//store suggested viable words here
        if (get(key) != null){
            System.out.println("No mistakes found");
        }
        else {
            for (int i = 0;i < 26; i++){//check to see if adding a letter to start or end makes a viable word
                 String trial = alphabet[i]+key.toString();
                 Key X = (Key) trial;
                 if (get(X) != null){Suggestions.add(trial);}
                 String trial2 = key.toString() + alphabet[i];
                 Key Y = (Key) trial2;
                if (get(Y) != null){Suggestions.add(trial2);}
            }
            String trial3 = key.toString().substring(0,key.toString().length()-1);
            Key Z = (Key) trial3; //check to see if removing a ltter from beginning or end makes a viable word
            if (get(Z) != null){Suggestions.add(trial3);}
            String trial4 = key.toString().substring(1,key.toString().length());
            Key A = (Key) trial4;
            if (get(A) != null){Suggestions.add(trial4);}
            for (int i = 0; i < key.toString().length(); i++) { //swap letters to check for a transpose error
                if (i < key.toString().length() - 1) {
                    char[] breakdown = key.toString().toCharArray();
                    char temp = breakdown[i];
                    breakdown[i] = breakdown[i + 1];
                    breakdown[i + 1] = temp;
                    String trial5 = new String(breakdown);
                    Key B = (Key) trial5;
                    if (get(B) != null) {
                        Suggestions.add(trial5);
                    }
                }
            }
                System.out.print("Spelling Error ");
            if (Suggestions.size() == 0){System.out.print("- No Suggestions Found");}
            else {
                System.out.print("- Did you mean: ");//output suggestions
                String[] ArraySuggestions = Suggestions.toArray(new String[Suggestions.size()]);
                for (int i = 0; i < Suggestions.size(); i++){
                    System.out.println(ArraySuggestions[i]);
                }
            }
            }
        }
    }

        public static void main(String[] args) {
            String[] dict = reader();
            LPHash<String,String> Dictionary = new LPHash<>(73);
            for (int i = 0; i < 25144; i++){
                Dictionary.put(dict[i],dict[i]);
            }
           System.out.println("Enter a word: ");
            Scanner scanner = new Scanner(System.in);
            String word = scanner.nextLine();
            Dictionary.SpellChecker(word);
        }
}
/*25144
Enter a word:
ood
Spelling Error - Did you mean: food
good
hood
mood
rood
wood*/