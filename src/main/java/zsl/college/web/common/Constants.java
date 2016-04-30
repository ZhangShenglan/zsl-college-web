package zsl.college.web.common;

/**
 * Created by zhangshenglan on 16/4/30.
 */
public class Constants {
    /**
     * =====接口返回状态码
     200   （成功）  服务器已成功处理了请求。
     401   （未授权） 请求要求身份验证,无权限访问。
     404   （未找到） 服务器找不到请求的资源。
     406   （不接受） 非法请求,如参数错误,格式不正确。
     500   （服务器内部错误）  服务器遇到错误，无法完成请求。
     */
    public static final int RESULT_CODE_SUCCESS = 200;  // 成功处理请求
    public static final int RESULT_CODE_UNAUTHORIZED= 401;  // 未授权,无权限访问
    public static final int RESULT_CODE_NOT_CONTENT = 404;  // 没有对应结果
    public static final int RESULT_CODE_ILLEGAL_REQUST = 406;  // 非法请求
    public static final int RESULT_CODE_NOT_EXISTS = 412;  // 指定的对象不存在或已被删除
    public static final int RESULT_CODE_SERVER_ERROR = 500;  // 没有对应结果
}
