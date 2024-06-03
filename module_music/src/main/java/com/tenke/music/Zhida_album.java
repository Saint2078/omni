package com.tenke.music;

public class Zhida_album extends Bean {
    private long albumID;

    private String albumMID;

    private String albumName;

    private String albumPic;

    private int albumQuality;

    private String albumname_hilight;

    private String company;

    private int language;

    private String publicTime;

    private long singerID;

    private String singerMID;

    private String singerName;

    private String singername_hilight;

    private int songNum;

    private List<Songlist> songlist;

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

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public String getAlbumPic() {
        return this.albumPic;
    }

    public void setAlbumQuality(int albumQuality) {
        this.albumQuality = albumQuality;
    }

    public int getAlbumQuality() {
        return this.albumQuality;
    }

    public void setAlbumname_hilight(String albumname_hilight) {
        this.albumname_hilight = albumname_hilight;
    }

    public String getAlbumname_hilight() {
        return this.albumname_hilight;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getLanguage() {
        return this.language;
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

    public void setSingername_hilight(String singername_hilight) {
        this.singername_hilight = singername_hilight;
    }

    public String getSingername_hilight() {
        return this.singername_hilight;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public int getSongNum() {
        return this.songNum;
    }

    public void setSonglist(List<Songlist> songlist) {
        this.songlist = songlist;
    }

    public List<Songlist> getSonglist() {
        return this.songlist;
    }
}
