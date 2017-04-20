package com.fwk.lkxj3.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/11.
 */

public class JieguoType implements Serializable {

    /**
     * Success : 10000
     * Message : null
     * RerurnValue : [{"DataDictionaryType":"IsOrNoevaluation","DataDictionaryId":1,"DataDictionaryName":"是","LevelCode":"0","Symbol":"0","ParentId":0,"DisplayOrder":1},{"DataDictionaryType":"IsOrNoevaluation","DataDictionaryId":2,"DataDictionaryName":"否","LevelCode":"0","Symbol":"0","ParentId":0,"DisplayOrder":2}]
     */

    private int Success;
    private Object Message;
    /**
     * DataDictionaryType : IsOrNoevaluation
     * DataDictionaryId : 1
     * DataDictionaryName : 是
     * LevelCode : 0
     * Symbol : 0
     * ParentId : 0
     * DisplayOrder : 1
     */

    private List<RerurnValueBean> RerurnValue;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public List<RerurnValueBean> getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(List<RerurnValueBean> RerurnValue) {
        this.RerurnValue = RerurnValue;
    }

    public static class RerurnValueBean implements Serializable {
        private String DataDictionaryType;
        private int DataDictionaryId;
        private String DataDictionaryName;
        private String LevelCode;
        private String Symbol;
        private int ParentId;
        private int DisplayOrder;

        public String getDataDictionaryType() {
            return DataDictionaryType;
        }

        public void setDataDictionaryType(String DataDictionaryType) {
            this.DataDictionaryType = DataDictionaryType;
        }

        public int getDataDictionaryId() {
            return DataDictionaryId;
        }

        public void setDataDictionaryId(int DataDictionaryId) {
            this.DataDictionaryId = DataDictionaryId;
        }

        public String getDataDictionaryName() {
            return DataDictionaryName;
        }

        public void setDataDictionaryName(String DataDictionaryName) {
            this.DataDictionaryName = DataDictionaryName;
        }

        public String getLevelCode() {
            return LevelCode;
        }

        public void setLevelCode(String LevelCode) {
            this.LevelCode = LevelCode;
        }

        public String getSymbol() {
            return Symbol;
        }

        public void setSymbol(String Symbol) {
            this.Symbol = Symbol;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public int getDisplayOrder() {
            return DisplayOrder;
        }

        public void setDisplayOrder(int DisplayOrder) {
            this.DisplayOrder = DisplayOrder;
        }
    }
}
