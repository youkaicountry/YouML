package com.youkaicountry.youml.module.connection;

import com.youkaicountry.youml.module.Module;

public class FullConnection extends Connection
{
    public FullConnection(String name, Module module_a, int a_offset, int a_erroffset, Module module_b, int b_offset, int b_erroffset)
    {
        super(name, module_a, a_offset, a_erroffset, module_b, b_offset, b_erroffset, module_a.output_dim*module_b.input_dim);
        return;
    }
    
    public FullConnection(String name, Module module_a, Module module_b)
    {
        super(name, module_a, 0, 0, module_b, 0, 0, module_a.output_dim*module_b.input_dim);
        return;
    }
    
    @Override
    public void forwardProp(double[] input, double[] output)
    {
        int param = 0;
        for (int i = 0; i < this.input_dim; i++)
        {
            for (int j = 0; j < this.output_dim; j++)
            {
                output[j+this.output_offset] += input[i+this.input_offset] * this.getParam(param);
                param++;
            }
        }
        return;
    }

    @Override
    public void backProp(double[] outerr, double[] inerr, double[] output, double[] input)
    {
        int param = 0;
        double oe;
        double nd;
        for (int i = 0; i < this.input_dim; i++)
        {
            for (int j = 0; j < this.output_dim; j++)
            {
                oe = outerr[j + this.outerr_offset];
                inerr[i+this.inerr_offset] += oe * this.getParam(param);
                nd = this.getDeriv(param)+(oe*input[i+this.input_offset]);
                this.setDeriv(param, nd);
                param++;
            }
        }
        return;
    }

}
