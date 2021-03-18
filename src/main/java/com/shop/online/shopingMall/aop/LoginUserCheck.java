package com.shop.online.shopingMall.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/*
* AOP는 중복되는 코드를 떼어내서 분리해서 자신이 필요한 작업만 갖고 있자는 개념
* */


@Target(ElementType.METHOD)
public @interface LoginUserCheck {
}
