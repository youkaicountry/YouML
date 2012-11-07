package com.youkaicountry.youml.module.layer.layerfactory;

import com.youkaicountry.youml.module.layer.Layer;
import com.youkaicountry.youml.module.layer.LinearLayer;

public class LinearLayerFactory extends LayerFactory 
{
	public LinearLayerFactory(String base_name, int neurons)
	{
		super(base_name, neurons);
		return;
	}
	
	@Override
	public Layer implBuildLayer(String name, int neurons) 
	{
		return new LinearLayer(name, neurons);
	}
}
