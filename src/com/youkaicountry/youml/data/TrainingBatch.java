/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.data;

import java.util.ArrayList;

//TODO: Have a shuffle index function

public class TrainingBatch
{
    int size = 0;
    private ArrayList<double[]> input_data;
    private ArrayList<double[]> output_data;
    
    public TrainingBatch()
    {
    	this.input_data = new ArrayList<double[]>();
    	this.output_data = new ArrayList<double[]>();
        return;
    }
    
    public void addCase(double[] input, double[] output)
    {
        input_data.add(input);
        output_data.add(output);
        size++;
        return;
    }
    
    public double[] getCaseInput(int i)
    {
        return this.input_data.get(i);
    }
    
    public double[] getCaseOutput(int i)
    {
        return this.output_data.get(i);
    }
    
    public int size()
    {
        return this.size;
    }
}
