package com.example.rabbitmqproducer.enums;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-27 17:38
 * @Version V1.0
 */
public interface RabbitEnums {


    //queue

    enum QueueEnum {

        DEFAULT_QUEUE("DEFAULT_QUEUE", "默认QUEUE"),
        ;


        private final String name;

        private final String desc;

        QueueEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }
    }

    //exchange

    enum ExchangeEnum {

        DEFAULT_EXCHANGE("DEFAULT_EXCHANGE", "默认EXCHANGE"),

        ;

        private final String name;

        private final String desc;

        ExchangeEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

    }


    enum RoutEnum {


        DEFAULT_ROUT("DEFAULT_ROUT", "默认"),
        ;


        private final String name;

        private final String desc;

        RoutEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

    }


}
