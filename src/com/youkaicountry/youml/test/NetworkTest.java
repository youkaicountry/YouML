package com.youkaicountry.youml.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.youkaicountry.youml.module.BiasUnit;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.connection.Connection;
import com.youkaicountry.youml.module.connection.FullConnection;
import com.youkaicountry.youml.module.connection.IdentityConnection;
import com.youkaicountry.youml.module.layer.LinearLayer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;
import com.youkaicountry.youml.module.layer.ThresholdLayer;
import com.youkaicountry.youml.module.network.FeedForwardNetwork;
import com.youkaicountry.youml.module.network.Network;
import com.youkaicountry.youml.netgraph.NetGraph;

public class NetworkTest
{

    @Test
    public void test_graph()
    {
        // o\ /o\
        //   o   o
        // o/ \o/
        SigmoidLayer in0 = new SigmoidLayer("in0", 2);
        SigmoidLayer in1 = new SigmoidLayer("in1", 2);
        SigmoidLayer hid0 = new SigmoidLayer("hid0", 2);
        SigmoidLayer hid1 = new SigmoidLayer("hid1", 2);
        SigmoidLayer hid2 = new SigmoidLayer("hid2", 2);
        SigmoidLayer out0 = new SigmoidLayer("out0", 2);
        Connection c0 = new FullConnection("c0", in0, hid0);
        Connection c1 = new FullConnection("c1", in1, hid0);
        Connection c2 = new FullConnection("c2", hid0, hid1);
        Connection c3 = new FullConnection("c3", hid0, hid2);
        Connection c4 = new FullConnection("c4", hid1, out0);
        Connection c5 = new FullConnection("c5", hid2, out0);
        Module[] inputs = new Module[] {in0, in1};
        Module[] hidden = new Module[] {hid0, c1, c5, hid2, c0, c2, hid1, c4, c3};
        Module[] outputs = new Module[] {out0};
        
        NetGraph ng = new NetGraph(inputs, hidden, outputs);
        String a = ng.sorted_modules[0].name;
        String b = ng.sorted_modules[1].name;
        if (a == "c0") 
        {
            assertTrue(b == "c1");
        }
        else
        {
            assertTrue(b == "c0");
        }
        assertTrue(ng.sorted_modules[2].name == "hid0");
        a = ng.sorted_modules[3].name;
        b = ng.sorted_modules[4].name;
        if (a == "c2") 
        {
            assertTrue(b == "c3");
        }
        else
        {
            assertTrue(b == "c2");
        }
        a = ng.sorted_modules[5].name;
        b = ng.sorted_modules[6].name;
        if (a == "hid1") 
        {
            assertTrue(b == "hid2");
        }
        else
        {
            assertTrue(b == "hid1");
        }
        a = ng.sorted_modules[7].name;
        b = ng.sorted_modules[8].name;
        if (a == "c4") 
        {
            assertTrue(b == "c5");
        }
        else
        {
            assertTrue(b == "c4");
        }
        return;
    }
    
    @Test
    public void test_feed_forward_network()
    {
        //construct a simple 2 hidden layer feed-forward network
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        BiasUnit bhid0 = new BiasUnit("bhid0", 1.0);
        SigmoidLayer hid0 = new SigmoidLayer("hid0", 8);
        Connection c0 = new FullConnection("c0", inp0, hid0);
        Connection c1 = new FullConnection("c1", bhid0, hid0);
        BiasUnit bhid1 = new BiasUnit("bhid1", 1.0);
        SigmoidLayer hid1 = new SigmoidLayer("hid1", 8);
        Connection c2 = new FullConnection("c2", hid0, hid1);
        Connection c3 = new FullConnection("c3", bhid1, hid1);
        BiasUnit bout0 = new BiasUnit("bout0", 1.0);
        SigmoidLayer out0 = new SigmoidLayer("out0", 3);
        Connection c4 = new FullConnection("c4", bout0, out0);
        Connection c5 = new FullConnection("c5", hid1, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {hid0, c1, bhid1, bout0, c0, c5, bhid0, c2, hid1, c4, c3};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn", inputs, hidden, outputs);
        //There should be 123 parameters in this network:
        //8 for bias 0, 8 for bias 1, 3 for bias 2
        //16, 64, 24 for the connections
        assertEquals(123, n.size());
        for (int i = 0; i < n.size(); i++)
        {
            n.setParam(i, 1.0);
        }
        n.clearBuffers();
        n.activate(new double[] {1.5, 1.0});
        
        return;
    }
    
    @Test
    public void test_perceptron()
    {
        Module n0 = makeSigmoidPerceptron();
        //First small tests case should is all weights 1, all inputs 1
        double[] tc0 = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, sigmoid(2.0), sigmoid(2.0)};
        testCase(tc0, n0, 0.0);
        double[] tc1 = new double[] {.1, .2, .3, .4, .8, .6, sigmoid(.8*.1+.6*.3), sigmoid(.8*.2+.6*.4)};
        testCase(tc1, n0, 0.0);
        //Now add a bias unit and create a new network
        Module n1 = makeBiasedSigmoidPerceptron();
        double[] tc2 = new double[] {.1, .2, .3, .4, .5, .7, .8, .6, sigmoid(.8*.1+.6*.3+1*.5), sigmoid(.8*.2+.6*.4+1.0*.7)};
        testCase(tc2, n1, 0.0);
        return;
    }
    
    @Test
    public void test_multi_network()
    {
        //Just make a perceptron encased, and call it.
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        Module out0 = makeBiasedSigmoidPerceptron();
        Connection c0 = new IdentityConnection("c0", inp0, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {c0};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn2", inputs, hidden, outputs);
        double[] tc = new double[] {1.0, 1.0, 1.0, 1.0, .1, .2, .3, .4, .5, .7, .8, .6, sigmoid(.8*.1+.6*.3+1*.5), sigmoid(.8*.2+.6*.4+1.0*.7)};
        testCase(tc, n, 0.0);
    }
    
    @Test
    public void test_xor()
    {
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        BiasUnit b0 = new BiasUnit("bias0", 1.0);
        BiasUnit b1 = new BiasUnit("bias1", 1.0);
        BiasUnit b2 = new BiasUnit("bias2", 1.0);
        ThresholdLayer t0 = new ThresholdLayer("t0", 1, 0.0);
        ThresholdLayer t1 = new ThresholdLayer("t1", 1, 0.0);
        ThresholdLayer out0 = new ThresholdLayer("out0", 1, 0.0);
        Connection c0 = new FullConnection("c0", inp0, t0);
        Connection c1 = new FullConnection("c1", inp0, t1);
        Connection c2 = new FullConnection("c2", t0, out0);
        Connection c3 = new FullConnection("c3", t1, out0);
        Connection c4 = new FullConnection("c4", b0, t0);
        Connection c5 = new FullConnection("c5", b1, t1);
        Connection c6 = new FullConnection("c6", b2, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {c0, c1, c2, c3, c4, c5, c6, b0, b1, b2, t0, t1};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn", inputs, hidden, outputs);
        double[] tc0 = new double[] {1.0, 1.0, 1.0, 1.0, -2.0, 1.0, -1.5, -0.5, -0.5, 1.0, 1.0, 0.0};
        testCase(tc0, n, 0.0);
        double[] tc1 = new double[] {1.0, 1.0, 1.0, 1.0, -2.0, 1.0, -1.5, -0.5, -0.5, 0.0, 1.0, 1.0};
        testCase(tc1, n, 0.0);
        double[] tc2 = new double[] {1.0, 1.0, 1.0, 1.0, -2.0, 1.0, -1.5, -0.5, -0.5, 1.0, 0.0, 1.0};
        testCase(tc2, n, 0.0);
        double[] tc3 = new double[] {1.0, 1.0, 1.0, 1.0, -2.0, 1.0, -1.5, -0.5, -0.5, 0.0, 0.0, 0.0};
        testCase(tc3, n, 0.0);
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
    
    private double sigmoid(double z)
    {
        return 1.0/(1.0+Math.exp(-z));
    }
    
    private Module makeSigmoidPerceptron()
    {
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        SigmoidLayer out0 = new SigmoidLayer("out0", 2);
        Connection c0 = new FullConnection("c0", inp0, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {c0};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn", inputs, hidden, outputs);
        return n;
    }
    
    private Module makeBiasedSigmoidPerceptron()
    {
        LinearLayer inp0 = new LinearLayer("inp0", 2);
        SigmoidLayer out0 = new SigmoidLayer("out0", 2);
        Connection c0 = new FullConnection("c0", inp0, out0);
        BiasUnit b0 = new BiasUnit("bias", 1.0);
        Connection c1 = new FullConnection("c1", b0, out0);
        Module[] inputs = new Module[] {inp0};
        Module[] hidden = new Module[] {c0, b0, c1};
        Module[] outputs = new Module[] {out0};
        FeedForwardNetwork n = new FeedForwardNetwork("ffn", inputs, hidden, outputs);
        return n;
    }
    
    private void print_params(Module mod)
    {
        System.out.println(mod.name + " params");
        for (int i = 0; i < mod.size(); i++)
        {
            System.out.println(mod.getParam(i));
        }
        return;
    }
    
    private void print_out(Module mod)
    {
        System.out.println(mod.name);
        for (int i = 0; i < mod.output_dim; i++)
        {
            System.out.println(mod.output_buffer[i]);
        }
        return;
    }
    
    private void print_in(Module mod)
    {
        System.out.println(mod.name);
        for (int i = 0; i < mod.input_dim; i++)
        {
            System.out.println(mod.input_buffer[i]);
        }
        return;
    }
}
