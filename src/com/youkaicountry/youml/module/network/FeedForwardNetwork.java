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
            this.graph.input_modules[i].setInput(input, inpmod_input_offset);
            this.graph.input_modules[i].step();
            inpmod_input_offset += this.graph.input_modules[i].input_dim;
        }
        for (int i = 0; i < this.graph.sorted_modules.length; i++)
        {
            this.graph.sorted_modules[i].step();
        }
        int outmod_output_offset = 0 + this.output_offset;
        for (int i = 0; i < this.graph.output_modules.length; i++)
        {
            this.graph.output_modules[i].setOutput(output, outmod_output_offset);
            this.graph.output_modules[i].step();
            outmod_output_offset += this.graph.output_modules[i].output_dim;
        }
    }

    @Override
    public void backProp()
    {
        // TODO Auto-generated method stub

    }

}
