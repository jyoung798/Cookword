package jyp.cooksite.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jyp.cooksite.api.response.CommonResult;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.SingleResult;

@Service
public class ResponseService {
	// enum���� api ��û ����� ���� code, message�� �����մϴ�.
	public enum CommonResponse {
		SUCCESS(0, "�����Ͽ����ϵ�."), FAIL(-1, "�����Ͽ����ϴ�.");

		int code;
		String msg;

		CommonResponse(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}

	// ��� �𵨿� api ��û ���� �����͸� �������ִ� �޼ҵ�
	private void setSuccessResult(CommonResult result) {  //���� ����� �� 3���� ������ �ִ� ����,�ڵ�,�޼��� 
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}

	// ���ϰ� ����� ó���ϴ� �޼ҵ�
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);       //���� ����� ���� 
		setSuccessResult(result);   //���� ��� 3�� ���� 
		return result;
	}

	// ���߰� ����� ó���ϴ� �޼ҵ�
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}

	// ���� ����� ó���ϴ� �޼ҵ�
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}

	// ���� ����� ó���ϴ� �޼ҵ�
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
		return result;
	}
	//�ڵ�� �޼��� ���� �ۼ� ������ ���� ���� Ŭ���� 
	public CommonResult getFailResult(int code,String msg) {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

}