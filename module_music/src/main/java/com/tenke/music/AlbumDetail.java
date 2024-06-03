package com.tenke.music;

import java.util.List;

public class AlbumDetail extends Bean {
    private long albumID;

    private String albumMID;

    private String albumName;

    private String albumName_hilight;

    private String albumPic;

    private String catch_song;

    private String docid;

    private String publicTime;

    private long singerID;

    private String singerMID;

    private String singerName;

    private String singerName_hilight;

    private List<Singer> singer_list;

    private int song_count;

    private int type;

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public long getAlbumID() {
        return this.albumID;
    }

    public void setAlbumMID(String albumMID) {
        this.albumMID = albumMID;
    }

    public String getAlbumMID() {
        return this.albumMID;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName_hilight(String albumName_hilight) {
        this.albumName_hilight = albumName_hilight;
    }

    public String getAlbumName_hilight() {
        return this.albumName_hilight;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public String getAlbumPic() {
        return this.albumPic;
    }

    public void setCatch_song(String catch_song) {
        this.catch_song = catch_song;
    }

    public String getCatch_song() {
        return this.catch_song;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getDocid() {
        return this.docid;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public String getPublicTime() {
        return this.publicTime;
    }

    public void setSingerID(long singerID) {
        this.singerID = singerID;
    }

    public long getSingerID() {
        return this.singerID;
    }

    public void setSingerMID(String singerMID) {
        this.singerMID = singerMID;
    }

    public String getSingerMID() {
        return this.singerMID;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerName() {
        return this.singerName;
    }

    public void setSingerName_hilight(String singerName_hilight) {
        this.singerName_hilight = singerName_hilight;
    }

    public String getSingerName_hilight() {
        return this.singerName_hilight;
    }

    public void setSinger_list(List<Singer> singer_list) {
        this.singer_list = singer_list;
    }

    public List<Singer> getSinger_list() {
        return this.singer_list;
    }

    public void setSong_count(int song_count) {
        this.song_count = song_count;
    }

    public int getSong_count() {
        return this.song_count;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
