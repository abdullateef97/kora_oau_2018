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

                    <form method="POST" action="{{ route('user-category') }}">
                    
                    <b>Name:</b> <input type="text" id="name" name="name" value="{{ old('name') }}" placeholder=""/>          <br>
                    <b>Description:</b> <input type="text" id="description" name="description" value="{{ old('description') }}" placeholder=""/>             <br>
                        
                        @csrf()
                        <button type="submit">Create</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
