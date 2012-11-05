/**
 * HeadType.java
 * Purpose: Creates a Enum for handling each type of head.
 * 
 * @version 1.2.0 11/5/12
 * @author Scott Woodward
 */
package com.gmail.scottmwoodward.headhunter.helpers;

public enum HeadType {
    SKELETON(0),WITHERSKELETON(1),ZOMBIE(2),HUMAN(3),CREEPER(4);
    
    private int value;
    
    private HeadType(int value){
        setValue(value);
    }
    
    private void setValue(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
