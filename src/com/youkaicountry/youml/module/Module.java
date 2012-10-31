package com.youkaicountry.youml.module;

import java.util.Arrays;

import com.youkaicountry.youml.parameter.ParameterVector;
import com.youkaicountry.youml.parameter.SingleCaseVector;

//TODO: Add offsets for input/output

//TODO: Will implement evolvable, and point to the pv evolvable components
public abstract class Module extends SingleCaseVector
{
    public double[] input_buffer;
    public double[] output_buffer;
    public double[] input_error_buffer;
    public double[] output_error_buffer;
    
    public int input_dim;
    public int output_dim;
    
    public String name;
    
    public Module(String name, int input_dim, int output_dim, ParameterVector pv)
    {
        super();
        this.parameterInit(this);
        this.name = name;
        this.moduleInit(input_dim, output_dim, pv);
        return;
    }
    
    public Module(String name)
    {
        super();
        this.parameterInit(this);
        this.name = name;
    }
    
    public void moduleInit(int input_dim, double[] input_buffer, int output_dim, double[] output_buffer, ParameterVector pv)
    {
        this.setParameterVector(pv);
        this.input_dim = input_dim;
        this.output_dim = output_dim;
        this.setBuffers(input_buffer, output_buffer);
        //this.input_buffer = null;
        //this.output_buffer = new double[output_dim];
        //this.input_error_buffer = null;
        //this.output_error_buffer = new double[output_dim];
        return;
    }
    
    public void moduleInit(int input_dim, int output_dim, ParameterVector pv)
    {
        this.setParameterVector(pv);
        this.input_dim = input_dim;
        this.output_dim = output_dim;
        this.setBuffers(new double[input_dim], new double[output_dim]);
        //this.input_buffer = null;
        //this.output_buffer = new double[output_dim];
        //this.input_error_buffer = null;
        //this.output_error_buffer = new double[output_dim];
        return;
    }
    
    public void step()
    {
        this.forwardProp(this.input_buffer, this.output_buffer);
        return;
    }
    
    public void activate(double[] input)
    {
        //TODO: ensure input is the length of input_dim
        this.input_buffer = input;
        this.step();
        return;
    }
    
    public void setBuffers(double[] input, double[] output)
    {
        this.setInput(input);
        this.setOutput(output);
        return;
    }
    
    public void setInput(double[] input)
    {
        this.input_buffer = input;
        return;
    }
    
    public void setOutput(double[] input)
    {
        this.output_buffer = input;
        return;
    }
    
    public abstract void forwardProp(double[] input, double[] output);
    public abstract void backProp();
}
