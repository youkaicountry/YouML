package com.youkaicountry.youml.module;

import com.youkaicountry.youml.parameter.NullVector;

public class BiasUnit extends Module
{
    private double value;
    
    public BiasUnit(String name, double value)
    {
        super(name);
        this.moduleInit(0, 1, new NullVector(this));
        this.value = value;
        return;
    }
    
    @Override
    public void forwardProp(double[] input, double[] output)
    {
        output[this.output_offset] = value;
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        //do nothing.
        return;
    }
}
