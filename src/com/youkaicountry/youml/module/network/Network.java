package com.youkaicountry.youml.module.network;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.netgraph.NetGraph;
import com.youkaicountry.youml.parameter.MultiCaseVector;
import com.youkaicountry.youml.parameter.NullVector;
import com.youkaicountry.youml.parameter.ParameterVector;

public abstract class Network extends Module
{
    protected NetGraph graph;
    
    public Network(String name, Module[] input_modules, Module[] hidden_modules, Module[] output_modules)
    {
        super(name);
        this.graph = new NetGraph(input_modules, hidden_modules, output_modules);
        //TODO: track offsets for each input
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
        
    }

}
