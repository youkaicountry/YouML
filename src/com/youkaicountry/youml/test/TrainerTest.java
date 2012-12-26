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

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.youkaicountry.youml.data.TrainingBatch;
import com.youkaicountry.youml.module.BiasUnit;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.connection.Connection;
import com.youkaicountry.youml.module.connection.FullConnection;
import com.youkaicountry.youml.module.layer.LinearLayer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;
import com.youkaicountry.youml.module.network.FeedForwardNetwork;
import com.youkaicountry.youml.trainers.BackPropTrainer;

public class TrainerTest 
{
	@Test
    public void test_back_prop()
    {
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        SigmoidLayer out0 = new SigmoidLayer("out0", 2);
        Connection c0 = new FullConnection("c0", inp0, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {c0};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn", inputs, hidden, outputs);
        n.setParam(0, .2);
        n.setParam(1, .1);
        n.setParam(2, .02);
        n.setParam(3, .3);
        TrainingBatch td = new TrainingBatch();
        td.addCase(new double[] {1.0, 1.0}, new double[] {sigmoid(2.0), sigmoid(2.0)});
        BackPropTrainer bpt = new BackPropTrainer(n, .01, 1.0, 0.0);
        testErrorDecreasing(bpt, td, 100);
        return;
    }
    
    @Test
    public void test_backprop_xor()
    {
        //construct a simple 2 hidden layer feed-forward network
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        BiasUnit bhid0 = new BiasUnit("bhid0", 1.0);
        SigmoidLayer hid0 = new SigmoidLayer("hid0", 2);
        Connection c0 = new FullConnection("c0", inp0, hid0);
        Connection c1 = new FullConnection("c1", bhid0, hid0);
        BiasUnit bout0 = new BiasUnit("bout0", 1.0);
        SigmoidLayer out0 = new SigmoidLayer("out0", 1);
        Connection c4 = new FullConnection("c4", bout0, out0);
        Connection c5 = new FullConnection("c5", hid0, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {hid0, c1, bout0, c0, c5, bhid0, c4};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn", inputs, hidden, outputs);
        Random r = new Random(1217);
        for (int i = 0; i < n.size(); i++)
        {
            n.setParam(i, r.nextDouble()*.5-.25);
        }
        
        TrainingBatch td = new TrainingBatch();
        td.addCase(new double[] {1.0, 1.0}, new double[] {0.0});
        td.addCase(new double[] {1.0, 0.0}, new double[] {1.0});
        td.addCase(new double[] {0.0, 1.0}, new double[] {1.0});
        td.addCase(new double[] {0.0, 0.0}, new double[] {0.0});
        BackPropTrainer bpt = new BackPropTrainer(n, .1, 1.0, 0.0);
        testErrorDecreasing(bpt, td, 100);
        return;
    }
    
    private double sigmoid(double z)
    {
        return 1.0/(1.0+Math.exp(-z));
    }
    
    private void testErrorDecreasing(BackPropTrainer bpt, TrainingBatch tb, int iterations)
    {
    	double ierror = Math.abs(bpt.trainBatch(tb)); //get the error on the training set
        for (int i = 0; i < iterations; i++)
        {
        	bpt.trainBatch(tb);
        }
        double oerror = Math.abs(bpt.trainBatch(tb));
        assertTrue(oerror < ierror);
    	return;
    }
}
