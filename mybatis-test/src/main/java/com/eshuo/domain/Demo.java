package com.eshuo.domain;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-12-18 17:29
 * @Version V1.0
 */
public class Demo {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
