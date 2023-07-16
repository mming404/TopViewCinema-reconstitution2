package com.ysm.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/13
 * @Version V1.0
 **/
@SpringBootApplication
public class CinemaApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(CinemaApplication.class,args);
//    }

    public static void main(String[] args) {

//        System.out.println(calculateNum(((left, right) -> left + right)));

//        printNum(value -> value%2==1);

        Integer integer = typeConver(s -> Integer.valueOf(s));
        System.out.println("integer = " + integer);

    }
    public static <R> R typeConver(Function<String,R> function){
        String str = "1234";
        R result = function.apply(str);
        return result;
    }

    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a,b);
    }

    public static void printNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if (predicate.test(i)){
                System.out.println(i);
            }
        }
    }

}
