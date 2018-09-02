package com.pc.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public class Result_carVO {
    private List<DtCarListBean> dtCarList;
    private List<DtStaffListBean> dtStaffList;

    public List<DtCarListBean> getDtCarList() {
        return dtCarList;
    }

    public void setDtCarList(List<DtCarListBean> dtCarList) {
        this.dtCarList = dtCarList;
    }

    public List<DtStaffListBean> getDtStaffList() {
        return dtStaffList;
    }

    public void setDtStaffList(List<DtStaffListBean> dtStaffList) {
        this.dtStaffList = dtStaffList;
    }

    public static class DtCarListBean {
        /**
         * 车辆名称 : 五十铃水罐车
         * 所属部门 : 特勤一中队
         * 车牌号 : WJ苏6224
         * 水 : 2.5
         * 泡沫 : 2.5
         * 干粉 : 0.0
         * 最大流量 : 0.0
         * 最小流量 : 0.0
         * 状态 : 返队
         * 最大举升高度 : 0.0
         */

        private String 车辆名称;
        private String 所属部门;
        private String 车牌号;
        private double 水;
        private double 泡沫;
        private double 干粉;
        private double 最大流量;
        private double 最小流量;
        private String 状态;
        private double 最大举升高度;
        private int no;

        public DtCarListBean(int no,String 车辆名称,String 所属部门,String 车牌号,double 水, double 泡沫, double 干粉,
                             double 最大流量,double 最小流量, String 状态,double 最大举升高度) {
            this.no=no;
            this.车辆名称 =车辆名称;
            this.所属部门=所属部门;
            this.车牌号=车牌号;
            this.水=水;
            this.泡沫=泡沫;
            this.干粉=干粉;
            this.最大流量=最大流量;
            this.最小流量=最小流量;
            this.状态=状态;
            this.最大举升高度=最大举升高度;

        }

        public String get车辆名称() {
            return 车辆名称;
        }

        public void set车辆名称(String 车辆名称) {
            this.车辆名称 = 车辆名称;
        }

        public String get所属部门() {
            return 所属部门;
        }

        public void set所属部门(String 所属部门) {
            this.所属部门 = 所属部门;
        }

        public String get车牌号() {
            return 车牌号;
        }

        public void set车牌号(String 车牌号) {
            this.车牌号 = 车牌号;
        }

        public double get水() {
            return 水;
        }

        public void set水(double 水) {
            this.水 = 水;
        }

        public double get泡沫() {
            return 泡沫;
        }

        public void set泡沫(double 泡沫) {
            this.泡沫 = 泡沫;
        }

        public double get干粉() {
            return 干粉;
        }

        public void set干粉(double 干粉) {
            this.干粉 = 干粉;
        }

        public double get最大流量() {
            return 最大流量;
        }

        public void set最大流量(double 最大流量) {
            this.最大流量 = 最大流量;
        }

        public double get最小流量() {
            return 最小流量;
        }

        public void set最小流量(double 最小流量) {
            this.最小流量 = 最小流量;
        }

        public String get状态() {
            return 状态;
        }

        public void set状态(String 状态) {
            this.状态 = 状态;
        }

        public double get最大举升高度() {
            return 最大举升高度;
        }

        public void set最大举升高度(double 最大举升高度) {
            this.最大举升高度 = 最大举升高度;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }

    public static class DtStaffListBean {

        private int no;
        private String 人员ID;
        private String 部门;
        private String 姓名;
        private String 职务;
        private String 技术等级;
        private String 出生时间;
        private String 入伍时间;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        private String info = "";

        public DtStaffListBean(int no, String 入伍时间, String 出生时间, String 姓名, String 技术等级, String 职务, String 部门) {
            this.no = no;
            this.入伍时间 = 入伍时间;
            this.出生时间 = 出生时间;
            this.姓名 = 姓名;
            this.技术等级 = 技术等级;
            this.职务 = 职务;
            this.部门 = 部门;
        }

        public String get人员ID() {
            return 人员ID;
        }

        public void set人员ID(String 人员ID) {
            this.人员ID = 人员ID;
        }

        public String get部门() {
            return 部门;
        }

        public void set部门(String 部门) {
            this.部门 = 部门;
        }

        public String get姓名() {
            return 姓名;
        }

        public void set姓名(String 姓名) {
            this.姓名 = 姓名;
        }

        public String get职务() {
            return 职务;
        }

        public void set职务(String 职务) {
            this.职务 = 职务;
        }

        public String get技术等级() {
            return 技术等级;
        }

        public void set技术等级(String 技术等级) {
            this.技术等级 = 技术等级;
        }

        public String get出生时间() {
            return 出生时间;
        }

        public void set出生时间(String 出生时间) {
            this.出生时间 = 出生时间;
        }

        public String get入伍时间() {
            return 入伍时间;
        }

        public void set入伍时间(String 入伍时间) {
            this.入伍时间 = 入伍时间;
        }

        /**
         * 部门 : 特勤一中队
         * 姓名 : 人员2
         * 职务 : 指导员
         * 技术等级 : A级
         * 出生时间 : 1988.05.3
         * 入伍时间 : 2015-12-20 15:30:30
         */
        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "DtStaffListBean{" +
                    "info='" + info + '\'' +
                    ", no=" + no +
                    ", 人员ID='" + 人员ID + '\'' +
                    ", 部门='" + 部门 + '\'' +
                    ", 姓名='" + 姓名 + '\'' +
                    ", 职务='" + 职务 + '\'' +
                    ", 技术等级='" + 技术等级 + '\'' +
                    ", 出生时间='" + 出生时间 + '\'' +
                    ", 入伍时间='" + 入伍时间 + '\'' +
                    '}';
        }
    }
}
