package com.dousnl.function;

import com.dousnl.model.HelloResp;

public interface Route {

    void route(FunctionInterface<HelloResp> functionInterface);
}
