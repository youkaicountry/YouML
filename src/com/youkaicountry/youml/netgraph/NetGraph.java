/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.netgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.connection.Connection;

public class NetGraph
{
    public Module[] input_modules;
    public Module[] hidden_modules;
    public Module[] output_modules;
    public Module[] sorted_modules;
    
    private Module[] edges;
    private Module[] vertices;
    
    public NetGraph(Module[] input_modules, Module[] hidden_modules, Module[] output_modules)
    {
        this.input_modules = input_modules;
        this.hidden_modules = hidden_modules;
        this.output_modules = output_modules;
        
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        ArrayList<Module> sorted = new ArrayList<Module>();
        HashMap<String, ArrayList<Module>> before = new HashMap<String, ArrayList<Module>>();
        HashMap<String, Module> remaining= new HashMap<String, Module>();
        for (int i = 0; i < input_modules.length; i++)
        {
            visited.put(input_modules[i].name, true);
        }
        for (int i = 0; i < hidden_modules.length; i++)
        {
            visited.put(hidden_modules[i].name, false);
            before.put(hidden_modules[i].name, new ArrayList<Module>());
            remaining.put(hidden_modules[i].name, hidden_modules[i]);
        }
        for (int i = 0; i < output_modules.length; i++)
        {
            before.put(output_modules[i].name, new ArrayList<Module>());
        }
        
        //Do the connection loop, setting up the before table
        for (int i = 0; i < hidden_modules.length; i++)
        {
            if (hidden_modules[i] instanceof Connection) {this.setBefore(before, (Connection)hidden_modules[i]);}
        }
        
        //Do the main iteration
        while (remaining.size() > 0)
        {
            ArrayList<Module> removal = new ArrayList<Module>();
            Iterator<String> it = remaining.keySet().iterator();
            while (it.hasNext())
            {
                String mn = it.next();
                Module m = remaining.get(mn);
                //now check if given module has all befores visited. 
                // If yes, mark as removal needed
                ArrayList<Module> b = before.get(mn);
                boolean all_visited = true;
                for (int i = 0; i < b.size(); i++)
                {
                    if (!visited.get(b.get(i).name))
                    {
                        all_visited = false;
                        break;
                    }
                }
                if (all_visited)
                {
                    removal.add(m);
                }
            }
            //now parse the removals. add to sorted, remove, mark as visited
            for (int i = 0; i < removal.size(); i++)
            {
                Module m = removal.get(i);
                String mn = m.name;
                remaining.remove(mn);
                visited.put(mn, true);
                sorted.add(m);
            }
        }
        this.sorted_modules = new Module[sorted.size()];
        for (int i = 0; i < sorted.size(); i++)
        {
            this.sorted_modules[i] = (Module)sorted.get(i);
        }
        return;
    }
    
    private void setBefore(HashMap<String, ArrayList<Module>> before, Connection c)
    {
        before.get(c.name).add(c.module_a);
        before.get(c.module_b.name).add(c);
        return;
    }
}
