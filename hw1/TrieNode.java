/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

/**
 *
 * @author nefga
 */
public class TrieNode {
    
    public StringBuilder str = new StringBuilder();
    TrieNode[] children;
    boolean telos;
    
    /**
     *
     * @param telos
     */
    public TrieNode(boolean telos){
        this.telos = telos;
        this.children = new TrieNode[26];
    }
    
    public void noChar(){
        this.str.append("");
    }
    
    public void putString(StringBuilder word){
        this.str.append(word);
    }
    
}
