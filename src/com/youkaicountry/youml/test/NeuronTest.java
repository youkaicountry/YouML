package com.youkaicountry.youml.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.youkaicountry.youml.module.BiasUnit;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.connection.Connection;
import com.youkaicountry.youml.module.connection.FullConnection;
import com.youkaicountry.youml.module.layer.LinearLayer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;
import com.youkaicountry.youml.netgraph.NetGraph;

public class NeuronTest
{
    
    private double sigmoid(double z)
    {
        return 1.0/(1.0+Math.exp(-z));
    }
    
    @Test
    public void test_sigmoid_layer()
    {
        /*
         * Test the sigmoid layer object
         */
        double[] cases_in = {1.0, 2.2, 3.4, 6.0};
        double[] cases_out = new double[cases_in.length];
        for (int i = 0; i < cases_out.length; i++)
        {
            cases_out[i] = sigmoid(cases_in[i]);
        }
        
        SigmoidLayer s = new SigmoidLayer("sigmoid", cases_in.length);
        s.activate(cases_in);
        
        for (int i = 0; i < s.output_buffer.length; i++)
        {
            assertEquals(cases_out[i], s.output_buffer[i], .000001);
        }
        //fail("Not yet implemented");
        return;
    }

}
