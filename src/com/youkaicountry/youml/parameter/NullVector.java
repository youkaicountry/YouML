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
    public void addParam(int i, double v)
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
    public void addDeriv(int i, double v)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public void clearParams(double val)
    {
        return;
    }

    @Override
    public void clearDerivs(double val)
    {
        return;
    }

}
