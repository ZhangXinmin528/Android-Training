package com.zxm.coding.lib_jsoup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zxm.coding.lib_jsoup.YiCaiNewsCategory;

/**
 * Created by ZhangXinmin on 2020/3/19.
 * Copyright (c) 2020 . All rights reserved.
 * 第一财经新闻资讯实体类
 */
public class YiCaiEntity implements Parcelable {

    public static final int TYPE_IMAGE_TEXT = 0;
    public static final int TYPE_TEXT = 1;

    public static final Creator<YiCaiEntity> CREATOR = new Creator<YiCaiEntity>() {
        @Override
        public YiCaiEntity createFromParcel(Parcel in) {
            return new YiCaiEntity(in);
        }

        @Override
        public YiCaiEntity[] newArray(int size) {
            return new YiCaiEntity[size];
        }
    };

    //资讯类型
    private int type;
    //新闻类别
    private YiCaiNewsCategory category;
    //标题
    private String title;
    //简讯
    private String briefNews;
    //缩略图
    private String thumbnailUrl;
    //新闻详情链接
    private String linkfy;
    //发布时间
    private String timeStamp;

    public YiCaiEntity() {
    }

    protected YiCaiEntity(Parcel in) {
        type = in.readInt();
        title = in.readString();
        briefNews = in.readString();
        thumbnailUrl = in.readString();
        linkfy = in.readString();
        timeStamp = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(title);
        dest.writeString(briefNews);
        dest.writeString(thumbnailUrl);
        dest.writeString(linkfy);
        dest.writeString(timeStamp);
    }

    public YiCaiNewsCategory getCategory() {
        return category;
    }

    public void setCategory(YiCaiNewsCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefNews() {
        return briefNews;
    }

    public void setBriefNews(String briefNews) {
        this.briefNews = briefNews;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLinkfy() {
        return linkfy;
    }

    public void setLinkfy(String linkfy) {
        this.linkfy = linkfy;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getType() {
        return -1;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "YiCaiEntity{" +
                "type=" + type +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", briefNews='" + briefNews + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", linkfy='" + linkfy + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
