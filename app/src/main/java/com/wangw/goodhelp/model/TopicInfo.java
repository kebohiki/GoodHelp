package com.wangw.goodhelp.model;

import java.util.List;

/**
 * Created by wangw on 2016/4/20.
 */
public class TopicInfo {


    /**
     * topic_id : 1488
     * admin_id : 1451
     * topic_name :
     * topic_tag_id : 1453
     * file_id : 0
     * isused : 0
     * topic_intro : 一盏春风润物喉——安吉白茶
     地址：郑州市文博城民康街二层十楼26-29号瀚宇轩茶铺
     电话：037156159590/18837161917
     * topic_desc :
     * topic_tag :
     * created_time : 1460448770
     * author : {"uid":"1451","avatar_id":"0","username":"崔梦鑫","nickname":"豆豆。","usertype":"auth","email":"","passwd":"7f44b13955235245b249","file":{"key":"","type":"file","width":"180","height":"180","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1}}
     * tag : {"topic_tag_id":"1453","tag_name":"茶叶","following_count":"0","rating":"0","locked":"0","created_time":"1460011421"}
     * files : [{"key":"2016/04/12/204e35e3ed13330ce9a22bba64b5aeaf.jpg","path":"2016/04/12/","type":"image","width":"720","height":"699","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1},{"key":"2016/04/12/5dedd88c81ce519441ec0c73687bde8c.jpg","path":"2016/04/12/","type":"image","width":"960","height":"1280","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1},{"key":"2016/04/12/689539da2d17c27b4d0f777fd9b1603d.jpg","path":"2016/04/12/","type":"image","width":"960","height":"1185","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1},{"key":"2016/04/12/2253c04cfa1bf1ff8282e299e496ebfd.jpg","path":"2016/04/12/","type":"image","width":"960","height":"1280","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1},{"key":"2016/04/12/6b17bd44b31f2eb0616484f6a41edbec.jpg","path":"2016/04/12/","type":"image","width":"960","height":"1280","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1}]
     */


    private String topic_id;
    private String admin_id;
    private String topic_name;
    private String topic_tag_id;
    private String file_id;
    private String isused;
    private String topic_intro;
    private String topic_desc;
    private String topic_tag;
    private String created_time;
    /**
     * uid : 1451
     * avatar_id : 0
     * username : 崔梦鑫
     * nickname : 豆豆。
     * usertype : auth
     * email :
     * passwd : 7f44b13955235245b249
     * file : {"key":"","type":"file","width":"180","height":"180","farm":"farm1","bucket":"hbimg","host":"http://ningweb.com/product_picture/","frames":1}
     */

    private AuthorEntity author;
    /**
     * topic_tag_id : 1453
     * tag_name : 茶叶
     * following_count : 0
     * rating : 0
     * locked : 0
     * created_time : 1460011421
     */

    private TagEntity tag;
    /**
     * key : 2016/04/12/204e35e3ed13330ce9a22bba64b5aeaf.jpg
     * path : 2016/04/12/
     * type : image
     * width : 720
     * height : 699
     * farm : farm1
     * bucket : hbimg
     * host : http://ningweb.com/product_picture/
     * frames : 1
     */

    private List<FilesEntity> files;

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_tag_id() {
        return topic_tag_id;
    }

    public void setTopic_tag_id(String topic_tag_id) {
        this.topic_tag_id = topic_tag_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused;
    }

    public String getTopic_intro() {
        return topic_intro;
    }

    public void setTopic_intro(String topic_intro) {
        this.topic_intro = topic_intro;
    }

    public String getTopic_desc() {
        return topic_desc;
    }

    public void setTopic_desc(String topic_desc) {
        this.topic_desc = topic_desc;
    }

    public String getTopic_tag() {
        return topic_tag;
    }

    public void setTopic_tag(String topic_tag) {
        this.topic_tag = topic_tag;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    public List<FilesEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FilesEntity> files) {
        this.files = files;
    }

    public static class AuthorEntity {
        private String uid;
        private String avatar_id;
        private String username;
        private String nickname;
        private String usertype;
        private String email;
        private String passwd;
        /**
         * key :
         * type : file
         * width : 180
         * height : 180
         * farm : farm1
         * bucket : hbimg
         * host : http://ningweb.com/product_picture/
         * frames : 1
         */

        private FileEntity file;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatar_id() {
            return avatar_id;
        }

        public void setAvatar_id(String avatar_id) {
            this.avatar_id = avatar_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public FileEntity getFile() {
            return file;
        }

        public void setFile(FileEntity file) {
            this.file = file;
        }

        public static class FileEntity {
            private String key;
            private String type;
            private String width;
            private String height;
            private String farm;
            private String bucket;
            private String host;
            private int frames;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getFarm() {
                return farm;
            }

            public void setFarm(String farm) {
                this.farm = farm;
            }

            public String getBucket() {
                return bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getFrames() {
                return frames;
            }

            public void setFrames(int frames) {
                this.frames = frames;
            }
        }
    }

    public static class TagEntity {
        private String topic_tag_id;
        private String tag_name;
        private String following_count;
        private String rating;
        private String locked;
        private String created_time;

        public String getTopic_tag_id() {
            return topic_tag_id;
        }

        public void setTopic_tag_id(String topic_tag_id) {
            this.topic_tag_id = topic_tag_id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getFollowing_count() {
            return following_count;
        }

        public void setFollowing_count(String following_count) {
            this.following_count = following_count;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getLocked() {
            return locked;
        }

        public void setLocked(String locked) {
            this.locked = locked;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }
    }

    public static class FilesEntity {
        private String key;
        private String path;
        private String type;
        private String width;
        private String height;
        private String farm;
        private String bucket;
        private String host;
        private int frames;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getFarm() {
            return farm;
        }

        public void setFarm(String farm) {
            this.farm = farm;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getFrames() {
            return frames;
        }

        public void setFrames(int frames) {
            this.frames = frames;
        }
    }
}
