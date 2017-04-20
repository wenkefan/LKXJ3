package com.fwk.lkxj3.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/9/22.
 */
public class CreatType implements Serializable {

    /**
     * Success : 10000
     * Message :
     * RerurnValue : [{"CheckItemId":5,"CheckCategoryId":1,"ItName":"厨房","ItemContent":"厨房卫生检查","MinScore":1,"MaxScore":20,"OrganizationCode":"1","ParentId":1,"DepthLevel":1,"CheckConclusionId":1,"State":1}]
     */

    private int Success;
    private String Message;
    /**
     * CheckItemId : 5
     * CheckCategoryId : 1
     * ItName : 厨房
     * ItemContent : 厨房卫生检查
     * MinScore : 1
     * MaxScore : 20
     * OrganizationCode : 1
     * ParentId : 1
     * DepthLevel : 1
     * CheckConclusionId : 1
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
        private int CheckItemId;
        private int CheckCategoryId;
        private String ItName;
        private String ItemContent;
        private int MinScore;
        private int MaxScore;
        private String OrganizationCode;
        private int ParentId;
        private int DepthLevel;
        private int CheckConclusionId;
        private int State;

        public int getCheckItemId() {
            return CheckItemId;
        }

        public void setCheckItemId(int CheckItemId) {
            this.CheckItemId = CheckItemId;
        }

        public int getCheckCategoryId() {
            return CheckCategoryId;
        }

        public void setCheckCategoryId(int CheckCategoryId) {
            this.CheckCategoryId = CheckCategoryId;
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

        public String getOrganizationCode() {
            return OrganizationCode;
        }

        public void setOrganizationCode(String OrganizationCode) {
            this.OrganizationCode = OrganizationCode;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public int getDepthLevel() {
            return DepthLevel;
        }

        public void setDepthLevel(int DepthLevel) {
            this.DepthLevel = DepthLevel;
        }

        public int getCheckConclusionId() {
            return CheckConclusionId;
        }

        public void setCheckConclusionId(int CheckConclusionId) {
            this.CheckConclusionId = CheckConclusionId;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }
    }
}
