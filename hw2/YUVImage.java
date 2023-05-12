/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.util.*;
import java.io.*;

/**
 *
 * @author nefga
 */


public class YUVImage {
    
    String magicNum = new String();
    public int width;
    public int height;
    
    YUVPixel[][] yuvimage;
    
    
    public YUVImage(){
        
    }
    
    public YUVImage(int width, int height){
        
        this.width = width;
        this.height = height;
        this.magicNum = "YUV3";
        
        this.yuvimage = new YUVPixel[height][width];
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                
                this.yuvimage[i][j] = new YUVPixel((short)16, (short)128, (short)128);
            }
        }
        
        
    }
    
    public YUVImage(YUVImage copyImg){
        
        this.width = copyImg.width;
        this.height = copyImg.height;
        this.magicNum = copyImg.magicNum;
        
        this.yuvimage = new YUVPixel[height][width];
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                
                this.yuvimage[i][j] = new YUVPixel(copyImg.yuvimage[i][j]);
                
            }
        }
        
        
    }
    
    
    
    
    public YUVImage(java.io.File file) throws UnsupportedFileFormatException, java.io.FileNotFoundException{
        
        int i = 0;
        int j = 0;
        
        int y;
        int u;
        int v;
        
        
        Scanner sc = new Scanner(file);


        if(file.exists() == false){
            throw new java.io.FileNotFoundException();
        }


        while(sc.hasNext()){
            this.magicNum = sc.next();
            this.width = sc.nextInt();
            this.height = sc.nextInt();


            this.yuvimage = new YUVPixel[height][width];


            if(magicNum.equals("YUV3")==false){
                throw new UnsupportedFileFormatException();
            }



            while(sc.hasNext()){
                for(i=0; i<height; i++){
                    for(j=0; j<width; j++){

                        y = sc.nextInt();
                        u = sc.nextInt();
                        v = sc.nextInt();

                        this.yuvimage[i][j] = new YUVPixel((short)y, (short)u, (short)v);
                    }
                }  
            }

        }

            
    }
    
    
    
    public YUVImage(RGBImage img){
        
        int i = 0;
        int j = 0;
        
        this.magicNum = "YUV3";
        this.width = img.width;
        this.height = img.height;
        
                
        yuvimage = new YUVPixel[height][width];
        
        
        for(i=0; i<img.height; i++){
            for(j=0; j<img.width; j++){
                
                if(img.eikona[i][j] != null){
                    
                    this.yuvimage[i][j] = new YUVPixel(img.eikona[i][j]);
                    
                }
            }
        }
          
    }
    
    
    @Override
    public String toString(){
        
        StringBuilder  str = new StringBuilder();
        String string = new String();
        
        str.append(this.magicNum);
        str.append(" \n");
        str.append(this.width);
        str.append(" ");
        str.append(this.height);
        str.append(" \n");
        
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                str.append(this.yuvimage[i][j].getY());
                str.append(" ");
                str.append(this.yuvimage[i][j].getU());
                str.append(" ");
                str.append(this.yuvimage[i][j].getV());
                
                str.append("\n");
            }
            //str.append("\n");
        }
        
        str.append("\n");
        
        string = str.toString();
     
        
        return(string);
    }
    
    
    
    public void toFile(java.io.File file){
        
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
        int[][] arx_telY = new int[2][236];
        double[][] probs = new double[3][236];
        
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                
                timiY = this.yuvimage[i][j].getY();
                
                arx_telY[0][timiY] = arx_telY[0][timiY] + 1;
                
            }
        }
        
        //System.out.println("PITHANOTHTA");
        for(int j=0; j<236; j++){
            probs[0][j] = (double)arx_telY[0][j] / (double)(this.height*this.width);
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
            arx_telY[1][j] = (int) (probs[2][j]);
            //System.out.println(arx_telY[1][j]);
        }
        
        
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                
                timiY = this.yuvimage[i][j].getY();
                
                this.yuvimage[i][j].setY((short) arx_telY[1][timiY]);
                
            }
        }
        
    }
    
}