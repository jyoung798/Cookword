package jyp.cooksite.api.response;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResult<T>  extends CommonResult {
    private Page<T> page;
}
