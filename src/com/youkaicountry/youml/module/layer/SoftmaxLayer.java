package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.parameter.NullVector;

public class SoftmaxLayer extends Layer
{
    public SoftmaxLayer(String name, int neurons)
    {
        super(name);
        this.moduleInit(neurons, neurons, new NullVector(this));
        return;
    }

    @Override
    public void forwardProp(double[] input, double[] output)
    {
        double adder = 0;
        double val = 0;
        for (int i = 0; i < this.input_dim; i++)
        {
            val = input[i+this.input_offset];
            adder += val;
            output[i+this.output_offset] = boundedExp(val);
        }
        for (int i = 0; i < this.input_dim; i++)
        {
            output[i+this.output_offset] /= adder;
        }
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        // TODO Auto-generated method stub
    }
    
    private double boundedExp(double x)
    {
        
        if (x > 500) {x = 500;}
        else if (x < -500) {x = -500;}
        return Math.exp(x);
    }

}
