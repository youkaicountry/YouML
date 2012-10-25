package com.youkaicountry.youml.parameter;

public abstract class ParameterVector<T>
{
    private int param_dim;
    private double[] params;
    private double[] derivatives;
    public T owner;
    
    public ParameterVector(int dim, T owner)
    {
        parameterInit(dim, owner);
        return;
    }
    
    public ParameterVector()
    {
        return;
    }
    
    public void parameterInit(int dim, T owner)
    {
        this.params = new double[dim];
        this.derivatives = new double[dim];
        this.owner = owner;
        return;
    }
    
    public abstract void getParam(int i);
    public abstract void setParam(int i, double v);
    public abstract void getDeriv(int i);
    public abstract void setDeriv(int i, double v);
    public abstract void size();
}
