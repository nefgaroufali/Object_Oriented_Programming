/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author nefga
 */


public class Histogram {
    
    int[] histogram;
    int[] relate_luminocities;
    int arithmos_pixel;
    
    
    public Histogram(YUVImage img){
        
        int timiY;
        histogram = new int[236];
        arithmos_pixel = img.height * img.width;
        
        for(int i=0; i<img.height; i++){
            for(int j=0; j<img.width; j++){
                
                timiY = img.yuvimage[i][j].getY();
                
                histogram[timiY] = histogram[timiY] + 1;
                
            }
        }
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        
        String akeraios = new String();
        String str = new String();
        StringBuilder string = new StringBuilder();
        int chiliades;
        int ekatontades;
        int dekades;
        int monades;
        
        for(int i=0; i<this.histogram.length; i++){
            string.append("\n");
            akeraios = String.format("%3d", i);
            string.append(akeraios);
            string.append(".");
            string.append("(");
            akeraios = String.format("%4d", histogram[i]);
            string.append(akeraios);
            string.append(")");
            string.append("\t");
            
            chiliades = histogram[i] / 1000;
            ekatontades = (histogram[i] % 1000) / 100;
            dekades = ((histogram[i] % 1000) % 100) / 10;
            monades = ((histogram[i] % 1000) % 100) % 10;
            
            for(int j=0; j<chiliades; j++){
                string.append("#");
            }
            
            for(int j=0; j<ekatontades; j++){
                string.append("$");
            }
            
            for(int j=0; j<dekades; j++){
                string.append("@");
            }
            
            for(int j=0; j<monades; j++){
                string.append("*");
            }
            
            
        }
        
        string.append("\n");
        
        return(string.toString());
    }
    
    
    public void toFile(File file){
        
        
        try{
            if(file.exists() && file.length() != 0){
                PrintWriter pw = new PrintWriter(file.getPath());
                pw.print("");
                
                pw.write(this.toString());
                
                pw.close();
                
                //System.out.println(file.getPath());
            }
            else{
                PrintWriter pw = new PrintWriter(file.getPath());
                //System.out.println(file.getPath());
            
                pw.write(this.toString());
                pw.close();
            }
            //System.out.println(this.toString());
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    public void equalize(){
        
        int timiY;
        
        int[] pinakas = new int[236];
        int[] equalized_histogram = new int[236];
        double[][] probs = new double[3][236];
        
        //System.out.println("PITHANOTHTA");
        for(int j=0; j<236; j++){
            probs[0][j] = (double)this.histogram[j] / (double)arithmos_pixel;
        }
        
        probs[1][0] = probs[0][0];
        
        //System.out.println("ATHR. PITHANOTHTA");
        for(int j=1; j<236; j++){
            probs[1][j] = probs[1][j-1] + probs[0][j];
            //System.out.println(probs[1][j]);
        }
        
        //System.out.println("KANONIKOPOIISI");
        for(int j=0; j<236; j++){
            probs[2][j] = probs[1][j] * (double)235;
            //System.out.println(probs[2][j]);
        }
        
        //System.out.println("TELIKI TIMI Y");
        for(int j=0; j<236; j++){
            pinakas[j] = (int) (probs[2][j]);
            //System.out.println(arx_telY[1][j]);
        }
        
        this.relate_luminocities = pinakas;
        
        for(int j=0; j<236; j++){
            if(histogram[j] == 0){
                if(equalized_histogram[j] == 0){
                    equalized_histogram[j] = 0;
                }
            }
            else{
                equalized_histogram[pinakas[j]] = histogram[j] + equalized_histogram[pinakas[j]];
            }
        }
        
        histogram = equalized_histogram;
        
        
    }
    
    
    public short getEqualizedLuminocity(int luminocity){
        
        short new_luminocity;
        
        if(this.relate_luminocities == null){
            this.equalize();
        }
        
        new_luminocity = (short) this.relate_luminocities[luminocity];
        
        return(new_luminocity);
    }
    
    
}
