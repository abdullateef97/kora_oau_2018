<?php

namespace App\Http\Controllers;

use App\Donation;
use Illuminate\Http\Request;
use App\Post;
use App\Category;

class DonationController extends Controller
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
        //
    }

    public function donate(Category $category, Post $post, Request $request)
    {
        // return $request;

        $authToken = Donation::token();

        if (isset($authToken['token'])) {
            $authToken = $authToken['token'];
        } else {

            session()->flash('error', "Something went Wrong. Please make sure you have good Internet Connection and then refresh the page");
            // session()->flash('message', $message);
            return redirect()->back();
        }

        // return $authToken;


        $curl = curl_init();
        $base_url = env("MONEYWAVE_BASE_URL");
        $header = array(
          "Authorization: ". $authToken,
          "Content-Type: application/json",
        );
        $body = array(
            "firstname" => $post->user->first_name,
            "lastname" => $post->user->last_name,
            "phonenumber" => $post->user->phone,
            "email" => $post->user->email,
            "recipient_bank" => $post->user->billing->bank_name,
            "recipient_account_number" => $post->user->billing->account_number,

            "card_no" => $request["card_no"],
            "cvv" => $request["cvv"],
            "expiry_year" => $request["expiry_year"],
            "expiry_month" => $request["expiry_month"],

            "apiKey" => env('MONEYWAVE_API_KEY'),

            "amount" => (int)$request["amount"],
            "fee" => (int)$request["fee"],

            "redirecturl" => $request["redirecturl"],
            "medium" => "web",
        );
        curl_setopt_array($curl, array(
          CURLOPT_URL => $base_url . "/v1/transfer",
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
        if ($err) {
          return "cURL Error #:" . $err;
        } else {
          $decodedResponse = json_decode($response, true);
          // return $decodedResponse;

          // check for the status message the determine the next action
          if ($decodedResponse["status"] == "error") {
                $message = $decodedResponse["message"];

                session()->flash('error', "Something went wrong. try again later.");
                session()->flash('message', $message);

                return redirect()->back();

          } elseif ($decodedResponse["status"] == "success") {
                // Store the donation
                Donation::create([
                    'post_id'           => $post->id,
                    'user_id'           => auth()->user()->id,
                    'amount'            => $decodedResponse["data"]["transfer"]["amountToSend"],
                    'transaction_status'=> "pending",
                    'transaction_msg'   => $decodedResponse["data"]["transfer"]["flutterChargeResponseMessage"],
                    'transaction_id'    => $decodedResponse["data"]["transfer"]["id"],
                    'transaction_ref'   => $decodedResponse["data"]["transfer"]["flutterChargeReference"],
                ]);

                //TODO
                // Calculation if target is met
                // ...

                $data = str_replace('\\', '', $decodedResponse["data"]["responsehtml"]);
                return view('render', compact('data'));
              
          } else {
                session()->flash('error', "Something went wrong. try again later.");
                return redirect()->back();
          }

        }


    }

    public function donated(Category $category, Post $post, Request $request)
    {
        $user = auth()->user();

        // return $request;

        if ($request->transactionStatus == "fail") {
            $message = $request->responseMessage;

            session()->flash('error', "Something went wrong. try again later.");
            session()->flash('message', $message);

            $donation = Donation::where('transaction_ref', $request->ref)->first();

            $donation->transaction_rc       =  $request->rc;
            $donation->transaction_status   =  $request->transactionStatus;
            $donation->transaction_msg      =  $request->responseMessage;
            $donation->transaction_id       =  $request->id;

            $donation->save();

            
        } elseif ($request->transactionStatus == "success") {
            $message = $request->responseMessage;

            session()->flash('success', "Thanks for your Donation");
            session()->flash('message', $message);
        }


        return redirect('/category/'.$post->category->slug.'/'.$post->slug);

    }





    public function banks()
    {
        $banks =  Donation::banks();
        return $banks['data'];
    }
    public function authToken()
    {
        $token = Donation::token();
        return $token;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Donation  $donation
     * @return \Illuminate\Http\Response
     */
    public function show(Donation $donation)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Donation  $donation
     * @return \Illuminate\Http\Response
     */
    public function edit(Donation $donation)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Donation  $donation
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Donation $donation)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Donation  $donation
     * @return \Illuminate\Http\Response
     */
    public function destroy(Donation $donation)
    {
        //
    }
}
