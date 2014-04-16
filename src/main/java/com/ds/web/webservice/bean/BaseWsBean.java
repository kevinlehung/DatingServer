package com.ds.web.webservice.bean;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class BaseWsBean {
	public interface ProcessageStatus {
		public static final String SUCCESSED = "SUCCESSED";
		public static final String FAILED = "FAILED";
	}

	public interface StatusMessage {
		public static final String SUCCESSED = "Service is processed successfully.";
		public static final String FAILED = "Failed to process service.";
	}

	private String processStatus = ProcessageStatus.SUCCESSED;
	private String statusMessage = StatusMessage.SUCCESSED;
	private String result;

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
