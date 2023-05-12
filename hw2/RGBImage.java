/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

/**
 *
 * @author nefga
 */
public class RGBImage {
    
    public int width;
    public int height;
    int ColorDepth;
    public static final int MAX_COLORDEPTH = 255;
    
    RGBPixel[][] eikona;
    
    
    
    public RGBImage(){
        
    }
    
    public RGBImage(int width, int height, int colordepth){
        this.width = width;
        this.height = height;
        this.ColorDepth = colordepth;
        
        eikona = new RGBPixel[height][width];
        
        
    }
    
    public RGBImage(RGBImage copyImg){
        this.width = copyImg.width;
        this.height = copyImg.height;
        this.ColorDepth = copyImg.ColorDepth;
        
        this.eikona = new RGBPixel[height][width];
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(copyImg.eikona[i][j] != null){
                    this.eikona[i][j] = new RGBPixel(copyImg.eikona[i][j]);
                }
            }
        }
        
        
    }
    
   
    public RGBImage(YUVImage YUVImg){
        
        this.width = YUVImg.width;
        this.height = YUVImg.height;
        this.ColorDepth = 255;
        
        this.eikona = new RGBPixel[this.height][this.width];
        
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                if(YUVImg.yuvimage[i][j] != null){
                    this.eikona[i][j] = new RGBPixel(YUVImg.yuvimage[i][j]);
                }
            }
        }
        
        
    }
    
    
    int getWidth(){
        return this.width;
    }
    
    int getHeight(){
        return this.height;
    }
    
    int getColorDepth(){
        return MAX_COLORDEPTH;
    }
    
    
    void setPixel(int row, int col, RGBPixel pixel){
        if(this.eikona[row][col] != null){
            this.eikona[row][col] = pixel;
        }
        else{
            this.eikona[row][col] = new RGBPixel(pixel);
        }
    }
    
    
    public void grayscale(){
        
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(eikona[i][j] != null){
                   short red = eikona[i][j].getRed();
                   short green = eikona[i][j].getGreen();
                   short blue = eikona[i][j].getBlue();
                   
                   short gray = (short)(red * 0.3 + green * 0.59 + blue * 0.11);
                   
                   eikona[i][j].setRed(gray);
                   eikona[i][j].setGreen(gray);
                   eikona[i][j].setBlue(gray);
                   
                }
            }
        }
        
        
    }
    
    public void doublesize(){
        
        int new_height = height * 2;
        int new_width = width * 2;
        
        RGBImage new_image = new RGBImage(new_width, new_height, ColorDepth);
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(eikona[i][j] != null){
                    //new_image.eikona[i][j] = new RGBPixel(eikona[i][j]);
                    new_image.eikona[2*i][2*j] = new RGBPixel(eikona[i][j]);
                    new_image.eikona[2*i+1][2*j] = new RGBPixel(eikona[i][j]);
                    new_image.eikona[2*i][2*j+1] = new RGBPixel(eikona[i][j]);
                    new_image.eikona[2*i+1][2*j+1] = new RGBPixel(eikona[i][j]);
                }
            }
        }
        
        eikona = new_image.eikona;
        this.width = new_width;
        this.height = new_height;
        
    }
    
    public void halfsize(){
        
        int new_height = height / 2;
        int new_width = width / 2;
        
        short red = 0;
        short green = 0;
        short blue = 0;
        
        
        RGBImage new_image = new RGBImage(new_width, new_height, ColorDepth);
        
        for(int i=0; i<new_height; i++){
            for(int j=0; j<new_width; j++){
                if(eikona[2*i][2*j] != null){
                    if(eikona[2*i][2*j] != null){
                        red = eikona[2*i][2*j].getRed();
                        green = eikona[2*i][2*j].getGreen();
                        blue = eikona[2*i][2*j].getBlue();
                        
                    }
                    if(eikona[2*i+1][2*j] != null){
                        red = (short) (red + eikona[2*i+1][2*j].getRed());
                        green = (short) (green + eikona[2*i+1][2*j].getGreen());
                        blue = (short) (blue + eikona[2*i+1][2*j].getBlue());
                    }
                    if(eikona[2*i][2*j+1] != null){
                        red = (short) (red + eikona[2*i][2*j+1].getRed());
                        green = (short) (green + eikona[2*i][2*j+1].getGreen());
                        blue = (short) (blue + eikona[2*i][2*j+1].getBlue());
                    }
                    if(eikona[2*i+1][2*j+1] != null){
                        red = (short) (red + eikona[2*i+1][2*j+1].getRed());
                        green = (short) (green + eikona[2*i+1][2*j+1].getGreen());
                        blue = (short) (blue + eikona[2*i+1][2*j+1].getBlue());
                    }

                    red = (short) (red/4);
                    green = (short) (green/4);
                    blue = (short) (blue/4);

                    new_image.eikona[i][j] = new RGBPixel();
                    new_image.eikona[i][j].setRGB(red, green, blue);
                }
            }
        }
        
        eikona = new_image.eikona;
        this.width = new_width;
        this.height = new_height;
        
        
    }
    
    
    public void rotateClockwise(){
        
        int new_height = width;
        int new_width = height;
        
        RGBImage new_image = new RGBImage(new_width, new_height, ColorDepth);
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(eikona[i][j] != null){
                    new_image.eikona[j][height - 1 - i] = new RGBPixel(eikona[i][j]);
                }
            }
        }
        
        eikona = new_image.eikona;
        this.width = new_width;
        this.height = new_height;
        
    }
    
    
    RGBPixel getPixel(int row, int col){
        return(this.eikona[row][col]);
    }
        
        
}
    

