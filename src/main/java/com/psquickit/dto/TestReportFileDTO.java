package com.psquickit.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the testreportfile database table.
 * 
 */
@Entity
@Table(name="Testreportfile")
@NamedQuery(name="Testreportfile.findAll", query="SELECT t FROM TestReportFileDTO t")
public class TestReportFileDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	//bi-directional many-to-one association to Filestore
	@ManyToOne
	@JoinColumn(name="FileStoredId")
	private FileStoreDTO filestore;

	//bi-directional many-to-one association to Usertestreport
	@ManyToOne
	@JoinColumn(name="UserTestReportId")
	private UserTestReportDTO usertestreport;

	public TestReportFileDTO() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FileStoreDTO getFilestore() {
		return this.filestore;
	}

	public void setFilestore(FileStoreDTO filestore) {
		this.filestore = filestore;
	}

	public UserTestReportDTO getUsertestreport() {
		return this.usertestreport;
	}

	public void setUsertestreport(UserTestReportDTO usertestreport) {
		this.usertestreport = usertestreport;
	}

}