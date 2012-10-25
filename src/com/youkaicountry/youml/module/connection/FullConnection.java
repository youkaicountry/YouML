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
        // TODO Auto-generated method stub

    }

    @Override
    public void backProp()
    {
        // TODO Auto-generated method stub

    }

}
