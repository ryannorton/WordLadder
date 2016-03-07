/**
 Word Ladder Class
 @author Ryan Norton
 @author Brian Gao
 @version 1.0 2016-03-05
*/ 


package assignment4;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
   private Dictionary dictionary;
   private List<String> solutionList = new ArrayList<String>();
   private List<String> checkedList = new ArrayList<String>();

   /**
    * Initializes a WordLadderSolver with a dictionary
    * @param dictionary The dictionary to check words against
    */
   public WordLadderSolver(Dictionary dictionary)
   {
      this.dictionary = dictionary;
   }

   /**
    * Computes a word ladder.
    * @param startWord to start of the ladder
    * @param endWord the end of the ladder
    * @return the word ladder
    */
   @Override
   public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
   {
      solutionList.clear();
      checkedList.clear();
      makeLadder(startWord, endWord, -1);
      if (solutionList.size() == 0)
      {
         throw new NoSuchLadderException("No ladder exists for " + startWord + " and " + endWord);
      }
      return solutionList;
   }

   /**
    * Validates a proposed word ladder
    * @param startWord the start of the ladder
    * @param endWord the end of the ladder
    * @wordLadder the ladder to check
    * @return true of the ladder is valid else false
    */
   @Override
   public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
   {
      for (int i = 0; i < wordLadder.size() - 1; i++)
      {
         if (!this.isNeighbor(wordLadder.get(i), wordLadder.get(i+1))) return false;
      }
      return true;
   }

   /**
    * Computes a word ladder
    * @param fromWord the start of the ladder
    * @param toWord the end of the ladder
    * @param position the index of the last changed letter
    * @return true of a word ladder was successfully created else false
    */
   private Boolean makeLadder(String fromWord, String toWord, int position)
   {

      solutionList.add(fromWord);
      checkedList.add(fromWord);

      // base case
      if (fromWord.equals(toWord)) {
         return true;
      }
      
      ArrayList<String> wordsToCheck = new ArrayList();
      for (int i = 0; i <= 4; i++)
      {
         if (i == position) continue;
         for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            String newFromWord = fromWord.substring(0, i) + alphabet + fromWord.substring(i + 1);
            if (!this.dictionary.search(newFromWord)) continue;
            if (checkedList.contains(newFromWord)) continue;
            wordsToCheck.add(Integer.toString(this.hammingDistance(newFromWord, toWord)) + newFromWord);
         }
      }
      
      Collections.sort(wordsToCheck, String.CASE_INSENSITIVE_ORDER);
      
      for (String word: wordsToCheck) {
         String cleanWord = word.substring(1);
         int changedIndex = indexChanged(cleanWord, fromWord);
         if (!makeLadder(cleanWord, toWord, changedIndex)) solutionList.remove(cleanWord);
         else return true;
      }

      solutionList.remove(fromWord);
      return false;
      
   }
   
   /**
    * Determines if a word differs by another by at most 1 letter
    * @param startWord the first word to compare
    * @param endWord the word to compare to
    * @return true of the words are neighbors
    */
   private Boolean isNeighbor(String startWord, String endWord)
   {
      int differ = 0;
      for (int i = 0; i < startWord.length(); i++)
      {
         if (startWord.charAt(i) != endWord.charAt(i)) differ++;
         if (differ > 1) return false;
      }
      return true;
   }
   
   /**
    * Computes the number of letter differences between two words
    * @param startWord the first word to compare
    * @param endWord the word to compare to
    * @return the number of letter differences
    */
   protected int hammingDistance(String startWord, String endWord)
   {
      int count = 0;
      for (int i = 0; i < 5; i++)
      {
         if (startWord.charAt(i) != endWord.charAt(i)) count++;
      }
      return count;
   }
   
   /**
    * Determines the index of the different letter between two words that are one letter apart
    * @param startWord the first word to compare
    * @param endWord the word to compare to
    * @return the index of the different letter
    */
   private int indexChanged(String startWord, String endWord)
   {
      for (int i = 0; i < 5; i++)
      {
         if (startWord.charAt(i) != endWord.charAt(i)) return i;
      }
      return -1;
   }
}
