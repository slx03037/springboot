package com.tools.desgin.advanced.factory.node06;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:25
 */
public  class GermanyCarFactory implements CarProductFactory{
    @Override
    public CarProduct getCar(String type) {
        CarProduct carProduct=null;
        if("ad".equalsIgnoreCase(type)){
            carProduct = new ADCar();
            carProduct.name="奥迪";
            carProduct.date="1999-09-09";
            carProduct.material();
            carProduct.origin();
        }else if("bm".equalsIgnoreCase(type)){
            carProduct = new BMCar();
            carProduct.name="宝马";
            carProduct.date="1999-09-09";
            carProduct.material();
            carProduct.origin();
        }
        return carProduct;

    }
}
