/*
Author: Andrey Risukhin
Project: Table Generator
Purpose: Generate a table used for my Modified Vigenere Cypher (oo)
Psuedocode: Takes console input, creates square table with it.
Maintenance Log:
12/16/19
- Started. 
- Correctly builds table to System.out and to a new file.
- Code is redundant, make less so before publishing.
12/19/19
- Removed commented field, this is written as a main() type program.
- Better UI with prints to console. 
- Better comments, no lines longer than 90 characters. 
- Still seems redundant to have two table constructors so similar, 
      but unsure how to reduce well.
*/

import java.util.*;
import java.io.*;

public class TableGenerator {
   
   public static void main(String args[]) throws FileNotFoundException {
      welcome();
      ArrayList<String> characterList = new ArrayList<String>();
      Scanner console = new Scanner(System.in);
      File output = new File(fileOutQuery(console));
      PrintStream toOutput = new PrintStream(output);
      addToTable(characterList, console); // Why does this work? Reference semantics
      String response = formatQuery(characterList, console);
      if (response.equals("A")) {
         buildTableSpaces(characterList, toOutput);
      } else if (response.equals("B")) {
         buildTableSpecial(characterList, toOutput);
      }
      System.out.println("Table saved to file. :D");
   }
   
   // Pre: Passed List<String>, Scanner fixed to System.in.
   // Post: Return a String. 
   public static String formatQuery(List<String> characterList, Scanner console) {
      System.out.println(characterList);
      System.out.println("What formatting do you want? ");
      System.out.println("A) Spaces seperating each character");
      System.out.println("B) Option A) with commas and quotes between each and" 
            + "braces on each line");
      System.out.println();
      String response = console.next();
      return response.toUpperCase();      
   }
   
   // Pre: Passed ArrayList<String>, PrintStream fixed to file to add table.
   // Post: Builds table with commas, spaces, quotes and braces, adds to output file.
   public static void buildTableSpecial(ArrayList<String> characterList, 
         PrintStream toOutput) {
      for (int i = 0; i < characterList.size(); i++) {
         toOutput.print("{");
         System.out.print("{");         
         for (int j = 0; j < characterList.size(); j++) { // Each row is offset by + i
            if (i + j >= characterList.size()) {
               toOutput.print("\"" + characterList.get(i + j - characterList.size()) 
                     + "\"");
               System.out.print("\"" + characterList.get(i + j - characterList.size()) 
                     + "\"");
            } else {
               toOutput.print("\"" + characterList.get(i + j) + "\"");
               System.out.print("\"" + characterList.get(i + j) + "\"");
            }
            if (j + 1 < characterList.size()) {
               toOutput.print(", ");
               System.out.print(", ");
            }
         }
         toOutput.println("},");
         System.out.println("},");
      }
   }

   // Pre: Passed ArrayList<String>, PrintStream fixed to file to add table.
   // Post: Builds table with spaces, adds to output file.   
   public static void buildTableSpaces(ArrayList<String> characterList, 
         PrintStream toOutput) {
      for (int i = 0; i < characterList.size(); i++) {
         for (int j = 0; j < characterList.size(); j++) { // Each row is offset by + i
            if (i + j >= characterList.size()) {
               toOutput.print(characterList.get(i + j - (characterList.size())));
               System.out.print(characterList.get(i + j - (characterList.size())));
            } else {
               toOutput.print(characterList.get(i + j));
               System.out.print(characterList.get(i + j));
            }
            toOutput.print(" ");
            System.out.print(" ");
         }
         toOutput.println();
         System.out.println();
      }
   }
   
   // Pre: Takes a Scanner hooked to System.in.
   // Post: Returns a String.
   public static String fileOutQuery(Scanner console) {
      System.out.println("File name for the table? (include .txt) ");
      return console.next();
   }
   
   // Pre: Takes ArrayList<String>, Scanner hooked to System.in.
   // Post: Returns List<String> populated with console input.
   public static List<String> addToTable(ArrayList<String> characterList, 
         Scanner console) {  
      boolean keepAdding = true;
      while(keepAdding) {
         String added = console.next();
         if (added.equals("~~~")) {
            System.out.println("You have ended the adding process.");   
            keepAdding = false;
         } else if (added.length() > 1) {
            System.out.println("Looks like you typed more than 1 character, "
                  + "try again!");
         } else {
            added = added.toUpperCase();
            characterList.add(added);
         }
      }
      return characterList;
   }
   
   // Post: Welcomes the user with instructions. 
   public static void welcome() {
      System.out.println("Welcome to the Table Generator!");
      System.out.println();
      System.out.println("After entering the File Name, type a character, "
            + "then enter, to add to the table. " + 
            "Press ~~~, then enter, to mark the end of adding charaters."); 
      System.out.println();
   }   
   
}