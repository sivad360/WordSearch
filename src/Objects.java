
import java.util.LinkedList;
import java.util.List;

 import java.util.Random;

public class Objects {


        List<String> wordList = new LinkedList<>(); // declare variables to store information about
        // the word search. The Menu class accesses these directly but that is probably poor form.
        int numberOfStrings;
        String[] wordStringArray = new String[numberOfStrings];
        char[][] wordCharArray;
        int arraySize;
        char[][] solution;
        char[][] main;


    public Objects(List<String> wordListNew) { // constructor initializes word search arrays
        //from the word list gathered in Menu.menugenerate

        wordList = wordListNew;
        numberOfStrings = wordList.size();
        wordStringArray = ListToString(wordList);
        wordCharArray = stringToChar(wordStringArray);
        arraySize = determineSize(wordStringArray);
        solution = place(wordCharArray);
        main = replaceX(solution);

    } // end objects method 


    public  char[][] get(char wordSearchType) { // returns WordSearch char array to menu 
        // never used this but probably should have used with more private data
        
        if(wordSearchType == 'w') { // select formatted unsolved wordsearch or formatted word 
            // search to return.

            return main; 

        }else if(wordSearchType =='s') {

            return solution;

        }

        return main;

    }// end get method


    public String[] ListToString(List<String> givenWordList){ // turn word list into array
        // of strings

        String[] givenStringArray = givenWordList.toArray(new String[0]);

        return givenStringArray;
        
    } // end listToString method


    public char[][] stringToChar(String[] givenStringArray){ // turn array of strings into
        // 2d array of chars x index represents word y index represents letter of word

        char[][] givenCharArray = new char[givenStringArray.length][];

        for(int i =0; i < givenStringArray.length; i++) {
            
        
            givenCharArray[i] = new char[givenStringArray[i].length()];

            for(int j = 0; j <givenStringArray[i].length(); j++) { 

                givenCharArray[i][j] = Character.toLowerCase(givenStringArray[i].charAt(j)); 

            }

        }

        return givenCharArray;

    } // end stringToChar method


    public int determineSize(String[]givenStringArray) { // takes array of strings and returns 
        // int representing the length of one side of the word search. checks to see if the length 
        // of the longest word is greater or less than the number of words. takes greater 
        // value and adds 5 to give room to place words

        int numberOfStrings = givenStringArray.length;

        String currentLongestString = givenStringArray[0];

        int arraySize = 0;

        for(String givenString: givenStringArray ){ // iterate through strings search for longest one

            if(currentLongestString.length() < givenString.length()) { 

                currentLongestString = givenString;

            } else {

            }

        }

        int lengthOfLongestString = currentLongestString.length();

        if (lengthOfLongestString <= numberOfStrings) { // check which is greater to determine array size

            arraySize = numberOfStrings;

        } else if (lengthOfLongestString > numberOfStrings) {

            arraySize = lengthOfLongestString;

        }

        arraySize +=5;

        return arraySize;

    } // end determineSize method


    public char[][] replaceX(char[][] wordSearchArray) { // replaces any 'x' chars in the solution
    // array with randomly generated chars for the main array.

        char[][] wordSearchArraynew = new char[arraySize][arraySize]; 

        for(int i = 0; i < arraySize; i++){ // iterate through given word search and assign values
            // to a new word search so that the arrays are not linked

            for( int j = 0; j < arraySize; j++){

                wordSearchArraynew[i][j] = wordSearchArray[i][j];

            }

        }

        Random rand = new Random();

        for(int i = 0; i < arraySize; i ++) {

            for(int j = 0; j < arraySize;j++){

                if (wordSearchArraynew[j][i] == 'x') {

                    int randInt = rand.nextInt(25)+97;
                    char randChar = (char) randInt;
                    wordSearchArraynew[j][i] = randChar;

                } else {

                }
            }
        }

        return wordSearchArraynew;
        
    } // end replaceX method

    public char[][] place(char[][] wordCharArray) { // method that 
        //takes in an array of chars representing the words to put in word search. generates random
        // coordinates and direction and tries to place word there. If fails then tries again.
        // returns 2d char array representing formatted word search solution

        Random Rand = new Random();
        int coordX;
        int coordY;
        int furthestIndex = arraySize-1; // greatest index value within the array
        char[][] wordSearchArray = new char[arraySize][arraySize];
        int direction;
        char currentCoordValue = 'x';

        for (int h = 0; h < arraySize; h++) { // replace all indexes with 'x'
           
           for (int g =0; g < arraySize; g++){

            wordSearchArray[h][g] = 'x';
           }

        }

        for(int i = 0; i < wordCharArray.length; i++) { // iterate through words in char array

            int j = 0; 
            Boolean cont = false;
            int currentWordLength = wordCharArray[i].length; 
            char[] currentWordChar = new char[currentWordLength];

            for(int z = 0; z < currentWordLength; z++) {

                currentWordChar[z] = wordCharArray[i][z];
            }

            while(j<1000 && cont == false) { // try and place word 100 times or until placed.
                
                j++;
                coordX = Rand.nextInt(furthestIndex); // create values for index to place first 
                //letter of word

                coordY = Rand.nextInt(furthestIndex) ;

                direction = Rand.nextInt(8); // creates values for direction each at
                //multiples of 45 degrees. 90 to 270 degrees will print word backwards
                Boolean emptyVector = true; // boolean represents if all the indexes of a potential
                //word placement are empty
                int[][]coordArray = new int[currentWordLength][2]; // 2d array to represent
                // the indexes of each char in a word keeps track to place word after checking
                
                for(int k = 0; k < currentWordLength; k++) { // loop which contains all
                    // directional ifs will iterate through each index of an individual word

                    if (direction ==0){ // east

                        if(coordX + currentWordLength > furthestIndex) { // check if word the whole
                            // word will fit into the array

                            emptyVector = false;

                        } else { // check if any of the chars overlap with other words

                            currentCoordValue = wordSearchArray[coordX+k][coordY]; 
                            coordArray[k][0] = coordX + k;
                            coordArray[k][1] = coordY;
                            cont = true;

                        }

                    } else if (direction ==1) { // North East
                        if(coordX + currentWordLength > furthestIndex || coordY + currentWordLength > furthestIndex ) {

                            emptyVector = false;

                        } else {

                        currentCoordValue = wordSearchArray[coordX+k][coordY+k]; // 1/4 pi radians
                        coordArray[k][0] = coordX + k;
                        coordArray[k][1] = coordY + k;
                        cont = true;

                        }

                    } else if (direction ==2) { // North
                        if(coordY + currentWordLength> furthestIndex ) {

                            emptyVector = false;

                        } else {

                            currentCoordValue = wordSearchArray[coordX][coordY+k]; //1/2 pi radians
                            coordArray[k][0] = coordX;
                            coordArray[k][1] = coordY + k;
                            cont = true;

                        }

                    } else if (direction ==3) { // North West 

                        if(coordX - currentWordLength< 0 || coordY + currentWordLength> furthestIndex ) {

                            emptyVector = false;

                        } else {
                            
                            currentCoordValue = wordSearchArray[coordX-k][coordY+k]; //3/4 pi
                            coordArray[k][0] = coordX - k;
                            coordArray[k][1] = coordY + k;
                            cont = true;

                        }

                    } else if (direction ==4) { // West

                        if(coordX - currentWordLength < 0) {

                            emptyVector = false;

                        } else {

                            currentCoordValue = wordSearchArray[coordX-k][coordY];
                            coordArray[k][0] = coordX-k;
                            coordArray[k][1] = coordY;
                            cont = true;

                        }
                    } else if (direction == 5) { // South West

                        if(coordX - currentWordLength < 0 || coordY - currentWordLength < 0 ) {

                            emptyVector = false;

                        } else {

                            currentCoordValue = wordSearchArray[coordX-k][coordY-k];
                            coordArray[k][0] = coordX - k;
                            coordArray[k][1] = coordY - k;
                            cont = true;

                        }

                    } else if (direction == 6) { // South

                        if(coordY - currentWordLength< 0 ) {

                            emptyVector = false;

                        } else {

                            currentCoordValue = wordSearchArray[coordX][coordY-k]; 
                            coordArray[k][0] = coordX;
                            coordArray[k][1] = coordY - k;
                            cont = true;

                        }

                    } else if (direction ==7) { // South East

                        if(coordX + currentWordLength> furthestIndex || coordY - currentWordLength < 0) {

                            emptyVector = false;

                        } else {

                            currentCoordValue = wordSearchArray[coordX+k][coordY-k]; 
                            coordArray[k][0] = coordX + k;
                            coordArray[k][1] = coordY - k;
                            cont = true;

                        }

                    }

                    if(currentCoordValue != 'x') {

                        emptyVector = false;

                    } else{

                    }

                }

                if(emptyVector == true) { // if all checks are valid then place each char of word 
                    // into its checked index
 
                    for(int l= 0; l < wordCharArray[i].length; l++) {

                        wordSearchArray[coordArray[l][0]][coordArray[l][1]] = wordCharArray[i][l];
                        
                    }

                }

            }

        }

        return wordSearchArray;

    } // end place method

} // end Objects class


