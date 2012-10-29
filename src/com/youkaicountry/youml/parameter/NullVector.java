package com.youkaicountry.youml.parameter;

public class NullVector extends ParameterVector
{
    public NullVector(Object owner)
    {
        super(owner);
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
    public double getDeriv(int i)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void setDeriv(int i, double v)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size()
    {
        return 0;
    }

}
