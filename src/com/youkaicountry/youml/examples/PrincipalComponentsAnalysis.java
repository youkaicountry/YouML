package com.youkaicountry.youml.examples;

import java.util.Random;

import com.youkaicountry.youml.builder.HiddenLayerNetworkBuilder;
import com.youkaicountry.youml.data.TrainingBatch;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.network.FeedForwardNetwork;
import com.youkaicountry.youml.trainers.BackPropTrainer;

public class PrincipalComponentsAnalysis
{

    // Toy example of principal component analysis on a classifier type vector
    // Vector is 4 length. One component is 1, and the rest 0's.
    // This net will discover a way to encode this sort of vector into a single
    // value.
    public static void main(String[] args)
    {
        HiddenLayerNetworkBuilder builder = new HiddenLayerNetworkBuilder(4, 3, 1, 3, 4);
        FeedForwardNetwork n = (FeedForwardNetwork)builder.build("ffn");
        Random r = new Random(1211);
        for (int i = 0; i < n.size(); i++)
        {
            n.setParam(i, r.nextDouble()*.5-.25);
        }
        TrainingBatch td = new TrainingBatch();
        td.addCase(new double[] {1.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0, 0.0});
        td.addCase(new double[] {0.0, 1.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0, 0.0});
        td.addCase(new double[] {0.0, 0.0, 1.0, 0.0}, new double[] {0.0, 0.0, 1.0, 0.0});
        td.addCase(new double[] {0.0, 0.0, 0.0, 1.0}, new double[] {0.0, 0.0, 0.0, 1.0});
        BackPropTrainer bpt = new BackPropTrainer(n, .1, 1.0, 0.9);
        int iterations = 800000;
        for (int i = 0; i < iterations; i++)
        {
            bpt.trainBatch(td);
        }
        double oerror = Math.abs(bpt.trainBatch(td));
        System.out.println(oerror);
        n.clearBuffers();
        n.clearDerivs(0.0);
        Module m = n.getModule("layer2_units");
        n.activate(new double[] {1.0, 0.0, 0.0, 0.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(render_output(n));
        n.clearBuffers();
        n.activate(new double[] {0.0, 1.0, 0.0, 0.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(render_output(n));
        n.clearBuffers();
        n.activate(new double[] {0.0, 0.0, 1.0, 0.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(render_output(n));
        n.clearBuffers();
        n.activate(new double[] {0.0, 0.0, 0.0, 1.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(render_output(n));
        return;
    }
    
    private static String render_output(Module m)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.output_dim; i++)
        {
            sb.append(number_clip(m.output_buffer[i]));
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private static double number_clip(double val)
    {
        if (val < 1e-6) {return 0;}
        if (val > .999999) {return 1.0;}
        return val;
    }

}
