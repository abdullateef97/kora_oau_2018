<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Donation extends Model
{

    protected $fillable = [
        'post_id', 'user_id', 'amount', 'transaction_status', 'transaction_msg', 'transaction_id', 'transaction_ref', 
    ];



    public function post()
    {
    		$this->belongsTo(Post::class);
    }

    public function user()
    {
    		$this->belongsTo(User::class);
    }

    public static function banks()
    {
	    	$curl = curl_init();
	    	$base_url = env("MONEYWAVE_BASE_URL");
	    	$header = array(
	    	  "Content-Type: application/json",
	    	);
	    	$query = "?country=NG";
	    	curl_setopt_array($curl, array(
	    	  CURLOPT_URL => $base_url . "/v1/banks" . $query,
	    	  CURLOPT_CUSTOMREQUEST => "POST",
	    	  CURLOPT_RETURNTRANSFER => true,
	    	  CURLOPT_MAXREDIRS => 10,
	    	  CURLOPT_TIMEOUT => 180,
	    	  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
	    	  CURLOPT_HTTPHEADER => $header,
	    	));
	    	$response = curl_exec($curl);
	    	$err = curl_error($curl);
	    	curl_close($curl);
	    	if (!$err) {
	    	    $decodedResponse = json_decode($response, true);
	    	    return $decodedResponse;
	    	} else {
	    	    return $err;
	    	}
    }

    public static function token()
    {
    		$curl = curl_init();
    		$base_url = env("MONEYWAVE_BASE_URL");
    		$header = array(
    		  "Content-Type: application/json",
    		);
    		$body = array(
    		  "apiKey" => env("MONEYWAVE_API_KEY"),
    		  "secret" => env("MONEYWAVE_SECRET"),
    		);
    		curl_setopt_array($curl, array(
    		  CURLOPT_URL => $base_url . "/v1/merchant/verify",
    		  CURLOPT_CUSTOMREQUEST => "POST",
    		  CURLOPT_RETURNTRANSFER => true,
    		  CURLOPT_MAXREDIRS => 10,
    		  CURLOPT_TIMEOUT => 180,
    		  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
    		  CURLOPT_POSTFIELDS => json_encode($body),
    		  CURLOPT_HTTPHEADER => $header,
    		));
    		$response = curl_exec($curl);
    		$err = curl_error($curl);
    		curl_close($curl);
    		if (!$err) {
    		    $decodedResponse = json_decode($response, true);
    		    return $decodedResponse;
    		} else {
    		    return $err;
    		}

    } 
    
}
