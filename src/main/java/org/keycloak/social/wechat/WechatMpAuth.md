#### 小程序端 登录验证

auth.code2Session
登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。更多使用方法详见 小程序登录。


###### 请求地址
GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
###### 请求参数
属性|类型|默认值|必填|说明|
|---|---|---|---|---|
|appid|string| |是|小程序 appId|
|secret|string| |是|小程序 appSecret|
|js_code|string| |是|登录时获取的 code|
|grant_type|string| |是|授权类型，此处只需填写 authorization_code|
返回值
Object
返回的 JSON 数据包

|属性|类型|说明|
|---|---|---|
|openid|string|用户唯一标识|
|session_key|string|会话密钥|
|unionid|string|用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
|errcode|number|错误码|
|errmsg|string|错误信息|

###### errcode 的合法值
|值|说明|最低版本|
|---|---|---|
|-1|系统繁忙，此时请开发者稍候再试| |
|0|请求成功| |
|40029|code 无效| |
|45011|频率限制，每个用户每分钟100次| |