/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

/**
 *
 * @author nefga
 */
public class Trie {
    public TrieNode root;
    //public int diafores;
    
    public Trie(){
        this.root = new TrieNode(false);
        this.root.noChar();
        //this.diafores = 0;
    }
    
    /**
     *
     * @param word
     * @param node
     * @return
     */
    public int findPrefix(StringBuilder word, TrieNode node){
        
        int omoiotites = 0;
        int minLength = 0;
        
        if(node.str.length() >= word.length()){   
            minLength = word.length();
        }
        else if(node.str.length()<word.length()){
            minLength = node.str.length();
        }
        
        if(minLength>0){
            for(int i = 0; i<minLength; i++){
                
                if(word.charAt(i) == node.str.charAt(i)){
                    
                    //Idio gramma kai sta dyo strings
                    omoiotites++;
                }
                else{
                    //Diaforetiko gramma
                    return omoiotites;
                }
            }
        }
        
        return omoiotites;
    }
    
    private void insertFunc(StringBuilder word, TrieNode node){
        int base = 'a';
        int thesi;
        boolean itan_leksi;
        
        //Evresi koinou prothematos (an yparxei)
        int prefix = findPrefix(word, node);
        
        // CASE 1 | CASE 2 - komvos = riza
        if((prefix == 0) || (node == root) || 
                ((prefix > 0) && (prefix < word.length()) && (prefix >= node.str.length()))){
            
            
            // Apothikeusi leksis ektos tou koinou prothematos
            StringBuilder newWordPart = new StringBuilder();
            newWordPart.append(word.substring(prefix, word.length()));
            
            thesi = (newWordPart.charAt(0)-base);

            
            // An den ksekinaei kapoia leksi me auto to gramma 
            if(node.children[thesi] == null){
                
                // Dimiourgia neou komvou stin thesi ekeinou tou grammatos kai apothikeusi leksis
                node.children[thesi] = new TrieNode(true);
                node.children[thesi].putString(newWordPart);
            }
            
            // An ksekinaei leksi apo auto to gramma
            else if(node.children[thesi] != null){
                
                // ksanakaloume me komvo auton tou prwtou grammatos
                insertFunc(newWordPart, node.children[thesi]);
            }
        }
        
        // CASE 3 Spasimo leksis kai dimiourgia 2 newn komvwn (palia ypoloipi, nea ypoloipi)
        else if((prefix <= word.length()) && (prefix != node.str.length())){
            
            // Antlisi tou koinou prothematos
            StringBuilder commonPrefix = new StringBuilder();
            commonPrefix.append(word.substring(0, prefix));
            
            // Antlisi tis ypoloipis palias leksis 
            StringBuilder prevWordCont = new StringBuilder();
            prevWordCont.append(node.str.substring(prefix, node.str.length()));
            
            // Antlisi tis ypoloipis kainourias leksis
            StringBuilder wordCont = new StringBuilder();
            wordCont.append(word.substring(prefix, word.length()));
            
            itan_leksi = node.telos;
            
            // Topothetisi ston komvo pou idi yparxei to koino prothema
            node.str.delete(0, node.str.length());
            node.putString(commonPrefix);
            node.telos = prefix >= word.length();
            
            TrieNode komvos = new TrieNode(true);
            
            //hasChildren(node);
            if(hasChildren(node) > 0){
                if(hasChildren(node) == 1){
                    komvos.telos = true;
                }
                if(hasChildren(node) > 1){
                    if(itan_leksi == true){
                        komvos.telos = true;
                    }
                    else{
                        komvos.telos = false;
                    }
                }
                
                anathesiChild(node, komvos);
            }
            
            // Dimiourgia neou komvou gia tin ypoloipi palia leksi
            thesi = (prevWordCont.charAt(0)-base);
            node.children[thesi] = komvos; ///////
            node.children[thesi].putString(prevWordCont);
            
            // Dimiourgia neou komvou gia tin ypoloipi kainouria leksi
            if(prefix < word.length()){
                thesi = (wordCont.charAt(0)-base);
                node.children[thesi] = new TrieNode(true);
                node.children[thesi].putString(wordCont);
            }
        }
        
        // CASE 4 Apolyti tautisi tis leksis me prothema kapoiou komvou (Aplo markarisma tou komvou ws termatiko)
        else if((prefix == word.length()) && (prefix == node.str.length()) ){
              node.telos = true;
        }
    }
    
    
    private int hasChildren(TrieNode palios){
        
        int i = 0;
        int count = 0;
        
        while(i<26){
            if(palios.children[i] != null){
                count++;
            }
            i++;
        }
        return count;
    }
    
    
    private void anathesiChild(TrieNode palios, TrieNode kainourios){
        
        int i;
        
        for(i=0; i<26; i++){
            if((palios.children[i] != null) && (palios.children[i] != kainourios)){
            
                kainourios.children[i] = new TrieNode(true);
                //kainourios.children[i].str.append(palios.children[i].str);
                
                kainourios.children[i] = palios.children[i];
                palios.children[i]=null;
            
            }
        }
        
    }
    
    
        
    public void insert(StringBuilder word){
        this.insertFunc(word, this.root);
    }
    
    
    public void printPreorder(TrieNode node){
        
        if(node == null){
            return;
        }
        
        System.out.print(node.str);
        if(node.telos == true){
            System.out.print("# ");
        }
        else if(node.telos == false && node != root){
            System.out.print(" ");
        }
        
        for(int i = 0; i<26; i++){
            printPreorder(node.children[i]);
        }
        
    }
    
    public boolean searchFunc(StringBuilder word, TrieNode node){
        
        int base = 'a';
        int thesi;
        boolean epistrofi = false;
        
        //Evresi koinou prothematos (an yparxei)
        int prefix = findPrefix(word, node);
        if((prefix == 0 && node != root) || (prefix < node.str.length())){
            //System.out.println("NOT FOUND !");
            epistrofi = false;
            return epistrofi;
        }
        if(prefix == word.length()){
            if(word.length() == node.str.length() && node.telos == true){
                //System.out.println("FOUND !");
                epistrofi = true;
                return epistrofi;
            }
            else{
                return false;
            }
            
        }
        
        if(word.length()!= 0){
            
            // Apothikeusi leksis ektos tou koinou prothematos
            StringBuilder newWordPart = new StringBuilder();
            newWordPart.append(word.substring(prefix, word.length()));
            
            thesi = (newWordPart.charAt(0)-base);
            
            // An den ksekinaei kapoia leksi me auto to gramma 
            if(node.children[thesi] == null){
                //System.out.println("NOT FOUND !");
                epistrofi = false;
                return epistrofi;
            }
            else if(node.children[thesi] != null){
                
                // ksanakaloume me komvo auton tou prwtou grammatos
                epistrofi = searchFunc(newWordPart, node.children[thesi]);
                //System.out.println(epistrofi);
            }
        }
        
        
        return epistrofi;
    }
    
    public boolean search(StringBuilder word){
        return(this.searchFunc(word, this.root));
    }
    
    
    public void print() {
        printFunc(this.root, new StringBuilder());
    }


    private void printFunc(TrieNode node, StringBuilder str){

        if (node.telos == true) {
            System.out.println(str);
        }

        for (int i = 0; i < 26; ++i) {

            // If edgeLabel is not null
            if (node.children[i] != null) {
                int length = str.length();

                str = str.append(node.children[i].str);
                printFunc(node.children[i], str);
                str = str.delete(length, str.length());
            }
        }
    }

    
    public void findResemble(StringBuilder word, int diafores) {
        findResembleFunc(this.root, new StringBuilder(), word, diafores);
    }


    private void findResembleFunc(TrieNode node, StringBuilder str, StringBuilder word, int diafores){

        if (node.telos == true && str.length() == word.length()) {
            compareWords(str, word, diafores);
        }

        for (int i = 0; i < 26; ++i) {

            // If edgeLabel is not null
            if (node.children[i] != null) {
                int length = str.length();

                str = str.append(node.children[i].str);
                findResembleFunc(node.children[i], str, word, diafores);
                str = str.delete(length, str.length());
            }
        }
    }

    
    private boolean compareWords(StringBuilder str, StringBuilder word, int diafores){

        int i = 0;
        int count = 0;
        
        if(word.length() != str.length()){
            return false;
        }
        
        while(i<word.length()){

            if(word.charAt(i) != str.charAt(i)){
                count++;
            }
            i++;
        }
        
        if(count == 0){
            return true;
        }
        
        if(count == diafores){
            System.out.println(str);
        }
        
        return false;
    }

      
    public void findSuffix(StringBuilder word) {
        findSuffixFunc(this.root, new StringBuilder(), word);
    }


    private void findSuffixFunc(TrieNode node, StringBuilder str, StringBuilder word){

        if (node.telos == true) {
            compareSuffix(str, word);
        }

        for (int i = 0; i < 26; ++i) {

            // If edgeLabel is not null
            if (node.children[i] != null) {
                int length = str.length();

                str = str.append(node.children[i].str);
                findSuffixFunc(node.children[i], str, word);
                str = str.delete(length, str.length());
            }
        }
    }

    
    private void compareSuffix(StringBuilder str, StringBuilder word){

        int i = word.length()-1;
        int j = str.length()-1;
        int count = 0;
        
        
        if(str.length() < word.length()){
            return;
        }
                
        while(i>-1){

            if(word.charAt(i) != str.charAt(j)){
                count++;
            }
            i--;
            j--;
        }
        
        if(count == 0){
            System.out.println(str);
        }
        
    }
    
    
    public void delete(StringBuilder word) {
        deleteFunc(this.root, word);
    }
    
    
    private int diagrafi(TrieNode node){
        
        int count = 0;
        //StringBuilder voithitiko = new StringBuilder();
        
        for (int i = 0; i < 26; ++i) {

            // If edgeLabel is not null
            if (node.children[i] != null) {
                count++;
            }
        }
        
        // CASE 1
        if(count > 1){
            node.telos = false;
            
            return 2;
        }
    
        // CASE 2
        if(count == 1){
            return 1;
        }
    
        // CASE 3
        if(count == 0){
            return 0;
        }
        
        return -1;
    }
    
    
    public int deleteFunc(TrieNode node, StringBuilder word){
        
        int count = 0;
        int base = 'a';
        int thesi;
        int epistrofi;
        StringBuilder voithitiko = new StringBuilder();
        StringBuilder substring = new StringBuilder();
        
        //Evresi koinou prothematos (an yparxei)
        int prefix = findPrefix(word, node);
        
        
        if(prefix == word.length()){
            epistrofi = diagrafi(node);
            return epistrofi;
        }
        
        if(word.length()!= 0){
            
            // Apothikeusi leksis ektos tou koinou prothematos
            StringBuilder newWordPart = new StringBuilder();
            newWordPart.append(word.substring(prefix, word.length()));
            
            thesi = (newWordPart.charAt(0)-base);
            
            // An den ksekinaei kapoia leksi me auto to gramma 
            if(node.children[thesi] == null){
                //System.out.println("NOT FOUND !");
                return -1;
            }
            else if(node.children[thesi] != null){
                
                // ksanakaloume me komvo auton tou prwtou grammatos
                epistrofi = deleteFunc(node.children[thesi], newWordPart);
                
                if(epistrofi == 1){
                    for (int i = 0; i < 26; ++i) {
                        if(substring.length() != 0){
                            substring.delete(0, substring.length());
                        }

                        // If edgeLabel is not null
                        if (node.children[i] != null) {
                            substring.append(node.str);
                            substring.append(node.children[i].str);

                            if(compareWords(substring, word, 0) == true){
                                voithitiko.append(node.children[i].str);
                                for (int j = 0; j < 26; ++j) {

                                    // If edgeLabel is not null
                                    if (node.children[i].children[j] != null && count == 0) {
                                        count++;
                                        voithitiko.append(node.children[i].children[j].str);
                                        node.children[i] = node.children[i].children[j];
                                        //node.children[i].children[j] = null;
                                        node.children[i].str.delete(0, node.children[i].str.length());
                                        node.children[i].str.append(voithitiko);
                                    }
                                }
                            }
                        }              
                    }
                }
                if(epistrofi == 0){
                    for (int i = 0; i < 26; ++i) {
                        if(substring.length() != 0){
                            substring.delete(0, substring.length());
                        }

                        // If edgeLabel is not null
                        if (node.children[i] != null) {
                            substring.append(node.str);
                            substring.append(node.children[i].str);

                            if(compareWords(substring, word, 0) == true){
                                node.children[i] = null;
                            }
                        }
                    }
                    return 4;
                }
                if(epistrofi == 4){
                    for (int i = 0; i < 26; ++i) {
                        if(substring.length() != 0){
                            substring.delete(0, substring.length());
                        }

                        // If edgeLabel is not null
                        if (node.children[i] != null) {
                            substring.append(node.str);
                            substring.append(node.children[i].str);

                            if(substring.charAt(node.str.length()) == word.charAt(node.str.length()) && hasChildren(node.children[i]) == 1 && node.children[i].telos == false){
                                voithitiko.append(node.children[i].str);
                                for (int j = 0; j < 26; ++j) {

                                    // If edgeLabel is not null
                                    if (node.children[i].children[j] != null && count == 0) {
                                        count++;
                                        voithitiko.append(node.children[i].children[j].str);
                                        node.children[i] = node.children[i].children[j];
                                        //node.children[i].children[j] = null;
                                        node.children[i].str.delete(0, node.children[i].str.length());
                                        node.children[i].str.append(voithitiko);
                                    }
                                }
                            }
                        }             
                    } 
                }
            }
        }
        return -1;
    }
    
}
    
 