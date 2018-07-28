<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDonationsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('donations', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('post_id'); // post that the donation was made
            $table->integer('user_id'); // User that made the donation
            $table->string('amount');

            // from payment gateway
            $table->string('transaction_rc')->nullable();
            $table->string('transaction_status')->nullable();
            $table->string('transaction_msg')->nullable();
            $table->string('transaction_id')->nullable();
            $table->string('transaction_ref')->nullable();

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
        Schema::dropIfExists('donations');
    }
}
