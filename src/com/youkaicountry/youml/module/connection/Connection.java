package com.youkaicountry.youml.module.connection;
import com.youkaicountry.youml.module.Module;

public abstract class Connection extends Module
{
    public Module module_a;
    public Module module_b;
    
    public Connection(String name, Module module_a, Module module_b, int parameters)
    {
        super(name, module_a.output_dim, module_b.input_dim, parameters);
        this.module_a = module_a;
        this.module_b = module_b;
        return;
    }
    
}
