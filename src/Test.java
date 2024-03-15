//Name: Davis Tyler
//Class: CS145
//Date: 2/6/2024
//file: Test
//Resources: cs 145 Lectures
//Program: this program promps generates a word search from user inputted words. The menu will
// prompt users to choose to generate a new word search, print a formatted word search, or print
// a formatted solution to their word search. 

public class Test {
    public static void main(String[] args){

        Objects MyObjects = Menu.welcome(); // call welcome for first loop in program

        while(true){
            
            MyObjects = Menu.main(MyObjects);
        
        }

    }

}
