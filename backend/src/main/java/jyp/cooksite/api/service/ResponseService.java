package jyp.cooksite.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jyp.cooksite.api.response.CommonResult;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.SingleResult;

@Service
public class ResponseService {
	// enum으로 api 요청 결과에 대한 code, message를 정의합니다.
	public enum CommonResponse {
		SUCCESS(0, "성공하였습니디."), FAIL(-1, "실패하였습니다.");

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

	// 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
	private void setSuccessResult(CommonResult result) {  //공통 결과는 총 3개를 가지고 있다 성공,코드,메세지 
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}

	// 단일건 결과를 처리하는 메소드
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);       //단일 결과를 셋팅 
		setSuccessResult(result);   //공통 결과 3개 셋팅 
		return result;
	}

	// 다중건 결과를 처리하는 메소드
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}

	// 성공 결과만 처리하는 메소드
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}

	// 실패 결과만 처리하는 메소드
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
		return result;
	}

}