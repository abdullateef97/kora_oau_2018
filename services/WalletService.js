const Wallet = require('../models/Wallet');
const User = require('../models/User');

class WalletService {
    constructor(){};

    createWallet(owner_id){
        return new Promise((resolve, reject) => {
            const newWallet = new Wallet({
                owners: [owner_id],
                creator: owner_id
            });
            newWallet.save().then((wallet) => {

                // this.pushWalletToUser(owner_id, newWallet);
                resolve(wallet);
            }).catch(err => reject(err));


        })
    }

    pushWalletToUser(user_id, newWallet){
        const query = {_id: user_id};
        User.findOne(query).then(user => {
            user.wallets.push(newWallet);
            user.save();
        })
    }
}

module.exports = WalletService;