/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.utils;

import java.util.Arrays;

import com.youkaicountry.youml.parameter.ParameterVector;

public class GradientDescent
{
    public ParameterVector pv;
    private double momentum;
    private double alpha;
    private double alpha_decay;
    private double[] momentum_vector;
    
    public GradientDescent(ParameterVector pv, double alpha, double alpha_decay, double momentum)
    {
        this.pv = pv;
        this.alpha = alpha;
        this.alpha_decay = alpha_decay;
        this.momentum = momentum;
        this.momentum_vector = new double[this.pv.size()];
        Arrays.fill(this.momentum_vector, 0.0);
        return;
    }
    
    public void descend()
    {
        
        for (int i = 0; i < this.pv.size(); i++)
        {
            this.momentum_vector[i] *= this.momentum;
            this.momentum_vector[i] += this.alpha * this.pv.getDeriv(i);
            this.pv.addParam(i, this.momentum_vector[i]);
            this.alpha *= this.alpha_decay;
        }
        return;
    }
}
