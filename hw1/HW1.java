/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

import java.util.Scanner;
/**
 *
 * @author nefga
 */
public class HW1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String leksi;
        boolean epistrofi;
        int akeraios;
        StringBuilder word = new StringBuilder();
        
        Trie trie = new Trie();
        
        
        
        while(sc.hasNext()){
            leksi = sc.next();
            
            System.out.println("?: ");
            
            if(leksi.equals("-i")){
                leksi = sc.next();
                if( ((leksi != null) && (!leksi.equals("")) && (leksi.matches("^[a-zA-Z]*$"))) == false ){
                    continue;
                }
            
                leksi = leksi.toLowerCase();
                word.delete(0, word.length());
                word.append(leksi);
                epistrofi = trie.search(word);
                if(epistrofi == false){
                    System.out.print("ADD ");
                    System.out.print(word);
                    System.out.println(" OK");
                    trie.insert(word);
                }
                else{
                    System.out.print("ADD ");
                    System.out.print(word);
                    System.out.println(" NOK");
                }
                
            }
            
            else if(leksi.equals("-f")){
                leksi = sc.next();
                if( ((leksi != null) && (!leksi.equals("")) && (leksi.matches("^[a-zA-Z]*$"))) == false ){
                    continue;
                }
            
                leksi = leksi.toLowerCase();
                word.delete(0, word.length());
                word.append(leksi);
                epistrofi = trie.search(word);
                if(epistrofi == true){
                    System.out.print("FND ");
                    System.out.print(word);
                    System.out.println(" OK");
                }
                else{
                    System.out.print("FND ");
                    System.out.print(word);
                    System.out.println(" NOK");
                }
                
            }
            
            else if(leksi.equals("-r")){
                leksi = sc.next();
                if( ((leksi != null) && (!leksi.equals("")) && (leksi.matches("^[a-zA-Z]*$"))) == false ){
                    continue;
                }
            
                leksi = leksi.toLowerCase();
                word.delete(0, word.length());
                word.append(leksi);
                epistrofi = trie.search(word);
                if(epistrofi == true){
                    System.out.print("RMV ");
                    System.out.print(word);
                    System.out.println(" OK");
                    trie.delete(word);
                }
                else{
                    System.out.print("RMV ");
                    System.out.print(word);
                    System.out.println(" NOK");
                }
                
            }
            
            else if(leksi.equals("-p")){
                System.out.print("PreOrder: ");
                trie.printPreorder(trie.root);
                System.out.println();
            }
            
            else if(leksi.equals("-d")){
                System.out.println();
                System.out.println("***** Dictionary *****");
                trie.print();
                System.out.println();
            }
            
            else if(leksi.equals("-w")){
                leksi = sc.next();
                if( ((leksi != null) && (!leksi.equals("")) && (leksi.matches("^[a-zA-Z]*$"))) == false ){
                    continue;
                }
            
                leksi = leksi.toLowerCase();
                word.delete(0, word.length());
                word.append(leksi);
                akeraios = sc.nextInt();
                System.out.println();
                System.out.print("Distant words of ");
                System.out.print(word);
                System.out.print(" (");
                System.out.print(akeraios);
                System.out.print(")");
                System.out.println(":");
                
                trie.findResemble(word, akeraios);
                System.out.println();
                
            }
            
            else if(leksi.equals("-s")){
                leksi = sc.next();
                if( ((leksi != null) && (!leksi.equals("")) && (leksi.matches("^[a-zA-Z]*$"))) == false ){
                    continue;
                }
            
                leksi = leksi.toLowerCase();
                word.delete(0, word.length());
                word.append(leksi);
                System.out.println();
                System.out.print("Words with suffix ");
                System.out.print(word);
                System.out.println(":");
                
                
                trie.findSuffix(word);
                System.out.println();
                
            }
            
            else if(leksi.equals("-q")){
                System.out.println("Bye bye!");
                sc.close();
                break;
            }    
        }  
    }
}
