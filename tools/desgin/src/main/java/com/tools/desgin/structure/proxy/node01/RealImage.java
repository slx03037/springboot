package com.tools.desgin.structure.proxy.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 16:46
 */
public class RealImage implements Image{
    private final String fileName;

    public  RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDish(fileName);
    }

    @Override
    public void display() {
        System.out.println("displaying:"+fileName);
    }

    private void loadFromDish(String fileName){
        System.out.println("loading:"+fileName);
    }
}
