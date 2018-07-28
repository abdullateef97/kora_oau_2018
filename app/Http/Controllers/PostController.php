<?php

namespace App\Http\Controllers;

use App\Post;
use Illuminate\Http\Request;
use App\Category;
use App\Donation;
use \App\Services\Slug;

class PostController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index()
    {
        $posts = Post::latest()->get();

        return $posts;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $categories = Category::select()->orderBy('name', 'asc')->get();
        return view('post.create', compact('categories'));
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
            'title'         => 'required|string|min:1',
            'category'      => 'required|string|min:1',
            'target'        => 'required|string|min:1',
            'content'       => 'required|string|min:1',
            'image'         => 'image|nullable|image|mimes:jpg,jpeg,png,gif|max:1999',
        ]);

        $post = Post::create([
            'user_id'       => auth()->user()->id,
            'category_id'   => request('category'),
            'title'         => request('title'),
            'slug'          => $slug->createSlug($request->title),
            'target'        => request('target'),
            'content'       => request('content'),
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
            $post->image = $file_name_stored;
        } else {
            $file_name_stored = null;
            $post->image = $file_name_stored;
        }


        $post->save();

        return redirect('/category/'.$post->category->slug.'/'.$post->slug);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Post  $post
     * @return \Illuminate\Http\Response
     */
    public function show(Category $category, Post $post)
    {

        return view('post.show', compact('post'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Post  $post
     * @return \Illuminate\Http\Response
     */
    public function edit(Category $category, Post $post)
    {
        $categories = Category::select()->orderBy('name', 'asc')->get();

        return view('post.edit', compact('categories', 'post'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Post  $post
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Category $category, Post $post)
    {
        $this->validate(request(), [
            'title'         => 'required|string|min:1',
            'category'      => 'required|string|min:1',
            'target'        => 'required|string|min:1',
            'content'       => 'required|string|min:1',
            'image'         => 'image|nullable|image|mimes:jpg,jpeg,png,gif|max:1999',
        ]);

        $post->category_id = $request->category;
        $post->title = $request->title;
        $post->content = $request->content;
        $post->target = $request->target;

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
            $post->image = $file_name_stored;
        }

        $post->save();

        return redirect('/category/'.$post->category->slug.'/'.$post->slug);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Post  $post
     * @return \Illuminate\Http\Response
     */
    public function destroy(Post $post)
    {
        //
    }
}
