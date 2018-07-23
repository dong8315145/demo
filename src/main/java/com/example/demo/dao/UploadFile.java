package com.example.demo.dao;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author  上传文件
 *
 */
@Entity
@DynamicUpdate
public class UploadFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 32)
	private String id; //

	@Column(name = "name", length = 100)
	private String name; // 文件名

	@Column(name = "path", length = 100)
	private String path; // 文件存储路径

	@Column(name = "type", length = 10)
	private String type; // 文件类型

	@Column(name = "file_name", length = 100)
	private String fileName; // 磁盘上的文件名
	
	@Column(name = "pdf_file_name", length = 100)
	private String pdfFileName;

	@Column(name = "file_index")
	private Integer index; // 同类型文件的索引

	@Column(name = "input_item_id", length = 32)
	private String inputItemId; // 录入资料类型类型
	
	@Column(name="create_time")
	private Timestamp createTime;	//创建时间
	
	@Column(name="flag")
	private String flag;	//标识
	
	@Column(name="water_mark_number")
	private String waterMarkNumber; //水印号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getInputItemId() {
		return inputItemId;
	}

	public void setInputItemId(String inputItemId) {
		this.inputItemId = inputItemId;
	}

	
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFile other = (UploadFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UploadFile [id=" + id + ", flag=" + flag + "]";
	}

	public String getWaterMarkNumber() {
		return waterMarkNumber;
	}

	public void setWaterMarkNumber(String waterMarkNumber) {
		this.waterMarkNumber = waterMarkNumber;
	}
	

}
