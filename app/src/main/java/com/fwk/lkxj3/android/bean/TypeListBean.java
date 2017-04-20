package com.fwk.lkxj3.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/14.
 */

public class TypeListBean implements Serializable {

    /**
     * Success : 10000
     * Message : 获取结果成功！
     * RerurnValue : [{"xjkpCheckDetailList":null,"xjkpCheckPersonList":null,"xjkpCheckPerson":null,"xjkpCheckResponsiblePersonList":null,"xjkpCheckObject":null,"Name":null,"CheckResultsId":56,"CheckCategoryId":2,"CheckDate":"2016-09-21T00:00:00","IntCheckResult":null,"VcharCheckResult":null,"Remark":null,"Modifier":80,"ModifyTime":"2016-09-21T19:53:46.923","State":1},{"xjkpCheckDetailList":null,"xjkpCheckPersonList":null,"xjkpCheckPerson":null,"xjkpCheckResponsiblePersonList":null,"xjkpCheckObject":null,"Name":null,"CheckResultsId":57,"CheckCategoryId":2,"CheckDate":"2016-09-21T00:00:00","IntCheckResult":null,"VcharCheckResult":null,"Remark":null,"Modifier":80,"ModifyTime":"2016-09-21T19:53:51.247","State":1},{"xjkpCheckDetailList":null,"xjkpCheckPersonList":null,"xjkpCheckPerson":null,"xjkpCheckResponsiblePersonList":null,"xjkpCheckObject":null,"Name":null,"CheckResultsId":58,"CheckCategoryId":2,"CheckDate":"2016-09-21T00:00:00","IntCheckResult":null,"VcharCheckResult":null,"Remark":null,"Modifier":80,"ModifyTime":"2016-09-21T19:53:54.72","State":1},{"xjkpCheckDetailList":null,"xjkpCheckPersonList":null,"xjkpCheckPerson":null,"xjkpCheckResponsiblePersonList":null,"xjkpCheckObject":null,"Name":null,"CheckResultsId":59,"CheckCategoryId":2,"CheckDate":"2016-09-21T00:00:00","IntCheckResult":null,"VcharCheckResult":null,"Remark":null,"Modifier":80,"ModifyTime":"2016-09-21T19:53:57.977","State":1},{"xjkpCheckDetailList":null,"xjkpCheckPersonList":null,"xjkpCheckPerson":null,"xjkpCheckResponsiblePersonList":null,"xjkpCheckObject":null,"Name":null,"CheckResultsId":116,"CheckCategoryId":2,"CheckDate":"2016-10-13T00:00:00","IntCheckResult":null,"VcharCheckResult":null,"Remark":null,"Modifier":80,"ModifyTime":"2016-10-13T14:33:23.037","State":1}]
     */

    private int Success;
    private String Message;
    /**
     * xjkpCheckDetailList : null
     * xjkpCheckPersonList : null
     * xjkpCheckPerson : null
     * xjkpCheckResponsiblePersonList : null
     * xjkpCheckObject : null
     * Name : null
     * CheckResultsId : 56
     * CheckCategoryId : 2
     * CheckDate : 2016-09-21T00:00:00
     * IntCheckResult : null
     * VcharCheckResult : null
     * Remark : null
     * Modifier : 80
     * ModifyTime : 2016-09-21T19:53:46.923
     * State : 1
     */

    private List<RerurnValueBean> RerurnValue;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<RerurnValueBean> getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(List<RerurnValueBean> RerurnValue) {
        this.RerurnValue = RerurnValue;
    }

    public static class RerurnValueBean implements Serializable {
        private Object xjkpCheckDetailList;
        private Object xjkpCheckPersonList;
        private Object xjkpCheckPerson;
        private Object xjkpCheckResponsiblePersonList;
        private Object xjkpCheckObject;
        private Object Name;
        private int CheckResultsId;
        private int CheckCategoryId;
        private String CheckDate;
        private Object IntCheckResult;
        private Object VcharCheckResult;
        private Object Remark;
        private int Modifier;
        private String ModifyTime;
        private int State;
        private int Itme;

        public void setItme(int itme){
            this.Itme = itme;
        }
        public int getItme(){
            return Itme;
        }
        public Object getXjkpCheckDetailList() {
            return xjkpCheckDetailList;
        }

        public void setXjkpCheckDetailList(Object xjkpCheckDetailList) {
            this.xjkpCheckDetailList = xjkpCheckDetailList;
        }

        public Object getXjkpCheckPersonList() {
            return xjkpCheckPersonList;
        }

        public void setXjkpCheckPersonList(Object xjkpCheckPersonList) {
            this.xjkpCheckPersonList = xjkpCheckPersonList;
        }

        public Object getXjkpCheckPerson() {
            return xjkpCheckPerson;
        }

        public void setXjkpCheckPerson(Object xjkpCheckPerson) {
            this.xjkpCheckPerson = xjkpCheckPerson;
        }

        public Object getXjkpCheckResponsiblePersonList() {
            return xjkpCheckResponsiblePersonList;
        }

        public void setXjkpCheckResponsiblePersonList(Object xjkpCheckResponsiblePersonList) {
            this.xjkpCheckResponsiblePersonList = xjkpCheckResponsiblePersonList;
        }

        public Object getXjkpCheckObject() {
            return xjkpCheckObject;
        }

        public void setXjkpCheckObject(Object xjkpCheckObject) {
            this.xjkpCheckObject = xjkpCheckObject;
        }

        public Object getName() {
            return Name;
        }

        public void setName(Object Name) {
            this.Name = Name;
        }

        public int getCheckResultsId() {
            return CheckResultsId;
        }

        public void setCheckResultsId(int CheckResultsId) {
            this.CheckResultsId = CheckResultsId;
        }

        public int getCheckCategoryId() {
            return CheckCategoryId;
        }

        public void setCheckCategoryId(int CheckCategoryId) {
            this.CheckCategoryId = CheckCategoryId;
        }

        public String getCheckDate() {
            return CheckDate;
        }

        public void setCheckDate(String CheckDate) {
            this.CheckDate = CheckDate;
        }

        public Object getIntCheckResult() {
            return IntCheckResult;
        }

        public void setIntCheckResult(Object IntCheckResult) {
            this.IntCheckResult = IntCheckResult;
        }

        public Object getVcharCheckResult() {
            return VcharCheckResult;
        }

        public void setVcharCheckResult(Object VcharCheckResult) {
            this.VcharCheckResult = VcharCheckResult;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public int getModifier() {
            return Modifier;
        }

        public void setModifier(int Modifier) {
            this.Modifier = Modifier;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public void setModifyTime(String ModifyTime) {
            this.ModifyTime = ModifyTime;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }
    }
}
