const Wallet = require('../models/Wallet');
const jwt = require('jsonwebtoken');
const User = require('../models/User');
const kolo = 'Kolo';
const aajo = 'Aajo';
    const createWallet = (owner_id) => {
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

    const pushWalletToUser = (user_id, newWallet) => new Promise((resolve, reject) => {
        const query = {_id: user_id};
        User.findOne(query).then(user => {
            user.wallets.push(newWallet);
            return user.save().then(user => resolve(user)).catch(err => reject(err));
        })
    })

    createKolo = (koloObj, user_id) => new Promise((resolve, reject) => {
        const newKolo = new Wallet({
            owners: [user_id],
            creator: user_id,
            name: kolo,
            description: koloObj.description || 'Kolo',
            end_date: koloObj.end_date,
            deduction_amount: kolo.deduction_amount,
            deduction_interval: koloObj.deduction_interval
        })

        newKolo.save().then(wallet => resolve(wallet)).catch(err => reject(err));
    })

    createAajo = (aajoObj, user_id) => new Promise((resolve, reject) => {
        const newAajo = new Wallet({
            owners: [user_id],
            creator: user_id,
            name: aajo,
            description: aajo.description || 'Aajo',
            end_date: aajo.end_date,
            deduction_amount: kolo.deduction_amount,
            deduction_interval: aajo.deduction_interval
        })

        newAajo.save().then(wallet => resolve(wallet)).catch(err => reject(err));
    })

    getAllWallets = (owner_id) => new Promise((resolve, reject) => {
        const condition = { $or : [{creator: owner_id}, {owners: owner_id}]};
        Wallet.find(condition).then(wallet => {
            return resolve(wallet)
        }).catch(err => reject(err));
    })

    getAajoUrl = (user_id, wallet_id)=> {
        const aajoObj = {user_id, wallet_id};
        const token = generateAajoToken(aajoObj);
        return token;   
    }

    generateAajoToken = (userObj) => {
       const token = jwt.sign(userObj, process.env.JWT_SECRET);
       return token
    }

    


module.exports = {createWallet, pushWalletToUser, createKolo, getAllWallets, createAajo,
                    getAajoUrl,};