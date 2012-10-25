package com.youkaicountry.youml.module;
import java.lang.Math;

public class SigmoidLayer extends Module
{
    public SigmoidLayer(String name, int neurons)
    {
        super(name, neurons, neurons, 0);
        return;
    }

    @Override
    public void forwardProp(double[] input, double[] output)
    {
        for (int i = 0; i < input.length; i++)
        {
            output[i] = 1.0/(1.0+Math.exp(-input[i]));
        }
    }

    @Override
    public void backProp()
    {
        // TODO Auto-generated method stub
    }

}
