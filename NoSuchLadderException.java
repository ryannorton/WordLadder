/**
 Exception Classes for invalid word ladders.
 @author Ryan Norton
 @author Brian Gao
 @version 1.0 2016-03-05
*/ 


package assignment4;

public class NoSuchLadderException extends Exception
{
    private static final long serialVersionUID = 1L;

    public NoSuchLadderException(String message)
    {
        super(message);
    }

    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
