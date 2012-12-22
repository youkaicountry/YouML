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
        BackPropTrainer bpt = new BackPropTrainer(n, .1);
        int iterations = 300000;
        double error = 0;
        for (int i = 0; i < iterations; i++)
        {
            error = bpt.trainBatch(td);
        }
        double oerror = Math.abs(bpt.trainBatch(td));
        System.out.println(oerror);
        n.clearBuffers();
        n.clearDerivs(0.0);
        Module m = n.getModule("layer2_units");
        n.activate(new double[] {1.0, 0.0, 0.0, 0.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(n.output_buffer[0] +" " + n.output_buffer[1]+" " + n.output_buffer[2]+" " + n.output_buffer[3]);
        n.clearBuffers();
        n.activate(new double[] {0.0, 1.0, 0.0, 0.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(n.output_buffer[0] +" " + n.output_buffer[1]+" " + n.output_buffer[2]+" " + n.output_buffer[3]);
        n.clearBuffers();
        n.activate(new double[] {0.0, 0.0, 1.0, 0.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(n.output_buffer[0] +" " + n.output_buffer[1]+" " + n.output_buffer[2]+" " + n.output_buffer[3]);
        n.clearBuffers();
        n.activate(new double[] {0.0, 0.0, 0.0, 1.0});
        System.out.println(m.output_buffer[0]);
        System.out.println(n.output_buffer[0] +" " + n.output_buffer[1]+" " + n.output_buffer[2]+" " + n.output_buffer[3]);
        return;
    }

}
