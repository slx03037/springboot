//package com.tools.mybatisplus.basic.web.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//
///**
// * @author shenlx
// * @description
// * @date 2026/6/20 22:14
// */
//@Configuration
//public class MyBatisConfig {
//    /**
//     * inject pagination interceptor.
//     *
//     * @return pagination
//     */
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        return new PaginationInnerInterceptor();
//    }
//
//    /**
//     * add pagination interceptor.
//     *
//     * @return MybatisPlusInterceptor
//     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
//        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor());
//        return mybatisPlusInterceptor;
//    }
//}
