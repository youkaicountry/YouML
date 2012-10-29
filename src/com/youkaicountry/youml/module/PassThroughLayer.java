package com.youkaicountry.youml.module;

import com.youkaicountry.youml.parameter.NullVector;

public class PassThroughLayer extends Module
{
    public PassThroughLayer(String name, int neurons)
    {
        super(name);
        this.moduleInit(neurons, neurons, new NullVector(this));
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
