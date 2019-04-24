package com.chejconsultor.chej_androidnativemyfirstapp;

public class Response {

    public boolean IsSucced;

    public String Message;

    public Object Result;

    public Response(
            boolean _isSucced,
            String _message,
            Object _result)
    {
        this.IsSucced = _isSucced;
        this.Message = _message;
        this.Result = _result;
    }
}
