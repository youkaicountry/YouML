package com.youkaicountry.youml.module;

import com.youkaicountry.youml.parameter.NullVector;

public class BiasUnit extends Module
{
    public BiasUnit(String name)
    {
        super(name);
        this.moduleInit(0, 1, new NullVector(this));
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
