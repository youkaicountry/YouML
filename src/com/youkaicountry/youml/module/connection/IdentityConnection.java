package com.youkaicountry.youml.module.connection;

import com.youkaicountry.youml.module.Module;

public class IdentityConnection extends Connection
{
    //TODO: Throw an exception if the 2 buffers being connected aren't the same size
    public IdentityConnection(String name, Module module_a, int a_offset, int a_erroffset, Module module_b, int b_offset, int b_erroffset)
    {
        super(name, module_a, a_offset, a_erroffset, module_b, b_offset, b_erroffset, module_a.output_dim*module_b.input_dim);
        return;
    }
    
    public IdentityConnection(String name, Module module_a, Module module_b)
    {
        super(name, module_a, 0, 0, module_b, 0, 0, 0);
        return;
    }

    @Override
    public void forwardProp(double[] input, double[] output)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            output[i+this.output_offset] = input[i+this.input_offset];
        }
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        for (int i = 0; i < this.input_dim; i++)
        {
            inerr[i+this.inerr_offset] += outerr[i+this.outerr_offset];
        }
        return;
    }

}
