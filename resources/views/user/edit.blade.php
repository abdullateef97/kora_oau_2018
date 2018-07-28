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

                    <form method="POST" action="{{ route('user-view') }}">{{--  enctype="multipart/form-data"> --}}
                    
                    <b>First Name:</b>    <input type="text" id="first_name" name="first_name" value="{{ $user->first_name }}" placeholder=""/>          <br>
                    <b>Last Name:</b>     <input type="text" id="last_name" name="last_name" value="{{ $user->last_name }}" placeholder=""/>             <br>
                    <b>Email:</b>         {{ $user->email }}<br>
                    <b>Username:</b>      <input type="text" id="username" name="username" value="{{ $user->username }}" placeholder=""/>                <br>

                    <b>Picture:</b>       <input type="file" id="picture" name="picture" value="{{ $user->picture }}" placeholder=""/>                   <br>

                    <b>Sex:</b>           <input type="text" id="sex" name="sex" value="{{ $user->sex }}" placeholder=""/>                               <br>

                    <b>Phone:</b>         <input type="text" id="phone" name="phone" value="{{ $user->phone }}" placeholder=""/>                         <br>
                    <b>Date Of Birth:</b> <input type="text" id="date_of_birth" name="date_of_birth" value="{{ $user->date_of_birth }}" placeholder=""/> <br>
                    <b>Address:</b>       <input type="text" id="address" name="address" value="{{ $user->address }}" placeholder=""/>                   <br>
                    <b>Country:</b>       <input type="text" id="country" name="country" value="{{ $user->country }}" placeholder=""/>                   <br>
                    <b>State:</b>         <input type="text" id="state" name="state" value="{{ $user->state }}" placeholder=""/>                         <br>
                    <b>Town:</b>          <input type="text" id="town" name="town" value="{{ $user->town }}" placeholder=""/>                            <br>
                    <b>Facebook:</b>      <input type="text" id="facebook" name="facebook" value="{{ $user->facebook }}" placeholder=""/>                <br>
                    <b>Twitter:</b>       <input type="text" id="twitter" name="twitter" value="{{ $user->twitter }}" placeholder=""/>                   <br>
                    <b>Instagram:</b>     <input type="text" id="instagram" name="instagram" value="{{ $user->instagram }}" placeholder=""/>             <br>
                        
                        @csrf()
                        <button type="submit">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
