package com.green.greengram3;

import org.junit.jupiter.api.*;

public class Exam2 {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }
    // 밑에 test 클래스가 실행되기전에 beforeall이 먼저 실행하도록 하라

    @AfterAll
    public static void AfterAll() {
        System.out.println("AfterAll");
    }
    // 밑에 test 클래스가 실행되고 난후 afterall이 나중에 실행하도록 하라

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("Test2");
    }

    @Test
    public void test3() {
        System.out.println("Test3");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
    }
    // 각 테스트 들어가기 전에 무조건 BeforeEach라고 출력해둘것

    @AfterEach
    public void AfterEach() {
        System.out.println("AfterEach");
    }
    // 각 테스트 실행 후 무존건 AfterEach라고 출력해둘것
}
