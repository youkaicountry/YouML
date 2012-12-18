/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.youkaicountry.youml.parameter.MultiCaseVector;
import com.youkaicountry.youml.parameter.NullVector;
import com.youkaicountry.youml.parameter.ParameterVector;
import com.youkaicountry.youml.parameter.SingleVector;

public class ParameterTest
{
    
    @Test
    public void test_single_vector()
    {
        int size = 5;
        SingleVector pv = new SingleVector(size, true, null);
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
    
    @Test 
    public void test_multi_case_vector()
    {
        SingleVector pv0 = new SingleVector(3, true, null);
        SingleVector pv1 = new SingleVector(1, true, null);
        SingleVector pv2 = new SingleVector(8, true, null);
        MultiCaseVector pv = new MultiCaseVector(new ParameterVector[] {pv0, pv1, pv2}, null);
        baseTest(pv, 12);
        testRW(pv, 8765799);
        //do a simple check that setting an index on the multicase is setting
        //the proper vector, in this case 5 goes to pv2[1]
        pv.setParam(5, 10.0);
        assertEquals(10.0, pv2.getParam(1), 0);
        return;
    }
    
    //does basic tests
    private void baseTest(ParameterVector pv, int expected_size)
    {
        assertEquals(expected_size, pv.size());
        for (int i = 0; i < pv.size(); i++)
        {
            assertEquals(0, pv.getParam(i), 0);
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
