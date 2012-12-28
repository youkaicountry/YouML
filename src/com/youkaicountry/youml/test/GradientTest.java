package com.youkaicountry.youml.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.youkaicountry.youml.parameter.SingleVector;
import com.youkaicountry.youml.utils.GradientDescent;

// sage plots for functions of interest:
// Has a new 
// plot(0.0572*cos(4.667*x)+0.0218*cos(12.22*x), (x, 0, 2));
// plot(0.0572*cos(4.667*x)+0.0218*cos(12.22*x), (x, 8, 9.4));

// function
// 0.0572*cos(4.667*x)+0.0218*cos(12.22*x)
// derivative
// -0.2669524*sin(4.667*x) - 0.266396*sin(12.22*x)

public class GradientTest
{
    @Test
    public void test_descent()
    {
        SingleVector sv = new SingleVector(1, true, null);
        GradientDescent gd = new GradientDescent(sv, .1, 1.0, 0.0);
        this.dim1_test(gd, 1.4, 150, 1.25, .005);
        return;
    }
    
    @Test
    public void test_momentum()
    {
        SingleVector sv = new SingleVector(1, true, null);
        GradientDescent gd = new GradientDescent(sv, .1, 1.0, 0.9);
        this.dim1_test(gd, 1.4, 150, .745, .005);
        
        return;
    }
    
    private void dim1_test(GradientDescent gd, double param, int iterations, double target_val, double delta)
    {
        gd.pv.clearParams(0);
        gd.pv.clearDerivs(0);
        gd.pv.setParam(0, param);
        
        //GradientDescent gd = new GradientDescent(sv, .1, 1.0, 0.0);
        for (int i = 0; i < 150; i++)
        {
            gd.pv.setDeriv(0, -this.hills_deriv(gd.pv.getParam(0)));
            gd.descend();
            System.out.println(gd.pv.getParam(0));
        }
        //System.out.println(sv.getParam(0));
        assertEquals(target_val, gd.pv.getParam(0), delta);
    }
    
    @SuppressWarnings("unused")
    private double hills(double x)
    {
        return 0.0572*Math.cos(4.667*x)+0.0218*Math.cos(12.22*x);
    }
    
    @SuppressWarnings("unused")
    private double hills_deriv(double x)
    {
        return -0.2669524*Math.sin(4.667*x) - 0.266396*Math.sin(12.22*x);
    }
}
