/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.module.layer;

import com.youkaicountry.youml.module.layer.SigmoidLayer.LayerOptions;
import com.youkaicountry.youml.parameter.NullVector;
import java.lang.Math;

public class TanHLayer extends Layer
{
    public TanHLayer(String name, int neurons)
    {
        super(name);
        this.moduleInit(neurons, neurons, new NullVector(this));
        return;
    }
    
    public TanHLayer(LayerOptions lo)
    {
        this(lo.name, lo.neurons);
        return;
    }
    
    //For the loader class
    public static class LayerOptions
    {
        public String name;
        public int neurons;
    }
    
    @Override
    public void forwardProp(double[] input, double[] output)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            output[i+this.output_offset] = Math.tanh(input[i+this.input_offset]);
        }
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
    	double val;
        for (int i = 0; i < this.input_dim; i++)
        {
            val = output[i+this.output_offset];
            inerr[i+this.inerr_offset] = (1.0 - Math.pow(val, 2.0)) * outerr[i+this.outerr_offset];
        }
        return;
    }
}
