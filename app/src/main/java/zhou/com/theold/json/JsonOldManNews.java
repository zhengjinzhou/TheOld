package zhou.com.theold.json;

/**
 * Created by zhou on 2017/8/24.
 */

public class JsonOldManNews {
    /**
     * message : 查询成功
     * old : {"accident":"无摔倒,无噎食","accident_others":"","birthday":"2017-07-31","diploma":"文盲","gender":1,"id":90,"illness":"未明确诊断患有疾病 ,消化系统疾病","illness_others":"","income":10000,"income_others":"","income_ways":"退休金/养老金,子女补贴","infoCode":"","insantity":"癫痫所致精神障碍,无精神病表现","insantity_others":"","living":"独居,与配偶/伴侣居住,养老机构","marry_status":"已婚","miniUrl":"/upload/file/mini/201707/31210950xcdg.jpg","nowTime":"","oafish":"无痴呆表现","pay_others":"","payways":"城镇职工基本医疗保险","photoUrl":"/upload/file/201707/31210950mek1.jpg","recordCode":"","religion":1,"town":{"id":1,"townName":"莞城"},"username":"fuckME"}
     * error : -1
     */

    private String message;
    private OldBean old;
    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OldBean getOld() {
        return old;
    }

    public void setOld(OldBean old) {
        this.old = old;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class OldBean {
        /**
         * accident : 无摔倒,无噎食
         * accident_others :
         * birthday : 2017-07-31
         * diploma : 文盲
         * gender : 1
         * id : 90
         * illness : 未明确诊断患有疾病 ,消化系统疾病
         * illness_others :
         * income : 10000
         * income_others :
         * income_ways : 退休金/养老金,子女补贴
         * infoCode :
         * insantity : 癫痫所致精神障碍,无精神病表现
         * insantity_others :
         * living : 独居,与配偶/伴侣居住,养老机构
         * marry_status : 已婚
         * miniUrl : /upload/file/mini/201707/31210950xcdg.jpg
         * nowTime :
         * oafish : 无痴呆表现
         * pay_others :
         * payways : 城镇职工基本医疗保险
         * photoUrl : /upload/file/201707/31210950mek1.jpg
         * recordCode :
         * religion : 1
         * town : {"id":1,"townName":"莞城"}
         * username : fuckME
         */

        private String accident;
        private String accident_others;
        private String birthday;
        private String diploma;
        private int gender;
        private int id;
        private String illness;
        private String illness_others;
        private int income;
        private String income_others;
        private String income_ways;
        private String infoCode;
        private String insantity;
        private String insantity_others;
        private String living;
        private String marry_status;
        private String miniUrl;
        private String nowTime;
        private String oafish;
        private String pay_others;
        private String payways;
        private String photoUrl;
        private String recordCode;
        private int religion;
        private TownBean town;
        private String username;

        public String getAccident() {
            return accident;
        }

        public void setAccident(String accident) {
            this.accident = accident;
        }

        public String getAccident_others() {
            return accident_others;
        }

        public void setAccident_others(String accident_others) {
            this.accident_others = accident_others;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getDiploma() {
            return diploma;
        }

        public void setDiploma(String diploma) {
            this.diploma = diploma;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIllness() {
            return illness;
        }

        public void setIllness(String illness) {
            this.illness = illness;
        }

        public String getIllness_others() {
            return illness_others;
        }

        public void setIllness_others(String illness_others) {
            this.illness_others = illness_others;
        }

        public int getIncome() {
            return income;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        public String getIncome_others() {
            return income_others;
        }

        public void setIncome_others(String income_others) {
            this.income_others = income_others;
        }

        public String getIncome_ways() {
            return income_ways;
        }

        public void setIncome_ways(String income_ways) {
            this.income_ways = income_ways;
        }

        public String getInfoCode() {
            return infoCode;
        }

        public void setInfoCode(String infoCode) {
            this.infoCode = infoCode;
        }

        public String getInsantity() {
            return insantity;
        }

        public void setInsantity(String insantity) {
            this.insantity = insantity;
        }

        public String getInsantity_others() {
            return insantity_others;
        }

        public void setInsantity_others(String insantity_others) {
            this.insantity_others = insantity_others;
        }

        public String getLiving() {
            return living;
        }

        public void setLiving(String living) {
            this.living = living;
        }

        public String getMarry_status() {
            return marry_status;
        }

        public void setMarry_status(String marry_status) {
            this.marry_status = marry_status;
        }

        public String getMiniUrl() {
            return miniUrl;
        }

        public void setMiniUrl(String miniUrl) {
            this.miniUrl = miniUrl;
        }

        public String getNowTime() {
            return nowTime;
        }

        public void setNowTime(String nowTime) {
            this.nowTime = nowTime;
        }

        public String getOafish() {
            return oafish;
        }

        public void setOafish(String oafish) {
            this.oafish = oafish;
        }

        public String getPay_others() {
            return pay_others;
        }

        public void setPay_others(String pay_others) {
            this.pay_others = pay_others;
        }

        public String getPayways() {
            return payways;
        }

        public void setPayways(String payways) {
            this.payways = payways;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getRecordCode() {
            return recordCode;
        }

        public void setRecordCode(String recordCode) {
            this.recordCode = recordCode;
        }

        public int getReligion() {
            return religion;
        }

        public void setReligion(int religion) {
            this.religion = religion;
        }

        public TownBean getTown() {
            return town;
        }

        public void setTown(TownBean town) {
            this.town = town;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public static class TownBean {
            /**
             * id : 1
             * townName : 莞城
             */

            private int id;
            private String townName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTownName() {
                return townName;
            }

            public void setTownName(String townName) {
                this.townName = townName;
            }
        }
    }
}
