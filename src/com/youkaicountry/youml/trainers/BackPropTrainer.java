package com.youkaicountry.youml.trainers;

import java.util.Arrays;

import com.youkaicountry.youml.data.TrainingData;
import com.youkaicountry.youml.module.Module;

//TODO: have a Trainer superclass
public class BackPropTrainer
{
    private TrainingData td;
    public Module m;
    private double learning_rate;
    
    public BackPropTrainer(Module m, TrainingData td, double learning_rate)
    {
        this.m = m;
        this.td = td;
        this.learning_rate = learning_rate;
        return;
    }
    
    //trains for 1 epoch
    //returns average error
    //TODO: Maybe have a method which runs and returns error
    public double trainBatch()
    {
    	double[] error = new double[this.m.output_dim];
    	double[] set_error = new double[this.td.size()];
    	double error_sum = 0;
    	this.m.clearBuffers();
    	this.m.clearDerivs(0.0);
    	error_sum = 0.0;
        for (int i = 0; i < td.size(); i++)
        {
        	double[] ic = this.td.getCaseInput(i);
        	double[] oc = this.td.getCaseOutput(i);
        	this.m.clearBuffers();
        	//Arrays.fill(error, 0);
        	this.m.activate(ic);
            for (int j = 0; j < this.m.output_dim; j++)
            {
            	double res_error = oc[j]-m.output_buffer[j];
            	error[j] = res_error;
            	error_sum += Math.abs(res_error);
            	//System.out.println(res_error);
            	//System.out.println(m.output_buffer[j]);
            }
            this.m.backtivate(error, this.td.getCaseInput(0));
        }
        
        for (int i = 0; i < this.m.size(); i++)
        {
        	//System.out.println(this.m.getParam(i));
            //this.m.setParam(i, (this.m.getDeriv(i)*this.learning_rate)+this.m.getParam(i));
        	double dw = this.learning_rate*this.m.getDeriv(i);
        	//System.out.println(dw);
        	this.m.setParam(i, this.m.getParam(i)+dw);
        }
        return error_sum;
    }
    
    public double train()
    {
    	double[] error = new double[this.m.output_dim];
    	double[] set_error = new double[this.td.size()];
    	double error_sum = 0;
    	this.m.clearBuffers();
    	this.m.clearDerivs(0.0);
    	error_sum = 0.0;
        for (int i = 0; i < td.size(); i++)
        {
        	double[] ic = this.td.getCaseInput(i);
        	double[] oc = this.td.getCaseOutput(i);
        	this.m.clearBuffers();
        	this.m.clearDerivs(0.0);
        	//Arrays.fill(error, 0);
        	this.m.activate(ic);
            for (int j = 0; j < this.m.output_dim; j++)
            {
            	double res_error = oc[j]-m.output_buffer[j];
            	error[j] = res_error;
            	error_sum += Math.abs(res_error);
            	//System.out.println(res_error);
            	//System.out.println(m.output_buffer[j]);
            }
            this.m.backtivate(error, this.td.getCaseInput(0));
            
            for (int k = 0; k < this.m.size(); k++)
            {
            	//System.out.println(this.m.getParam(i));
                //this.m.setParam(i, (this.m.getDeriv(i)*this.learning_rate)+this.m.getParam(i));
            	double dw = this.learning_rate*this.m.getDeriv(k);
            	//System.out.println(dw);
            	this.m.setParam(k, this.m.getParam(k)+dw);
            }
        }
        
        
        return error_sum;
    }
    
    private double mean(double[] d) 
    {
        double sum = 0;  // sum of all the elements
        for (int i=0; i<d.length; i++) {
            sum += d[i];
        }
        return sum / d.length;
    }
}
