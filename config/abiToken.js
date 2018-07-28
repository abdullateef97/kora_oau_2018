/**
 * Created by mayomi on 7/28/18 by 9:23 AM.
 */
module.exports = [
  {
    "constant": false,
    "inputs": [
      {
        "name": "userId",
        "type": "string"
      },
      {
        "name": "authCode",
        "type": "string"
      }
    ],
    "name": "addUser",
    "outputs": [],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "inputs": [],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "constructor"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "userId",
        "type": "string"
      },
      {
        "name": "authCode",
        "type": "string"
      }
    ],
    "name": "updateUser",
    "outputs": [],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [
      {
        "name": "userId",
        "type": "string"
      }
    ],
    "name": "getAuthCode",
    "outputs": [
      {
        "name": "",
        "type": "string"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  }
];
