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
                    
                    <b>Title:</b>  <input type="text" id="title" name="title" value="{{ old('title') }}" placeholder=""/>          <br>
                    <b>Target:</b>   <input type="text" id="target" name="target" value="{{ old('target') }}" placeholder=""/>     <br>

                    <b>Category:</b> 
                    <select name="category" id="category">
                        <option value="1">Select Category</option>
                        @foreach ($categories as $category)
                            <option value="{{ $category->id }}">{{ ucfirst($category->name) }}</option>
                        @endforeach
                    </select>       <br>

                    <b>Content:</b>    <input type="text" id="content" name="content" value="{{ old('content') }}" placeholder=""/><br>
                        
                        @csrf()
                        <button type="submit">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
