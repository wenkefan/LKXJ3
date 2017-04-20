package com.fwk.lkxj3.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/9/19.
 */
public class NewTypeBean implements Serializable {


    /**
     * Success : 10000
     * Message :
     * RerurnValue : [{"CheckCategoryList":[{"CheckCategoryId":3,"OrganizationId":1,"CategoryName":"厨房检查","CheckConclusionId":3,"CheckObjectTable":"1","CheckObjectCategory":2,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2170,"CheckClassificationId":1},{"CheckCategoryId":13,"OrganizationId":1,"CategoryName":"校车安全","CheckConclusionId":1,"CheckObjectTable":"1","CheckObjectCategory":7,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2172,"CheckClassificationId":1},{"CheckCategoryId":14,"OrganizationId":1,"CategoryName":"校车卫生","CheckConclusionId":3,"CheckObjectTable":"1","CheckObjectCategory":7,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2174,"CheckClassificationId":1},{"CheckCategoryId":8,"OrganizationId":1,"CategoryName":"幼儿午睡","CheckConclusionId":5,"CheckObjectTable":"1","CheckObjectCategory":1,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2158,"CheckClassificationId":1}],"CheckClassificationId":1,"Name":"保育工作","OrganizationId":0,"DisplayOrder":1,"FunctionItemID":2151},{"CheckCategoryList":[{"CheckCategoryId":2,"OrganizationId":1,"CategoryName":"备课检查","CheckConclusionId":3,"CheckObjectTable":"2","CheckObjectCategory":1,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2162,"CheckClassificationId":2},{"CheckCategoryId":6,"OrganizationId":1,"CategoryName":"教学反馈","CheckConclusionId":1,"CheckObjectTable":"1","CheckObjectCategory":1,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2164,"CheckClassificationId":2},{"CheckCategoryId":5,"OrganizationId":1,"CategoryName":"日常教学","CheckConclusionId":4,"CheckObjectTable":"1","CheckObjectCategory":1,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2221,"CheckClassificationId":2},{"CheckCategoryId":7,"OrganizationId":1,"CategoryName":"听课评课","CheckConclusionId":0,"CheckObjectTable":"1","CheckObjectCategory":1,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2166,"CheckClassificationId":2}],"CheckClassificationId":2,"Name":"医疗保健工作","OrganizationId":115,"DisplayOrder":2,"FunctionItemID":2421}]
     */

    private int Success;
    private String Message;
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
        /**
         * CheckCategoryList : [{"CheckCategoryId":3,"OrganizationId":1,"CategoryName":"厨房检查","CheckConclusionId":3,"CheckObjectTable":"1","CheckObjectCategory":2,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2170,"CheckClassificationId":1},{"CheckCategoryId":13,"OrganizationId":1,"CategoryName":"校车安全","CheckConclusionId":1,"CheckObjectTable":"1","CheckObjectCategory":7,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2172,"CheckClassificationId":1},{"CheckCategoryId":14,"OrganizationId":1,"CategoryName":"校车卫生","CheckConclusionId":3,"CheckObjectTable":"1","CheckObjectCategory":7,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2174,"CheckClassificationId":1},{"CheckCategoryId":8,"OrganizationId":1,"CategoryName":"幼儿午睡","CheckConclusionId":5,"CheckObjectTable":"1","CheckObjectCategory":1,"OrganizationCode":"0","ParentId":0,"DepthLevel":1,"State":1,"ManualChooseObject":1,"FunctionItemID":2158,"CheckClassificationId":1}]
         * CheckClassificationId : 1
         * Name : 保育工作
         * OrganizationId : 0
         * DisplayOrder : 1
         * FunctionItemID : 2151
         */

        private int CheckClassificationId;
        private String Name;
        private int OrganizationId;
        private int DisplayOrder;
        private int FunctionItemID;
        private List<CheckCategoryListBean> CheckCategoryList;

        public int getCheckClassificationId() {
            return CheckClassificationId;
        }

        public void setCheckClassificationId(int CheckClassificationId) {
            this.CheckClassificationId = CheckClassificationId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getOrganizationId() {
            return OrganizationId;
        }

        public void setOrganizationId(int OrganizationId) {
            this.OrganizationId = OrganizationId;
        }

        public int getDisplayOrder() {
            return DisplayOrder;
        }

        public void setDisplayOrder(int DisplayOrder) {
            this.DisplayOrder = DisplayOrder;
        }

        public int getFunctionItemID() {
            return FunctionItemID;
        }

        public void setFunctionItemID(int FunctionItemID) {
            this.FunctionItemID = FunctionItemID;
        }

        public List<CheckCategoryListBean> getCheckCategoryList() {
            return CheckCategoryList;
        }

        public void setCheckCategoryList(List<CheckCategoryListBean> CheckCategoryList) {
            this.CheckCategoryList = CheckCategoryList;
        }

        public static class CheckCategoryListBean implements Serializable {
            /**
             * CheckCategoryId : 3
             * OrganizationId : 1
             * CategoryName : 厨房检查
             * CheckConclusionId : 3
             * CheckObjectTable : 1
             * CheckObjectCategory : 2
             * OrganizationCode : 0
             * ParentId : 0
             * DepthLevel : 1
             * State : 1
             * ManualChooseObject : 1
             * FunctionItemID : 2170
             * CheckClassificationId : 1
             */

            private int CheckCategoryId;
            private int OrganizationId;
            private String CategoryName;
            private int CheckConclusionId;
            private String CheckObjectTable;
            private int CheckObjectCategory;
            private String OrganizationCode;
            private int ParentId;
            private int DepthLevel;
            private int State;
            private int ManualChooseObject;
            private int FunctionItemID;
            private int CheckClassificationId;

            public int getCheckCategoryId() {
                return CheckCategoryId;
            }

            public void setCheckCategoryId(int CheckCategoryId) {
                this.CheckCategoryId = CheckCategoryId;
            }

            public int getOrganizationId() {
                return OrganizationId;
            }

            public void setOrganizationId(int OrganizationId) {
                this.OrganizationId = OrganizationId;
            }

            public String getCategoryName() {
                return CategoryName;
            }

            public void setCategoryName(String CategoryName) {
                this.CategoryName = CategoryName;
            }

            public int getCheckConclusionId() {
                return CheckConclusionId;
            }

            public void setCheckConclusionId(int CheckConclusionId) {
                this.CheckConclusionId = CheckConclusionId;
            }

            public String getCheckObjectTable() {
                return CheckObjectTable;
            }

            public void setCheckObjectTable(String CheckObjectTable) {
                this.CheckObjectTable = CheckObjectTable;
            }

            public int getCheckObjectCategory() {
                return CheckObjectCategory;
            }

            public void setCheckObjectCategory(int CheckObjectCategory) {
                this.CheckObjectCategory = CheckObjectCategory;
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

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public int getManualChooseObject() {
                return ManualChooseObject;
            }

            public void setManualChooseObject(int ManualChooseObject) {
                this.ManualChooseObject = ManualChooseObject;
            }

            public int getFunctionItemID() {
                return FunctionItemID;
            }

            public void setFunctionItemID(int FunctionItemID) {
                this.FunctionItemID = FunctionItemID;
            }

            public int getCheckClassificationId() {
                return CheckClassificationId;
            }

            public void setCheckClassificationId(int CheckClassificationId) {
                this.CheckClassificationId = CheckClassificationId;
            }
        }
    }
}
