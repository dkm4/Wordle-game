import java.util.ArrayList;         //import ArrayList
import java.util.Collections;
import java.util.Scanner;           //import Scanner;
import java.io.FileInputStream;     //import FileInputStream;
import java.util.Random;            //import random;

public class Dlewor {
    public static boolean isSorted(ArrayList<String> wordList){                                 //this method check if the wordList is sorted in alphabetical order
        for (int i=0; i<wordList.size()-1; i++){
            if(wordList.get(i).compareTo(wordList.get(i + 1)) > 0){                             //check if each word is smaller than the next word. If there is such word bigger than next word, the program returns false
                return false;
            }
        }
        return true;
    }

    public static int binarySearch(ArrayList<String> wordList, int min, int max, String check){     //method for binary search of the wordList to find a specific word
        int mid;
        if(min <= max) {                                                                            //if min is not <= max it means that the specific word does not exist in the wordList
            mid = (min + max) / 2;
            if (check.equals(wordList.get(mid))) {                                                  //base case where the program find that word in the wordlist.
                return mid;
            } else if (check.compareTo(wordList.get(mid)) < 0) {                                    //if the checking word is smaller than the word in the mid index, the program search the left section
                return binarySearch(wordList, min, mid-1, check);
            } else {
                return binarySearch(wordList, mid + 1, max, check);                            //else search right section
            }
        }
        return -1;                                                                                  //return -1 if the word does not exist in the word list
    }

    public static ArrayList<String> readDictionary(Scanner read){       //method for selecting the 5character words from the dictionary and assigning to the arrayList
        ArrayList<String> Dictionary = new ArrayList<>();               //new arrayList for 5character words
        int i;                                                          // I found that there exist word such as cry9c in the vocab.nytimes.random.txt file. So I added a logic to only select 5 character word without any numbers

        while(read.hasNextLine()){                                      //iterate while the dictionary file has next word
            String line = read.nextLine();                              //read the next each word in the dictionary file
            //while I was testing for code I encountered a situation where the target word was cry9c. I learned that I have to also check if there is no number inside my selected word that has 5 characters
            if (line.length() == 5) {
                boolean onlyString = true;                              //boolean variable to check if selected 5 character does not contain any number in it
                for(i=0; i<line.length(); i++) {
                    if(!Character.isLetter(line.charAt(i))){            //if the selected number contain character that is not alphabet, the onlyString will be assigned as false and the for loop will break which means that the selected letter will not be added to dictionary
                        onlyString = false;
                        break;

                    }
                }
                if(onlyString){                                         //only if the word is 5 character and made up of alphabets
                    Dictionary.add(line);                               //add the word to the dictionary
                }
            }
        }
        return Dictionary;                                              //return the arrayList
    }

    public static boolean checkWord(String attempt, ArrayList<String> dictionary){      //check if the user input word is inside the arrayList
        /*int i;
        String check;
        for(i=0; i<dictionary.size(); i++){                                             //iterate through the arrayList to find the user Input word
            check = dictionary.get(i);
            if(attempt.equals(check)){
                return true;
            }
        }
        return false;*/
        int checkWord;
        boolean sortCheck = isSorted(dictionary);                                   //check if the wordList is sorted
        if(sortCheck){                                                              //if the wordList is sorted, use binary search
            checkWord = binarySearch(dictionary,0,dictionary.size(), attempt);
            if(checkWord == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{                                                                       //if the wordList is not sorted, use linear search
            checkWord = linearSearch(dictionary,0,dictionary.size(),attempt);
            if(checkWord == -1){
                return false;
            }
            else{
                return true;
            }
        }
    }

    public static int[] matchDlewor( String target, String attempt) {                  //method for assigning color to each character of the attempt
        int[] match = {0,0,0,0,0};                                                     //array of length 5
        int i;
        int j;
        ArrayList<Character> checkP = new ArrayList<>();                               //arrayList to read each character of target word.
        for(i=0; i<target.length(); i++){
            checkP.add(target.charAt(i));
        }


        for(i=0; i<5; i++) {
            if (attempt.charAt(i) == checkP.get(i)) {                                  //if the charcter of each attempt word match target assign number 1 to the list and remove that character from the checkP to prevent redundancy
                match[i] = 1;
                checkP.set(i, '0');
            }
        }
        for(i=0; i<5; i++) {                                                          //if the character of each attempt word is in different location of the target word assign 2 and remove that character from checkP to prevent redundancy
            if (match[i] != 1) {
                for (j = 0; j < 5; j++) {
                    if (match[j] != 1 && attempt.charAt(i) == checkP.get(j)) {
                        match[i] = 2;
                        checkP.set(j, '0');
                        break;                                                      //use break since there can be same two letter in the target word. Ex: buddy
                    }
                }
            }
        }
        for(i=0; i<5; i++) {                                                        //if particular index of match does not have 1 or 2 value than assign 3 to make it grey
            if((match[i] == 0)){
                match[i] = 3;
            }
        }
        return match;                                                               //return match
    }

    public static int linearSearch(ArrayList<String> wordList, int start, int end, String target){      //method using linear Search to check if the target word exist inside the arrayList that contains 5word character
        /*int i;
        for(i=start; i<end; i++){
            if(wordList.get(i).equals(target)){
                return i;
            }
        }
        return -1;*/

        if(end <= start){                                                   //there can be multi ways to code linear search. I checked one by one starting from the end of the wordList
            return -1;                                                      //if word does not exist return -1
        }
        else if(wordList.get(end-1).equals(target)){                        //base case where word is found in the wordList
            return end-1;
        }
        else{
            return linearSearch(wordList,start,end-1,target);          //recursive statement
        }
    }

    public static boolean foundMatch(int[] match){                          //method to check if all the word of the target is colored as green
        int i;
        for(i=0; i<match.length; i++){
            if(match[i] != 1){
                return false;
            }
        }
        return true;
    }


    public static void printDelwor(String attempt, int[] setColor){         //method for printing each character of the target word for assigned color
        int i;
        for(i=0; i<setColor.length; i++){
            if(setColor[i] == 1){
                System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + attempt.charAt(i));
            }
            if(setColor[i] == 2){
                System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + attempt.charAt(i));
            }
            if(setColor[i] == 3){
                System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + attempt.charAt(i));
            }
        }
        System.out.print(ANSI_RESET + "\n");
    }

    // constants to allow colored text and backgrounds
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);              //new Scanner
        FileInputStream file = null;                        //new fileInput stream to open the dictionary file
        Scanner read;                                       //new Scanner to read the dictionary file
        Random random = new Random();                       //new random variable

        ArrayList<String> wordList;                         //ArrayList to assign 5 word character in the dictionary
        int[] setColor;                                     //new array to set colors to the attempt word
        String target;                                      //String variable for the target word
        int randomNumber;
        int count = 1;

        try{
            if(args.length > 1){                                                        //if the user input in command Line is greater than one throw exception
                throw new Exception("You should only enter one file name");
            }
            file = new FileInputStream(args[0]);                                        //read the file
        }
        catch (IndexOutOfBoundsException e) {                                           //if user didn't input anything
            System.out.println("You should provide at least one file to run the program");
            System.exit(1);
        }
        catch(java.io.FileNotFoundException x){                                         //if the file cannot be found
            System.out.println("File cannot be found. Please re-enter the file name to run the program");
            System.exit(1);
        }
        catch(Exception e){                                                             //user defined exception if the args length is greater than 1
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // print welcome message
        System.out.println("Welcome to Dlewor(TM)");
        if(args[0].equals("French.random.txt")){                                                                    //if the program is reading french dictionary tell additional rules for the users to clarify confusions
            System.out.println("\n****************************************************************************");
            System.out.println("This is Dlewor for french version");
            System.out.println("Note only 5 character that doesn't have any space are choosen as an answer");
            System.out.println("The words with special characters can also be anwer!");
            System.out.println("****************************************************************************\n");
        }

        read = new Scanner(file);
        wordList = readDictionary(read);

        randomNumber = random.nextInt(wordList.size());                                 //assign random number based on the length of the ArrayList for the 5 word characters
        target = wordList.get(randomNumber);                                            //get the word the index of the random number and set it as target word
        int index = linearSearch(wordList,0,wordList.size(),target);              //check if the word exist in the arraylist using linear search

        int i;

        while(count <7){                                                                //give user 6 chance to guess the target word
            System.out.print("Enter Word (" + count + ") :");
            String attempt = scnr.next();                                               //read the attempt word
            boolean check = checkWord(attempt, wordList);


            while (!check) {                                 //while loop until user input is valid
                System.out.println("Invalid word.");
                System.out.print("Enter Word (" + count + ") :");
                attempt = scnr.next();
                check = checkWord(attempt, wordList);
            }

            setColor = matchDlewor(target,attempt);                                     //set color of each character based on the rule
            printDelwor(attempt,setColor);                                              //print the attempt word in color

            if(foundMatch(setColor) && index != -1){                                    //if the attempt word is all green, and it is inside the wordList
                System.out.println("Yesss!");
                System.exit(1);                                                  //user win the game and end the program
            }
            count++;
        }
        System.out.println("The answer was \"" + target + "\"");                        //if user failed 6 attempt, tell the user the target word
        read.close();
        scnr.close();
    }
}
