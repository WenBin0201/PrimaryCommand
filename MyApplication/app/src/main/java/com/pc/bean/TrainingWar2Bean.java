package com.pc.bean;

/**
 * Created by Administrator on 2018-03-27.
 */

public class TrainingWar2Bean {

    /**
     * ID : 1
     * StaffId : 23
     * 姓名 : 张卫强
     * SubjectCode : 0102
     * SubjectName : 地震救援培训资质
     * StaffScore : 100.0
     * TrainingResult : 1
     * ResultDesc : 该战士在培训期间表现良好，拥有地震救援资质
     * ResultPicture : /Event/StaffCombatTraining/1_2018032616302696690359.jpg
     * ResultDate : 2018-03-26T16:29:49
     * AddTime : 2018-03-26T16:29:49
     */

    private int ID;
    private int StaffId;
    private String 姓名;
    private String SubjectCode;
    private String SubjectName;
    private double StaffScore;
    private int TrainingResult;
    private String ResultDesc;
    private String ResultPicture;
    private String ResultDate;
    private String AddTime;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStaffId() {
        return StaffId;
    }

    public void setStaffId(int StaffId) {
        this.StaffId = StaffId;
    }

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public double getStaffScore() {
        return StaffScore;
    }

    public void setStaffScore(double StaffScore) {
        this.StaffScore = StaffScore;
    }

    public int getTrainingResult() {
        return TrainingResult;
    }

    public void setTrainingResult(int TrainingResult) {
        this.TrainingResult = TrainingResult;
    }

    public String getResultDesc() {
        return ResultDesc;
    }

    public void setResultDesc(String ResultDesc) {
        this.ResultDesc = ResultDesc;
    }

    public String getResultPicture() {
        return ResultPicture;
    }

    public void setResultPicture(String ResultPicture) {
        this.ResultPicture = ResultPicture;
    }

    public String getResultDate() {
        return ResultDate;
    }

    public void setResultDate(String ResultDate) {
        this.ResultDate = ResultDate;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String AddTime) {
        this.AddTime = AddTime;
    }
}
