@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Dashboard</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif

                    @if(count($errors))
                      <div class="alert alert-danger alert-dismissible fade show">
                        <ul>
                          @foreach ($errors->all() as $error)
                            <li>{{ $error }}</li>
                          @endforeach
                        </ul>

                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                    @endif

                    <form method="POST" action="{{ route('user-billing') }}">

                    <b>Bank Name:</b>
                    <select name="bank_name" id="bank_name">
                        @foreach ($banks as $code => $name)
                            <option value="{{ $code }}">{{ $name }}</option>
                        @endforeach
                    </select><br>

                    <b>Account Name:</b>     <input type="text" id="account_name" name="account_name" value="{{ $userBilling->account_name }}" placeholder=""/>             <br>

                    <b>Account Number:</b>      <input type="text" id="account_number" name="account_number" value="{{ $userBilling->account_number }}" placeholder=""/>                <br>
                        
                        @csrf()
                        <button type="submit">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
