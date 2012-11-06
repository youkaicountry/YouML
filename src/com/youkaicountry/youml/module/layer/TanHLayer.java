package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.parameter.NullVector;
import java.lang.Math;

public class TanHLayer extends Layer
{
    public TanHLayer(String name, int neurons)
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
            output[i+this.output_offset] = Math.tanh(input[i+this.input_offset]);
        }
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
    	double val;
        for (int i = 0; i < this.input_dim; i++)
        {
            val = output[i+this.output_offset];
            inerr[i+this.inerr_offset] = (1.0 - Math.pow(val, 2.0)) * outerr[i+this.outerr_offset];
        }
        return;
    }
}
