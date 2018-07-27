//In alphabetical order, kindly leave a space before next letter
const Constants = require('./constants');

module.exports = {
    ALL_FIELDS_REQUIRED: 'All fields are required. Kindly fill all',

    ERROR_OCCURRED: 'An error occurred, please try again',

    INVALID_EMAIL: 'The email is invalid. Kindly enter a valid email',
    INVALID_PHONE: 'The phone number is invalid. Kindly enter a valid Nigerian phone number',
    INCORRECT_LOGIN_PARAMS: 'Incorrect email or pin. Kindly confirm you have entered a valid email address or pin',
    INVALID_NAME_CHARACTER: 'Names can contain only letters. Not special characters e.g. @, #, *...',
    INCORRECT_PIN: 'Incorrect pin. Kindly enter a correct pin',

    LOGIN_SUCCESSFUL: 'Successfully logged in',
    LOGOUT_SUCCESSFUL: 'Successfully logged out',

    NOT_PERMITTED: 'We\'re sorry. You do not have access to this resource',

    PIN_LENGTH: `Pin can not be less than ${Constants.PIN_LENGTH} characters`,
    PIN_SHOULD_CONTAIN: 'Pin can only contain numbers',

    USER_ALREADY_EXISTS: 'Looks like you already have an account with us. Kindly login.',
    USER_CREATED: 'User creation successful',
};
