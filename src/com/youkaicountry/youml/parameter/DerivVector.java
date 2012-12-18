package com.youkaicountry.youml.parameter;

public class DerivVector extends ParameterVector
{
    private double[] deriv_buffer;
    public int dim;
    
    public DerivVector(int dim)
    {
        this.dim = dim;
        this.deriv_buffer = new double[dim];
        return;
    }

    @Override
    public double getParam(int i)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void setParam(int i, double v)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void addParam(int i, double v)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public double getDeriv(int i)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setDeriv(int i, double v)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addDeriv(int i, double v)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int size()
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
