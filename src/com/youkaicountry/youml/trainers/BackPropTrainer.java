package com.youkaicountry.youml.trainers;

import com.youkaicountry.youml.data.TrainingData;
import com.youkaicountry.youml.module.Module;

//TODO: have a Trainer superclass
public class BackPropTrainer
{
    private TrainingData td;
    private Module m;
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
    public double train()
    {
    	double[] error = new double[this.m.output_dim];
    	this.m.clearBuffers();
    	this.m.clearDerivs(0.0);
        for (int i = 0; i < td.size(); i++)
        {
        	double[] ic = this.td.getCaseInput(i);
        	double[] oc = this.td.getCaseOutput(i);
        	this.m.activate(ic);
            for (int j = 0; j < this.m.output_dim; j++)
            {
            	error[j] = oc[j]-m.output_buffer[j];
            	//System.out.println(m.output_buffer[j]);
            }
            this.m.backtivate(error, ic);
        }
        for (int i = 0; i < this.m.size(); i++)
        {
        	//System.out.println(this.m.getParam(i));
            this.m.setParam(i, (this.m.getDeriv(i)*this.learning_rate)+this.m.getParam(i));
        }
        return mean(error);
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
