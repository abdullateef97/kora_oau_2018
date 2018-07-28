<?php

use Illuminate\Database\Seeder;

use Illuminate\Support\Facades\Hash;
use App\User;
use App\Billing;

class UsersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
    		$user =  User::create([
    		    'first_name' => 'Abdulwahab',
    		    'last_name' => 'Nasir',
    		    'email' => 'weybansky@gmail.com',
    		    'phone' => '+2348147638236',
    		    'password' => Hash::make('password'),
    		]);

    		Billing::create([
    		    'user_id' => $user->id,
                'bank_name' => "011",
                'account_name' => "NASIR ABDULWAHAB",
                'account_number' => "3094732668",
    		]);

    }
}
