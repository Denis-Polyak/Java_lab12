package com.example.web117.beans;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@Component
@Getter
@SessionScope
public class TabulationBean {
    private List<Point> points;

    public int steps(double end, double start, double step){
        return (int) round ((end-start)/step)+1;
    }
    public double[] massiveX (double end, double start, double step) {
        double [] arr = new double[steps(end, start, step)];
        for (int i = 0; i < arr.length; i++)
            arr[i] = start + step*i;
        return arr;
    }
    public double calcY (double x, double a, double p){
        double y, eps = 0.0001;
        if(x < 1.3 - eps)
            y = p*x*x-(7/(x*x));
        else if (x > 1.3 + eps)
            y = Math.log(x+7*Math.sqrt(Math.abs(x+a)));
        else
            y = a * Math.pow(x,3)+7*Math.sqrt(x);
        return y;
    }
    public double[] massiveY (double [] arr, double a, double p) {
        double [] ara = new double[arr.length];
        for (int i = 0; i < ara.length; i++)
            ara[i] = calcY(arr[i], a, p);
        return ara;
    }
    public int maxY (double [] ara) {
        int maxi = 0;
        double max = ara[0];
        for (int i = 1; i < ara.length; i++)
            if (ara[i] > max) {
                max = ara[i];
                maxi = i;
            }
        return maxi;
    }
    public double getMaxY(double[] ara) {
        int maxY = maxY(ara);
        return ara[maxY];
    }
    public double getMaxX(double[] ara, double[] arr) {
        int maxY = maxY(ara);
        return arr[maxY];
    }
    public int minY (double [] ara) {
        int mini = 0;
        double min = ara[0];
        for (int i = 1; i < ara.length; i++)
            if (ara[i] < min) {
                min = ara[i];
                mini = i;
            }
        return mini;
    }
    public double getMinY(double[] ara) {
        int minY = minY(ara);
        return ara[minY];
    }
    public double getMinX(double[] ara, double[] arr) {
        int minY = minY(ara);
        return arr[minY];
    }
    public double sum (double [] ara) {
        double sum = 0.0;
        for (int i = 0; i < ara.length; i++)
            sum += ara[i];
        return sum;
    }
    public double arifm (double [] ara) {
        double sum = sum(ara);
        double average = 0;
        average = sum / ara.length;
        return average;
    }



    public void fillPoints(double start, double end, double step, double a){
        points = new ArrayList<Point>();
        int n = (int) round((end - start)/step +1);
        for (int i = 0; i<n;i++){
            double x = start  + i * step;
            double y = f(x);
            points.add(new Point(x,y));
        }
    }

    private double f(double x) {
        return Math.sin(x);
    }

}
