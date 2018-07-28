pragma solidity ^0.4.11;


contract Kontribute {

    address owner;

    mapping(string=>User) users;

    struct User {
        string id;
        string authCode;
    }

    constructor() public {
        owner = msg.sender;
    }

    function addUser(string userId, string authCode) public {
        require(msg.sender == owner);
        var user = users[userId];
        user.id = userId;
        user.authCode = authCode;
    }

    function getAuthCode(string userId) public view returns (string) {
        require(msg.sender == owner);
        return users[userId].authCode;
    }

     function updateUser(string userId, string authCode) public {
        require(msg.sender == owner);
        var user = users[userId];
        user.authCode = authCode;
    }


}
