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
public class PPMImage extends RGBImage {
    
    String magicNum = new String();
    //int width;
    //int height;
    int colordepth;
    
    //int[][] ppmimage;
    
    //File writefile;
    
    public PPMImage(){
        
    }
    
    
    public PPMImage(java.io.File file) throws UnsupportedFileFormatException, java.io.FileNotFoundException{
        
        int i = 0;
        int j = 0;
        
        

        if(file.exists() == false){
            throw new java.io.FileNotFoundException();
        }

        Scanner sc = new Scanner(file);



        while(sc.hasNext()){
            this.magicNum = sc.next();
            this.width = sc.nextInt();
            this.height = sc.nextInt();
            this.colordepth = sc.nextInt();

            this.eikona = new RGBPixel[height][width];


            if(magicNum.equals("P3") == false){
                throw new UnsupportedFileFormatException();
            }


            while(sc.hasNext()){
                for(i=0; i<height; i++){
                    for(j=0; j<width; j++){
                        
                        this.eikona[i][j] = new RGBPixel();
                        
                        this.eikona[i][j].setRed((short) sc.nextInt());
                        this.eikona[i][j].setGreen((short) sc.nextInt());
                        this.eikona[i][j].setBlue((short) sc.nextInt());
                    }
                }  
            }

        }
        
        
    }
    
    
    public PPMImage(RGBImage img){
        
        super(img);
        
        magicNum = "P3";
        colordepth = img.ColorDepth;
          
    }
    
    
    public PPMImage(YUVImage img){
        
        super(img);
        
        magicNum = "P3";
        
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
        str.append(this.colordepth);
        str.append(" \n");
        
        
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                str.append(this.eikona[i][j].getRed());
                str.append(" ");
                str.append(this.eikona[i][j].getGreen());
                str.append(" ");
                str.append(this.eikona[i][j].getBlue());
                str.append(" ");
            }
            str.append("\n");
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
    
    
    
}
