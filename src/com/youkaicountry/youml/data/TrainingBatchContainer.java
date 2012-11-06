package com.youkaicountry.youml.data;

import java.util.ArrayList;

//TODO: Have a shuffle index function

public class TrainingBatchContainer 
{
	private ArrayList<TrainingBatch> td;
	private int current_batch;
	
	public TrainingBatchContainer()
	{
		this.td = new ArrayList<TrainingBatch>();
		this.current_batch = -1;
	}
	
	public void startNewBatch()
	{
		this.td.add(new TrainingBatch());
		this.current_batch++;
		return;
	}
	
	public void addCase(double[] input, double[] output)
	{
		if (this.current_batch < 0) {this.startNewBatch();}
		this.td.get(this.current_batch).addCase(input, output);
		return;
	}
	
	public double[] getCaseInput(int batch_index, int i)
    {
        return this.td.get(batch_index).getCaseInput(i);
    }
    
    public double[] getCaseOutput(int batch_index, int i)
    {
        return this.td.get(batch_index).getCaseOutput(i);
    }
	
	public int batches()
	{
		return this.td.size();
	}
	
	public int batchSize(int batch_index)
	{
		return this.td.get(batch_index).size();
	}
}
