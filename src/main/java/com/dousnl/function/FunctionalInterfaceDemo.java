package com.dousnl.function;

public class FunctionalInterfaceDemo {


    public static void main(String[] args) {
        NoParamNoReturn noParamNoReturn = () -> System.out.println("hello world1");
        noParamNoReturn.execute();

        SingleParamNoReturn singleParamNoReturn = (message) -> System.out.println("无参返回："+message);
        singleParamNoReturn.print("hello world2");

        GenericFunction<String,String> genericFunction = (s) -> "泛型函数："+s;
        genericFunction.apply("hello world3");
    }
}
