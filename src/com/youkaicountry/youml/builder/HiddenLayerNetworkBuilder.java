/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.builder;

import java.util.ArrayList;

import com.youkaicountry.youml.module.BiasUnit;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.module.connection.Connection;
import com.youkaicountry.youml.module.connection.FullConnection;
import com.youkaicountry.youml.module.layer.loader.LayerLoader;
import com.youkaicountry.youml.module.network.FeedForwardNetwork;

public class HiddenLayerNetworkBuilder extends Builder 
{
	private ArrayList<String> layer_types;
	private ArrayList<Integer> layer_sizes;
	private ArrayList<Double> layer_bias;
	private LayerLoader loader;
	
	public HiddenLayerNetworkBuilder(double hidden_bias, double output_bias, String hidden_layers, String output_layer,  int... layer_sizes)
	{
		init();
		for (Integer s : layer_sizes)
		{
			this.layer_types.add(hidden_layers);
			this.layer_sizes.add(s);
			this.layer_bias.add(hidden_bias);
		}
		int last = this.layer_types.size()-1;
		this.layer_types.set(last, output_layer);
		this.layer_bias.set(last, output_bias);
		this.layer_types.set(0, "LinearLayer");
		this.layer_bias.set(0, 0.0);
		return;
	}
	
	public HiddenLayerNetworkBuilder(String hidden_layers, String output_layer, int... layer_sizes)
	{
	    this(1.0, 1.0, hidden_layers, output_layer, layer_sizes);
	    return;
	}
	
	public HiddenLayerNetworkBuilder(int... layer_sizes)
    {
        //this("SigmoidLayer", "LinearLayer", 1.0, 1.0, layer_sizes);
	    this("SigmoidLayer", "LinearLayer", layer_sizes);
	    return;
    }
	
	private void init()
	{
	    this.loader = new LayerLoader();
		layer_types = new ArrayList<String>();
		layer_sizes = new ArrayList<Integer>();
		layer_bias = new ArrayList<Double>();
		return;
	}

	@Override
	public Module build(String name)
	{
	    ArrayList<Module> inp = new ArrayList<Module>();
		ArrayList<Module> m = new ArrayList<Module>();
		ArrayList<Module> out = new ArrayList<Module>();
		String lname = "layer0";
		Module last_layer = addModules(lname, 0, m);
		inp.add(last_layer);
		for (int i = 1; i < this.layer_sizes.size(); i++)
		{
		    lname = "layer" + i;
		    ArrayList<Module> um = (i == this.layer_sizes.size()-1) ? out : m;
		    Module this_layer = addModules(lname, i, m);
		    um.add(this_layer);
		    //do connection to last module
            Connection c = new FullConnection(lname+"_in_connection", last_layer, this_layer);
            m.add(c);
            last_layer = this_layer;
		}
		//System.out.println(inp);
		//System.out.println(m);
		//System.out.println(out);
		return new FeedForwardNetwork(name, al2a(inp), al2a(m), al2a(out));
	}
	
	private Module[] al2a(ArrayList<Module> m)
	{
	    Module[] out = new Module[m.size()];
	    for (int i = 0; i < m.size(); i++)
	    {
	        out[i] = (Module)m.get(i);
	    }
	    return out;
	}
	
	private Module addModules(String lname, int i, ArrayList<Module> m)
	{
        Module this_layer = loader.load(this.layer_types.get(i), lname+"_units", this.layer_sizes.get(i));
        double bias = this.layer_bias.get(i);
        if (bias != 0)
        {
            BiasUnit b = new BiasUnit(lname+"_bias", bias);
            Connection bc = new FullConnection(lname+"_bias_connection", b, this_layer);
            m.add(b);
            m.add(bc);
        }
	    return this_layer;
	}
	
}
