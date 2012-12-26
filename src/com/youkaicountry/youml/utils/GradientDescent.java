package com.youkaicountry.youml.utils;

import java.util.Arrays;

import com.youkaicountry.youml.parameter.ParameterVector;

public class GradientDescent
{
    private ParameterVector pv;
    private double momentum;
    private double alpha;
    private double alpha_decay;
    private double[] momentum_vector;
    
    public GradientDescent(ParameterVector pv, double alpha, double alpha_decay, double momentum)
    {
        this.pv = pv;
        this.alpha = alpha;
        this.alpha_decay = alpha_decay;
        this.momentum = momentum;
        this.momentum_vector = new double[this.pv.size()];
        Arrays.fill(this.momentum_vector, 0.0);
        return;
    }
    
    public void descend()
    {
        for (int i = 0; i < this.pv.size(); i++)
        {
            this.momentum_vector[i] *= this.momentum;
            this.momentum_vector[i] += this.alpha * this.pv.getDeriv(i);
            this.pv.addParam(i, this.momentum_vector[i]);
            this.alpha *= this.alpha_decay;
        }
        return;
    }
}
