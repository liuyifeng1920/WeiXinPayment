/*
 * 文 件 名:  GetTokenUtil.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  fKF60988
 * 修改时间:  2013-5-2
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.miteno.util;

import org.apache.commons.lang.StringUtils;

import com.wo.sdk.message.APIRequest;
import com.wo.sdk.message.AuthorizationHeader;
import com.wo.sdk.openapi.auth.domain.GetTokenResponse;
import com.wo.sdk.openapi.auth.impl.AuthenticateAPIImpl;

/**
 * 获取token工具类.
 * <功能详细描述>
 * 
 * @author  fKF60988
 * @version  [版本号, 2013-5-2]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class GetTokenUtil
{
    /**
     * Instantiates a new gets the token util.
     */
    private GetTokenUtil()
    {
        
    }
    
    /**
     * Gets the token.
     *
     * @param appKey the app key
     * @param appSecret the app secret
     * @return the token
     */
    public static String getToken(String appKey, String appSecret)
    {
        APIRequest request = new APIRequest();
        
        //閴存潈娑堟伅澶?
        AuthorizationHeader header = new AuthorizationHeader();
        header.setAppKey(appKey.trim());
        header.setAppSecret(appSecret.trim());
        request.setAuthHeader(header);
        
        //HTTP澶翠俊鎭?
        request.setAccept("application/json");
        request.setContentType("application/json");
        
        //璋冪敤鏈嶅姟鍦板潃
        request.setUri("https://open.wo.com.cn/openapi/authenticate/v1.0");
        
        AuthenticateAPIImpl auth = new AuthenticateAPIImpl();
        GetTokenResponse response = auth.getToken(request);
        
        String token = response.getToken();
        if (StringUtils.isBlank(token))
        {
            System.out.println(response.getStatusCode());
            System.out.println(response.getResponseData());
        }
        
        return response.getToken();
    }
    
    public static void main(String[] args) {
		getToken("7935349a429a382152ebbda84421fbbdd20b5c0d", " 11e1226c0f166c675f40894bce677d902bc72efa");
	}
}
