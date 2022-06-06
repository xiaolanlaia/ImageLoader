package com.renxing.imageloader.activity;

/**
 * @author :  WuJianFeng
 * @date :  2022/6/6 14:09
 */
/**
 * 测试泛型方法
 */
public class TestFX {
    /**
     * 返回第一个元素
     * @param a
     * @param <T> 声明一个类型
     * @return
     */
    public static <T> T getFirst(T... a) {
        return a[0];
    }

    public static void main(String[] args) {

        if (args[0] instanceof String){

        }

        //调用泛型方法时，在方法名前的尖括号中放入具体的类型
        String first1 = TestFX.<String>getFirst("AAA", "BBB");
        System.out.println(first1);

        //调用泛型方法时，在方法名前的尖括号中放入具体的类型
        Integer first2 = TestFX.<Integer>getFirst(123, 456);
        System.out.println(first2);

        //大多数情况下，方法调用可以省略<T>，编译器有足够信息推断出调用的方法，
        //这里利用形参的类型String[]与泛型类型T[]匹配，推断出T是String
        //故可以调用：
        String first3 = TestFX.getFirst("AAA", "BBB");
        System.out.println(first3);

        Integer first4 = TestFX.getFirst(123, 456);
        System.out.println(first4);
    }
}
