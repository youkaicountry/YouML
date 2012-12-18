package com.youkaicountry.youml.module.layer.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.youkaicountry.youml.module.layer.Layer;

public class LayerLoader
{
    public HashMap<String, Class<?>> name2class;
    
    public LayerLoader(Class<?>[] types)
    {
        this.name2class = new HashMap<String, Class<?>>();
        for (int i = 0; i < types.length; i++)
        {
            this.name2class.put(types[i].getSimpleName(), types[i]);
        }
        return;
    }
    
    public LayerLoader()
    {
        this(LayerTypes.standard_layers);
    }
    
    // Thanks Java. You certainly make life easy.
    public Layer load(String class_name, Map<String, Object> params)
    {
        Class<?> layer_class = this.name2class.get(class_name);
        Class<?>[] class_objects = layer_class.getDeclaredClasses();
        Class<?> options_class = null;
        for (Class<?> c : class_objects)
        {
            if (c.getSimpleName().equals("LayerOptions"))
            {
                options_class = c;
                break;
            }
        }
        if (options_class == null) {throw(new RuntimeException("No ClassOptions inner class found in " + class_name + "."));}
        try
        {
            Object layer_options = options_class.newInstance();
            //Iterator<String> it = params.keySet().iterator();
            for (String param_name : params.keySet())
            {
                Field f = options_class.getDeclaredField(param_name);
                f.set(layer_options, params.get(param_name));
            }
            Constructor<?> constructor = layer_class.getDeclaredConstructor(options_class);
            return (Layer)constructor.newInstance(layer_options);
        }
        catch (InstantiationException e)
        {
            throw(new RuntimeException(e));
        } 
        catch (IllegalAccessException e)
        {
            throw(new RuntimeException(e));
        } 
        catch (SecurityException e)
        {
            throw(new RuntimeException(e));
        } 
        catch (NoSuchFieldException e)
        {
            throw(new RuntimeException(e));
        } 
        catch (NoSuchMethodException e)
        {
            throw(new RuntimeException(e));
        } 
        catch (IllegalArgumentException e)
        {
            throw(new RuntimeException(e));
        } 
        catch (InvocationTargetException e)
        {
            throw(new RuntimeException(e));
        }
    }
}
