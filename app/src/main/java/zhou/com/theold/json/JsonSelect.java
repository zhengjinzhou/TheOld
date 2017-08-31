package zhou.com.theold.json;

import java.util.List;

/**
 * Created by zhou on 2017/8/24.
 */

public class JsonSelect {

    /**
     * message : 获取记录选项成功
     * error : -1
     * selectList : [{"id":1,"name":"日常活动","priority":5,"types":[{"assesItems":[{"description":"需极大帮助或完全依赖他人","grade":0,"id":18},{"description":"需部分帮助（需他人搀扶去厕所、需他人帮忙冲水或整理衣裤等）","grade":5,"id":17},{"description":"可独立完成","grade":10,"id":16}],"id":7,"typeName":"如厕"},{"assesItems":[{"description":"完全失控，或留置导尿管","grade":0,"id":15},{"description":"偶尔失控（每天<1次，但每周>1次），或需要他人提示","grade":5,"id":14},{"description":"可控制小便","grade":10,"id":13}],"id":6,"typeName":"小便控制"},{"assesItems":[{"description":"需部分帮助（因肢体残疾、平衡能力差、过度衰弱、视力等问题，在一定程度上需他人地搀扶或使用拐杖、助行器等辅助用具）","grade":10,"id":24},{"description":"可独立在平地上行走45m","grade":15,"id":23},{"description":"需极大帮助（因肢体残疾、平衡能力差、过度衰弱、视力等问题，在较大程度上依赖他人搀扶，或坐在轮椅上自行移动）","grade":5,"id":25},{"description":" 完全依赖他人","grade":0,"id":26}],"id":9,"typeName":"平地行走"},{"assesItems":[{"description":"需部分帮助（需他人搀扶或使用拐杖）","grade":10,"id":20},{"description":"可独立完成","grade":15,"id":19},{"description":"完全依赖他人","grade":0,"id":22},{"description":" 需极大帮助（较大程度上依赖他人搀扶和帮助）","grade":5,"id":21}],"id":8,"typeName":"床椅转移"},{"assesItems":[{"description":"可独立上下楼梯（连续上下10-15个台阶）","grade":10,"id":27},{"description":" 需部分帮助（需扶着楼梯、他人搀扶，或使用拐杖等）","grade":5,"id":28},{"description":" 需极大帮助或完全依赖他人","grade":0,"id":29}],"id":10,"typeName":"上下楼梯"},{"assesItems":[{"description":"需极大帮助或完全依赖他人，或有留置营养管","grade":0,"id":3},{"description":"需部分帮助（进食过程中需要一定帮助，如协助把持餐具）","grade":5,"id":2},{"description":"可独立进食（在合理的时间内独立进食准备好的食物）","grade":10,"id":1}],"id":1,"typeName":"进食"},{"assesItems":[{"description":"在洗澡过程中需他人帮助","grade":0,"id":5},{"description":"准备好洗澡水后，可自己独立完成洗澡过程","grade":5,"id":4}],"id":2,"typeName":"洗澡"},{"assesItems":[{"description":"需他人帮助","grade":0,"id":7},{"description":" 可自己独立完成","grade":5,"id":6}],"id":3,"typeName":"修饰"},{"assesItems":[{"description":"需极大帮助或完全依赖他人","grade":0,"id":82},{"description":"需部分帮助（能自己穿脱，但需他人帮助整理衣物、系扣/鞋带、拉拉链）","grade":5,"id":9},{"description":"可独立完成","grade":10,"id":8}],"id":4,"typeName":"穿衣"},{"assesItems":[{"description":"完全失控","grade":0,"id":12},{"description":"可控制大便","grade":10,"id":10},{"description":" 偶尔失控（每周<1次），或需要他人提示","grade":5,"id":11}],"id":5,"typeName":"大便控制"}]},{"id":2,"name":"精神状态","priority":7,"types":[{"assesItems":[{"description":"无身体攻击行为（如打/踢/推/咬/抓/摔东西）和语言攻击行为（如骂人、语言威胁、尖叫）","grade":0,"id":33},{"description":"每周有几次身体攻击行为，或每日有语言攻击行为","grade":2,"id":35},{"description":"每月有几次身体攻击行为，或每周有几次语言攻击行为","grade":1,"id":34}],"id":12,"typeName":"攻击行为"},{"assesItems":[{"description":"画钟错误（画的圆不闭锁，或指针位置不准确），或只回忆出0-1个词","grade":1,"id":31},{"description":"画钟正确（画出一个闭锁圆，指针位置准确），且能回忆出2-3个词","grade":0,"id":30},{"description":"已确诊为认知障碍，如老年痴呆","grade":2,"id":32}],"id":11,"typeName":"认知功能"},{"assesItems":[{"description":"无","grade":0,"id":36},{"description":"情绪低落、不爱说话、不爱梳洗、不爱活动","grade":1,"id":37},{"description":"有自杀念头或自杀行为","grade":2,"id":38}],"id":13,"typeName":"抑郁症状"}]},{"id":3,"name":"感知沟通","priority":3,"types":[{"assesItems":[{"description":"讲话者大声说话或说话很慢，才能部分听见","grade":3,"id":51},{"description":"正常交流有些困难，需在安静的环静或大声说话才能听到","grade":2,"id":50},{"description":"完全听不见","grade":4,"id":52},{"description":"在轻声说话或说话距离超过2米时听不清","grade":1,"id":49},{"description":"可正常交谈，能听到电视、电话、门铃的声音","grade":0,"id":48}],"id":16,"typeName":"听力"},{"assesItems":[{"description":"辨认物体有困难，但眼睛能跟随物体移动，只能看到光、颜色和形状","grade":2,"id":46},{"description":"视力有限，看不清报纸大标题，但能辨认物体","grade":2,"id":45},{"description":"能看清楚大字体， 看不清书报上的标准字体","grade":1,"id":44},{"description":"能看清书报上的标准字体","grade":0,"id":43},{"description":"没有视力，眼睛不能跟随物体移动","grade":4,"id":47}],"id":15,"typeName":"视力"},{"assesItems":[{"description":"昏迷，处于浅昏迷时对疼痛刺激有回避和痛苦表情；处于深昏迷时对刺激无反应","grade":3,"id":42},{"description":"昏睡，一般的外界刺激不能使其觉醒，给予较强烈的刺激时可有短时的意识清醒，醒后可简短回答提问，当刺激减弱后又很快进入睡眠状态","grade":2,"id":41},{"description":"嗜睡，表现为睡眠状态过度延长。当呼唤或推动患者的肢体时可唤醒，并能进行正确的交谈或执行指令，停止刺激后又继续入睡","grade":1,"id":40},{"description":"神志清醒，对周围环境警觉","grade":0,"id":39}],"id":14,"typeName":"意识水平"},{"assesItems":[{"description":"表达需要或理解有困难，需频繁重复或简化口头表达","grade":2,"id":55},{"description":"能够表达自己的需要及理解别人的话，但需要增加时间或给予帮助","grade":1,"id":54},{"description":"不能表达需要或理解他人的话","grade":3,"id":56},{"description":"无困难，能与他人正常沟通和交流","grade":0,"id":53}],"id":17,"typeName":"沟通交流"}]},{"id":4,"name":"社会参与","priority":6,"types":[{"assesItems":[{"description":"时间观念较差，年、月、日不清楚，可知上半年或下半年；只能单独在家附近行动，对现住地只知名称，不知道方位","grade":2,"id":69},{"description":"时间观念很差，年、月、日不清楚，可知上午或下午；只能在左邻右舍间串门，对现住地不知名称和方位","grade":3,"id":70},{"description":"无时间观念；不能单独外出","grade":4,"id":71},{"description":"时间观念（年月日时）清楚；可单独出远门，能很快掌握新环境的方位","grade":0,"id":67},{"description":"时间观念有些下降，年、月、日清楚，但有时相差几天；可单独来往于近街，知道现住地的名称和方位，但不知回家路线","grade":1,"id":68}],"id":20,"typeName":"时间/空间能力"},{"assesItems":[{"description":"知道周围人们的关系，知道祖孙、叔伯、姑姨、侄子侄女等称谓的意义；可分辨陌生人的大致年龄和身份，可用适当称呼","grade":0,"id":72},{"description":"只知家中亲密近亲的关系，不会分辨陌生人的大致年龄，不能称呼陌生人","grade":1,"id":73},{"description":"只能称呼家中人，或只能照样称呼，不知其关系，不辨辈分","grade":2,"id":74},{"description":"只认识常同住的亲人，可称呼子女或孙子女，可辨熟人和生人","grade":3,"id":75},{"description":"只认识保护人，不辨熟人和生人","grade":4,"id":76}],"id":21,"typeName":"人物定向"},{"assesItems":[{"description":"难以与人接触","grade":4,"id":81},{"description":"勉强可与人交往，谈吐内容不清楚，表情不恰当","grade":3,"id":80},{"description":"参与社会，在社会环境有一定的适应能力，待人接物恰当","grade":0,"id":77},{"description":"能适应单纯环境，主动接触人，初见面时难让人发现智力问题，不能理解隐喻语","grade":1,"id":78},{"description":"脱离社会，可被动接触，不会主动待人，谈话中很多不适词句，容易上当受骗","grade":2,"id":79}],"id":22,"typeName":"社会交往能力"},{"assesItems":[{"description":"原来熟练的脑力工作或体力技巧性工作能力有所下降","grade":1,"id":63},{"description":"原来熟练的脑力工作或体力技巧性工作明显不如以往，部分遗忘","grade":2,"id":64},{"description":"原来熟练的脑力工作或体力技巧性工作可照常进行","grade":0,"id":62},{"description":"对熟练工作只有一些片段保留，技能全部遗忘","grade":3,"id":65},{"description":"对以往的知识或技能全部磨灭","grade":4,"id":66}],"id":19,"typeName":"工作能力"},{"assesItems":[{"description":"除个人生活自理外（如饮食、洗漱、穿戴、二便），能料理家务（如做饭、洗衣）或当家管理事务","grade":0,"id":57},{"description":"个人基本生活事务能自理（如饮食、二便），在督促下可洗漱","grade":3,"id":60},{"description":"个人基本生活事务（如饮食、二便）需要部分帮助或完全依赖他人帮助","grade":4,"id":61},{"description":"除个人生活自理外，能做家务，但欠好，家庭事务安排欠条理","grade":1,"id":58},{"description":"个人生活能自理；只有在他人帮助下才能做些家务，但质量不好","grade":2,"id":59}],"id":18,"typeName":"生活能力"}]},{"id":11,"name":"行为习惯","priority":7,"types":[{"assesItems":[{"description":"每天开心一点","grade":5,"id":84}],"id":28,"typeName":"心情"}]}]
     */

    private String message;
    private String error;
    private List<SelectListBean> selectList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<SelectListBean> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<SelectListBean> selectList) {
        this.selectList = selectList;
    }

    public static class SelectListBean {
        /**
         * id : 1
         * name : 日常活动
         * priority : 5
         * types : [{"assesItems":[{"description":"需极大帮助或完全依赖他人","grade":0,"id":18},{"description":"需部分帮助（需他人搀扶去厕所、需他人帮忙冲水或整理衣裤等）","grade":5,"id":17},{"description":"可独立完成","grade":10,"id":16}],"id":7,"typeName":"如厕"},{"assesItems":[{"description":"完全失控，或留置导尿管","grade":0,"id":15},{"description":"偶尔失控（每天<1次，但每周>1次），或需要他人提示","grade":5,"id":14},{"description":"可控制小便","grade":10,"id":13}],"id":6,"typeName":"小便控制"},{"assesItems":[{"description":"需部分帮助（因肢体残疾、平衡能力差、过度衰弱、视力等问题，在一定程度上需他人地搀扶或使用拐杖、助行器等辅助用具）","grade":10,"id":24},{"description":"可独立在平地上行走45m","grade":15,"id":23},{"description":"需极大帮助（因肢体残疾、平衡能力差、过度衰弱、视力等问题，在较大程度上依赖他人搀扶，或坐在轮椅上自行移动）","grade":5,"id":25},{"description":" 完全依赖他人","grade":0,"id":26}],"id":9,"typeName":"平地行走"},{"assesItems":[{"description":"需部分帮助（需他人搀扶或使用拐杖）","grade":10,"id":20},{"description":"可独立完成","grade":15,"id":19},{"description":"完全依赖他人","grade":0,"id":22},{"description":" 需极大帮助（较大程度上依赖他人搀扶和帮助）","grade":5,"id":21}],"id":8,"typeName":"床椅转移"},{"assesItems":[{"description":"可独立上下楼梯（连续上下10-15个台阶）","grade":10,"id":27},{"description":" 需部分帮助（需扶着楼梯、他人搀扶，或使用拐杖等）","grade":5,"id":28},{"description":" 需极大帮助或完全依赖他人","grade":0,"id":29}],"id":10,"typeName":"上下楼梯"},{"assesItems":[{"description":"需极大帮助或完全依赖他人，或有留置营养管","grade":0,"id":3},{"description":"需部分帮助（进食过程中需要一定帮助，如协助把持餐具）","grade":5,"id":2},{"description":"可独立进食（在合理的时间内独立进食准备好的食物）","grade":10,"id":1}],"id":1,"typeName":"进食"},{"assesItems":[{"description":"在洗澡过程中需他人帮助","grade":0,"id":5},{"description":"准备好洗澡水后，可自己独立完成洗澡过程","grade":5,"id":4}],"id":2,"typeName":"洗澡"},{"assesItems":[{"description":"需他人帮助","grade":0,"id":7},{"description":" 可自己独立完成","grade":5,"id":6}],"id":3,"typeName":"修饰"},{"assesItems":[{"description":"需极大帮助或完全依赖他人","grade":0,"id":82},{"description":"需部分帮助（能自己穿脱，但需他人帮助整理衣物、系扣/鞋带、拉拉链）","grade":5,"id":9},{"description":"可独立完成","grade":10,"id":8}],"id":4,"typeName":"穿衣"},{"assesItems":[{"description":"完全失控","grade":0,"id":12},{"description":"可控制大便","grade":10,"id":10},{"description":" 偶尔失控（每周<1次），或需要他人提示","grade":5,"id":11}],"id":5,"typeName":"大便控制"}]
         */

        private int id;
        private String name;
        private int priority;
        private List<TypesBean> types;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public List<TypesBean> getTypes() {
            return types;
        }

        public void setTypes(List<TypesBean> types) {
            this.types = types;
        }

        public static class TypesBean {
            /**
             * assesItems : [{"description":"需极大帮助或完全依赖他人","grade":0,"id":18},{"description":"需部分帮助（需他人搀扶去厕所、需他人帮忙冲水或整理衣裤等）","grade":5,"id":17},{"description":"可独立完成","grade":10,"id":16}]
             * id : 7
             * typeName : 如厕
             */

            private int id;
            private String typeName;
            private List<AssesItemsBean> assesItems;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<AssesItemsBean> getAssesItems() {
                return assesItems;
            }

            public void setAssesItems(List<AssesItemsBean> assesItems) {
                this.assesItems = assesItems;
            }

            public static class AssesItemsBean {
                /**
                 * description : 需极大帮助或完全依赖他人
                 * grade : 0
                 * id : 18
                 */

                private String description;
                private int grade;
                private int id;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
