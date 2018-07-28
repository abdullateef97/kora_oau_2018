<?php

use Illuminate\Database\Seeder;

use App\Post;
use App\Comment;
use App\Donation;

class PostsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $post = Post::create([
        		'user_id' 		=> '1',
        		'category_id' => '1',
        		'title' 			=> 'Scholarship Abroad',
        		'slug' 				=> 'scholarship-abroad',
        		'content' 		=> 'My nanme is Makopolo, I am a graduate of the Depaartment of Electronic and Electrical Engineering from Obafemi Awolowo University. I am looking to forward my studies abroad in this current field. I need a total sum of 5000000 Naira to complete my Phd.',
        		'target' 			=> '5000000'
        ]);

        Comment::create([
        		'user_id' => '1',
        		'post_id'	=> $post->id,
        		'body'		=> "God's speed bro",
        ]);


    }
}
