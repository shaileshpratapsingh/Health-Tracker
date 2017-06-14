package com.psquickit.managerImpl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psquickit.manager.HealthRecordManager;
import com.psquickit.pojo.health.record.DeleteDiagnosisResponse;
import com.psquickit.pojo.health.record.DeletePrescriptionResponse;
import com.psquickit.pojo.health.record.GetHealthRecordResponse;
import com.psquickit.pojo.health.record.UpdateBasicHealthParameterResponse;
import com.psquickit.pojo.health.record.UploadBasicHealthParameterResponse;
import com.psquickit.pojo.health.record.UploadDiagnosisResponse;
import com.psquickit.pojo.health.record.UploadHealthRecordResponse;
import com.psquickit.pojo.health.record.UploadPrescriptionResponse;

@Service
public class HealthRecordManagerImpl implements HealthRecordManager {

	@Override
	public UploadHealthRecordResponse uploadHealthRecord(String authToken, MultipartFile basicHealthParameter,
			MultipartFile[] prescription, MultipartFile[] diagnosis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetHealthRecordResponse getHealthRecord(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadBasicHealthParameterResponse uploadBasicHealthParameter(String authToken,
			MultipartFile basicHealthParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateBasicHealthParameterResponse updateBasicHealthParameter(String authToken, Long basicHealthParameterId,
			MultipartFile basicHealthParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadPrescriptionResponse uploadPrescription(String authToken, MultipartFile[] prescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadDiagnosisResponse uploadDiagnosis(String authToken, MultipartFile[] diagnosis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteDiagnosisResponse deleteDiagnosis(String authToken, long diagnosisId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeletePrescriptionResponse deletePrescription(String authToken, long prescriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getPrescription(String authToken, long prescriptionId, HttpServletResponse httpResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDiagnosis(String authToken, long diagnosisId, HttpServletResponse httpResponse) {
		// TODO Auto-generated method stub
		
	}

}
