package com.youkaicountry.youml.module.layer.loader;

import com.youkaicountry.youml.module.layer.LinearLayer;
import com.youkaicountry.youml.module.layer.SigmoidLayer;
import com.youkaicountry.youml.module.layer.SoftmaxLayer;
import com.youkaicountry.youml.module.layer.TanHLayer;
import com.youkaicountry.youml.module.layer.ThresholdLayer;

public class LayerTypes
{
    public static Class<?>[] standard_layers = new Class[]
    {
        LinearLayer.class,
        SigmoidLayer.class,
        SoftmaxLayer.class,
        TanHLayer.class,
        ThresholdLayer.class,
    };
}
