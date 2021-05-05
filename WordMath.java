// https://www.w3schools.com/java/java_hashmap.asp
// https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

/**
 *  @author Tobechi Onwenu
 *  Instructor: Siva Jasthi
 *  Class: ICS 141 (programming with objects)
 *  Project: Final Exam
 *  Class: WordMath.java
 *  Files used: char_value.txt 
 */

/**
 * WordMath provides some helper methods to figure out the value of the words
 * Assume that a text file "char_value.txt" exists indicating the value of each character
 * If any character does not exist in the char_value, its default value is 1
 * 
 * what is the value of CAT?
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordMath implements Comparator<String> {

	final static String filePath = "char_value.txt";

	// charValueMap holds the information from char_value.txt
	// with key is the character and integer as the value
	static Map<String, Integer> charValueMap = new HashMap<String, Integer>();

	/**
	 * This method takes the file name and uses BufferredReader to loads up the
	 * charValueMap. charValueMap contains only those characters that are there in
	 * the file.
	 * 
	 * @return
	 */
//	public WordMath(String a_file_name) {

	// create a HashMap method and use BufferredReader to read file content
	public static Map<String, Integer> getHashMapFromTextFile() {

		// Initialize BufferedReader
		BufferedReader br = null;

		// Start try and catch file statement
		try {

			// retrieve file from folder
			File file = new File("char_value.txt");

			// Send file into bufferedReader for processing
			br = new BufferedReader(new FileReader(file));

			// initialize string to store file content from bufferedReader
			String line = null;

			// While loop with split and trim method to get individual contents
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(":");

				String character = parts[0].trim(); // get char values
				Integer number = Integer.parseInt(parts[1].trim()); // get int values

				// if statement to grab all contents not empty and add them to charValueMap
				if (!character.equals("") && !number.equals(0))
					charValueMap.put(character, number);
			}

			// catch exception
		} catch (Exception e) {
			e.printStackTrace();
			// final catch with bufferredReader close()
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
				}
			}
		}
		// return
		return charValueMap;
	}

	/**
	 * Given a string, this method returns the value of that string
	 * 
	 * @param a_string
	 * @return
	 */

	public int getValue(String a_string) {

		// initialize count and result variable
		int count = 0;
		String result = null;

		// for loop to iterate over string length
		for (int i = 0; i < a_string.length(); i++) {

			// add character value of string to result
			result = Character.toString(a_string.charAt(i));

			// if found in hashMap count++
			if (charValueMap.containsKey(result)) {
				count += charValueMap.get(result);

				// if not found add 1 to count e.g T
			} else {
				count += 1;
			}
		}
		// return final count
		return count;
	}

	/**
	 * This methods checks whether the given string matches the given value
	 * 
	 * @param a_string
	 * @param some_value
	 * @return
	 */
	public boolean stringEqualsValue(String a_string, int some_value) {

		// Initialize count and result
		int count = 0;
		String result = null;
		// iterate over string
		for (int i = 0; i < a_string.length(); i++) {

			// add character value of string to result
			result = Character.toString(a_string.charAt(i));

			// if found in hashMap count++
			if (charValueMap.containsKey(result)) {
				count += charValueMap.get(result);
				// if not found add 1 to count e.g T
			} else {
				count += 1;
			}
		}
		// check if equal in value
		if (count == some_value) {
			// then return true
			return true;
		}
		// else return false
		return false;
	}

	/**
	 * This method implements compare method. This compares the strings based on the
	 * value. This method returns -1, 0, 1 based on the comparisons. See Comparator
	 * interface for more details.
	 */

	public int compare(String string_1, String string_2) {
		// using the compareTo since class implements comparator
		return string_1.compareTo(string_2);
	}

	/**
	 * This method takes in a file_name as the input Processed all the words in the
	 * file and prints out the value of each word.  
	 * 
	 * @param words_file_name
	 * @return
	 */
	public boolean massUpdate(String words_file_name) {

		// initialize words, value and scanner for the file
		String words = null;
		int value = 0;
		Scanner input = null;
		Scanner input2 = null;
		
		// try and catch using the scanner class
		try {
			File file = new File("word_list_input.txt");
			input = new Scanner(file);
			while (input.hasNext()) {
				// insert words to string
				words = input.nextLine();
				System.out.println(words);
			}

			// new file initialize to getvalue
			File file2 = new File("word_list_input.txt");
			input2 = new Scanner(file2);
			while (input2.hasNext()) {

				// using the getValue Method
				value = getValue(input2.nextLine());
				System.out.println( value);
			}
			
			// still needs some enjoy.

		} catch (Exception e) {
			String error = e.getMessage();
			System.out.println(error);
		}
		input.close();
		return false;
	}

	/**
	 * This method sorts the ArrayList lexicographically
	 * 
	 * @param some_strings
	 */

	public void sort(ArrayList<String> some_strings) {
		// sorting in natural order
		some_strings.sort(null);
	}

	/**
	 * This method returns the word with the maximum value
	 * 
	 * @param a_word_list
	 * @return
	 */

	public String getMaxValueWord(ArrayList<String> a_word_list) {

		// initialize max variable
		String max = null;
		// iterate over ArrayList loop
		for (int i = 0; i < a_word_list.size(); i++) {
			// using collections class
			Collections.sort(a_word_list);
			// get the string at index 0
			max = a_word_list.get(0);
		}
		return max;
	}

	/**
	 * This method returns the word with the minimum value
	 * 
	 * @param a_word_list
	 * @return
	 */
	public String getMinValueWord(ArrayList<String> a_word_list) {

		// initialize min variable
		String min = null;
		// iterate over ArrayList loop
		for (int i = 0; i < a_word_list.size(); i++) {
			// using collections class
			Collections.sort(a_word_list);
			// get the string at end of index
			min = a_word_list.get(a_word_list.size() - 1);
		}
		return min;
	}

	/**
	 * This method returns a miniList containing all the words matching the given
	 * value If there is no word matching that value, then "null" is returned
	 * 
	 * @param a_word_list
	 * @return
	 */
	public ArrayList<String> containsValue(int some_value, ArrayList<String> some_strings) {

		// create ArrayList miniList
		ArrayList<String> miniList = new ArrayList<>();

		// iterate over ArrayList some_strings
		for (int i = 0; i < some_strings.size(); i++) {
			// get the integer value of each string
			int b = some_strings.get(i).length();
			// check if value is equal to some_value entered
			if (b == some_value) {
				// add result of boolean to miniList
				miniList.add(some_strings.get(i));
				return miniList;
			}
		}
		return null;
	}

	/**
	 * This method returns a hash_map of (string, int) where key is the input_string
	 * and value is the value of the word For example: input (cat, dog, fish)
	 * output([cat,9], [dog, 5], [fish,10])
	 * 
	 * @param some_strings
	 */
	public HashMap<String, Integer> wordValueMap(ArrayList<String> some_strings) {

		// Create HashMap object to store result
		HashMap<String, Integer> result = new HashMap<>();

		// for loop for ArrayList some_strings
		for (int i = 0; i < some_strings.size(); i++) {
			// put into HashMap
			result.put(some_strings.get(i), this.getValue(some_strings.get(i)));
		}
		// return HashMap
		return result;

	}

	/**
	 * This method starts a game promoting the user to input a word matching a
	 * value. "Enter a word with value of 10". And when the user enters a word, it
	 * calculates its value, and promotes "Try Again: Go Higher" "Try Again: Go
	 * Lower" "Perfect! You guessed the correct word matching that value" And the
	 * game keeps on continuing till the user wins the game.
	 * 
	 * @param some_value
	 */

	public void play(int some_value) {

		// word variable
		String word;

		// initialize scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a word with a  value of: " + some_value);

		word = scanner.nextLine(); // Read user input

		// control for while loop
		int count = 1;
		// start while loop
		while (count > 0) {

			if (word.length() < some_value) { // check if too low
				System.out.println("Word value is: " + word.length());
				System.out.println("Too Low Try again:");
				word = scanner.nextLine();
			} else if (word.length() > some_value) { // check too high
				System.out.println("Word Value is: " + word.length());
				System.out.println("Too High Try again: ");
				word = scanner.nextLine();
			} else { // You win.
				System.out.println("You got it right");
				// stop count
				break;
			}
		}
		// close scanner
		scanner.close();

	}

	public static void main(String[] args) {
		// Test 1
		// Used to access the method getHashMapFromTextFile
		Map<String, Integer> mapFromFile = getHashMapFromTextFile();
		System.out.println("Printing the char map");
		System.out.println(mapFromFile);

		System.out.println("*********************************************");

		// Test 2. get value
		WordMath wm = new WordMath();
		System.out.println("Getting the value of CAT");
		System.out.println(wm.getValue("CAT"));

		System.out.println("*********************************************");

		// Test 3. stringEqualsValue
		System.out.println("StringEqualsValue");
		System.out.println("Using string \"CAT\" " + wm.stringEqualsValue("CAT", 10) + " For 10");
		// my Text code CBT is True. as C=4, B=5 and T=1 by default.
		System.out.println("Using string \"CBT\" " + wm.stringEqualsValue("CBT", 10) + " For 10");

		System.out.println("*********************************************");

		// Test 4. comparingValues
		System.out.println("compare");
		System.out.println(wm.compare("CAT", "CAT"));
		System.out.println(wm.compare("cap", "dog"));
		System.out.println(wm.compare("dog", "cat"));

		System.out.println("*********************************************");

		// Test 5. massUpdate
		// open the word_list_output.txt and verify that all the words are processed.
		// you can also print here - that is an optional work
		System.out.println("Mass update Method");
		wm.massUpdate("word_list_input.txt");

		System.out.println("*********************************************");

//		// Test 6. getMaxValueWord
		WordMath wmMine = new WordMath();
		ArrayList<String> some_words = new ArrayList<String>();
		some_words.add("cat");
		some_words.add("CAT");
		some_words.add("dog");
		some_words.add("DOG");
		some_words.add("FISH");
		some_words.add("ELEPHANT");
		some_words.add("xmas");
		some_words.add("a");

		System.out.println("*********************************************");

		String word_with_max_value = wmMine.getMaxValueWord(some_words);
		System.out.println("Word with Max Value is : " + word_with_max_value);

		System.out.println("*********************************************");

		// Test 7. getMinValueWord
		String word_with_min_value = wmMine.getMinValueWord(some_words);
		System.out.println("Word with Min Value is : " + word_with_min_value);

		System.out.println("*********************************************");

		// Test 8. containsValue

		ArrayList<String> mini_list = wmMine.containsValue(10, some_words);
		System.out.println("Words with the value of 10 are : " + mini_list);

		// for testing if value ten is working
		ArrayList<String> checkTen = new ArrayList<String>();
		checkTen.add("catcatcat");
		checkTen.add("catcatcata");
		checkTen.add("catcatcatcat");
		System.out.println("Word with the value of 10 are: " + wmMine.containsValue(10, checkTen));

		System.out.println("*********************************************");

		// Test 9. wordValueMap
		HashMap<String, Integer> my_hash_map = wmMine.wordValueMap(some_words);
		System.out.println("Words with the value of 10 are : " + my_hash_map);

		System.out.println("*********************************************");

		// Test 10. sort
		System.out.println("word list before sorting");
		System.out.println(some_words);
		wmMine.sort(some_words);
		System.out.println("word list after sorting");
		System.out.println(some_words);

		System.out.println("*********************************************");

		// Test 11. play (extra credit)
		System.out.println("PLay method");
		wmMine.play(10);

	}

}
