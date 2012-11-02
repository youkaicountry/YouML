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
    
    public void clearParams(double val)
    {
        for (int i = 0; i < this.size(); i++)
        {
            this.setParam(i, val);
        }
        return;
    }
    
    public void clearDerivs(double val)
    {
        for (int i = 0; i < this.size(); i++)
        {
            this.setDeriv(i, val);
        }
        return;
    }
    
    //public void initParams(double stdev, Random r)
    
    public abstract double getParam(int i);
    public abstract void setParam(int i, double v);
    public abstract double getDeriv(int i);
    public abstract void setDeriv(int i, double v);
    public abstract int size();
    
}
