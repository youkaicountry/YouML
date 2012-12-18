package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.module.layer.SigmoidLayer.LayerOptions;
import com.youkaicountry.youml.parameter.NullVector;

public class ThresholdLayer extends Layer
{
    public double threshold;
    
    public ThresholdLayer(String name, int neurons, double threshold)
    {
        super(name);
        this.moduleInit(neurons, neurons, new NullVector(this));
        return;
    }
    
    public ThresholdLayer(LayerOptions lo)
    {
        this(lo.name, lo.neurons, lo.threshold);
        return;
    }
    
    //For the loader class
    public static class LayerOptions
    {
        public String name;
        public int neurons;
        public double threshold;
    }

    @Override
    public void forwardProp(double[] input, double[] output)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            output[i+this.output_offset] = input[i+this.input_offset] >= this.threshold ? 1.0 : 0.0;
        }
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        // TODO: Raise an exception here... Not sure which one yet
    }

}
