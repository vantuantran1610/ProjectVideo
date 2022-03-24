package edu.vn.domain;

import java.util.Date;

public class FavoriteReport {
	private String videoTitle;
	private Long favoriteCount;
	private Date newstDate;
	private Date oldestDate;

	public FavoriteReport() {
	}

	public FavoriteReport(String videoTitle, Long favoriteCount, Date newstDate, Date oldestDate) {
		super();
		this.videoTitle = videoTitle;
		this.favoriteCount = favoriteCount;
		this.newstDate = newstDate;
		this.oldestDate = oldestDate;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public Long getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public Date getNewstDate() {
		return newstDate;
	}

	public void setNewstDate(Date newstDate) {
		this.newstDate = newstDate;
	}

	public Date getOldestDate() {
		return oldestDate;
	}

	public void setOldestDate(Date oldestDate) {
		this.oldestDate = oldestDate;
	}

}
