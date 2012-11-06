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
