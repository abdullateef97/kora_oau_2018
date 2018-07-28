/**
 * Created by mayomi on 7/27/18 by 3:31 PM.
 */
module.exports = {
  toHex(str) {
    var n = str;
    return String("0000000000000000000000000000000000000000000000000000000000000000" + n).slice(-64);
  },
  getUserAuthCode(userId, authCode){
    return  "0xbffd5871" + userId + authCode;
  },
  success(res, message, data){
    return res.json({
      status: true,
      message: message,
      data: data,
    })
  },
  fail(res, message){
    return res.json({
      status: false,
      message: message,
      data: data,
    })
  }
};
