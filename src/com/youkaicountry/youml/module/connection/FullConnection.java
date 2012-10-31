package com.youkaicountry.youml.module.connection;

import com.youkaicountry.youml.module.Module;

public class FullConnection extends Connection
{
    public FullConnection(String name, Module module_a, Module module_b)
    {
        super(name, module_a, module_b, module_a.output_dim*module_b.input_dim);
        return;
    }
    
    @Override
    public void forwardProp(double[] input, double[] output)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            for (int j = 0; j < this.output_dim; j++)
            {
                output[j] += input[i] * this.getParam(i);
            }
        }
    }

    @Override
    public void backProp()
    {
        // TODO Auto-generated method stub

    }

}
