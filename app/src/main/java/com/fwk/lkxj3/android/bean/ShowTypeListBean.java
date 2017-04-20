package com.fwk.lkxj3.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/17.
 */

public class ShowTypeListBean implements Serializable {


    /**
     * Success : 10000
     * Message : 考评详情加载成功！
     * RerurnValue : {"xjkpCheckDetailList":[{"DetailedContentId":642,"CheckResultsId":231,"ItName":"态度认真、格式规范","ItemContent":"态度认真、格式规范","MinScore":1,"MaxScore":20,"IntCheckResult":50,"VcharCheckResult":"一般","Remark":null},{"DetailedContentId":643,"CheckResultsId":231,"ItName":"目标的制定突出学科作用，着眼于幼儿现有水平，体现发展性，符合本班幼儿知识、情感、态度、价值观","ItemContent":"目标的制定突出学科作用，着眼于幼儿现有水平，体现发展性，符合本班幼儿知识、情感、态度、价值观","MinScore":1,"MaxScore":20,"IntCheckResult":100,"VcharCheckResult":"很好","Remark":null},{"DetailedContentId":644,"CheckResultsId":231,"ItName":"教具、学具符合教学目标且实用","ItemContent":"教具、学具符合教学目标且实用","MinScore":1,"MaxScore":20,"IntCheckResult":100,"VcharCheckResult":"很好","Remark":null},{"DetailedContentId":645,"CheckResultsId":231,"ItName":"教学过程层次清楚，寓教于乐，合理地突破重难点。提问语言准确、具有启发性","ItemContent":"教学过程层次清楚，寓教于乐，合理地突破重难点。提问语言准确、具有启发性","MinScore":0,"MaxScore":0,"IntCheckResult":0,"VcharCheckResult":"较差","Remark":null},{"DetailedContentId":646,"CheckResultsId":231,"ItName":"计划的制订科学合理","ItemContent":"计划的制订科学合理","MinScore":0,"MaxScore":0,"IntCheckResult":100,"VcharCheckResult":"很好","Remark":null}],"xjkpCheckPersonList":null,"xjkpCheckPerson":null,"xjkpCheckResponsiblePersonList":null,"xjkpCheckResponsiblePerson":{"InstanceResponsiblePersonId":60,"CheckResultsId":231,"WorkerExtensionId":31},"xjkpCheckObject":null,"XjkpCheckPhotosListMode":[{"PhotoId":118,"CheckResultsId":231,"PhotoFile":"/Other/e0b3478d-be99-4ffd-ac94-4c2f5fef4c9e.png"},{"PhotoId":119,"CheckResultsId":231,"PhotoFile":"/Other/d928d87d-d074-4b94-92a0-a4526719ae73.png"}],"Name":"中1班","CheckResultsId":231,"CheckCategoryId":2,"CheckDate":"2016-10-20T13:15:01","IntCheckResult":70,"VcharCheckResult":null,"Remark":null,"Modifier":140,"ModifyTime":"2016-10-20T13:15:01","State":1}
     */

    private int Success;
    private String Message;
    /**
     * xjkpCheckDetailList : [{"DetailedContentId":642,"CheckResultsId":231,"ItName":"态度认真、格式规范","ItemContent":"态度认真、格式规范","MinScore":1,"MaxScore":20,"IntCheckResult":50,"VcharCheckResult":"一般","Remark":null},{"DetailedContentId":643,"CheckResultsId":231,"ItName":"目标的制定突出学科作用，着眼于幼儿现有水平，体现发展性，符合本班幼儿知识、情感、态度、价值观","ItemContent":"目标的制定突出学科作用，着眼于幼儿现有水平，体现发展性，符合本班幼儿知识、情感、态度、价值观","MinScore":1,"MaxScore":20,"IntCheckResult":100,"VcharCheckResult":"很好","Remark":null},{"DetailedContentId":644,"CheckResultsId":231,"ItName":"教具、学具符合教学目标且实用","ItemContent":"教具、学具符合教学目标且实用","MinScore":1,"MaxScore":20,"IntCheckResult":100,"VcharCheckResult":"很好","Remark":null},{"DetailedContentId":645,"CheckResultsId":231,"ItName":"教学过程层次清楚，寓教于乐，合理地突破重难点。提问语言准确、具有启发性","ItemContent":"教学过程层次清楚，寓教于乐，合理地突破重难点。提问语言准确、具有启发性","MinScore":0,"MaxScore":0,"IntCheckResult":0,"VcharCheckResult":"较差","Remark":null},{"DetailedContentId":646,"CheckResultsId":231,"ItName":"计划的制订科学合理","ItemContent":"计划的制订科学合理","MinScore":0,"MaxScore":0,"IntCheckResult":100,"VcharCheckResult":"很好","Remark":null}]
     * xjkpCheckPersonList : null
     * xjkpCheckPerson : null
     * xjkpCheckResponsiblePersonList : null
     * xjkpCheckResponsiblePerson : {"InstanceResponsiblePersonId":60,"CheckResultsId":231,"WorkerExtensionId":31}
     * xjkpCheckObject : null
     * XjkpCheckPhotosListMode : [{"PhotoId":118,"CheckResultsId":231,"PhotoFile":"/Other/e0b3478d-be99-4ffd-ac94-4c2f5fef4c9e.png"},{"PhotoId":119,"CheckResultsId":231,"PhotoFile":"/Other/d928d87d-d074-4b94-92a0-a4526719ae73.png"}]
     * Name : 中1班
     * CheckResultsId : 231
     * CheckCategoryId : 2
     * CheckDate : 2016-10-20T13:15:01
     * IntCheckResult : 70
     * VcharCheckResult : null
     * Remark : null
     * Modifier : 140
     * ModifyTime : 2016-10-20T13:15:01
     * State : 1
     */

    private RerurnValueBean RerurnValue;

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

    public RerurnValueBean getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(RerurnValueBean RerurnValue) {
        this.RerurnValue = RerurnValue;
    }

    public static class RerurnValueBean implements Serializable {
        private Object xjkpCheckPersonList;
        private Object xjkpCheckPerson;
        private Object xjkpCheckResponsiblePersonList;
        /**
         * InstanceResponsiblePersonId : 60
         * CheckResultsId : 231
         * WorkerExtensionId : 31
         */

        private XjkpCheckResponsiblePersonBean xjkpCheckResponsiblePerson;
        private Object xjkpCheckObject;
        private String Name;
        private int CheckResultsId;
        private int CheckCategoryId;
        private String CheckDate;
        private float IntCheckResult;
        private Object VcharCheckResult;
        private Object Remark;
        private int Modifier;
        private String ModifyTime;
        private int State;
        /**
         * DetailedContentId : 642
         * CheckResultsId : 231
         * ItName : 态度认真、格式规范
         * ItemContent : 态度认真、格式规范
         * MinScore : 1
         * MaxScore : 20
         * IntCheckResult : 50
         * VcharCheckResult : 一般
         * Remark : null
         */

        private List<XjkpCheckDetailListBean> xjkpCheckDetailList;
        /**
         * PhotoId : 118
         * CheckResultsId : 231
         * PhotoFile : /Other/e0b3478d-be99-4ffd-ac94-4c2f5fef4c9e.png
         */

        private List<XjkpCheckPhotosListModeBean> XjkpCheckPhotosListMode;

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

        public XjkpCheckResponsiblePersonBean getXjkpCheckResponsiblePerson() {
            return xjkpCheckResponsiblePerson;
        }

        public void setXjkpCheckResponsiblePerson(XjkpCheckResponsiblePersonBean xjkpCheckResponsiblePerson) {
            this.xjkpCheckResponsiblePerson = xjkpCheckResponsiblePerson;
        }

        public Object getXjkpCheckObject() {
            return xjkpCheckObject;
        }

        public void setXjkpCheckObject(Object xjkpCheckObject) {
            this.xjkpCheckObject = xjkpCheckObject;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
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

        public float getIntCheckResult() {
            return IntCheckResult;
        }

        public void setIntCheckResult(float IntCheckResult) {
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

        public List<XjkpCheckDetailListBean> getXjkpCheckDetailList() {
            return xjkpCheckDetailList;
        }

        public void setXjkpCheckDetailList(List<XjkpCheckDetailListBean> xjkpCheckDetailList) {
            this.xjkpCheckDetailList = xjkpCheckDetailList;
        }

        public List<XjkpCheckPhotosListModeBean> getXjkpCheckPhotosListMode() {
            return XjkpCheckPhotosListMode;
        }

        public void setXjkpCheckPhotosListMode(List<XjkpCheckPhotosListModeBean> XjkpCheckPhotosListMode) {
            this.XjkpCheckPhotosListMode = XjkpCheckPhotosListMode;
        }

        public static class XjkpCheckResponsiblePersonBean implements Serializable {
            private int InstanceResponsiblePersonId;
            private int CheckResultsId;
            private int WorkerExtensionId;

            public int getInstanceResponsiblePersonId() {
                return InstanceResponsiblePersonId;
            }

            public void setInstanceResponsiblePersonId(int InstanceResponsiblePersonId) {
                this.InstanceResponsiblePersonId = InstanceResponsiblePersonId;
            }

            public int getCheckResultsId() {
                return CheckResultsId;
            }

            public void setCheckResultsId(int CheckResultsId) {
                this.CheckResultsId = CheckResultsId;
            }

            public int getWorkerExtensionId() {
                return WorkerExtensionId;
            }

            public void setWorkerExtensionId(int WorkerExtensionId) {
                this.WorkerExtensionId = WorkerExtensionId;
            }
        }

        public static class XjkpCheckDetailListBean implements Serializable {
            private int DetailedContentId;
            private int CheckResultsId;
            private String ItName;
            private String ItemContent;
            private int MinScore;
            private int MaxScore;
            private int IntCheckResult;
            private String VcharCheckResult;
            private Object Remark;

            public int getDetailedContentId() {
                return DetailedContentId;
            }

            public void setDetailedContentId(int DetailedContentId) {
                this.DetailedContentId = DetailedContentId;
            }

            public int getCheckResultsId() {
                return CheckResultsId;
            }

            public void setCheckResultsId(int CheckResultsId) {
                this.CheckResultsId = CheckResultsId;
            }

            public String getItName() {
                return ItName;
            }

            public void setItName(String ItName) {
                this.ItName = ItName;
            }

            public String getItemContent() {
                return ItemContent;
            }

            public void setItemContent(String ItemContent) {
                this.ItemContent = ItemContent;
            }

            public int getMinScore() {
                return MinScore;
            }

            public void setMinScore(int MinScore) {
                this.MinScore = MinScore;
            }

            public int getMaxScore() {
                return MaxScore;
            }

            public void setMaxScore(int MaxScore) {
                this.MaxScore = MaxScore;
            }

            public int getIntCheckResult() {
                return IntCheckResult;
            }

            public void setIntCheckResult(int IntCheckResult) {
                this.IntCheckResult = IntCheckResult;
            }

            public String getVcharCheckResult() {
                return VcharCheckResult;
            }

            public void setVcharCheckResult(String VcharCheckResult) {
                this.VcharCheckResult = VcharCheckResult;
            }

            public Object getRemark() {
                return Remark;
            }

            public void setRemark(Object Remark) {
                this.Remark = Remark;
            }
        }

        public static class XjkpCheckPhotosListModeBean implements Serializable {
            private int PhotoId;
            private int CheckResultsId;
            private String PhotoFile;

            public int getPhotoId() {
                return PhotoId;
            }

            public void setPhotoId(int PhotoId) {
                this.PhotoId = PhotoId;
            }

            public int getCheckResultsId() {
                return CheckResultsId;
            }

            public void setCheckResultsId(int CheckResultsId) {
                this.CheckResultsId = CheckResultsId;
            }

            public String getPhotoFile() {
                return PhotoFile;
            }

            public void setPhotoFile(String PhotoFile) {
                this.PhotoFile = PhotoFile;
            }
        }
    }
}
