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

import java.util.ArrayList;

public class MultiCaseVector extends ParameterVector
{
    private ParameterVector[] pvs;
    private int[] pv_lookup;       //{pvnum, i, pvnum, i, pvnum, i, ...}
    private int size = 0;
    
    public MultiCaseVector(Object owner)
    {
        super(owner);
        return;
    }
    
    public MultiCaseVector(ParameterVector[] pvs, Object owner)
    {
        super(owner);
        this.setParameterVectors(pvs);
        return;
    }
    
    public MultiCaseVector()
    {
        return;
    }
    
    public void setParameterVectors(ParameterVector[] pvs)
    {
        this.pvs = pvs;
        ArrayList<Integer> al_lookup = new ArrayList<Integer>();
        this.pv_lookup = new int[pvs.length*2];
        for (int i = 0; i < pvs.length; i++)
        {
            for (int j = 0; j < pvs[i].size(); j++)
            {
                al_lookup.add(i);
                al_lookup.add(j);
            }
        }
        this.pv_lookup = new int[al_lookup.size()];
        for (int i = 0; i < pv_lookup.length; i++)
        {
            this.pv_lookup[i] = al_lookup.get(i);
        }
        this.size = al_lookup.size()/2;
        return;
    }

    @Override
    public double getParam(int i)
    {
        int li = i*2;
        int pv_i = this.pv_lookup[li];
        int pv_i_i = this.pv_lookup[li+1];
        return this.pvs[pv_i].getParam(pv_i_i);
    }

    @Override
    public void setParam(int i, double v)
    {
        int li = i*2;
        int pv_i = this.pv_lookup[li];
        int pv_i_i = this.pv_lookup[li+1];
        this.pvs[pv_i].setParam(pv_i_i, v);
        return;
    }
    
    @Override
    public void addParam(int i, double v)
    {
        int li = i*2;
        int pv_i = this.pv_lookup[li];
        int pv_i_i = this.pv_lookup[li+1];
        this.pvs[pv_i].addParam(pv_i_i, v);
        return;
    }

    @Override
    public double getDeriv(int i)
    {
        int li = i*2;
        int pv_i = this.pv_lookup[li];
        int pv_i_i = this.pv_lookup[li+1];
        return this.pvs[pv_i].getDeriv(pv_i_i);
    }

    @Override
    public void setDeriv(int i, double v)
    {
        int li = i*2;
        int pv_i = this.pv_lookup[li];
        int pv_i_i = this.pv_lookup[li+1];
        this.pvs[pv_i].setDeriv(pv_i_i, v);
        return;
    }
    
    @Override
    public void addDeriv(int i, double v)
    {
        int li = i*2;
        int pv_i = this.pv_lookup[li];
        int pv_i_i = this.pv_lookup[li+1];
        this.pvs[pv_i].addDeriv(pv_i_i, v);
        return;
    }

    @Override
    public int size()
    {
        return this.size;
    }

}
