package assignment4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class WordLadderSolverTest
{
   WordLadderSolver solver;

   @Before
   public void setUp() throws Exception
   {
      ArrayList<String> dict = new ArrayList<String>();
      dict.add("abcde");
      dict.add("abfde");
      dict.add("abfdf");
      dict.add("bbfdf");
      dict.add("aaaaa");
      Dictionary dictionary = new Dictionary(dict);
      this.solver = new WordLadderSolver(dictionary);
   }

   @Test
   public void testComputeLadder()
   {
      try
      {
         List<String> solution = this.solver.computeLadder("abcde", "bbfdf");
         assert solution.size() == 4;
      }
      catch (NoSuchLadderException e)
      {
         fail("Ladder should be found between abcde and bbfdf but was not.");
      }
   }
   
   @Test
   public void testThrowsExceptoinForNoLadder()
   {
      try
      {
         // should throw exception
         List<String> solution = this.solver.computeLadder("abcde", "aaaaa");
         fail("Exception should have been thrown for invalid ladder");
      }
      catch (NoSuchLadderException e)
      {
         
      }
   }
   
   @Test
   public void testHammingDistance()
   {
      assert this.solver.hammingDistance("aaaaa", "abcde") == 4;
   }

}
