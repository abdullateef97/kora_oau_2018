let $ = require('jquery')
module.exports = {
    setToken: function(token){
        localStorage.setItem('trantoken1', JSON.stringify(token))
    },

    getToken:  function(){
        return localStorage.getItem('trantoken1');
    }
}