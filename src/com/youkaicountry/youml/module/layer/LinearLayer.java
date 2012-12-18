package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.NullVector;

public class LinearLayer extends Layer
{
    //TODO: Just have input_buffer and output_buffer point to the same thing?
    public LinearLayer(String name, int neurons)
    {
        super(name);
        this.moduleInit(neurons, neurons, new NullVector(this));
        return;
    }
    
    public LinearLayer(LayerOptions lo)
    {
        this(lo.name, lo.neurons);
        return;
    }
    
    //For the loader class
    public static class LayerOptions
    {
        public String name;
        public int neurons;
    }
    
    @Override
    public void forwardProp(double[] input, double[] output)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            output[i+this.output_offset] = input[i+this.input_offset];
        }
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            inerr[i+this.inerr_offset] = outerr[i+this.outerr_offset];
        }
        return;
    }

}
