package com.example.mybatisdemo.dao.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisdemo.dao.ILoginService;
import com.example.mybatisdemo.domain.Login;
import com.example.mybatisdemo.mapper.LoginMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2022-09-24
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    private final static String REGEX = "[0-9]+[\\.]?[0-9]*";

    @Override
    public Map<String, List<Map<String, Object>>> init() {

        final List<Map<String, Object>> csv = getBaseMapper().getCsv();

        final List<Map<String, Object>> collect = csv.stream().map(c -> {


            Map<String, Object> map = new HashMap<>();
            c.forEach((k, v) -> {
                if (null != v) {
                    final String valueOf = String.valueOf(v);
                    if (isNumeric(valueOf)) {
                        v = Integer.valueOf(valueOf);
                    }
                }
                map.put(k, v);
            });
            return map;
        }).collect(Collectors.toList());



        Map<String, List<Map<String, Object>>> data = new HashMap<>();
//
//        final List<String> userName = getBaseMapper().getUserName();
//        if (null != userName) {
//            for (String u : userName) {
//                //json
//                final List<Map<String, Object>> json = getBaseMapper().getJson(u);
//                if (null != json) {
//                    data.put(u, json);
//                }
//            }
//        }

        Gson gson = new Gson();

        String filePath = "F:/" + "data1.txt";
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(filePath);
            fw.write(gson.toJson(collect));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


//        IOUtils.read(new File())

        return data;


    }


    public static boolean isNumeric(String s) {
        // Checks if the provided string
        // is a numeric by applying a regular
        // expression on it.
        if (null == s) {
            return false;
        }
        return Pattern.matches(REGEX, s);
    }

    public void csv1() throws IOException {


        final List<String> allTime = getBaseMapper().getAllTime();


        final List<String> userName = getBaseMapper().getUserName();

        //head


        Map<Integer, String> userMap = new HashMap<>();
        Map<String, Map<String, Object>> userDataMap = new HashMap<>();

        List<List<String>> head = new ArrayList<>();

        List<String> timeHead = new ArrayList<>();
        timeHead.add("time");
        head.add(timeHead);
        userMap.put(0, "time");
        for (int i = 0; i < userName.size(); i++) {
            List<String> l = new ArrayList<>();
            final String s = userName.get(i);
            l.add(s);
            head.add(l);
            userMap.put(i + 1, s);
            final Map<String, Object> json = convert(getBaseMapper().getJson(s));

            userDataMap.put(s, json);
        }
        List<List<Object>> list = new ArrayList<>();

        for (int i = 0; i < allTime.size(); i++) {

            List<Object> data = new ArrayList<>();
            final String time1 = allTime.get(i);
            data.add(time1);
            //每列
            for (int i1 = 0; i1 < head.size(); i1++) {
                if (i1 != 0) {
                    final String s = userMap.get(i1);
                    final Map<String, Object> map = userDataMap.get(s);
                    final Object o = map.get(time1);
                    if (null == o) {
                        data.add(0);
                    } else {
                        data.add(o);
                    }
                }
            }
            list.add(data);
        }

        EasyExcel.write("F:/csv.csv").head(head).sheet("data").doWrite(list);



    }


    @Override
    public void csv() throws IOException {


        final List<String> allTime = getBaseMapper().getAllTime();


        final List<String> userName = getBaseMapper().getUserName();

        //head


        Map<Integer, String> userMap = new HashMap<>();
        Map<String, Map<String, Object>> userDataMap = new HashMap<>();

        List<List<String>> head = new ArrayList<>();

        List<String> timeHead = new ArrayList<>();
        timeHead.add("time");
        head.add(timeHead);
        userMap.put(0, "time");
        for (int i = 0; i < userName.size(); i++) {
            List<String> l = new ArrayList<>();
            final String s = userName.get(i);
            l.add(s);
            head.add(l);
            userMap.put(i + 1, s);
            final Map<String, Object> json = convert(getBaseMapper().getJson(s));

            userDataMap.put(s, json);
        }
        List<Map<String,Object>> list = new ArrayList<>();

        for (int i = 0; i < allTime.size(); i++) {


            Map<String,Object> data = new HashMap<>();

            final String time1 = allTime.get(i);
            data.put("time",time1);
            //每列
            for (int i1 = 0; i1 < head.size(); i1++) {
                if (i1 != 0) {
                    final String s = userMap.get(i1);
                    final Map<String, Object> map = userDataMap.get(s);
                    final Object o = map.get(time1);
//                    if (null == o) {
//                        data.put(s,0);
//                    } else {
//                        data.put(s,o);
//                    }
                    if (null != o) {
                        data.put(s,o);
                    }
                }
            }
            list.add(data);
        }

//        EasyExcel.write("F:/csv.csv").head(head).sheet("data").doWrite(list);

        Gson gson = new Gson();

        String filePath = "F:/" + "data4.txt";
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(filePath);
            fw.write(gson.toJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }



    public Map<String, Object> convert(List<Map<String, Object>> data) {

        Map<String, Object> rdata = new HashMap<>();

        for (Map<String, Object> datum : data) {
            rdata.put(String.valueOf(datum.get("time")), datum.get("c1"));
        }
        return rdata;
    }


}
