/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ce326.hw2;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author nefga
 */

/*
public class HW2 {
    public static void main(String[] args) {
        
        
        RGBPixel rgbpix = new RGBPixel((short)89, (short)61, (short)200);
        rgbpix.setRGB(16257);
        System.out.println(rgbpix.getRed());
        System.out.println(rgbpix.getGreen());
        System.out.println(rgbpix.getBlue());
        
        /*
        File yuvFIN = new File("C:\\Users\\nefga\\Desktop\\lost.yuv");
        YUVImage yuv = new YUVImage(yuvFIN);
        
        PPMImage ppm_before = new PPMImage(yuv);
        
        File ppmbefore = new File("C:\\Users\\nefga\\Desktop\\ppmbefore.ppm");
        ppm_before.toFile(ppmbefore);
        
        Histogram histogram = new Histogram(yuv);
        System.out.println("Azygisto istogramma");
        System.out.print(histogram);
        
        yuv.equalize();
        
        Histogram histogram_after = new Histogram(yuv);
        System.out.println("Zygismeno istogramma");
        System.out.print(histogram_after);
        
        PPMImage ppm_after = new PPMImage(yuv);
        
        File ppmafter = new File("C:\\Users\\nefga\\Desktop\\ppmafter.ppm");
        ppm_after.toFile(ppmafter);
        
        histogram.equalize();
        System.out.println("Zygismeno me tin methodo istogramma");
        System.out.println(histogram);
        
        System.out.println(histogram.getEqualizedLuminocity(16));
        */
        
       /* 
        YUVImage yuv = null;
        PPMImage ppm = null;
        RGBImage rgb = null;
        PPMImageStacker ppmstack = null;
        
        File landscape_yuv = new File("C:\\Users\\nefga\\Desktop\\landscape.yuv");
        File landscape_16x8_ppm = new File("C:\\Users\\nefga\\Desktop\\landscape_16x8.ppm");
        File bar_200x133_ppm = new File("C:\\Users\\nefga\\Desktop\\bar_200x133.ppm");
        File dangerous_200x112_ppm = new File("C:\\Users\\nefga\\Desktop\\dangerous_200x112.ppm");
        File dark_room_200x133_ppm = new File("C:\\Users\\nefga\\Desktop\\dark-room_200x133.ppm");
        File dir = new File("C:\\Users\\nefga\\Desktop\\WFC3UVIS");
        
        
        File my_landscape_yuv = new File("C:\\Users\\nefga\\Desktop\\my_landscape.yuv");
        File my_landscape_16x8_ppm = new File("C:\\Users\\nefga\\Desktop\\my_landscape_16x8.ppm");
        File my_bar_200x133_ppm = new File("C:\\Users\\nefga\\Desktop\\my_bar_200x133.ppm");
        File my_dangerous_200x112_ppm = new File("C:\\Users\\nefga\\Desktop\\my_dangerous_200x112.ppm");
        File my_dark_room_200x133_ppm = new File("C:\\Users\\nefga\\Desktop\\my_dark-room_200x133.ppm");
        
        
        try{
            ppmstack = new PPMImageStacker(dir);
        }
        catch(FileNotFoundException ex){
            
        }
        catch(UnsupportedFileFormatException ex){
            
        }
        
        ppm = new PPMImage();
        ppm = ppmstack.getStackedImage();
        
        File stackedImg = new File("C:\\Users\\nefga\\Desktop\\stackedImg.ppm");
        ppm.toFile(stackedImg);
        
        
        
        /*
        try{
            
        ppm = new PPMImage(landscape_16x8_ppm);
        
        }
        catch(FileNotFoundException ex){
            
        }
        catch(UnsupportedFileFormatException ex){
            
        }
        
        
        ppm.doublesize();
        
        ppm.toFile(my_landscape_16x8_ppm);
        */
       /* 
    }
    
}






















/*
Scanner sc = new Scanner(System.in);
        
        System.out.println("Give the RGB colours: Red Green Blue");
        short red = sc.nextShort();
        short green = sc.nextShort();
        short blue = sc.nextShort();
        
        
        RGBPixel pixel = new RGBPixel(red, green, blue);
        
        System.out.println("Pixel based on your rgb values: " +pixel);
        
        RGBPixel Spixel = new RGBPixel(pixel);
        
        System.out.println("Pixel based on the first pixel: " +pixel);
       
        /*
        Spixel.getRGB();
        Spixel.getRed();
        Spixel.getGreen();
        Spixel.getBlue();
        */
        
        /*
        pixel.setRGB((short)124, (short)161, (short)240);
        int rgb = pixel.getRGB();
        
        System.out.println(rgb);
        
        
        pixel.setRGB(8167920);
        
        System.out.println("Pixel based on new rgb value: " +pixel);
        
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        RGBImage picture = new RGBImage(3,2,255);
        picture.setPixel(0, 1, pixel);
        
        RGBImage new_picture = new RGBImage(picture);
        RGBImage new_picture2 = new RGBImage(picture);
        new_picture.grayscale();
        
        picture.doublesize();
        picture.width = picture.width * 2;
        picture.height = picture.height * 2;
        
        picture.halfsize();
        picture.width = picture.width / 2;
        picture.height = picture.height / 2;
        
        picture.RotateClockwise();
        int proswrini = picture.width;
        picture.width = picture.height;
        picture.height = proswrini;
        
        File file = new File("C:\\Users\\nefga\\Documents\\NetBeansProjects\\ce326.hw2\\bar_200x133.ppm");
        PPMImage ppmpic = new PPMImage(file);
        
        //PPMImage new_pic = new PPMImage(new_picture2);
        
        RGBImage paradeigma = new RGBImage(3, 2, 255);
        
        RGBPixel pixel_p = new RGBPixel((short)255, (short)0, (short)0);
        paradeigma.setPixel(0, 0, pixel_p);
        
        pixel_p.setRGB((short)255, (short)255, (short)0);
        paradeigma.setPixel(0, 1, pixel_p);
        
        pixel_p.setRGB((short)0, (short)255, (short)255);
        paradeigma.setPixel(0, 2, pixel_p);
        
        pixel_p.setRGB((short)255, (short)0, (short)255);
        paradeigma.setPixel(1, 0, pixel_p);
        
        pixel_p.setRGB((short)0, (short)255, (short)0);
        paradeigma.setPixel(1, 1, pixel_p);
        
        pixel_p.setRGB((short)128, (short)128, (short)128);
        paradeigma.setPixel(1, 2, pixel_p);
        
        PPMImage paradeigma_p = new PPMImage(paradeigma);
        String ektypwsi = paradeigma_p.toString();
        
        System.out.println(ektypwsi);
        
        File fileOut = new File("C:\\Users\\nefga\\Desktop\\fileOut.ppm");
        paradeigma_p.toFile(fileOut);
        
        
        File dir = new File("C:\\Users\\nefga\\Desktop\\WFC3UVIS");
        PPMImageStacker test = new PPMImageStacker(dir);
        
        
        PPMImage stacked = new PPMImage();
        stacked = test.getStackedImage();
        
        File stackedF = new File("C:\\Users\\nefga\\Desktop\\stackedF.ppm");
        stacked.toFile(stackedF);
        
        
        YUVImage yuv = new YUVImage(paradeigma);
        YUVImage yuv2 = new YUVImage(yuv);
        YUVImage yuv3 = new YUVImage(3,2);
        
        File yuvFIN = new File("C:\\Users\\nefga\\Desktop\\landscape.yuv");
        YUVImage yuv4 = new YUVImage(yuvFIN);
        
        
        File yuvFOUT = new File("C:\\Users\\nefga\\Desktop\\yuvFOUT.ppm");
        yuv4.toFile(yuvFOUT);
        
        yuv4.equalize();
        yuv4.toFile(yuvFOUT);
*/