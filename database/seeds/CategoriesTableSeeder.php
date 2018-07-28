<?php

use Illuminate\Database\Seeder;
use App\Category;

class CategoriesTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
    		Category::create([
    				'name' => 'uncategorized',
    				'slug' => 'uncategorized'
    		]);
    		Category::create([
    				'name' => 'education',
    				'slug' => 'education'
    		]);
    		Category::create([
    				'name' => 'health',
    				'slug' => 'health'
    		]);
    		Category::create([
    				'name' => 'travels',
    				'slug' => 'travels'
    		]);
    		Category::create([
    				'name' => 'orphanage homes',
    				'slug' => 'orphanage-homes'
    		]);
    		Category::create([
    				'name' => 'business',
    				'slug' => 'business'
    		]);

        
    }
}
