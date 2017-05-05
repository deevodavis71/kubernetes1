package com.sjd;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/api")
public class HelloWorldController
{
    @Autowired
    private DiscoveryClient discoveryClient;   

    @RequestMapping (path = "/sayHello/{name}", method = RequestMethod.GET)
    public Message sayHello (@PathVariable (name = "name") String name)
    {
        try
        {
            Message m = new Message ();
            m.setMessage ("Hello to " + name + " from " + InetAddress.getLocalHost().getHostAddress());
            m.setCounter(m.getCounter() + 1);
            return m;
        }
        catch (Exception e)
        {
            System.out.println (e);
        }

        return null;
    }

    @RequestMapping (path = "/getServices/{name}", method = RequestMethod.GET)
    public List<String> getServices (@PathVariable (name = "name") String name)
    {
        // Get the list of services
        List<String> ret = discoveryClient.getServices ();
        if (ret == null || ret.size() == 0)
        {
            // Just return back the implementation class for debug purposes
            ret = new ArrayList<String> ();
            ret.add(0, "" + discoveryClient);
        }

        return ret;
    }
}