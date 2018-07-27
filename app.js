const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cors = require('cors');
const morgan = require('morgan');

const indexRouter = require('./routes/index.rout');
require('dotenv').config();
const app = express();

// Configure your middlewares
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

mongoose.Promise = require('bluebird');
mongoose.connect(process.env.DB_URL, { useMongoClient: true })
    .catch(err => console.error(err));

app.use(morgan('dev')); // log every request to the console

app.use(cors());


// Routes
app.use('/', indexRouter);


// catch 404 and forward to error handler
app.use(function(req, res, next) {
    next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.send({
        status: Constants.ERROR,
        message: RespMessages.METHOD_NOT_IMPLEMENTED,
        error: err.stack //JSON.stringify(err)
    });
});


// Start the server
app.listen(process.env.PORT);
console.log('Your server is running on ' + process.env.PORT);
