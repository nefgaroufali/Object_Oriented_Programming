/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

/**
 *
 * @author nefga
 */
public class RGBPixel {
    
    private int rgb_value;
    byte r_value;
    byte g_value;
    byte b_value;
    
    
    public RGBPixel(){
        
    }
    
    public RGBPixel(short red, short green, short blue){
        r_value = (byte) (red - 128);
        g_value = (byte) (green - 128);
        b_value = (byte) (blue - 128);
        
        rgb_value = (int) red;
        
        rgb_value = rgb_value * 256;
        rgb_value = rgb_value + (int) green;
        rgb_value = rgb_value * 256;
        rgb_value = rgb_value + (int) blue;
        
    }
    
    public RGBPixel(RGBPixel pixel){
        r_value = pixel.r_value;
        g_value = pixel.g_value;
        b_value = pixel.b_value;
        
        rgb_value = pixel.rgb_value;
    }
    
    
    public int clip(int arithmos){
        
        if(arithmos < 0){
            return(0);
        }
        else if(arithmos > 255){
            return(255);
        }
        
        return(arithmos);
    }
    
    public RGBPixel(YUVPixel pixel){
        
        int c = pixel.getY() - 16;
        int d = pixel.getU() - 128;
        int e = pixel.getV() - 128;
        
        
        this.r_value = (byte) (clip((298*c + 409*e + 128)>>8) - 128);
        this.g_value = (byte) (clip((298*c - 100*d - 208*e + 128)>>8) - 128);
        this.b_value = (byte) (clip((298*c + 516*d + 128)>>8) - 128);
        
        
        setRGB((short)(r_value+128),(short)(g_value+128), (short)(b_value+128));
        
    }
    
    
    public short getRed(){
        short red = r_value;
        red = (short) (red + 128);
        
        return red;
    }
    
    public short getGreen(){
        short green = g_value;
        green = (short) (green + 128);
        
        return green;
    }
    
    public short getBlue(){
        short blue = b_value;
        blue = (short) (blue + 128);
        
        return blue;
    }
    
    public void setRed(short red){
        r_value = (byte) (red - 128);
        
        setRGB(red, (short) (g_value +128), (short) (b_value+128));
    }
    
    public void setGreen(short green){
        g_value = (byte) (green - 128);
        
        setRGB((short) (r_value+128), green, (short) (b_value+128));
    }
    
    public void setBlue(short blue){
        b_value = (byte) (blue - 128);
        
        setRGB((short) (r_value+128), (short) (g_value+128), blue);
    }
    
    public int getRGB(){
        return rgb_value;
    }
    
    public void setRGB(int value){
        rgb_value = value;
        
        b_value = (byte) ((rgb_value % 256) - 128);
        
        //System.out.println("Value for b is: "+ b);
        
        int rg = rgb_value / 256;
        g_value = (byte) ((rg % 256) - 128);
        
        //System.out.println("Value for g is: "+ g);
        
        r_value = (byte) ((rg / 256) - 128);
        
        
    }
    
    public final void setRGB(short red, short green, short blue){
        rgb_value = (int) red;
        
        rgb_value = rgb_value * 256;
        rgb_value = rgb_value + (int) green;
        rgb_value = rgb_value * 256;
        rgb_value = rgb_value + (int) blue;
        
        setRGB(rgb_value);
    }
    
    
    @Override
    public String toString(){
        short red = getRed();
        short green = getGreen();
        short blue = getBlue();
        
        
        return ( red + " " + green + " " + blue );
    }
    
}
