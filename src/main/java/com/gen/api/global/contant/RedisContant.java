package com.gen.api.global.contant;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-12
 * Time: 오후 1:17
 */
public class RedisContant {


    private RedisContant() {

        throw new IllegalStateException("Utility class");
    }

    public static final String ORG_LIST_KEY = "bxr_organization_code";
    public static final String COMMON_LIST_KEY = "bxr_common_code";

    public static final String LICENSE_KEY = "bxr_license_code";

    /**
     * 사용자 온라인 상태 키
     * */
    public static final String WEB_SOCKET_CLIENT_KEY = "bxr_web_socket_key";

    /**
     * 사용자의 방의 session_id 와 매핑
     * */
    public static final String WEB_SOCKET_CLIENT_INFO = "bxr_web_socket_info";

    /**
     * 채팅 client의 session_id 와 매핑
     * */
    public static final String CHAT_CLIENT_INFO = "bxr_chat_client_info";


    /**
     * 부서 정책 키
     */
    public static final String ORG_POLICY_KEY = "bxr_org_policy_key";

    /**
     * 사용자 정책 키
     */
    public static final String USER_POLICY_KEY = "bxr_user_policy_key";

    /**
     * pc 정책 키
     */
    public static final String AGENT_POLICY_KEY = "bxr_agent_policy_key";

    /**
     * 환경설정 키
     */
    public static final String SETTING_KEY = "bxr_setting_key";

    /**
     * 업데이트 키
     */
    public static final String UPDATE_KEY = "bxr_update_key";
}
