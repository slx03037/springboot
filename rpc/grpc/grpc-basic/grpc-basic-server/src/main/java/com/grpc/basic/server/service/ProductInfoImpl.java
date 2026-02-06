package com.grpc.basic.server.service;

import com.grpc.basic.common.Product;
import com.grpc.basic.common.ProductId;
import com.grpc.basic.common.ProductInfoGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author shenlx
 * @description
 * @date 2025/3/26 13:15
 */
public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {
    @Override
    public void addProduct(Product request, StreamObserver<ProductId> responseObserver) {
        System.out.println("request.toString() = " + request.toString());
        responseObserver.onNext(ProductId.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductId request, StreamObserver<Product> responseObserver) {
        responseObserver.onNext(Product.newBuilder().setId(request.getValue()).setName("grpc+project").build());
        responseObserver.onCompleted();
    }
}
