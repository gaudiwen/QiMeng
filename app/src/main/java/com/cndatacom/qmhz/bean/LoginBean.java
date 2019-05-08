package com.cndatacom.qmhz.bean;

import com.google.gson.Gson;

public class LoginBean {
    /**
     * wxmpUrl : https://deeptel.com.cn/
     * resourceUrl : http://maint.deeptel.com.cn/upload/
     * loginUrl : https://login.deeptel.com.cn/
     * loginToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzkwNjc1MDMsInN1YiI6IntcImJ1c0lkXCI6XCIzNlwiLFwiYnVzTmFtZVwiOlwiZ29vZHRvbVwifSIsImlzcyI6ImR1b2ZyaWVuZCIsImF1ZCI6ImR1b2ZlbiIsImV4cCI6MTUzOTE5NDQwMCwibmJmIjoxNTM5MDY3NTAzfQ.njxp09SuFl5UZuhJpgBAlyQ4jXUwG6YWUQe8XBW-GGM
     * projectType : 0
     * hardwareUrl : https://yj.deeptel.com.cn/
     * adminUrl : https://shop.deeptel.com.cn/
     * busUser : {"gender":"0","level":6,"smsCount":138716,"headUrl":"http://maint.duofriend.com/upload/M00/00/C8/ty_yCFuiJ56AaApxAAACfr93cSQ785.png","pid":0,"realname":"goodtom","token":"jnFEkvbHBrwd3waBobrMozmfBgAhFb6M","industryid":1,"phone":"15174258547","name":"goodtom","id":36,"endTime":"2099-09-24T16:00:00.000+0000","email":"3001335157@qq.com"}
     */

    private String wxmpUrl;
    private String resourceUrl;
    private String loginUrl;
    private String loginToken;
    private String projectType;
    private String hardwareUrl;
    private String adminUrl;
    private long passTime;
    private BusUserBean busUser;

    public long getPassTime() {
        return passTime;
    }

    public void setPassTime(long passTime) {
        this.passTime = passTime;
    }

    public String getWxmpUrl() {
        return wxmpUrl;
    }

    public void setWxmpUrl(String wxmpUrl) {
        this.wxmpUrl = wxmpUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getHardwareUrl() {
        return hardwareUrl;
    }

    public void setHardwareUrl(String hardwareUrl) {
        this.hardwareUrl = hardwareUrl;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    public BusUserBean getBusUser() {
        return busUser;
    }

    public void setBusUser(BusUserBean busUser) {
        this.busUser = busUser;
    }

    public static class BusUserBean {
        /**
         * gender : 0
         * level : 6
         * smsCount : 138716
         * headUrl : http://maint.duofriend.com/upload/M00/00/C8/ty_yCFuiJ56AaApxAAACfr93cSQ785.png
         * pid : 0
         * realname : goodtom
         * token : jnFEkvbHBrwd3waBobrMozmfBgAhFb6M
         * industryid : 1
         * phone : 15174258547
         * name : goodtom
         * id : 36
         * endTime : 2099-09-24T16:00:00.000+0000
         * email : 3001335157@qq.com
         */

        private String gender;
        private int level;
        private int smsCount;
        private String headUrl;
        private int pid;
        private String realname;
        private String token;
        private int industryid;
        private String phone;
        private String name;
        private int id;
        private String endTime;
        private String email;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getSmsCount() {
            return smsCount;
        }

        public void setSmsCount(int smsCount) {
            this.smsCount = smsCount;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIndustryid() {
            return industryid;
        }

        public void setIndustryid(int industryid) {
            this.industryid = industryid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }

}
