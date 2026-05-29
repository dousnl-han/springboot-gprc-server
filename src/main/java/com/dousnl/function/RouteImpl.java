package com.dousnl.function;

import com.dousnl.model.HelloResp;


public class RouteImpl implements Route {

    public static final String TEST;

    static {
        TEST = "test";
    }

    public static void main(String[] args) {
        Route route = new RouteImpl();
        route.route(RouteImpl::test);

    }

    public static void test(HelloResp i) {
        System.out.println(i);
    }

    @Override
    public void route(FunctionInterface<HelloResp> functionInterface) {
        System.out.println("开始执行");
    }
}
