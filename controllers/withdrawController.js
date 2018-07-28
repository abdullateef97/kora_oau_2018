/**
 * Created by mayomi on 7/28/18 by 5:41 PM.
 */
const unirest = require('unirest');

const transferRecipient = (req, res) => {
  unirest.post('https://api.paystack.co/transferrecipient')
    .headers({'Content-Type': 'application/json'})
    .send({
      name: '',
      auth_code: params.auth_code
    })
    .end(function (response) {
      logger.info(response.body);
    });
};


