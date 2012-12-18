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
