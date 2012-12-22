/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.module.network;

import java.util.HashMap;
import java.util.Set;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.netgraph.NetGraph;
import com.youkaicountry.youml.parameter.MultiCaseVector;
import com.youkaicountry.youml.parameter.ParameterVector;

public abstract class Network extends Module
{
    public NetGraph graph;
    protected HashMap<String, Module> name2module;
    
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
        this.name2module = new HashMap<String, Module>();
        for (Module m : this.graph.sorted_modules)
        {
            this.name2module.put(m.name, m);
        }
        return;
    }
    
    public Module getModule(String name)
    {
        return this.name2module.get(name);
    }
    
    public Set<String> getModuleNames()
    {
        return this.name2module.keySet();
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
