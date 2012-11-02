package com.youkaicountry.youml.module.layer;

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
