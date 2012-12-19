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

import com.youkaicountry.youml.module.Module;

public class HiddenLayerNetworkBuilder extends Builder 
{
	private ArrayList<String> layer_types;
	private ArrayList<Integer> layer_sizes;
	private ArrayList<Integer> bias;
	
	public HiddenLayerNetworkBuilder(String hidden_layers, String output_layer, int hidden_bias, int output_bias, Integer... layer_sizes)
	{
		init();
		for (Integer s : layer_sizes)
		{
			this.layer_types.add(hidden_layers);
			this.layer_sizes.add(s);
			this.bias.add(hidden_bias);
		}
		int last = this.layer_types.size()-1;
		this.layer_types.set(last, output_layer);
		this.bias.set(last, output_bias);
		this.layer_types.set(0, "LinearLayer");
		this.bias.set(0, 0);
		return;
	}
	
	private void init()
	{
		layer_types = new ArrayList<String>();
		layer_sizes = new ArrayList<Integer>();
		bias = new ArrayList<Integer>();
		return;
	}

	@Override
	public Module build()
	{
		ArrayList<Module> m = new ArrayList<Module>();
		for (int i = 0; i < this.layer_sizes.size()-1; i++)
		{
			// TODO: if bias is 0, don't even make a bias unit
		}
		return null;
	}
	
}
