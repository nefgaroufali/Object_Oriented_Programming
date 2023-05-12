/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.io.*;
import java.util.Scanner;
import java.util.*;


/**
 *
 * @author nefga
 */
public class PPMImageStacker {
 
    ArrayList<PPMImage> ppmList;
    PPMImage stackedImg;
    
    
    public PPMImageStacker(java.io.File dir) throws UnsupportedFileFormatException, java.io.FileNotFoundException {
        
        ppmList = new ArrayList<PPMImage>();
        
        
        StringBuilder msg1 = new StringBuilder();
        
        msg1.append("[ERROR] Directory <");
        msg1.append(dir);
        msg1.append("> does not exist!");
        
        StringBuilder msg2 = new StringBuilder();
        
        msg2.append("[ERROR] <");
        msg2.append(dir);
        msg2.append("> is not a directory!");
        
        if(dir.exists() == false){
            throw new FileNotFoundException(msg1.toString());
        }
        
        if(dir.isDirectory() == false){
            throw new FileNotFoundException(msg2.toString());
        }
        
        
        File filesList[] = dir.listFiles();
        
        
        for(File file : filesList) {
            
            ppmList.add(new PPMImage(file));
            
        }
    }
    
    
    public void stack(){
        
        PPMImage metavasi;
        
        metavasi = ppmList.get(0);
        
        stackedImg = new PPMImage();
        
        stackedImg.width = metavasi.width;
        stackedImg.height = metavasi.height;
        stackedImg.magicNum = metavasi.magicNum;
        stackedImg.colordepth = metavasi.colordepth;
        
        
        stackedImg.eikona = new RGBPixel[stackedImg.height][stackedImg.width];
        
        int[][][] ppmpics;
        ppmpics = new int[ppmList.size()][][];
        
        
        for(int i=0; i<ppmList.size(); i++){
            
            metavasi = ppmList.get(i);
            
            ppmpics[i] = makeIntArray(metavasi);
            
        }
        
        
        int[][]array = new int[stackedImg.height][3*stackedImg.width];
        
        for(int i=0; i<stackedImg.height; i++){
            for(int j=0; j<3* stackedImg.width; j++){
                
                               
                int value = 0;
                
                for(int l=0; l<ppmList.size(); l++){
                    value = value + ppmpics[l][i][j];
                }
                
                value = value / ppmList.size();
                
                array[i][j] = value;
                
                //stackedImg.ppmimage[i][j] = value;
                
            }
        }
        
        int pj = 0;
        
        for(int i=0; i<stackedImg.height; i++){
            for(int j=0; j<stackedImg.width; j++){
                
                pj = 3*j;
                
                stackedImg.eikona[i][j] = new RGBPixel((short) array[i][pj], (short) array[i][pj+1], (short) array[i][pj+2]);
                
                
            }
        }
        
    }
    
    
    public PPMImage getStackedImage(){
        
        stack();
        
        
        return(this.stackedImg);
    }
       
    
    public int[][] makeIntArray(PPMImage metavasi){
        
        int[][] array = new int[metavasi.height][metavasi.width*3];
        
        int pj=0;
        
        for(int i=0; i<metavasi.height; i++){
            for(int j=0; j<metavasi.width; j++){
                
                pj = 3*j;
                
                array[i][pj] = metavasi.eikona[i][j].getRed();
                array[i][pj+1] = metavasi.eikona[i][j].getGreen();
                array[i][pj+2] = metavasi.eikona[i][j].getBlue();
            }
        }
        
        return(array);
    }
    
    
}
