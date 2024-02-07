import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static Objects selector(char inputChar, Objects MyObjects) { // method takes in 
        // a char representing the menu selection and an object representing the word search
        
        if (inputChar== 'g' ){ // if 'g' then generate new wordsearch
            
            Objects MyObjectsNew = new Objects(menuGenerate()); // call menugenerate and pass
            // the word list to the constructor method.

            return MyObjectsNew;

        } else if(inputChar == 'p') { // print formatted word search

            print(MyObjects, 'm');

        } else if(inputChar == 's') { //print formatted solution

            print(MyObjects,'s');

        } else if(inputChar == 'q') {
            quit();
        }   

        
        return MyObjects;
        
    }

    public static Objects welcome () { // welcome menu for opening program

        System.out.printf("%s%n%s%n", "Welcome to my word search generator!"
        , "please press (g) to begin generating your first word search");

        Scanner input = new Scanner(System.in);
        char inputChar = Character.toLowerCase(input.next().charAt(0));
        Objects MyObjects = selector(inputChar,null);

        return MyObjects;

    }

    public static Objects main(Objects MyObjects){ // main menu runs in while loop

        System.out.printf("%s%n%s%n%s%n%s%n%s", "Please select an option:"
            , "generate a new word search (g)", "print out your word search (p)"
            , "Print out the solution to your word search (s)","Quit the program (q)");

        Scanner input = new Scanner(System.in);
        char inputChar = Character.toLowerCase(input.next().charAt(0));

        selector(inputChar,MyObjects);

        return MyObjects;

    }

    public static void quit(){
    System.exit(0);
    }

    public static List<String> menuGenerate(){ // takes lines of user input stores them as strings.
        // then stores them as strings and puts them in a string list. returns list of strings

        Scanner input = new Scanner(System.in);
        List<String> WordList = new LinkedList<>();

        boolean cont = true;

        System.out.printf("%s%n%s%n", "Please enter the words which you would like to be a part of your word search."
            , "After every word press enter. Do not include spaces on. enter nothing to end entries.");

        while (cont == true ) {

            String currentString = input.nextLine();

            if (currentString.isEmpty()) {

                cont = false;

            } else {

                WordList.add(currentString);

            }

        }
        
        return WordList;

    }

    public static void print(Objects MyObjects, char arrayType){

        int arraySize = MyObjects.solution.length;

        for(int i = 0; i < arraySize; i ++) {

            for(int j = 0; j < arraySize;j++){

                if (arrayType =='m') {

                    System.out.print(MyObjects.main[j][i]);

                } else if (arrayType =='s') {

                    System.out.print(MyObjects.solution[j][i]);
                }
            }

            System.out.println();

        }

    }

}
