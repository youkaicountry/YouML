/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
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
    
    public Layer load(String class_name, String layer_name, int neurons)
    {
        HashMap<String, Object> uparams = new HashMap<String, Object>();
        return this.load(class_name, layer_name, neurons, uparams);
    }
    
    public Layer load(String class_name, String layer_name, int neurons, Map<String, Object> params)
    {
        HashMap<String, Object> uparams = new HashMap<String, Object>(params);
        uparams.put("name", layer_name);
        uparams.put("neurons", neurons);
        return this.load(class_name, uparams);
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
