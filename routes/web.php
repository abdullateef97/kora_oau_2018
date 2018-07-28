<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', 'HomeController@index'); 	//Done
Route::redirect('/category', '/');	//Done
Route::get('category/create', 'CategoryController@create'); //Done
Route::post('category', 'CategoryController@store');  //Done
Route::get('category/{category}/edit', 'CategoryController@edit'); //Done
Route::patch('category/{category}', 'CategoryController@update'); //Done
Route::get('category/{category}', 'CategoryController@show'); //Done
// Route::delete('category/{category}', 'CategoryController@destroy'); //Not Used

// Post Donate
Route::get('category/{category}/{post}', 'PostController@show');
Route::get('ask-for-help', 'PostController@create');
Route::post('ask-for-help', 'PostController@store');
Route::get('render-help', 'PostController@index');
Route::get('category/{category}/{post}/edit', 'PostController@edit');
Route::patch('category/{category}/{post}/', 'PostController@update');

// Donations
Route::post('category/{category}/{post}/donate', 'DonationController@donate');		//
Route::get('category/{category}/{post}/donated', 'DonationController@donated');		//



Route::get('donate/banks', 'DonationController@banks');
Route::get('donate/token', 'DonationController@authToken');


Route::get('help/{post}/edit', 'PostController@edit');		//
Route::patch('help/{post}', 'PostController@update');
Route::delete('help/{post}', 'PostController@destroy');







Auth::routes();

Route::get('/home', 'HomeController@show')->name('home');

// Users //
Route::get('user', 'HomeController@user')->name('user-view');
Route::get('user/edit', 'HomeController@userEdit')->name('user-edit');
Route::post('user', 'HomeController@userUpdate');

// Billing
Route::get('user/billing', 'HomeController@userBilling')->name('user-billing');
Route::get('user/billing/edit', 'HomeController@userBillingEdit')->name('user-billing-edit');
Route::post('user/billing', 'HomeController@userBillingSave');

// Category
Route::get('user/category', 'HomeController@userCategory')->name('user-category');
Route::get('user/category/create', 'HomeController@userCategoryCreate')->name('user-category-create');
Route::post('user/category', 'HomeController@userCategorySave');
// Route::get('user/category/edit', 'HomeController@userCategoryEdit')->name('user-category-edit');
// Route::patch('user/category', 'HomeController@userCategoryUpdate');
// Route::delete('user/category', 'HomeController@userCategoryDelete');

// Posts
Route::get('user/posts', 'HomeController@userPosts')->name('user-post');
Route::get('user/posts/create', 'HomeController@userPostsCreate')->name('user-post-create');
Route::post('user/posts', 'HomeController@userPostsSave');
// Route::get('user/posts/edit', 'HomeController@userPostsEdit')->name('user-post-edit');
// Route::patch('user/posts', 'HomeController@userPostsUpdate');
// Route::delete('user/posts', 'HomeController@userPostsDelete');

// Donations
Route::get('user/donations', 'HomeController@userDonations')->name('user-donations');





Route::get('test', function () {
	return view('test');
});