/**
 Dictionary Class
 @author Ryan Norton
 @author Brian Gao
 @version 1.0 2016-03-05
*/ 


package assignment4;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

// do not change class name or interface it implements
public class Dictionary
{
   protected ArrayList<String> dictionary;
   
   /**
    * Create a Dictionary item from a file path.
    * @param filename The path of the file.
    */
   public Dictionary(String filename)
   {
      createLocalDictionary(filename);
   }
   
   /**
    * Create a dictionary from an ArrayList.
    * @param dict the dict to set.
    */
   public Dictionary(ArrayList<String> dict)
   {
      this.dictionary = dict;
   }
   
   /**
    * Searches dictionary for given word.
    * @param word The word to search for
    * @return true if found else false
    */
   public Boolean search(String word)
   {
      return this.dictionary.contains(word);
   }
   
   /**
    * Initializes a dictionary from a file into a Java ArrayList
    * @param filename The path of the file containing the dictionary
    */
   private void createLocalDictionary(String filename) 
   { 
      ArrayList<String> localDictionary = new ArrayList<String>(); 
      try 
      {
         FileReader freader = new FileReader(filename);
         BufferedReader reader = new BufferedReader(freader);
         
         int wordCount = 0;
         for (String s = reader.readLine(); s != null; s = reader.readLine()) 
         {
            if(s.charAt(0) == '*'){
               continue;
            }
            else{
               localDictionary.add(s.substring(0, 5));
               wordCount++;
            }
         }
         System.out.println(wordCount);
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
     this.dictionary = localDictionary;
   }
}