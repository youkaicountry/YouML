package com.youkaicountry.youml.module.layer.layerfactory;

import com.youkaicountry.youml.module.layer.Layer;

public abstract class LayerFactory 
{
	public int neurons;
	public String base_name;
	public int loc;
	
	public LayerFactory(String base_name, int neurons)
	{
		this.base_name = base_name;
		this.neurons = neurons;
		return;
	}
	
	public Layer buildLayer()
	{
		String name = this.base_name + this.loc;
		Layer out = this.implBuildLayer(name, this.neurons);
		loc++;
		return out;
	}
	
	public Layer buildLayer(int neurons)
	{
		String name = this.base_name + this.loc;
		Layer out = this.implBuildLayer(name, neurons);
		loc++;
		return out;
	}
	
	public abstract Layer implBuildLayer(String name, int neurons);
}
