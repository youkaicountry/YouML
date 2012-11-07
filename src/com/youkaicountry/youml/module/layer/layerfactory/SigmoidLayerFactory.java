package com.youkaicountry.youml.module.layer.layerfactory;

import com.youkaicountry.youml.module.layer.Layer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;

public class SigmoidLayerFactory extends LayerFactory 
{
	public SigmoidLayerFactory(String base_name, int neurons)
	{
		super(base_name, neurons);
		return;
	}
	
	@Override
	public Layer implBuildLayer(String name, int neurons) 
	{
		return new SigmoidLayer(name, neurons);
	}
}
