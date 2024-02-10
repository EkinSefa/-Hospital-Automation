/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

/**
 *
 * @author user
 */
public class Item {
    private int key;
    private String value;
    
   

    public Item() {
    }
    public Item(int key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

  
}
