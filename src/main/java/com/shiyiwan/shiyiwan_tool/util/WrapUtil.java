package com.shiyiwan.shiyiwan_tool.util;

import java.awt.*;
import java.util.function.Supplier;

public class WrapUtil {

    public static <R extends Container, T extends Component> R getWrappedComponent(R wrapper, T innerComponent) {
//        R wrapper = wrapperSupplier.get();
        wrapper.add(innerComponent);
        return wrapper;
    }

//    public static <R extends Container, T extends Component> R getWrappedComponent(Supplier<R> wrapperSupplier, Supplier<T> innerComponentSupplier) {
//        R wrapper = wrapperSupplier.get();
//        T innerConponent = innerComponentSupplier.get();
//        wrapper.add(innerConponent);
//        return wrapper;
//    }

}
