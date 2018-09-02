package com.pc.bean;

/**
 * Created by Administrator on 2018-03-26.
 */

public class TrainingQualificationBean {

    /**
     * ID : 1
     * SubjectName : 消防车驾驶资质
     * SubjectCode : 0101
     * SubjectType : 培训资质
     * SubjectTypeCode : 01
     * ResultDesc : 单位：个
     * FullMarks : 1
     * NoScore : 0
     */

    private int ID;
    private String SubjectName;
    private String SubjectCode;
    private String SubjectType;
    private String SubjectTypeCode;
    private String ResultDesc;
    private int FullMarks;
    private int NoScore;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(String SubjectType) {
        this.SubjectType = SubjectType;
    }

    public String getSubjectTypeCode() {
        return SubjectTypeCode;
    }

    public void setSubjectTypeCode(String SubjectTypeCode) {
        this.SubjectTypeCode = SubjectTypeCode;
    }

    public String getResultDesc() {
        return ResultDesc;
    }

    public void setResultDesc(String ResultDesc) {
        this.ResultDesc = ResultDesc;
    }

    public int getFullMarks() {
        return FullMarks;
    }

    public void setFullMarks(int FullMarks) {
        this.FullMarks = FullMarks;
    }

    public int getNoScore() {
        return NoScore;
    }

    public void setNoScore(int NoScore) {
        this.NoScore = NoScore;
    }
}
