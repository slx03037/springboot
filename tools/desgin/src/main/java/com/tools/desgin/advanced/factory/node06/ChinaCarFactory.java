package com.tools.desgin.advanced.factory.node06;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:26
 */
public class ChinaCarFactory implements CarProductFactory{
    @Override
    public CarProduct getCar(String type) {
        CarProduct carProduct=null;
        if("hq".equalsIgnoreCase(type)){
            carProduct = new HQCar();
            carProduct.name="红旗一号";
            carProduct.date="1999-09-09";
            carProduct.material();
            carProduct.origin();
        }else if("df".equalsIgnoreCase(type)){
            carProduct = new DFCar();
            carProduct.name="东风一号";
            carProduct.date="1999-09-09";
            carProduct.material();
            carProduct.origin();
        }
        return carProduct;

    }
}
