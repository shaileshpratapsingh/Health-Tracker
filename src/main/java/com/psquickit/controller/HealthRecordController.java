package com.psquickit.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
import com.psquickit.util.ServiceUtils;

@RestController
@RequestMapping("/healthrecord")
public class HealthRecordController {
	
	@Autowired
	HealthRecordManager manager;
	
	@RequestMapping(value = "/upload/all", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody UploadHealthRecordResponse uploadHealthRecord(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value="basicHealthParameter", required=false) MultipartFile basicHealthParameter,
			@RequestPart(value = "prescription", required=false) MultipartFile[] prescription,
			@RequestPart(value = "diagnosis", required=false) MultipartFile[] diagnosis) {
		UploadHealthRecordResponse response = new UploadHealthRecordResponse();
		try {
			response = manager.uploadHealthRecord(authToken, basicHealthParameter, prescription, diagnosis);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/get/healthrecord", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody GetHealthRecordResponse getHealthRecord(
			@RequestHeader(value="authToken", required=true) String authToken
			) {
		GetHealthRecordResponse response = new GetHealthRecordResponse();
		try {
			response = manager.getHealthRecord(authToken);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/upload/basichealthparameter", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody UploadBasicHealthParameterResponse uploadBasicHealthParameter(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value="basicHealthParameter", required=true) MultipartFile basicHealthParameter
			) {
		UploadBasicHealthParameterResponse response = new UploadBasicHealthParameterResponse();
		try {
			response = manager.uploadBasicHealthParameter(authToken, basicHealthParameter);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/update/basichealthparameter/{id}", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody UpdateBasicHealthParameterResponse updateBasicHealthParameter(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value="basicHealthParameter", required=true) MultipartFile basicHealthParameter,
			@RequestParam(value="id") String id
			) {
		UpdateBasicHealthParameterResponse response = new UpdateBasicHealthParameterResponse();
		try {
			Long basicHealthParameterId = Long.parseLong(id);
			response = manager.updateBasicHealthParameter(authToken, basicHealthParameterId, basicHealthParameter);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/upload/prescription", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody UploadPrescriptionResponse uploadPrescription(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value = "prescription", required=true) MultipartFile[] prescription
			) {
		UploadPrescriptionResponse response = new UploadPrescriptionResponse();
		try {
			response = manager.uploadPrescription(authToken, prescription);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/upload/diagnosis", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public @ResponseBody UploadDiagnosisResponse uploadDiagnosis(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestPart(value = "diagnosis", required=true) MultipartFile[] diagnosis
			) {
		UploadDiagnosisResponse response = new UploadDiagnosisResponse();
		try {
			response = manager.uploadDiagnosis(authToken, diagnosis);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/get/prescription/{id}", method = RequestMethod.GET)
	public void getPrescription(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestParam(value="id") String id,
			final HttpServletResponse httpResponse) {
		try {
			long prescriptionId = Long.parseLong(id);
			manager.getPrescription(authToken, prescriptionId, httpResponse);
		} catch (Exception e) {
			setHttpException(e, httpResponse);
		}
	}
	
	@RequestMapping(value = "/get/diagnosis/{id}", method = RequestMethod.GET)
	public @ResponseBody UploadHealthRecordResponse getDiagnosis(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestParam(value="id") String id,
			final HttpServletResponse httpResponse
			) {
		UploadHealthRecordResponse response = new UploadHealthRecordResponse();
		try {
			long diagnosisId = Long.parseLong(id);
			manager.getDiagnosis(authToken, diagnosisId, httpResponse);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/delete/prescription/{id}", method = RequestMethod.DELETE)
	public @ResponseBody DeletePrescriptionResponse deletePrescription(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestParam(value="id") String id
			) {
		DeletePrescriptionResponse response = new DeletePrescriptionResponse();
		try {
			long prescriptionId = Long.parseLong(id);
			response = manager.deletePrescription(authToken, prescriptionId);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	@RequestMapping(value = "/delete/diagnosis/{id}", method = RequestMethod.DELETE)
	public @ResponseBody DeleteDiagnosisResponse deleteDiagnosis(
			@RequestHeader(value="authToken", required=true) String authToken,
			@RequestParam(value="id") String id
			) {
		DeleteDiagnosisResponse response = new DeleteDiagnosisResponse();
		try {
			long diagnosisId = Long.parseLong(id);
			response = manager.deleteDiagnosis(authToken, diagnosisId);
		} catch (Exception e) {
			return ServiceUtils.setResponse(response, false, "Upload health record", e);
		}
		return response;
	}
	
	public static void setHttpException(Exception e, HttpServletResponse httpResponse) {
		httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		httpResponse.setHeader("Exception", e.getMessage());
	}

}
