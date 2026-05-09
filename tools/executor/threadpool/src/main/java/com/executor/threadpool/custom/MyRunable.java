package com.executor.threadpool.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Slf4j
public class MyRunable implements Runnable{
    public String command;

    @Override
    public void run() {
        log.info(Thread.currentThread().getName()+command+" Start. Time ="+new Date());
        processCommand();
        log.info(Thread.currentThread().getName()+command+" End. Time ="+new Date());
    }

    private void processCommand(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}