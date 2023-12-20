package com.green.greengram3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exam1 {

    @Test
    @DisplayName("Yokattane Mi.. Mio-Mio?")
    // 해당 메소드를 테스트할시 무조건 이거 붙일것. tdd의 핵
    public void test1() {
        // 접근지시어는 무조건 public void으로 해놓을것
        System.out.println("YamenaSai");
        int sum = 2 + 2;
        Assertions.assertEquals(4, sum);
    }

    @Test
    @DisplayName("Din Djarin")
    // 출력시 실행창 왼쪽화면에 이름 적은대로 뜬다
    public void test2() {
        System.out.println("This is The Way");
        int multi = 3 * 3;
        Assertions.assertEquals(9, multi);
    }

    @Test
    @DisplayName("This is The Way")
    public void test3() {
        System.out.println("I AM YOUR FATHER");
        Assertions.assertEquals(8, MyUtils.sum(4, 4));
        Assertions.assertEquals(16, MyUtils.sum(8, 8));
    }

    @Test
    public void test4() {
        System.out.println("MAY THE FORCE BE WITH YOU");
        MyUtils myutils = new MyUtils();
        Assertions.assertEquals(9, myutils.multi(3, 3));
        Assertions.assertEquals(0, myutils.multi(3, 0));
        Assertions.assertEquals(1, myutils.multi(1, 1));
        // static이 붙었으면 개인용, 안붙었으면 공용이라 객체를 새로 불러둘것

    }
}
