/*******************************************************************************
 * Copyright (c) <2012> <Nathaniel Caldwell>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.youkaicountry.youml.trainers;

import com.youkaicountry.youml.data.TrainingBatch;
import com.youkaicountry.youml.module.Module;
import com.youkaicountry.youml.utils.GradientDescent;

public class BackPropTrainer extends Trainer
{
    private double learning_rate;
    private GradientDescent gd;
    
    public BackPropTrainer(Module m, double alpha, double alpha_decay, double momentum)
    {
    	super(m);
        this.gd = new GradientDescent(m, alpha, alpha_decay, momentum);
        return;
    }
    
    //trains for 1 epoch
    //returns average error
    @Override
    public double implTrainBatch(TrainingBatch tb)
    {
    	double[] error = new double[this.m.output_dim];
    	double error_sum = 0;
    	this.m.clearBuffers();
    	this.m.clearDerivs(0.0);
    	error_sum = 0.0;
        for (int i = 0; i < tb.size(); i++)
        {
        	double[] ic = tb.getCaseInput(i);
        	double[] oc = tb.getCaseOutput(i);
        	this.m.clearBuffers();
        	this.m.activate(ic);
            for (int j = 0; j < this.m.output_dim; j++)
            {
            	double res_error = oc[j]-m.output_buffer[j];
            	error[j] = res_error;
            	error_sum += Math.abs(res_error);
            }
            this.m.backtivate(error, tb.getCaseInput(0));
        }
        
        //for (int i = 0; i < this.m.size(); i++)
        //{
        	//double dw = this.learning_rate*this.m.getDeriv(i);
        	//this.m.setParam(i, this.m.getParam(i)+dw);
        	//this.m.addParam(i, dw);
        //}
        this.gd.descend();
        return error_sum / tb.size();
    }
    
    @Override
    public double implTrainOnline(TrainingBatch tb)
    {
    	double[] error = new double[this.m.output_dim];
    	double error_sum = 0;
    	this.m.clearBuffers();
    	this.m.clearDerivs(0.0);
    	error_sum = 0.0;
        for (int i = 0; i < tb.size(); i++)
        {
        	double[] ic = tb.getCaseInput(i);
        	double[] oc = tb.getCaseOutput(i);
        	this.m.clearBuffers();
        	this.m.clearDerivs(0.0);
        	this.m.activate(ic);
            for (int j = 0; j < this.m.output_dim; j++)
            {
            	double res_error = oc[j]-m.output_buffer[j];
            	error[j] = res_error;
            	error_sum += Math.abs(res_error);
            }
            this.m.backtivate(error, tb.getCaseInput(0));
            
            //for (int k = 0; k < this.m.size(); k++)
            //{
                // delta weight, this parameter's value of the gradient times
                // the learning rate
            	//double dw = this.learning_rate*this.m.getDeriv(k);
            	//this.m.setParam(k, this.m.getParam(k)+dw);
            	//this.m.addParam(i, dw);
                
            //}
            this.gd.descend();
        }
        return error_sum / tb.size();
    }
}
