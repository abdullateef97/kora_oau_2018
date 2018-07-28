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

                    {{-- "id": 2, --}}
                    <b>First Name:</b>   {{ $user->first_name }}    <br>
                    <b>Last Name:</b>    {{ $user->last_name }}     <br>
                    <b>Email:</b>        {{ $user->email }}         <br>
                    <b>Username:</b>     {{ $user->username }}      <br>
                    <b>Picture:</b>      {{ $user->picture }}       <br>
                    <b>Sex:</b>          {{ $user->sex }}           <br>
                    <b>Phone:</b>        {{ $user->phone }}         <br>
                    <b>Date Of Birth:</b>{{ $user->date_of_birth }} <br>
                    <b>Address:</b>      {{ $user->address }}       <br>
                    <b>Country:</b>      {{ $user->country }}       <br>
                    <b>State:</b>        {{ $user->state }}         <br>
                    <b>Town:</b>         {{ $user->town }}          <br>
                    <b>Facebook:</b>     {{ $user->facebook }}      <br>
                    <b>Twitter:</b>      {{ $user->twitter }}       <br>
                    <b>Instagram:</b>    {{ $user->instagram }}     <br>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
