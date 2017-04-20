package com.fwk.lkxj3.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/13.
 */

public class QuYuBean implements Serializable {


    /**
     * Success : 10000
     * Message :
     * RerurnValue : [{"CardNo":"2AE30C65","DistrictId":22,"OrganizationId":33,"Name":"大1班","OrganizationCode":"2","ParentId":21,"DepthLevel":1},{"CardNo":"0A220465","DistrictId":23,"OrganizationId":33,"Name":"大2班","OrganizationCode":"2","ParentId":21,"DepthLevel":1},{"CardNo":"0E24B8EE","DistrictId":54,"OrganizationId":33,"Name":"中1班","OrganizationCode":"2","ParentId":21,"DepthLevel":1},{"CardNo":"5EA991EE","DistrictId":55,"OrganizationId":33,"Name":"中2班","OrganizationCode":"2","ParentId":21,"DepthLevel":1},{"CardNo":null,"DistrictId":21,"OrganizationId":33,"Name":"","OrganizationCode":"2","ParentId":null,"DepthLevel":1}]
     */

    private int Success;
    private String Message;
    /**
     * CardNo : 2AE30C65
     * DistrictId : 22
     * OrganizationId : 33
     * Name : 大1班
     * OrganizationCode : 2
     * ParentId : 21
     * DepthLevel : 1
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

    public static class RerurnValueBean implements Serializable{
        private String CardNo;
        private int DistrictId;
        private int OrganizationId;
        private String Name;
        private String OrganizationCode;
        private int ParentId;
        private int DepthLevel;

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public int getDistrictId() {
            return DistrictId;
        }

        public void setDistrictId(int DistrictId) {
            this.DistrictId = DistrictId;
        }

        public int getOrganizationId() {
            return OrganizationId;
        }

        public void setOrganizationId(int OrganizationId) {
            this.OrganizationId = OrganizationId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
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
    }
}
