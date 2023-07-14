package com.ysm.www;

import com.alibaba.fastjson.JSONArray;
import com.ysm.www.util.IDataStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@SpringBootTest
public class TestJSON {

    @Resource
    private IDataStore  dataStore;

    @Test
    void arrJSON(){
        ArrayList<String> stringArrayList = new ArrayList<>();
//        stringArrayList.add("/listFilm");
//
//        String jsonString = JSONArray.toJSONString(stringArrayList);
//        System.out.println(jsonString);
        String jsonString = "[\"listFilm\"]";
        dataStore.put("test",jsonString,1000*60*5);
        String redisStr = dataStore.get("test");
        System.out.println(redisStr);

        List<String> strings = JSONArray.parseArray(redisStr, String.class);
        strings.forEach(System.out::println);
    }

}
