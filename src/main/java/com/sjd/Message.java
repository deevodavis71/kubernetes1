package com.sjd;

public class Message
{
    private String message;
    static private int counter;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public int getCounter ()
    {
        return counter;
    }

    public void setCounter (int counter)
    {
        this.counter = counter;
    }
}