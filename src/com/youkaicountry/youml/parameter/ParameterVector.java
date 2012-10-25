package com.youkaicountry.youml.parameter;

public abstract class ParameterVector
{
    public Object owner;
    
    public ParameterVector(Object owner)
    {
        parameterInit(owner);
        return;
    }
    
    public ParameterVector()
    {
        return;
    }
    
    public void parameterInit(Object owner)
    {
        this.owner = owner;
        return;
    }
    
    public abstract double getParam(int i);
    public abstract void setParam(int i, double v);
    public abstract double getDeriv(int i);
    public abstract void setDeriv(int i, double v);
    public abstract int size();
}
