require('dotenv').config();
module.exports = {
  // Database connection information
  'database': 'mongodb://[add your database here]',
  // Setting port for server
  'port': process.env.PORT || 3000,
  'paystack_secret': process.env.PAYSTACK_SECRET_KEY,

  // smart contract config
  'private_key': process.env.PRIVATE_KEY,
  'account_address': process.env.ACCOUNT_ADDRESS,
  'contract_address': process.env.CONTRACT_ADDRESS,
  'signer_and_http_provider': process.env.SIGNER_AND_HTTP_PROVIDER,
  'byte_code': process.env.BYTE_CODE,
};
