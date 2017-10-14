package com.tutorial.gandalf.user;

import java.util.Date;

/**
 * 用户信息
 *
 * @author Cc
 */
public class User {

    /**
     * 用户id
     */
    private long uid = -1;

    /**
     * 昵称
     */
    private String nickname = "";

    /**
     * 用户头像,为完整地址
     */
    private String avatar = "";

    /**
     * 用户签名
     */
    private String signature = "";

    /**
     * 性别
     */
    private String gender = "unknow";

    /**
     * 注册时间
     */
    private Date createdTime = null;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", gender='" + gender + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
