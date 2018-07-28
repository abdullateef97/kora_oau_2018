<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Post extends Model
{

    protected $fillable = [
        'user_id', 'category_id', 'title', 'slug', 'content', 'target', 'image',
    ];

    public function getRouteKeyName()
    {
        return 'slug';
    }

    public function comments()
    {
    	return $this->hasMany(Comment::class);
    }

    public function user()
    {
    	return $this->belongsTo(User::class);
    }

    public function category()
    {
        return $this->belongsTo(Category::class);
    }

    public function donations()
    {
    		return $this->hasMany(Donation::class);
    }
}
