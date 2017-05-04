package com.sjd;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/api")
public class HelloWorldController
{
    @RequestMapping (path = "/sayHello/{name}", method = RequestMethod.GET)
    public Message sayHello (@PathVariable (name = "name") String name)
    {
        Message m = new Message ();
        m.setMessage("Hello to " + name);
        return m;
    }
}