package com.tools.desgin.advanced.prototype.node01;

import java.util.Hashtable;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 10:55
 */
public class ShapeCache {
    private static final Hashtable<String,Shape> shapeHashtable=new Hashtable<>();

    public static Shape getShape(String shapeId){
        Shape cachedShape=shapeHashtable.get(shapeId);
        return (Shape)cachedShape.clone();
    }

    //对每种形状都运行数据库查询，并创建该形状
    public static void loadCache(){
        Circle circle=new Circle();
        circle.setId("1");
        shapeHashtable.put(circle.getId(), circle);

        Square square=new Square();
        square.setId("2");
        shapeHashtable.put(square.getId(), square);

        Rectangle rectangle=new Rectangle();
        rectangle.setId("3");
        shapeHashtable.put(rectangle.getId(), rectangle);
    }
}
