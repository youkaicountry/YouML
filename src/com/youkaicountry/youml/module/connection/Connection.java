package com.youkaicountry.youml.module.connection;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.SingleVector;

public abstract class Connection extends Module
{
    public Module module_a;
    public Module module_b;
    
    public Connection(String name, Module module_a, Module module_b, int parameters)
    {
        super(name);
        this.moduleInit(module_a.output_dim, module_a.output_buffer, module_b.input_dim, module_b.input_buffer, new SingleVector(parameters, this));
        this.module_a = module_a;
        this.module_b = module_b;
        return;
    }
    
    @Override
    public void clearBuffers()
    {
        return;
    }
    
}
