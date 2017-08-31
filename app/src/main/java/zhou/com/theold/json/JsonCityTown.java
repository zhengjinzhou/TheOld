package zhou.com.theold.json;

import java.util.List;

/**
 * Created by zhou on 2017/8/24.
 */

public class JsonCityTown {

    /**
     * message : 获取城镇成功!
     * error : 0
     * towns : [{"hashCode":-1171829762,"id":1,"townName":"莞城"},{"hashCode":-1171829761,"id":2,"townName":"南城"},{"hashCode":-1171829760,"id":3,"townName":"东城"},{"hashCode":-1171829759,"id":4,"townName":"万江"},{"hashCode":-1171829758,"id":5,"townName":"石碣镇"},{"hashCode":-1171829757,"id":6,"townName":"石龙镇"},{"hashCode":-1171829756,"id":7,"townName":"茶山镇"},{"hashCode":-1171829755,"id":8,"townName":"石排镇"},{"hashCode":-1171829754,"id":9,"townName":"企石镇"},{"hashCode":-1966984206,"id":10,"townName":"横沥镇"},{"hashCode":-1966984205,"id":11,"townName":"桥头镇"},{"hashCode":-1966984204,"id":12,"townName":"谢岗镇"},{"hashCode":-1966984203,"id":13,"townName":"东坑镇"},{"hashCode":-1966984202,"id":14,"townName":"常平镇"},{"hashCode":-1966984201,"id":15,"townName":"寮步镇"},{"hashCode":-1966984200,"id":16,"townName":"大朗镇"},{"hashCode":-1966984199,"id":17,"townName":"黄江镇"},{"hashCode":-1966984198,"id":18,"townName":"清溪镇"},{"hashCode":-1966984197,"id":19,"townName":"塘厦镇"},{"hashCode":-1966984175,"id":20,"townName":"凤岗镇"},{"hashCode":-1966984174,"id":21,"townName":"长安镇"},{"hashCode":-1966984173,"id":22,"townName":"虎门镇"},{"hashCode":-1966984172,"id":23,"townName":"厚街镇"},{"hashCode":-1966984171,"id":24,"townName":"沙田镇"},{"hashCode":-1966984170,"id":25,"townName":"道滘镇"},{"hashCode":-1966984169,"id":26,"townName":"洪梅镇"},{"hashCode":-1966984168,"id":27,"townName":"麻涌镇"},{"hashCode":-1966984167,"id":28,"townName":"中堂镇"},{"hashCode":-1966984166,"id":29,"townName":"高埗镇"},{"hashCode":-1966984144,"id":30,"townName":"樟木头镇"},{"hashCode":-1966984143,"id":31,"townName":"大岭山镇"},{"hashCode":-1966984142,"id":32,"townName":"望牛墩镇"}]
     */

    private String message;
    private String error;
    private List<TownsBean> towns;

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

    public List<TownsBean> getTowns() {
        return towns;
    }

    public void setTowns(List<TownsBean> towns) {
        this.towns = towns;
    }

    public static class TownsBean {
        /**
         * hashCode : -1171829762
         * id : 1
         * townName : 莞城
         */

        private int hashCode;
        private int id;
        private String townName;

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

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
