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

                    @if(count($posts) > 0)
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td>No</td>
                                    <td>Title</td>
                                    <td>Category</td>
                                    <td>Donation</td>
                                    <td>Target</td>
                                    <td>Created at</td>
                                </tr>
                            </thead>
                            <tbody>
                                @foreach ($posts as $post)
                                <tr>
                                    <td>{{ $loop->iteration }}</td>
                                    <td><a href="{{ url('/help') }}/{{ $post->slug }}" target="_blank">{{ $post->title }}</a></td>
                                    <td>{{ $post->category->name }}</td>
                                    <td>NIL</td>
                                    <td>{{ '₦'.$post->target }}</td>
                                    <td>{{ $post->created_at }}</td>
                                </tr>
                                @endforeach
                            </tbody>
                        </table>
                    @else
                        No Posts found
                    @endif
                    
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
