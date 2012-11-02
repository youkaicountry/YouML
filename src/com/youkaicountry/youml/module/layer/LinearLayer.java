package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.NullVector;

public class LinearLayer extends Module
{
    //TODO: Just have input_buffer and output_buffer point to the same thing?
    public LinearLayer(String name, int neurons)
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
            output[i+this.output_offset] = input[i+this.input_offset];
        }
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            inerr[i+this.inerr_offset] = outerr[i+this.outerr_offset];
        }
    }

}
