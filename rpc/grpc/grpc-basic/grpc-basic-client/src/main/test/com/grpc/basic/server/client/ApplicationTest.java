package com.grpc.basic.server.client;


import com.grpc.basic.common.Product;
import com.grpc.basic.common.ProductId;
import com.grpc.basic.common.ProductInfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shenlx
 * @description
 * @date 2025/3/26 14:10
 */
@SpringBootTest
public class ApplicationTest {

    @Test
    public void test(){

        ManagedChannel channle = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
                .usePlaintext()
                .build();
        //这个是阻塞调用的gRPC客户端类型，实际使用中跟HTTP接口请求->响应
        ProductInfoGrpc.ProductInfoBlockingStub stub=ProductInfoGrpc.newBlockingStub(channle);

        Product product = Product.newBuilder().setId("1")
                .setPrice(399.0f)
                .setName("grpc项目")
                .setDescription("Springboot+vue3")
                .build();

        ProductId productId = stub.addProduct(product);

        System.out.println("product.getValue()"+productId.getValue());

        Product product1 = stub.getProduct(ProductId.newBuilder().setValue("99999").build());

        System.out.println("product.toString()="+product1.toString());

    }
}
