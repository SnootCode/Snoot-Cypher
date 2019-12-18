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
*/

import java.util.*;
import java.io.*;

public class TableGenerator {
   
   // private List<Character> characterList;
   
   public static void main(String args[]) throws FileNotFoundException {
      welcome();
      ArrayList<String> characterList = new ArrayList<String>();
      Scanner console = new Scanner(System.in);
      File output = new File(fileOutQuery(console));
      PrintStream toOutput = new PrintStream(output);
      addToTable(characterList, console); // Why does this work? Reference semantics
      System.out.println(characterList);
      System.out.println("What formatting do you want? ");
      System.out.println("A) Spaces seperating each character");
      System.out.println("B) Option A) with commas and quotes between each and braces on each line");
      System.out.println();
      String response = console.next();
      if (response.equals("a")) {
         buildTableSpaces(characterList, toOutput);
      } else if (response.equals("b")) {
         buildTableSpecial(characterList, toOutput);
      }
      System.out.println("Table saved to file. :D");
   }
   
   // Builds a table with commas, spaces, quotes and braces using the ArrayList, prints to the output file
   // Used as code for SnootCypher hard-coding
   public static void buildTableSpecial(ArrayList<String> characterList, PrintStream toOutput) {
      for (int i = 0; i < characterList.size(); i++) {
         toOutput.print("{");
         System.out.print("{");         
         for (int j = 0; j < characterList.size(); j++) {
            // Each row is offset by + i
            if (i + j >= characterList.size()) {
               toOutput.print("\"" + characterList.get(i + j - characterList.size()) + "\"");
               System.out.print("\"" + characterList.get(i + j - characterList.size()) + "\"");
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
   
   // Builds a table using the ArrayList, prints to the output file
   public static void buildTableSpaces(ArrayList<String> characterList, PrintStream toOutput) {
      for (int i = 0; i < characterList.size(); i++) {
         for (int j = 0; j < characterList.size(); j++) {
            // Each row is offset by + i
            if (i + j >= characterList.size()) {
               toOutput.print(characterList.get(i + j - (characterList.size())));
               System.out.print(characterList.get(i + j - (characterList.size())));
            } else {
               toOutput.print(characterList.get(i + j));
               System.out.print(characterList.get(i + j));
            }
            // Change to print space, comma, or something else
            toOutput.print(" ");
            System.out.print(" ");
         }
         toOutput.println();
         System.out.println();
      }
   }
   
   // Returns the file name of the table text file
   public static String fileOutQuery(Scanner console) {
      System.out.println("File name for the table? (include .txt) ");
      return console.next();
   }
   
   // Adds all used characters to the ArrayList 
   public static List<String> addToTable(ArrayList<String> characterList, Scanner console) {  
      boolean keepAdding = true;
      while(keepAdding) {
         String added = console.next();
         if (added.equals("~~~")) {
            System.out.println("You typed enter, ending the adding process.");   
            keepAdding = false;
         } else if (added.length() > 1) {
            System.out.println("Looks like you typed more than 1 character, try again!");
         } else {
            added = added.toUpperCase();
            characterList.add(added);
         }
      }
      return characterList;
   }
   
   public static void welcome() {
      System.out.println("Welcome to the Table Generator!");
      System.out.println();
      System.out.println("After entering the File Name, type a character, then enter, to add to the table. " + 
            "Press ~~~, then enter, to mark the end of adding charaters."); 
      System.out.println();
   }   
   
}