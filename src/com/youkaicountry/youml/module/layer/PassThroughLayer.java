package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.NullVector;

public class PassThroughLayer extends Module
{
    //TODO: Just have input_buffer and output_buffer point to the same thing
    public PassThroughLayer(String name, int neurons)
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
            output[i] = input[i];
        }
    }

    @Override
    public void backProp()
    {
        // TODO Auto-generated method stub

    }

}
