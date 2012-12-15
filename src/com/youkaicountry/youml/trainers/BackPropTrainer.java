package com.youkaicountry.youml.trainers;

import com.youkaicountry.youml.data.TrainingBatch;
import com.youkaicountry.youml.module.Module;

public class BackPropTrainer extends Trainer
{
    private double learning_rate;
    
    public BackPropTrainer(Module m, double learning_rate)
    {
    	super(m);
        this.learning_rate = learning_rate;
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
        
        for (int i = 0; i < this.m.size(); i++)
        {
        	double dw = this.learning_rate*this.m.getDeriv(i);
        	//this.m.setParam(i, this.m.getParam(i)+dw);
        	this.m.addParam(i, dw);
        }
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
            
            for (int k = 0; k < this.m.size(); k++)
            {
                // delta weight, this parameter's value of the gradient times
                // the learning rate
            	double dw = this.learning_rate*this.m.getDeriv(k);
            	//this.m.setParam(k, this.m.getParam(k)+dw);
            	this.m.addParam(i, dw);
            }
        }
        return error_sum / tb.size();
    }
}
