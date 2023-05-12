/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

/**
 *
 * @author nefga
 */
public class YUVPixel {
    
    private short y_value;
    private short u_value;
    private short v_value;
    
    
    public YUVPixel(){
        
    }
    
    
    public YUVPixel(short Y, short U, short V){
        
        y_value = Y;
        u_value = U;
        v_value = V;
        
    }
    
    
    public YUVPixel(YUVPixel pixel){
        this.y_value = pixel.y_value;
        this.u_value = pixel.u_value;
        this.v_value = pixel.v_value;
        
    }
    
    
    public YUVPixel(RGBPixel pixel){
        
        y_value = (short) ((short)((66*pixel.getRed() + 129*pixel.getGreen() + 25*pixel.getBlue() + 128)>>8) + 16);
        u_value = (short) ((short)((-38*pixel.getRed() - 74*pixel.getGreen() + 112*pixel.getBlue() + 128)>>8) + 128);
        v_value = (short) ((short)((112*pixel.getRed() - 94*pixel.getGreen() - 18*pixel.getBlue() + 128)>>8) + 128);
        
    }
    
    
    public short getY(){
        return(this.y_value);
    }
    
    public short getU(){
        return(this.u_value);
    }
    
    public short getV(){
        return(this.v_value);
    }
    
    
    public void setY(short Y){
        this.y_value = Y;
    }
    
    public void setU(short U){
        this.u_value = U;
    }
    
    public void setV(short V){
        this.v_value = V;
    }
    
    
    
}
