package com.youkaicountry.youml.parameter;

public class ConstrainedEqualVector extends ParameterVector
{
    private double[] param_buffer;
    public ParameterVector deriv;
    private int dim;
    
    public ConstrainedEqualVector(int dim, DerivVector deriv)
    {
        this.param_buffer = new double[dim];
        this.dim = dim;
        this.deriv = deriv;
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
    public void addParam(int i, double v)
    {
        this.param_buffer[i] += v;
        return;
        
    }

    @Override
    public double getDeriv(int i)
    {
        return this.deriv.getDeriv(i);
    }

    @Override
    public void setDeriv(int i, double v)
    {
        this.deriv.setDeriv(i, v);
    }

    @Override
    public void addDeriv(int i, double v)
    {
        this.deriv.addDeriv(i, v);
        return;
    }
    
    @Override
    public void clearDerivs(double val)
    {
        //TODO: Needs to clear the deriv with its own contribution
        //       since the last clear
        // Have DerivVector have a function that takes a double[]
        // and efficiently subtracts it from its own. This class should
        // keep track of its own contributions since last clear.
    }
    
    @Override
    public int size()
    {
        return this.dim;
    }
    
}
