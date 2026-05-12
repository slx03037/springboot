package com.tools.desgin.behavior.chainofresponsibility.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 15:32
 */
public class ChanPatternDemo {

    private static AbstractLogger getChainOfLoggers(){
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);

        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);

        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[]args){
        AbstractLogger chainOfLoggers = getChainOfLoggers();

        chainOfLoggers.logMessage(AbstractLogger.INFO,"This is an information:");

        chainOfLoggers.logMessage(AbstractLogger.DEBUG,"This is a debug level information:");

        chainOfLoggers.logMessage(AbstractLogger.ERROR,"this is an error information:");
    }
}
