package com.tools.desgin.structure.proxy.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 16:48
 */
public class ProxyImage implements Image{
    private RealImage realImage;

    private final String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage==null){
            realImage=new RealImage(fileName);
        }
        realImage.display();
    }
}
