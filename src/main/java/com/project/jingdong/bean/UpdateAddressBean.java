package com.project.jingdong.bean;

/**
 * Author:AND
 * Time:2018/3/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class UpdateAddressBean {

    /**
     * msg : 更新成功
     * code : 0
     * data : {"addr":"asdasd","addrid":2,"mobile":131131131,"name":"李四","status":0,"uid":71}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : asdasd
         * addrid : 2
         * mobile : 131131131
         * name : 李四
         * status : 0
         * uid : 71
         */

        private String addr;
        private int addrid;
        private int mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public int getMobile() {
            return mobile;
        }

        public void setMobile(int mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
