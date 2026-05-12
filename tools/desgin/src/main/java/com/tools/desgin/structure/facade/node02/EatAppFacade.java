package com.tools.desgin.structure.facade.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:24
 */
public class EatAppFacade {
    private final Booking booking;
    private final TakeOrder takeOrder;

    private final Payment payment;

    public EatAppFacade(){
        this.booking=Booking.getInstance();
        this.takeOrder=TakeOrder.getInstance();
        this.payment=Payment.getInstance();
    }

    public void dining(){
        booking.bookPlace();
        takeOrder.orderDishes();
        payment.payMoney();
    }
}
