## 微信网页授权
### 网页授权access_token

首先请注意，这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止。<br>
尤其注意：由于公众号的secret和获取到的access_token安全级别都非常高，必须只保存在服务器，不允许传给客户端。后续刷新access_token、通过access_token获取用户信息等步骤，也必须从服务器发起。

请求方法

获取code后，请求以下链接获取access_token： https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code

###### 参数说明
|参数|是否必须|说明|
|:---:|:---:|:---:|
|appid|是|公众号的唯一标识|
|secret|是|公众号的appsecret|
|code|是|填写第一步获取的code参数|
|grant_type|是|填写为authorization_code|
###### 返回说明
正确时返回的JSON数据包如下：
```
{
"access_token":"ACCESS_TOKEN",
"expires_in":7200,
"refresh_token":"REFRESH_TOKEN",
"openid":"OPENID",
"scope":"SCOPE"
}
```

|参数|描述|
|:---:|:---:|
|access_token|网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同|
|expires_in|access_token接口调用凭证超时时间，单位（秒）|
|refresh_token|用户刷新access_token|
|openid|用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID|
|scope|用户授权的作用域，使用逗号（,）分隔|
错误时微信会返回JSON数据包如下（示例为Code无效错误）:
{"errcode":40029,"errmsg":"invalid code"}

### 刷新access_token（如果需要）
由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。

###### 请求方法
获取第二步的refresh_token后，请求以下链接获取access_token： https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN

|参数|是否必须|说明|
|:---:|:---:|:---:|
|appid|是|公众号的唯一标识|
|grant_type|是|填写为refresh_token|
|refresh_token|是|填写通过access_token获取到的refresh_token参数|
###### 返回说明
正确时返回的JSON数据包如下：
```
{
"access_token":"ACCESS_TOKEN",
"expires_in":7200,
"refresh_token":"REFRESH_TOKEN",
"openid":"OPENID",
"scope":"SCOPE"
}
```

|参数|描述|
|:---:|:---:|
|access_token|网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同|
|expires_in|access_token接口调用凭证超时时间，单位（秒）|
|refresh_token|用户刷新access_token|
|openid|用户唯一标识|
|scope|用户授权的作用域，使用逗号（,）分隔|

### 拉取用户信息(需scope为 snsapi_userinfo)
如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。

###### 请求方法
http：GET（请使用https协议） https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
###### 参数说明
|参数|描述|
|---|---|
|access_token|网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同|
|openid|用户的唯一标识|
|lang|返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语|
返回说明
正确时返回的JSON数据包如下：
```
{   
"openid": "OPENID",
"nickname": NICKNAME,
"sex": 1,
"province":"PROVINCE",
"city":"CITY",
"country":"COUNTRY",
"headimgurl":"https://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
"privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
"unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
}
```
|参数|描述|
|---|---|
|openid|用户的唯一标识|
|nickname|用户昵称|
|sex|用户的性别，值为1时是男性，值为2时是女性，值为0时是未知|
|province|用户个人资料填写的省份|
|city|普通用户个人资料填写的城市|
|country|国家，如中国为CN|
|headimgurl|用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。|
|privilege|用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）|
|unionid|只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。|
错误时微信会返回JSON数据包如下（示例为openid无效）:
```
{"errcode":40003,"errmsg":" invalid openid "}
```

### 附：检验授权凭证（access_token）是否有效
###### 请求方法
http：GET（请使用https协议） https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
###### 参数说明
|参数|描述|
|---|---|
|access_token|网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同|
|openid|用户的唯一标识|
返回说明
正确的JSON返回结果：
```
{ "errcode":0,"errmsg":"ok"}
```
错误时的JSON返回示例：
```
{ "errcode":40003,"errmsg":"invalid openid"}
```

