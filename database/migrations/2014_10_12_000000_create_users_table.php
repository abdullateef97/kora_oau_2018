<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            $table->increments('id');

            $table->string('first_name');
            $table->string('last_name');

            $table->string('email')->unique();
            $table->string('password');
            $table->rememberToken();


            $table->string('username')->unique()->nullable();
            $table->string('picture')->nullable();

            $table->enum('sex', ['male', 'female', 'other'])->default('other');
            $table->string('phone')->nullable();

            $table->string('date_of_birth')->nullable();

            // User Address
            $table->string('address')->nullable();
            $table->string('country')->nullable()->default('Nigeria');
            $table->string('state')->nullable();
            $table->string('town')->nullable();

            // Socials
            $table->string('facebook')->nullable();
            $table->string('twitter')->nullable();
            $table->string('instagram')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('users');
    }
}
