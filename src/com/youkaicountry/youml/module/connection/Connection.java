/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.module.connection;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.parameter.SingleVector;

public abstract class Connection extends Module
{
    public Module module_a;
    public Module module_b;
    
    public Connection(String name, Module module_a, int a_offset, int a_erroffset, Module module_b, int b_offset, int b_erroffset, int parameters)
    {
        super(name);
        this.moduleInit(module_a.output_dim, module_a.output_buffer, a_offset, module_a.output_error_buffer, b_erroffset, module_b.input_dim, module_b.input_buffer, b_offset, module_b.input_error_buffer, b_erroffset, new SingleVector(parameters, true, this));
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
