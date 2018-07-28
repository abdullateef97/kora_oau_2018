<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;
use App\Donation;
use App\Post;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */

    public function __construct()
    {
        $this->middleware('auth')->except(['index']);
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */

    public function index()
    {
        $categories = Category::select()->orderBy('name')->has('posts')->get();

        $posts = Post::latest()->paginate(12);

        $categoryPictures = [
            'images/category-1-400x250.jpg',
            'images/category-2-400x250.jpg',
            'images/category-3-400x250.jpg',
            'images/category-4-400x250.jpg',
            'images/category-5-400x250.jpg',
            'images/category-6-400x250.jpg'
        ];

        return view('welcome', compact('categories', 'posts', 'categoryPictures'));
    }

    public function show()
    {
        return view('home');
    }


    public function user()
    {
        $user = auth()->user();

        return view('user.index', compact('user'));
    }

    public function userEdit()
    {
        $user = auth()->user();

        return view('user.edit', compact('user'));
    }

    public function userUpdate(Request $request)
    {
        $user = auth()->user();

        // "picture": null,
        // "sex": "other",
        // "phone": null,
        // "date_of_birth": null,
        // "address": null,
        // "country": "Nigeria",
        // "state": null,
        // "town": null,

        $this->validate(request(), [
            'first_name'    => 'required|string|max:255',
            'last_name'     => 'required|string|max:255',
            'username'      => 'required|string|min:4|unique:users',

            'facebook'      => 'string|nullable',
            'twitter'       => 'string|nullable',
            'instagram'     => 'string|nullable',
        ]);

        $user->first_name = $request['first_name'];
        $user->last_name = $request['last_name'];
        $user->username = $request['username'];

        $user->facebook = $request['facebook'];
        $user->twitter = $request['twitter'];
        $user->instagram = $request['instagram'];

        $user->save();
        
        return $user;
    }

    public function userBilling()
    {
        $user = auth()->user();

        return $user->billing;
    }

    public function userBillingEdit()
    {
        $userBilling = auth()->user()->billing;

        $banks = Donation::banks()["data"];

        // $banks = {
        // "322": "ZENITH Mobile",
        // "323": "ACCESS MOBILE",
        // "401": "Aso Savings and Loans",
        // "044": "ACCESS BANK NIGERIA",
        // "014": "AFRIBANK NIGERIA PLC",
        // "063": "DIAMOND BANK PLC",
        // "050": "ECOBANK NIGERIA PLC",
        // "084": "ENTERPRISE BANK LIMITED",
        // "070": "FIDELITY BANK PLC",
        // "011": "FIRST BANK PLC",
        // }

        return view('user.billing.edit', compact('userBilling', 'banks'));
    }

    public function userBillingSave(Request $request)
    {
        $user = auth()->user();

        $this->validate(request(), [
            'bank_name'      => 'string|min:3',
            'account_name'   => 'string|min:5',
            'account_number' => 'string|min:7'
        ]);

        $user->billing->bank_name = $request['bank_name'];
        $user->billing->account_name = $request['account_name'];
        $user->billing->account_number = $request['account_number'];
        $user->billing->save();

        return $user->billing;
    }

    public function userCategory()
    {
        $categories = Category::latest()->get();

        return view('user.category.index', compact('categories'));
    }

    public function userCategoryCreate()
    {
        return view('user.category.create');
    }

    public function userCategorySave(Request $request)
    {
        $this->validate(request(), [
            'name'          => 'required|string|min:3|unique:categories',
            'description'   => 'string|max:255|nullable',
        ]);

        Category::create([
            'name'          => request('name'),
            'description'   => request('description')
        ]);

        $categories = Category::latest()->get();
        return view('user.category.index', compact('categories'));
    }

    public function userPosts()
    {
        $posts = auth()->user()->posts()->latest()->get();

        return view('user.posts.index', compact('posts'));
    }

    public function userPostsCreate()
    {
        $categories = Category::select()->orderBy('name', 'asc')->get();

        return view('user.posts.create', compact('categories'));
    }

}
