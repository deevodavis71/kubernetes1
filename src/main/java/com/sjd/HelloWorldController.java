package com.sjd;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/api")
public class HelloWorldController
{
    private final Logger logger = LoggerFactory.getLogger (this.getClass());
    
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
    public List<DiscoveredService> getServices (@PathVariable (name = "name") String name)
    {
        logger.info ("CALLED THE GET SERVICES!!!");

        List<DiscoveredService> ret = new ArrayList<DiscoveredService> ();

        // Get the list of services
        List<String> retServices = discoveryClient.getServices ();
 
        // Iterate around the services returned
        for (String s : retServices)
        {     
            // Set the uri's
            List<ServiceInstance> instances = discoveryClient.getInstances (s);
            logger.debug ("Count for : " + s + " is : " + instances.size ());

            for (ServiceInstance si : instances)
            {
                // Add it
                DiscoveredService ds = new DiscoveredService ();
                ds.setServiceName (s);
                ds.setUri (si.getUri ().toString());
                ret.add (ds);
            }
        }

       if (ret.size() == 0)
        {
            // Just return back the implementation class for debug purposes
            DiscoveredService debug = new DiscoveredService ();
            debug.setServiceName ("" + discoveryClient);
            debug.setUri ("debugmode");
    
            ret.add(debug);

            for (String s : retServices)
            {     
                debug = new DiscoveredService ();
                debug.setServiceName (s);
                debug.setUri ("debugmode");
    
                ret.add(debug);
            }

            // Add the local service instance
            if (discoveryClient.getLocalServiceInstance () != null)
            {
                debug = new DiscoveredService ();
                debug.setServiceName (discoveryClient.getLocalServiceInstance().getServiceId());
                debug.setUri (discoveryClient.getLocalServiceInstance().getUri().toString());
                
                ret.add (debug);
            }

            // Just return
            return ret;
        }

        return ret;
    }
}