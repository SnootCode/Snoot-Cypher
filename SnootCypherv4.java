/*
Author: Andrey Risukhin
Purpose: Encode and Decode secret messages for me and Chloe!
Psuedocode: 
 - Encode:
   > Take input from console, .txt message
   > Encode, save to .txt file
 - Decode:
   > Take .txt file and console input
   > Output .txt message
Conditions:
 - Message does not begin with spaces/enters (lost in encoding)
Notes:
- Turn into .exe we can run, without going through jGrasp.
- It's a class, but what about main method?
Maintenance Log:
12/12/19
- Started! Using my Modified Vigenere Cypher (oo)
12/13/19
- Wrote few helper methods, need to make sure each method is under 20 lines!
- Initializing array in a bad way. Hard coded, long and unwieldy. Will make nice
      later. 
12/16/19
- Writing as a main, not a class.
12/17/19
- Changed code, starting reading from code tbale file right away.
- Must declare a method might throw a FileNotFoundException.
- Capitalization issue. 
- Need to include spaces, convert between underscore and space. 
- Encoding seems to work! Breakfast/Lunch time, then decoding :D
- Maybe include the encoder in the file?
   > An encoder that can decode itself?
   > Or usual conventions (day sent, etc)
- The decoder works! It's a lot of redundant code but it works!
- Reducing redundancy in v2.
- Redundancy reduced somewhat, UI improved. 
- Changed getFile to return nextLine() instead of next(), for encoders with spaces
- This caused some other changes.
- Discovered an error in code! Worked accidentally: table is symmetric, but should 
         not have worked otherwise. Patched in encode, now testing decode.
- Decode ignores leading spaces. Deciding that the code file will use underscores.
- Decode works correctly now with leading spaces. v2 is complete, time for v3!
- Reducing redundancy further, with character generation methods for encode/decode. 
- _ replaced with space, but only if decoding (original file had spaces, not _).
- Works with some reduction, but we can do better. Version 4!
- Redundancy reduced even further, and still works with spaces!
- Some methods are quite long, thinking about reducing.
- Reduced some methods. Four scanner objects for each letter = inefficient.
- Eliminated Scanner 'second' by remembering the full row. 
- Improved readability with better variable names.
*/

import java.util.*;
import java.io.*;

public class SnootCypherv4 {

   // Main
   public static void main(String args[]) throws FileNotFoundException {
      String title = "SnootCypher";
      String version = "4.0";
      Scanner console = new Scanner(System.in);
            
      welcome(title, version);
      String encodeOrDecode = promptOperation(console);
      String encoder = getFile(console, "encoder"); // Not a file, just a String
      File codeTable = new File(getFile(console, "codeTable"));
      System.out.println("What's your input file?");
      File inFile = new File(getFile(console, encodeOrDecode));
      System.out.println("What's your output file?");
      File outFile = new File(getFile(console, encodeOrDecode));
      Scanner input = new Scanner(inFile); 
      PrintStream output = new PrintStream(outFile);
      encodeOrDecode(input, encoder, codeTable, output, encodeOrDecode);
        
      System.out.println("Have a nice day :)");
   }
   
   // Pre: Passed Scanner, String, File, PrintStream, String.
   // Post: Adds characters to PrintStream, encoded/decoded based on purpose.
   public static void encodeOrDecode(Scanner input, String encoder, File codeTable, 
         PrintStream output, String purpose) throws FileNotFoundException {
      int encoderIndex = 0;
      while (input.hasNext()) {
         String word = input.next();
         if (input.hasNext()) {
            word += " "; // Adds the space the scanner will skip
         }
         System.out.println("+++++++++++++++++++++++++++++++++++++");
         System.out.println("Operating word: " + word);
         for (int i = 0; i < word.length(); i++) {
            String inputChar = makeChar(word, i);
            if (encoderIndex >= encoder.length()) {
               encoderIndex -= encoder.length();
            }  
            String encodeChar = makeChar(encoder, encoderIndex);
            String resultChar = getResultingChar(encodeChar, inputChar, codeTable, purpose);
            System.out.println("\t(Input, Encoding, Result): " + inputChar + ", " + encodeChar + ", " + resultChar);
            if (purpose.equals("decoding") && resultChar.equals("_")) {
               resultChar = " "; // Reassigns space to underscore if decoding
            }
            output.print(resultChar);
            encoderIndex++;
         }
         System.out.println("+++++++++++++++++++++++++++++++++++++\n");
      }
   }
   
   // Pre: Passed a String at least as long as passed index
   // Post: Returns uppercase character at that index ("_" if " ")
   public static String makeChar(String s1, int index) {
      String s2 =  "" + s1.charAt(index);
      s2 = s2.toUpperCase();
      if (s2.equals(" ")) {
         s2 = "_";
      }
      return s2;
   }
   
   // Pre: Passed String, String, File, String.
   // Post: Returns String.
   // Gets the third char (c) of the codeTable:
   //    If decoding: row (a), intersection (b), column (c) 
   //    If encoding: row (a), column (b), intersection (c)
   public static String getResultingChar(String a, String b, File codeTable, 
         String purpose) throws FileNotFoundException {
      String c = "";
      Scanner codeRow = new Scanner(codeTable);
      String currentRowFull = codeRow.nextLine(); // Save full row
      while(!("" + currentRowFull.charAt(0)).equals(a)) { // Match row signatures
         currentRowFull = codeRow.nextLine();
      }
      String currentRowSignature = "" + currentRowFull.charAt(0); // Save row signature
      Scanner codeColumn = new Scanner(codeTable);
      String column = codeColumn.next(); // Save column signature
      Scanner codeIntersect = new Scanner(currentRowFull); 
      String intersect = codeIntersect.next(); // Save intersection point
      if (purpose.equals("decoding")) { // Row, intersection match => column found  
         while(!b.equals(intersect)) {
            column = codeColumn.next();
            intersect = codeIntersect.next(); 
         }     
         System.out.println("\tIntersection, Row: " + intersect + ", " + currentRowSignature);
         c = column; 
      } else if (purpose.equals("encoding")) { // Row, column match => intersection found
         while(!b.equals(column)) {
            column = codeColumn.next(); 
            intersect = codeIntersect.next(); 
         }
         System.out.println("\tColumn, Row: " + column + ", " + currentRowSignature);
         c = intersect;
      }
      return c;
   }
   
   // Pre: Given Scanner to System.in.
   // Post: Returns a String. 
   public static String promptOperation(Scanner console) {
      System.out.print("Are you encoding or decoding? ");
      String response = console.nextLine();
      while (!(response.equals("encoding") || response.equals("decoding"))) {
         System.out.print("That wasn't recognized. Are you encoding or decoding? ");
         response = console.nextLine();
      } 
      return response; // Either "encoding" or "decoding"
   }
   
   // Pre: Given Scanner to System.in, String.
   // Post: Returns a String.
   public static String getFile(Scanner console, String purpose) {
      if (purpose.equals("encoding")) {
         System.out.println("\tYour input should be the message, and output" 
               + "should be the encoded message.");
         System.out.print("\tEnter the file name: ");
      } else if (purpose.equals("decoding")) {
         System.out.println("\tYour input should be the encoded message, and" 
               + "output should be the decoded message.");
         System.out.print("\tEnter the file name: ");
      } else if (purpose.equals("encoder")) {
         System.out.print("What Encoder are you using? ");
      } else if (purpose.equals("codeTable")) {
         System.out.print("What Code Table are you using? ");
      } else {
         System.out.println("Purpose seen wasn't one of the cases. Taking" 
               + "input. ");
      }
      return console.nextLine();
   }
      
   // Pre: Passed String, String. 
   // Post: Prints to console.
   // Welcomes the user, showing name and version of program. 
   public static void welcome(String title, String version) {
      System.out.println("Welcome to " + title + ", version " + version + "!");
   }
}