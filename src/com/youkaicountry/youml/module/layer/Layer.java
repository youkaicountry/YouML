package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.ParameterVector;

public abstract class Layer extends Module
{
    public Layer(String name, int input_dim, int output_dim, ParameterVector pv)
    {
        super(name, input_dim, output_dim, pv);
        return;
    }
    
    public Layer(String name)
    {
        super(name);
        return;
    }
    
}
