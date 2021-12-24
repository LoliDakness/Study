package com.example.config;

/**
 * @author: yangjie
 * @data:2021/12/22 14:34
 */
public class Result {

        /**  */
        private static final long serialVersionUID = 1L;

        /** 状态码 */
        private String code;
        /** 状态描述 */
        private String msg;
        /** 业务数据 */
        private Object data;

        private Object data2;

        /**用户自定义返回错误CODE*/
        public static String ERROR_CODE = "-1";
        public static String FAIL_CODE = "0";
        public static String SUCCESS_CODE = "1";
        private String SUCCESS_MSG = "操作成功";
        /** 构造方法
         * @param i
         * @param s*/
        public Result(int i, String s) {
            super();
            this.code = SUCCESS_CODE;
            this.msg = SUCCESS_MSG;
        }

        public Result(Object data) {
            super();
            this.code = SUCCESS_CODE;
            this.msg = SUCCESS_MSG;
            this.data = data;
        }

        /**
         * 构造方法
         * @param code		状态码
         * @param msg	状态描述
         */
        public Result(String code, String msg) {
            super();
            this.code = code;
            this.msg = msg;
        }


        public Result(String code, String msg, String data) {
            super();
            this.code = code;
            this.msg = msg;
            this.data=data;
        }

        /**
         * 构造方法
         * @param code		状态码
         * @param msg	状态描述
         * @param data		业务数据
         */
        public Result(String code, String msg, Object data) {
            super();
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public Result(String code,String msg,Object data1,Object data2){
            this.code = code;
            this.msg = msg;
            this.data = data1;
            this.data2 = data2;
        }


        /** 状态码 */
        public String getCode() {
            return code;
        }

        /** 状态码 */
        public void setCode(String code) {
            this.code = code;
        }


        /** 状态描述 */
        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }


        /** 业务数据 */
        public Object getData() {
            return data;
        }

        /** 业务数据 */
        public void setData(Object data) {
            this.data = data;
        }

        public Object getData2() {
            return data2;
        }

        public void setData2(Object data2) {
            this.data2 = data2;
        }

        public boolean isSuccess(){
            return SUCCESS_CODE.equals(code);
        }
    }

