package com.youkaicountry.youml.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.youkaicountry.youml.module.layer.Layer;
import com.youkaicountry.youml.module.layer.LinearLayer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;
import com.youkaicountry.youml.module.layer.loader.LayerLoader;
import com.youkaicountry.youml.module.layer.loader.LayerTypes;

public class NeuronTest
{
    
    private double sigmoid(double z)
    {
        return 1.0/(1.0+Math.exp(-z));
    }
    
    @Test
    public void test_linear()
    {
        double[] cases_in = {1.0, 2.2, 3.4, 6.0};
        double[] cases_out = new double[cases_in.length];
        for (int i = 0; i < cases_out.length; i++)
        {
            cases_out[i] = cases_in[i];
        }
        LinearLayer l = new LinearLayer("linear", cases_in.length);
        l.activate(cases_in);
        
        for (int i = 0; i < l.output_buffer.length; i++)
        {
            assertEquals(l.output_buffer[i], cases_out[i], 0.0);
        }
        return;
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
            assertEquals(s.output_buffer[i], cases_out[i], .000001);
        }
        //fail("Not yet implemented");
        return;
    }
    
    @Test
    public void test_reflection_loader()
    {
        String uname = "dengus";
        int uneurons = 3;
        LayerLoader ll = new LayerLoader();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("name", uname);
        params.put("neurons", uneurons);
        for (Class<?> c : LayerTypes.standard_layers)
        {
            Layer l = ll.load(c.getSimpleName(), params);
            assertEquals(uneurons, l.input_dim);
            assertEquals(uneurons, l.output_dim);
            assertEquals(uname, l.name);
        }
        return;
    }

}
