package com.cafe24.mysite.vo;

import org.springframework.web.multipart.MultipartFile;

public class BoardVo {
	Long no;
	String title;
	String contents;
	int hit;
	String regDate;
	int groupNo;
	int orderNo;
	int depth;
	Long userNo;
	String userName;
	String originalFilename;
	String extName;
	String saveFileName;
	String imgUrl;
	MultipartFile attach;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public MultipartFile getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
				+ regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userNo=" + userNo
				+ ", userName=" + userName + ", originalFilename=" + originalFilename + ", extName=" + extName
				+ ", saveFileName=" + saveFileName + ", imgUrl=" + imgUrl + ", attach=" + attach + ", getNo()="
				+ getNo() + ", getTitle()=" + getTitle() + ", getContents()=" + getContents() + ", getHit()=" + getHit()
				+ ", getRegDate()=" + getRegDate() + ", getGroupNo()=" + getGroupNo() + ", getOrderNo()=" + getOrderNo()
				+ ", getDepth()=" + getDepth() + ", getUserNo()=" + getUserNo() + ", getUserName()=" + getUserName()
				+ ", getOriginalFilename()=" + getOriginalFilename() + ", getExtName()=" + getExtName()
				+ ", getSaveFileName()=" + getSaveFileName() + ", getAttach()=" + getAttach() + ", getImgUrl()="
				+ getImgUrl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
