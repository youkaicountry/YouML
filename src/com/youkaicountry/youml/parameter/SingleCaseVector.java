package com.youkaicountry.youml.parameter;

public class SingleCaseVector extends ParameterVector
{
    private ParameterVector pv;
    
    public SingleCaseVector(Object owner)
    {
        super(owner);
        return;
    }
    
    public SingleCaseVector(Object owner, ParameterVector pv)
    {
        super(owner);
        this.setParameterVector(pv);
        return;
    }
    
    public SingleCaseVector()
    {
        return;
    }
    
    public void setParameterVector(ParameterVector pv)
    {
        this.pv = pv;
        return;
    }

    @Override
    public double getParam(int i)
    {
        return this.pv.getParam(i);
    }

    @Override
    public void setParam(int i, double v)
    {
        this.pv.setParam(i, v);
        return;
    }

    @Override
    public double getDeriv(int i)
    {
        return this.pv.getDeriv(i);
    }

    @Override
    public void setDeriv(int i, double v)
    {
        this.pv.setDeriv(i, v);
        return;
    }

    @Override
    public int size()
    {
        return this.pv.size();
    }

}
