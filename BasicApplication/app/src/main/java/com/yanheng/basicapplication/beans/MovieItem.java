package com.yanheng.basicapplication.beans;

import java.io.Serializable;
import java.util.List;

public class MovieItem implements Serializable {

    /**
     * rating : {"max":10,"average":6.3,"stars":"35","min":0}
     * genres : ["剧情","喜剧"]
     * title : 来电狂响
     * casts : [{"alt":"https://movie.douban.com/celebrity/1009179/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp"},"name":"佟大为","id":"1009179"},{"alt":"https://movie.douban.com/celebrity/1319032/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444800807.11.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444800807.11.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444800807.11.webp"},"name":"马丽","id":"1319032"},{"alt":"https://movie.douban.com/celebrity/1000145/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2520.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2520.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2520.webp"},"name":"霍思燕","id":"1000145"}]
     * collect_count : 10600
     * original_title : 来电狂响
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1321152/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp"},"name":"于淼","id":"1321152"}]
     * year : 2018
     * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.webp"}
     * alt : https://movie.douban.com/subject/30377703/
     * id : 30377703
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public static class RatingBean implements Serializable{
        /**
         * max : 10
         * average : 6.3
         * stars : 35
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean implements Serializable{
        /**
         * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.webp
         * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.webp
         * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1009179/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp"}
         * name : 佟大为
         * id : 1009179
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean implements Serializable{
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1321152/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp"}
         * name : 于淼
         * id : 1321152
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX implements Serializable{
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
