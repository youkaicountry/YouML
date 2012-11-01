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
    public void backProp()
    {
        // TODO Auto-generated method stub
    }
}
