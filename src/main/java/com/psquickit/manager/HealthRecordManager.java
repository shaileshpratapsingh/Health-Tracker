package com.psquickit.manager;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.psquickit.pojo.health.record.DeleteDiagnosisResponse;
import com.psquickit.pojo.health.record.DeletePrescriptionResponse;
import com.psquickit.pojo.health.record.GetHealthRecordResponse;
import com.psquickit.pojo.health.record.UpdateBasicHealthParameterResponse;
import com.psquickit.pojo.health.record.UploadBasicHealthParameterResponse;
import com.psquickit.pojo.health.record.UploadDiagnosisResponse;
import com.psquickit.pojo.health.record.UploadHealthRecordResponse;
import com.psquickit.pojo.health.record.UploadPrescriptionResponse;

public interface HealthRecordManager {
	
	UploadHealthRecordResponse uploadHealthRecord(String authToken, MultipartFile basicHealthParameter,
			MultipartFile[] prescription, MultipartFile[] diagnosis);

	GetHealthRecordResponse getHealthRecord(String authToken);

	UploadBasicHealthParameterResponse uploadBasicHealthParameter(String authToken, MultipartFile basicHealthParameter);

	UpdateBasicHealthParameterResponse updateBasicHealthParameter(String authToken, Long basicHealthParameterId,
			MultipartFile basicHealthParameter);

	UploadPrescriptionResponse uploadPrescription(String authToken, MultipartFile[] prescription);

	UploadDiagnosisResponse uploadDiagnosis(String authToken, MultipartFile[] diagnosis);

	DeleteDiagnosisResponse deleteDiagnosis(String authToken, long diagnosisId);

	DeletePrescriptionResponse deletePrescription(String authToken, long prescriptionId);

	void getPrescription(String authToken, long prescriptionId, HttpServletResponse httpResponse);

	void getDiagnosis(String authToken, long diagnosisId, HttpServletResponse httpResponse);

}
