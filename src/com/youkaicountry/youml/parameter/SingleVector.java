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
    public void addParam(int i, double v)
    {
        this.param_buffer[i] += v;
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
    public void addDeriv(int i, double v)
    {
        this.deriv_buffer[i] += v;
        return;
    }

    @Override
    public int size()
    {
        return this.dim;
    }
}
