package com.pc.entity;

import java.util.List;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class LoginEntity {


    /**
     * Message : success
     * Status : 1
     * Data : [{"账户ID":16,"DeptId":44,"ParentDeptList":"44,5,1,","DeptName":"常州支队特勤一中队","账户类型":"中队现场指挥员","命令回复":2,"发送命令":2}]
     */

    private String Message;
    private int Status;
    private List<DataBean> Data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * 账户ID : 16
         * DeptId : 44
         * ParentDeptList : 44,5,1,
         * DeptName : 常州支队特勤一中队
         * 账户类型 : 中队现场指挥员
         * 命令回复 : 2
         * 发送命令 : 2
         */

        private int 账户ID;
        private int DeptId;
        private String ParentDeptList;
        private String DeptName;
        private String 账户类型;
        private int 命令回复;
        private int 发送命令;

        public int get账户ID() {
            return 账户ID;
        }

        public void set账户ID(int 账户ID) {
            this.账户ID = 账户ID;
        }

        public int getDeptId() {
            return DeptId;
        }

        public void setDeptId(int DeptId) {
            this.DeptId = DeptId;
        }

        public String getParentDeptList() {
            return ParentDeptList;
        }

        public void setParentDeptList(String ParentDeptList) {
            this.ParentDeptList = ParentDeptList;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String get账户类型() {
            return 账户类型;
        }

        public void set账户类型(String 账户类型) {
            this.账户类型 = 账户类型;
        }

        public int get命令回复() {
            return 命令回复;
        }

        public void set命令回复(int 命令回复) {
            this.命令回复 = 命令回复;
        }

        public int get发送命令() {
            return 发送命令;
        }

        public void set发送命令(int 发送命令) {
            this.发送命令 = 发送命令;
        }
    }
}
