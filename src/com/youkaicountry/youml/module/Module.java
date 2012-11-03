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
    public int input_offset = 0;
    public int inerr_offset = 0;
    public int output_dim;
    public int output_offset = 0;
    public int outerr_offset = 0;
    
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
    
    public void moduleInit(int input_dim, double[] input_buffer, int input_offset, double[] inerr, int inerr_offset, int output_dim, double[] output_buffer, int output_offset, double[] outerr, int outerr_offset, ParameterVector pv)
    {
        this.setParameterVector(pv);
        this.input_dim = input_dim;
        this.output_dim = output_dim;
        this.setBuffers(input_buffer, input_offset, output_buffer, output_offset);
        this.setErrorBuffers(inerr, inerr_offset, outerr, outerr_offset);
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
        this.setBuffers(new double[input_dim], 0, new double[output_dim], 0);
        this.setErrorBuffers(new double[input_dim], 0, new double[output_dim], 0);
        //this.input_buffer = null;
        //this.output_buffer = new double[output_dim];
        //this.input_error_buffer = null;
        //this.output_error_buffer = new double[output_dim];
        return;
    }
    
    public void clearBuffers()
    {
        Arrays.fill(this.input_buffer, 0);
        Arrays.fill(this.output_buffer, 0);
        Arrays.fill(this.input_error_buffer, 0);
        Arrays.fill(this.output_error_buffer, 0);
    }
    
    public void step()
    {
        this.forwardProp(this.input_buffer, this.output_buffer);
        return;
    }
    
    public void step(double[] input, double[] output)
    {
    	this.forwardProp(input, output);
    	return;
    }
    
    public void backStep()
    {
        this.backProp(this.output_error_buffer, this.input_error_buffer, this.output_buffer, this.input_buffer);
        return;
    }
    
    public void backStep(double[] outerr, double[] inerr, double[] output, double[] input)
    {
    	this.backProp(outerr, inerr, output, input);
    }
    
    public void activate(double[] input)
    {
        //TODO: ensure input is >= the length of input_dim
        this.step(input, this.output_buffer);
        return;
    }
    
    public void backtivate(double[] outerr, double[] input)
    {
        this.backStep(outerr, this.input_error_buffer, this.output_buffer, input);
        return;
    }
    
    public void setErrorBuffers(double[] inerr, int inerr_offset, double[] outerr, int outerr_offset)
    {
        this.setErrorInput(inerr, inerr_offset);
        this.setErrorOutput(outerr, outerr_offset);
        return;
    }
    
    public void setErrorInput(double[] inerr, int inerr_offset)
    {
        this.inerr_offset = inerr_offset;
        this.input_error_buffer = inerr;
        return;
    }
    
    public void setErrorOutput(double[] inerr, int outerr_offset)
    {
        this.outerr_offset = output_offset;
        this.output_error_buffer = inerr;
        return;
    }
    
    public void setBuffers(double[] input, int input_offset, double[] output, int output_offset)
    {
        this.setInput(input, input_offset);
        this.setOutput(output, output_offset);
        return;
    }
    
    public void setInput(double[] input, int input_offset)
    {
        this.input_offset = input_offset;
        this.input_buffer = input;
        return;
    }
    
    public void setOutput(double[] input, int output_offset)
    {
        this.output_offset = output_offset;
        this.output_buffer = input;
        return;
    }
    
    public abstract void forwardProp(double[] input, double[] output);
    public abstract void backProp(double[] outerr, double[] inerr, double[] output, double[] input);
}
