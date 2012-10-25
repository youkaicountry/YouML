package com.youkaicountry.youml.module;

public class PassThroughLayer extends Module
{
    public PassThroughLayer(String name, int neurons)
    {
        super(name, neurons, neurons, 0);
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
