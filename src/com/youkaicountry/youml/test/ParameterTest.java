package com.youkaicountry.youml.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.youkaicountry.youml.parameter.NullVector;
import com.youkaicountry.youml.parameter.ParameterVector;
import com.youkaicountry.youml.parameter.SingleVector;

public class ParameterTest
{
    
    @Test
    public void test_single_vector()
    {
        int size = 5;
        SingleVector pv = new SingleVector(size, null);
        baseTest(pv, size);
        testRW(pv, 121212);
        return;
    }
    
    @Test
    public void test_null_vector()
    {
        NullVector pv = new NullVector(null);
        baseTest(pv, 0);
        return;
    }
    
    //does basic tests
    private void baseTest(ParameterVector pv, int expected_size)
    {
        
        assertEquals(pv.size(), expected_size);
        for (int i = 0; i < pv.size(); i++)
        {
            assertEquals(pv.getParam(i), 0, 0);
        }
    }
    
    private void testRW(ParameterVector pv, long seed)
    {
        Random r = new Random(seed);
        double[] vals = new double[pv.size()];
        for (int i = 0; i < pv.size(); i++)
        {
            vals[i] = r.nextDouble();
            pv.setParam(i, vals[i]);
        }
        for (int i = 0; i < pv.size(); i++)
        {
            assertEquals(pv.getParam(i), vals[i], 0);
        }
    }

}
