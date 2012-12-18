/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
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
    public abstract void addParam(int i, double v);
    public abstract double getDeriv(int i);
    public abstract void setDeriv(int i, double v);
    public abstract void addDeriv(int i, double v);
    public abstract int size();
    
}
