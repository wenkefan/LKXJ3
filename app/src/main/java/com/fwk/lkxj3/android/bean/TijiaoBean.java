package com.fwk.lkxj3.android.bean;

import java.util.List;

/**
 * Created by fanwenke on 16/10/18.
 */

public class TijiaoBean {
    public int getCheckCategoryId() {
        return checkCategoryId;
    }

    public void setCheckCategoryId(int checkCategoryId) {
        this.checkCategoryId = checkCategoryId;
    }

    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int organizationId) {
        OrganizationId = organizationId;
    }

    //机构ID
    private int OrganizationId;
    //检查类别编号
    private int checkCategoryId;
    //检查结果编号
    private int CheckResultsId;
    //创建时间
    private String CheckDate;
    //数字检查结论
    private int IntCheckResult;
    //文本检查结论
    private String VcharCheckResult;
    //备注
    private String Remark;
    //更新人
    private int Modifier;
    //更新日期
    private String ModifyTime;
    //状态
    private int State;


    //检查人
    private xjkpCheckPerson xjkpCheckPerson;
    //被检查人实体
    private xjkpCheckResponsiblePerson xjkpCheckResponsiblePerson;
    //检查对象
    private xjkpCheckObject xjkpCheckObject;

    //详情检查结果
    private List<xjkpCheckDetailList> xjkpCheckDetailList;

    public int getCheckResultsId() {
        return CheckResultsId;
    }

    public void setCheckResultsId(int checkResultsId) {
        CheckResultsId = checkResultsId;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String checkDate) {
        CheckDate = checkDate;
    }

    public int getIntCheckResult() {
        return IntCheckResult;
    }

    public void setIntCheckResult(int intCheckResult) {
        IntCheckResult = intCheckResult;
    }

    public String getVcharCheckResult() {
        return VcharCheckResult;
    }

    public void setVcharCheckResult(String vcharCheckResult) {
        VcharCheckResult = vcharCheckResult;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getModifier() {
        return Modifier;
    }

    public void setModifier(int modifier) {
        Modifier = modifier;
    }

    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String modifyTime) {
        ModifyTime = modifyTime;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public List<xjkpCheckDetailList> getXjkpCheckDetailLists() {
        return xjkpCheckDetailList;
    }

    public void setXjkpCheckDetailLists(List<xjkpCheckDetailList> xjkpCheckDetailLists) {
        this.xjkpCheckDetailList = xjkpCheckDetailLists;
    }

    public TijiaoBean.xjkpCheckPerson getXjkpCheckPerson() {
        return xjkpCheckPerson;
    }

    public void setXjkpCheckPerson(TijiaoBean.xjkpCheckPerson xjkpCheckPerson) {
        this.xjkpCheckPerson = xjkpCheckPerson;
    }

    public TijiaoBean.xjkpCheckResponsiblePerson getXjkpCheckResponsiblePerson() {
        return xjkpCheckResponsiblePerson;
    }

    public void setXjkpCheckResponsiblePerson(TijiaoBean.xjkpCheckResponsiblePerson xjkpCheckResponsiblePerson) {
        this.xjkpCheckResponsiblePerson = xjkpCheckResponsiblePerson;
    }

    public TijiaoBean.xjkpCheckObject getXjkpCheckObject() {
        return xjkpCheckObject;
    }

    public void setXjkpCheckObject(TijiaoBean.xjkpCheckObject xjkpCheckObject) {
        this.xjkpCheckObject = xjkpCheckObject;
    }

    //检查人
    public static class xjkpCheckPerson {
        //实际检查人编号
        private int InstanceCheckPersonId;
        //检查结果编号
        private int CheckResultsId;
        //职工信息编号
        private int WorkerExtensionId;

        public int getInstanceCheckPersonId() {
            return InstanceCheckPersonId;
        }

        public void setInstanceCheckPersonId(int instanceCheckPersonId) {
            InstanceCheckPersonId = instanceCheckPersonId;
        }

        public int getCheckResultsId() {
            return CheckResultsId;
        }

        public void setCheckResultsId(int checkResultsId) {
            CheckResultsId = checkResultsId;
        }

        public int getWorkerExtensionId() {
            return WorkerExtensionId;
        }

        public void setWorkerExtensionId(int workerExtensionId) {
            WorkerExtensionId = workerExtensionId;
        }
    }

    //被检查人实体
    public static class xjkpCheckResponsiblePerson {
        //实际负责人编号
        private int InstanceResponsiblePersonId;
        //检查结果编号
        private int CheckResultsId;
        //职工信息编号
        private int WorkerExtensionId;

        public int getInstanceResponsiblePersonId() {
            return InstanceResponsiblePersonId;
        }

        public void setInstanceResponsiblePersonId(int instanceResponsiblePersonId) {
            InstanceResponsiblePersonId = instanceResponsiblePersonId;
        }

        public int getCheckResultsId() {
            return CheckResultsId;
        }

        public void setCheckResultsId(int checkResultsId) {
            CheckResultsId = checkResultsId;
        }

        public int getWorkerExtensionId() {
            return WorkerExtensionId;
        }

        public void setWorkerExtensionId(int workerExtensionId) {
            WorkerExtensionId = workerExtensionId;
        }
    }

    //检查对象
    public static class xjkpCheckObject {
        //检查对象编号
        private int InstanceCheckObjectId;
        //检查结果编号
        private int CheckResultsId;
        //检查对象表
        private String CheckObjectTable;
        //检查对象ID
        private int CheckObject;
        //对象名称
        private String Name;

        public int getInstanceCheckObjectId() {
            return InstanceCheckObjectId;
        }

        public void setInstanceCheckObjectId(int instanceCheckObjectId) {
            InstanceCheckObjectId = instanceCheckObjectId;
        }

        public int getCheckResultsId() {
            return CheckResultsId;
        }

        public void setCheckResultsId(int checkResultsId) {
            CheckResultsId = checkResultsId;
        }

        public String getCheckObjectTable() {
            return CheckObjectTable;
        }

        public void setCheckObjectTable(String checkObjectTable) {
            CheckObjectTable = checkObjectTable;
        }

        public int getCheckObject() {
            return CheckObject;
        }

        public void setCheckObject(int checkObject) {
            CheckObject = checkObject;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }

    //详情检查结果
    public static class xjkpCheckDetailList {
        //详细内容编号
        private int DetailedContentId;
        //检查结果编号
        private int CheckResultsId;
        //项目名称
        private String ItName;
        //项目内容
        private String ItemContent;
        //最高分
        private int MinScore;
        //最低分
        private int MaxScore;
        //数字检查结论
        private int IntCheckResult;
        //文本检查结论
        private String VcharCheckResult;
        //备注
        private String Remark;

        public int getDetailedContentId() {
            return DetailedContentId;
        }

        public void setDetailedContentId(int detailedContentId) {
            DetailedContentId = detailedContentId;
        }

        public int getCheckResultsId() {
            return CheckResultsId;
        }

        public void setCheckResultsId(int checkResultsId) {
            CheckResultsId = checkResultsId;
        }

        public String getItName() {
            return ItName;
        }

        public void setItName(String itName) {
            ItName = itName;
        }

        public String getItemContent() {
            return ItemContent;
        }

        public void setItemContent(String itemContent) {
            ItemContent = itemContent;
        }

        public int getMinScore() {
            return MinScore;
        }

        public void setMinScore(int minScore) {
            MinScore = minScore;
        }

        public int getMaxScore() {
            return MaxScore;
        }

        public void setMaxScore(int maxScore) {
            MaxScore = maxScore;
        }

        public int getIntCheckResult() {
            return IntCheckResult;
        }

        public void setIntCheckResult(int intCheckResult) {
            IntCheckResult = intCheckResult;
        }

        public String getVcharCheckResult() {
            return VcharCheckResult;
        }

        public void setVcharCheckResult(String vcharCheckResult) {
            VcharCheckResult = vcharCheckResult;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }
    }
}
