/**
 Word Ladder Driver Class
 @author Ryan Norton
 @author Brian Gao
 @version 1.0 2016-03-05
*/ 


package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Assign4Driver
{
   /**
    * Solves word ladders from a given input and dictionary file.
    * @param args
    */
    public static void main(String[] args)
    {
        // Create a word ladder solver object
       Dictionary dictionary = new Dictionary(args[0]);
       Assignment4Interface wordLadderSolver = new WordLadderSolver(dictionary);
        
       try 
       {
          FileReader freader = new FileReader(args[1]);
          BufferedReader reader = new BufferedReader(freader);

          for (String s = reader.readLine(); s != null; s = reader.readLine()) 
          {
             String[] input = s.split("\\s+");
             String startWord = input[0];
             String endWord = input[1];
              
             System.out.print("For input words \"" + startWord + "\" and \"" + endWord + "\"");
              
             if (!validateInput(startWord, endWord, dictionary))
             {
                System.out.println("\nAt least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary\n");
                continue;
             }

             try 
             {
                List<String> result = wordLadderSolver.computeLadder(startWord, endWord);
                boolean correct = wordLadderSolver.validateResult(startWord, endWord, result);
                System.out.print(" the following word ladder was found\n\t");
                for (String word: result)
                {
                   System.out.print(word + " ");
                }
                if (result.size() == 1)
                {
                   System.out.print(startWord);
                }
                System.out.println("\n\n*********\n");
             }
             catch (NoSuchLadderException e) 
             {
                System.out.println("\nThere is no word ladder between " + startWord + " and " + endWord + "!\n");
                e.printStackTrace();
                System.out.println("\n*********\n");
             }
          }
       } 
       catch (FileNotFoundException e) 
       {
          System.err.println ("Error: File not found. Exiting...");
          e.printStackTrace();
          System.exit(-1);
       } catch (IOException e) 
       {
          System.err.println ("Error: IO exception. Exiting...");
          e.printStackTrace();
          System.exit(-1);
       }
    }
    
    /**
     * Validate a word ladder input
     * @param s1 the first word to validate
     * @param s2 the end word to validate
     * @param dictionary the dictionary from which to validate
     * @return true of both words are valid
     */
    private static Boolean validateInput(String s1, String s2, Dictionary dictionary)
    {
       if (s1.length() != 5 || s2.length() != 5) return false;
       if (!dictionary.search(s1) || !dictionary.search(s2)) return false;
       return true;
    }
}
