<?php

namespace App\Http\Controllers;

use App\Category;
use Illuminate\Http\Request;
use \App\Services\Slug;
use Illuminate\Support\Facades\Storage;

class CategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public function __construct()
    {
        $this->middleware('auth')->except(['show    ']);
    }

    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('category.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request, Slug $slug)
    {
        $this->validate(request(), [
            'name'          => 'string|min:3|unique:categories,name|max:50',
            'description'   => 'string|nullable',
            'image'         => 'image|nullable|image|mimes:jpg,jpeg,png,gif|max:1999',
        ]);

        $category = Category::create([
            'name'        => request('name'),
            'description' => request('description'),
            'slug'        => $slug->createSlug($request->name)
        ]);

        // Upload and Store Image
        if ($request->hasFile('image')) {
            $uploaded_image       = $request->file('image');
            $uploaded_image_full  = $uploaded_image->getClientOriginalName();
            $uploaded_image_name  = pathinfo($uploaded_image_full, PATHINFO_FILENAME);
            $uploaded_image_ext   = $uploaded_image->getClientOriginalExtension();
            // Not Used
            // $uploaded_image_mime  = $uploaded_image->getClientMimeType();
            // $uploaded_image_size  = $uploaded_image->getClientSize();

            $file_name_stored     = $uploaded_image_name. '-' .time() . '.' . $uploaded_image_ext;
            $path                 = $request->file('image')->storeAs('public/posts', $file_name_stored);
            $category->image = $file_name_stored;
        } else {
            $file_name_stored = null;
            $category->image = $file_name_stored;
        }

        $category->save();

        return redirect('/category/'.$category->slug);

    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Category  $category
     * @return \Illuminate\Http\Response
     */
    public function show(Category $category)
    {
        $posts = $category->posts()->paginate(9);

        $categories = Category::all();

        return view('category.show', compact('category', 'posts', 'categories'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Category  $category
     * @return \Illuminate\Http\Response
     */
    public function edit(Category $category)
    {
        return view('category.edit', compact('category'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Category  $category
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Category $category, Slug $slug)
    {
        $this->validate(request(), [
            'name'          => 'string|min:3',
            'description'   => 'string|nullable',
            'image'         => 'image|nullable|image|mimes:jpg,jpeg,png,gif|max:1999',
        ]);

        $category->name = $request->name;
        $category->description = $request->description;

        // Slug
        $catSlug = $slug->createSlug($request->name);

        // Upload and Store Image
        if ($request->hasFile('image')) {
            $uploaded_image       = $request->file('image');
            $uploaded_image_full  = $uploaded_image->getClientOriginalName();
            $uploaded_image_name  = pathinfo($uploaded_image_full, PATHINFO_FILENAME);
            $uploaded_image_ext   = $uploaded_image->getClientOriginalExtension();
            // Not Used
            // $uploaded_image_mime  = $uploaded_image->getClientMimeType();
            // $uploaded_image_size  = $uploaded_image->getClientSize();

            $file_name_stored     = $uploaded_image_name. '-' .time() . '.' . $uploaded_image_ext;
            $path                 = $request->file('image')->storeAs('public/posts', $file_name_stored);
            $category->image      = $file_name_stored;
        }

        $category->save();

        return redirect('/category/'.$category->slug);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Category  $category
     * @return \Illuminate\Http\Response
     */
    public function destroy(Category $category)
    {
        $category->delete();
        return redirect('/');
    }
}
