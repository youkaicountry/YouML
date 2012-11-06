package com.youkaicountry.youml.module.network;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.netgraph.NetGraph;
import com.youkaicountry.youml.parameter.MultiCaseVector;
import com.youkaicountry.youml.parameter.ParameterVector;

public abstract class Network extends Module
{
    protected NetGraph graph;
    
    public Network(String name, Module[] input_modules, Module[] hidden_modules, Module[] output_modules)
    {
        super(name);
        this.graph = new NetGraph(input_modules, hidden_modules, output_modules);
        int input_size = 0;
        for (int i = 0; i < input_modules.length; i++)
        {
            input_size += input_modules[i].input_dim;
        }
        int output_size = 0;
        for (int i = 0; i < output_modules.length; i++)
        {
            output_size += output_modules[i].output_dim;
        }
        
        ParameterVector[] pvl = new ParameterVector[input_modules.length+hidden_modules.length+output_modules.length];
        for (int i = 0; i < input_modules.length; i++)
        {
            pvl[i] = input_modules[i];
        }
        int loc = input_modules.length;
        for (int i = 0; i < hidden_modules.length; i++)
        {
            pvl[i+loc] = hidden_modules[i];
        }
        loc += hidden_modules.length;
        for (int i = 0; i < output_modules.length; i++)
        {
            pvl[i+loc] = output_modules[i];
        }
        this.moduleInit(input_size, output_size, new MultiCaseVector(pvl, this));
        
        //Now set the input and output pointers of each module
        // Each module just has its own input and output used,
        // Each connection only has pointers
        
        //changing these might cause problems if what they are pointing to is changed?
        /*for (int i = 0; i < input_modules.length; i++)
        {
            input_modules[i].setInput(this.input_buffer);
        }
        for (int i = 0; i < output_modules.length; i++)
        {
            output_modules[i].setOutput(this.output_buffer);
        }*/
        return;
    }
    
    @Override
    public void clearBuffers()
    {
        for (int i = 0; i < this.graph.input_modules.length; i++)
        {
            this.graph.input_modules[i].clearBuffers();
        }
        for (int i = 0; i < this.graph.hidden_modules.length; i++)
        {
            this.graph.hidden_modules[i].clearBuffers();
        }
        for (int i = 0; i < this.graph.output_modules.length; i++)
        {
            this.graph.output_modules[i].clearBuffers();
        }
        return;
    }

}
