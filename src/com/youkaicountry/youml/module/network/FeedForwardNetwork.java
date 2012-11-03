package com.youkaicountry.youml.module.network;

import com.youkaicountry.youml.module.Module;

public class FeedForwardNetwork extends Network
{
    public FeedForwardNetwork(String name, Module[] input_modules, Module[] hidden_modules, Module[] output_modules)
    {
        super(name, input_modules, hidden_modules, output_modules);
        return;
    }
    
    @Override
    public void forwardProp(double[] input, double[] output)
    {
        int inpmod_input_offset = 0 + this.input_offset;
        for (int i = 0; i < this.graph.input_modules.length; i++)
        {
            //this.graph.input_modules[i].setInput(input, inpmod_input_offset);
            this.graph.input_modules[i].step(input, this.graph.input_modules[i].output_buffer);
            inpmod_input_offset += this.graph.input_modules[i].input_dim;
        }
        for (int i = 0; i < this.graph.sorted_modules.length; i++)
        {
            this.graph.sorted_modules[i].step();
        }
        int outmod_output_offset = 0 + this.output_offset;
        for (int i = 0; i < this.graph.output_modules.length; i++)
        {
            //this.graph.output_modules[i].setOutput(output, outmod_output_offset);
            this.graph.output_modules[i].step(this.graph.output_modules[i].input_buffer, output);
            outmod_output_offset += this.graph.output_modules[i].output_dim;
        }
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        int outmod_output_offset = 0 + this.output_offset;
        int outerr_outerr_offset = 0 + this.outerr_offset;
        for (int i = 0; i < this.graph.output_modules.length; i++)
        {
            //this.graph.output_modules[i].setErrorOutput(outerr, outerr_outerr_offset);
            //this.graph.output_modules[i].setOutput(output, outmod_output_offset);
            this.graph.output_modules[i].backStep(outerr, this.graph.output_modules[i].input_error_buffer, output, this.graph.output_modules[i].input_buffer);
            outmod_output_offset += this.graph.output_modules[i].output_dim;
            outerr_outerr_offset += this.graph.output_modules[i].output_dim;
        }
        for (int i = this.graph.sorted_modules.length-1; i >=0; i--)
        {
            this.graph.sorted_modules[i].backStep();
        }
        int inpmod_input_offset = 0 + this.input_offset;
        int inerr_inerr_offset = 0 + this.inerr_offset;
        for (int i = 0; i < this.graph.input_modules.length; i++)
        {
            //this.graph.input_modules[i].setInput(input, inpmod_input_offset);
            //this.graph.input_modules[i].setErrorInput(inerr, inerr_inerr_offset);
            this.graph.input_modules[i].backStep(this.graph.input_modules[i].output_error_buffer, inerr, this.graph.input_modules[i].output_buffer, input);
            inpmod_input_offset += this.graph.input_modules[i].input_dim;
            inerr_inerr_offset += this.graph.input_modules[i].input_dim;
        }
        return;
    }
}
