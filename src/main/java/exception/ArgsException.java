package exception;

public class ArgsException extends RuntimeException
{
    public ArgsException(){ }
    public ArgsException(String msg)
    {
        super(msg);
    }
}
