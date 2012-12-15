package com.youkaicountry.youml.trainers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.youkaicountry.youml.data.TrainingBatch;
import com.youkaicountry.youml.module.Module;

//TODO: Have a method which runs a validation set and returns error
//TODO: Make logs an abstract class or interface
public abstract class Trainer
{
    public Module m;
    private HashMap<String, ArrayList<Double> > logs;
    private String current_log = null;
    private boolean logging_enabled = false;
    
    public Trainer(Module m)
    {
        this.m = m;
        initLogs();
        return;
    }
    
    public void enableLogging(boolean yes)
    {
        this.logging_enabled = yes;
        return;
    }
    
    public void logData(double value)
    {
        this.logs.get(this.current_log).add(value);
        return;
    }
    
    public ArrayList<Double> getLog()
    {
        return this.logs.get(current_log);
    }

    public void changeLog(String log_name)
    {
        if (!this.logs.containsKey(log_name))
        {
            this.logs.put(log_name, new ArrayList<Double>());
        }
        this.current_log = log_name;
        return;
    }

    public void clearLogs()
    {
        Iterator<String> it = this.logs.keySet().iterator();
        while (it.hasNext())
        {
            logs.get(it.next()).clear();
        }
        return;
    }

    public void initLogs()
    {
        this.logs = new HashMap<String, ArrayList<Double> >();
        return;
    }
    
    public double trainBatch(TrainingBatch tb)
    {
        double val = this.implTrainBatch(tb);
        if (this.logging_enabled)
        {
            this.logData(val);
        }
        return val;
    }
    
    public double trainOnline(TrainingBatch tb)
    {
        double val = this.implTrainOnline(tb);
        if (this.logging_enabled)
        {
            this.logData(val);
        }
        return val;
    }
    
    public abstract double implTrainBatch(TrainingBatch tb);

    public abstract double implTrainOnline(TrainingBatch tb);
}
