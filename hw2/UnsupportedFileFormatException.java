/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.io.File;
import java.io.*;

/**
 *
 * @author nefga
 */
public class UnsupportedFileFormatException extends java.lang.Exception {
    String msg;
    
    public UnsupportedFileFormatException(){
        
    }
    
    public UnsupportedFileFormatException(String msg){
        this.msg = msg;
    }
    
}
