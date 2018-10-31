package com.yangxiong.gisuper.myapplication;

import java.util.List;

/**
 * 历史记录列表实体对象
 * Created by yangxiong on 2018/10/25.
 */
public class RecordedListBean  {

    private List<PayloadBean> payload;

    public List<PayloadBean> getPayload() {
        return payload;
    }

    public void setPayload(List<PayloadBean> payload) {
        this.payload = payload;
    }

    public static class PayloadBean {
        /**
         * goodsName : 5ETH
         * goodsValue : 5.0000
         * period : 2018082300009
         * joinedNum : 0
         * goodsCode : zsdwuawnz7xpl61fih3hleiyb56qzdn1
         * openTime : 1535092863
         * winningAddress :
         * hitCode : 5
         * status : 0
         */

        private String goodsName;
        private String goodsValue;
        private String period;
        private String joinedNum;
        private String goodsCode;
        private String openTime;
        private String winningAddress;
        private String hitCode;
        private int status;

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsValue() {
            return goodsValue;
        }

        public void setGoodsValue(String goodsValue) {
            this.goodsValue = goodsValue;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getJoinedNum() {
            return joinedNum;
        }

        public void setJoinedNum(String joinedNum) {
            this.joinedNum = joinedNum;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getWinningAddress() {
            return winningAddress;
        }

        public void setWinningAddress(String winningAddress) {
            this.winningAddress = winningAddress;
        }

        public String getHitCode() {
            return hitCode;
        }

        public void setHitCode(String hitCode) {
            this.hitCode = hitCode;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
