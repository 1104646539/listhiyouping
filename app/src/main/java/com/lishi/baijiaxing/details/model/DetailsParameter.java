package com.lishi.baijiaxing.details.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class DetailsParameter {

    /**
     * parameterTitle : 主体
     * parameters : [{"parameterName":"颜色","parameterValue":"红"},{"parameterName":"颜色","parameterValue":"红"}]
     */

    private List<ParameterListBean> parameterList;

    public List<ParameterListBean> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<ParameterListBean> parameterList) {
        this.parameterList = parameterList;
    }

    public static class ParameterListBean {
        private String parameterTitle;
        /**
         * parameterName : 颜色
         * parameterValue : 红
         */

        private List<ParametersBean> parameters;

        public String getParameterTitle() {
            return parameterTitle;
        }

        public void setParameterTitle(String parameterTitle) {
            this.parameterTitle = parameterTitle;
        }

        public List<ParametersBean> getParameters() {
            return parameters;
        }

        public void setParameters(List<ParametersBean> parameters) {
            this.parameters = parameters;
        }

        public static class ParametersBean {
            private String parameterName;
            private String parameterValue;

            public String getParameterName() {
                return parameterName;
            }

            public void setParameterName(String parameterName) {
                this.parameterName = parameterName;
            }

            public String getParameterValue() {
                return parameterValue;
            }

            public void setParameterValue(String parameterValue) {
                this.parameterValue = parameterValue;
            }
        }
    }
}
