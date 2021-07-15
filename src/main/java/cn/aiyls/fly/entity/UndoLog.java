package cn.aiyls.fly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.InputStream;

/**
 * @Description  
 * @Author  mahongyi
 * @Date 2021-07-15 11:02:49 
 */

@Data
@TableName("undo_log")
public class UndoLog  implements Serializable {

	private static final long serialVersionUID =  5871807789646369657L;

   @TableId(type = IdType.AUTO)
	private Integer id;

	private Integer branchId;

	private String xid;

	private String context;

	private InputStream rollbackInfo;

	private Integer logStatus;

   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime logCreated;

   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime logModified;

	private String ext;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBranchId() {
		return this.branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getXid() {
		return this.xid;
	}

	public void setXid(String xid) {
		this.xid = xid;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public InputStream getRollbackInfo() {
		return this.rollbackInfo;
	}

	public void setRollbackInfo(InputStream rollbackInfo) {
		this.rollbackInfo = rollbackInfo;
	}

	public Integer getLogStatus() {
		return this.logStatus;
	}

	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}

	public LocalDateTime getLogCreated() {
		return this.logCreated;
	}

	public void setLogCreated(LocalDateTime logCreated) {
		this.logCreated = logCreated;
	}

	public LocalDateTime getLogModified() {
		return this.logModified;
	}

	public void setLogModified(LocalDateTime logModified) {
		this.logModified = logModified;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"branchId='" + branchId + '\'' +
					"xid='" + xid + '\'' +
					"context='" + context + '\'' +
					"rollbackInfo='" + rollbackInfo + '\'' +
					"logStatus='" + logStatus + '\'' +
					"logCreated='" + logCreated + '\'' +
					"logModified='" + logModified + '\'' +
					"ext='" + ext + '\'' +
				'}';
	}

	public UndoLog(UndoLog model) {
		this.id = model.id;
		this.branchId = model.branchId;
		this.xid = model.xid;
		this.context = model.context;
		this.rollbackInfo = model.rollbackInfo;
		this.logStatus = model.logStatus;
		this.logCreated = model.logCreated;
		this.logModified = model.logModified;
		this.ext = model.ext;
	}

}
