package com.youkaicountry.youml.module.layer;
import java.lang.Math;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.NullVector;

public class SigmoidLayer extends Module
{
    public SigmoidLayer(String name, int neurons)
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
            output[i+this.output_offset] = 1.0/(1.0+Math.exp(-input[i+this.input_offset]));
        }
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        double val;
        for (int i = 0; i < this.input_dim; i++)
        {
            val = output[i+this.output_offset];
            inerr[i+this.inerr_offset] = val * (1.0 - val) * outerr[i+this.outerr_offset];
        }
        return;
    }
}
