package edu.vn.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the shares database table.
 * 
 */
@Entity
@Table(name = "shares")
@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shareId;

	private String emails;

	@Temporal(TemporalType.DATE)
	private Date sharedDate;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "Username")
	private User user;

	// bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Share() {
	}

	public int getShareId() {
		return this.shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getSharedDate() {
		return this.sharedDate;
	}

	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}