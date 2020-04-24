package com.yammii.oauth.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * 
 * @author yongyan.pu
 * @version $Id: UserFeginClient.java, v 0.1 2019年1月23日 下午5:12:53 yongyan.pu Exp $
 */
@FeignClient("yammii-notice")
public interface SmsFeginClient {

    /**
     * 校验验证码
     * 
     * @param key
     * @param code
     * @param phone
     * @return
     */
    @GetMapping("/rpc/phone/matcheCode")
    public Integer matcheCodeAndPhone(@RequestParam(name = "key") String key, @RequestParam(name = "code") String code,
                                      @RequestParam(name = "phone") String phone);

}
