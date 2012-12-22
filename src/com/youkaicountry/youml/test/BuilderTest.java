package com.youkaicountry.youml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.youkaicountry.youml.builder.HiddenLayerNetworkBuilder;
import com.youkaicountry.youml.data.TrainingBatch;
import com.youkaicountry.youml.module.BiasUnit;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.connection.Connection;
import com.youkaicountry.youml.module.connection.FullConnection;
import com.youkaicountry.youml.module.layer.LinearLayer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;
import com.youkaicountry.youml.module.layer.ThresholdLayer;
import com.youkaicountry.youml.module.network.FeedForwardNetwork;
import com.youkaicountry.youml.trainers.BackPropTrainer;

public class BuilderTest
{
    @Test
    public void test_ff_builder()
    {
        HiddenLayerNetworkBuilder builder = new HiddenLayerNetworkBuilder(2, 2);
        FeedForwardNetwork n = (FeedForwardNetwork)builder.build("ffn");
        n.setParam(0, .2);
        n.setParam(1, .1);
        n.setParam(2, .02);
        n.setParam(3, .3);
        n.setParam(4, .25);
        n.setParam(5, .11);
        TrainingBatch td = new TrainingBatch();
        td.addCase(new double[] {1.0, 1.0}, new double[] {sigmoid(2.0), sigmoid(2.0)});
        BackPropTrainer bpt = new BackPropTrainer(n, .01);
        testErrorDecreasing(bpt, td, 100);
        return;
    }
    
    @Test
    public void test_backprop_xor()
    {
        HiddenLayerNetworkBuilder builder = new HiddenLayerNetworkBuilder(2, 2, 1);
        FeedForwardNetwork n = (FeedForwardNetwork)builder.build("ffn");
        Random r = new Random(1213);
        for (int i = 0; i < n.size(); i++)
        {
            n.setParam(i, r.nextDouble()*.5-.25);
        }
        
        TrainingBatch td = new TrainingBatch();
        td.addCase(new double[] {1.0, 1.0}, new double[] {0.0});
        td.addCase(new double[] {1.0, 0.0}, new double[] {1.0});
        td.addCase(new double[] {0.0, 1.0}, new double[] {1.0});
        td.addCase(new double[] {0.0, 0.0}, new double[] {0.0});
        BackPropTrainer bpt = new BackPropTrainer(n, .1);
        testErrorDecreasing(bpt, td, 1000);
        return;
    }
    
    //A test case looks like:
    //param0, param1, ..., inp0, inp1, ..., out0, out1, ...
    private void testCase(double[] test_case, Module mod, double delta_error)
    {
        mod.clearBuffers();
        int next_offset = mod.size();
        for (int i = 0; i < next_offset; i++)
        {
            mod.setParam(i, test_case[i]);
        }
        int offset = next_offset;
        next_offset = mod.input_dim;
        double[] inp = new double[next_offset];
        for (int i = 0; i < next_offset; i++)
        {
            inp[i] = test_case[i+offset];
        }
        offset += next_offset;
        next_offset = mod.output_dim;
        mod.activate(inp); //actually run it now.
        for (int i = 0; i < next_offset; i++)
        {
            assertEquals(test_case[i+offset], mod.output_buffer[i], delta_error);
        }
        return;
    }
    
    private void testErrorDecreasing(BackPropTrainer bpt, TrainingBatch tb, int iterations)
    {
        double ierror = Math.abs(bpt.trainBatch(tb)); //get the error on the training set
        for (int i = 0; i < iterations; i++)
        {
            bpt.trainBatch(tb);
        }
        double oerror = Math.abs(bpt.trainBatch(tb));
        System.out.println(oerror);
        return;
    }
    
    private double sigmoid(double z)
    {
        return 1.0/(1.0+Math.exp(-z));
    }
}
