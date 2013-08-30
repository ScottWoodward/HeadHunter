/**
 * HeadType.java
 * Purpose: Creates a Enum for handling each type of head.
 * 
 * @version 1.2.0 11/5/12
 * @author Scott Woodward
 */
package com.scottwoodward.headhunter.helpers;

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
    
    public static HeadType getHead(int value){
        if(value == 0){
            return SKELETON;
        }else if(value == 1){
            return WITHERSKELETON;
        }else if(value == 2){
            return ZOMBIE;
        }else if(value == 3){
            return HUMAN;
        }else{
            return CREEPER;
        }
    }
}
