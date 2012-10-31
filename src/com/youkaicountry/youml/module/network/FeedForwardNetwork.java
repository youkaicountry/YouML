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
        for (int i = 0; i < this.graph.input_modules.length; i++)
        {
            this.graph.input_modules[i].setInput(input);
            this.graph.input_modules[i].step();
        }
        for (int i = 0; i < this.graph.sorted_modules.length; i++)
        {
            this.graph.sorted_modules[i].step();
        }
    }

    @Override
    public void backProp()
    {
        // TODO Auto-generated method stub

    }

}
