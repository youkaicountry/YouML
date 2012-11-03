package com.youkaicountry.youml.parameter;

public class SingleVector extends ParameterVector
{
    private double[] param_buffer;
    private double[] deriv_buffer;
    private int dim;
    private boolean derivs;
    
    public SingleVector(int dim, boolean derivs, Object owner)
    {
        super(owner);
        this.param_buffer = new double[dim];
        if (derivs) {this.deriv_buffer = new double[dim];}
        this.dim = dim;
        this.derivs = derivs;
        return;
    }

    @Override
    public double getParam(int i)
    {
        return this.param_buffer[i];
    }

    @Override
    public void setParam(int i, double v)
    {
        this.param_buffer[i] = v;
        return;
    }

    @Override
    public double getDeriv(int i)
    {
        return this.deriv_buffer[i];
    }

    @Override
    public void setDeriv(int i, double v)
    {
        this.deriv_buffer[i] = v;
        return;
    }

    @Override
    public int size()
    {
        return this.dim;
    }
}
