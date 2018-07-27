const config = require('../config/settings');
const unirest = require('unirest');

class paystackHelper {


   static postToPaystack(url, body){
        return new Promise((resolve, reject) => {
            return unirest.post(url)
            .headers({
                'Authorization': `Bearer ${process.env.paystack}`,
                'Content-Type': 'application/json'
              })
              .send(body)
              .end((response) => {
                  return resolve(response);
              })
        })
    }
}

module.exports = paystackHelper;