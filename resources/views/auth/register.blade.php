{{-- @extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __('Register') }}</div>

                <div class="card-body">
                    <form method="POST" action="{{ route('register') }}" aria-label="{{ __('Register') }}">
                        @csrf

                        <div class="form-group row">
                            <label for="first_name" class="col-md-4 col-form-label text-md-right">{{ __('First Name') }}</label>

                            <div class="col-md-6">
                                <input id="first_name" type="text" class="form-control{{ $errors->has('first_name') ? ' is-invalid' : '' }}" name="first_name" value="{{ old('first_name') }}" required autofocus>

                                @if ($errors->has('first_name'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('first_name') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="last_name" class="col-md-4 col-form-label text-md-right">{{ __('Last Name') }}</label>

                            <div class="col-md-6">
                                <input id="last_name" type="text" class="form-control{{ $errors->has('last_name') ? ' is-invalid' : '' }}" name="last_name" value="{{ old('last_name') }}" required autofocus>

                                @if ($errors->has('last_name'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('last_name') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="email" class="col-md-4 col-form-label text-md-right">{{ __('E-Mail Address') }}</label>

                            <div class="col-md-6">
                                <input id="email" type="email" class="form-control{{ $errors->has('email') ? ' is-invalid' : '' }}" name="email" value="{{ old('email') }}" required>

                                @if ($errors->has('email'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('email') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-md-4 col-form-label text-md-right">{{ __('Password') }}</label>

                            <div class="col-md-6">
                                <input id="password" type="password" class="form-control{{ $errors->has('password') ? ' is-invalid' : '' }}" name="password" required>

                                @if ($errors->has('password'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('password') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password-confirm" class="col-md-4 col-form-label text-md-right">{{ __('Confirm Password') }}</label>

                            <div class="col-md-6">
                                <input id="password-confirm" type="password" class="form-control" name="password_confirmation" required>
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    {{ __('Register') }}
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection --}}

<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>{{ env("APP_NAME") }} | Register</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <!-- Font -->

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">

    <!-- Stylesheets -->

    <link href="{{ asset('bona') }}/common-css/bootstrap.css" rel="stylesheet">

    <link href="{{ asset('bona') }}/common-css/ionicons.css" rel="stylesheet">


    <link href="{{ asset('bona') }}/single-post-2/css/styles.css" rel="stylesheet">

    <link href="{{ asset('bona') }}/single-post-2/css/responsive.css" rel="stylesheet">

</head>
<body >

    <header>
        <div class="container-fluid position-relative no-side-padding">

            <a href="#" class="logo">
                {{-- <img src="{{ asset('bona') }}/images/logo.png" alt="Logo Image"> --}}
                <span style="font-size: larger;font-weight:  900;">{{ env("APP_NAME") }}</span>
            </a>

            <div class="menu-nav-icon" data-nav-menu="#main-menu"><i class="ion-navicon"></i></div>

            <ul class="main-menu visible-on-click" id="main-menu">
                <li><a href="{{ url('/') }}">Home</a></li>
                <li><a href="{{ route('login') }}">Login</a></li>
                <li><a href="{{ route('register') }}">Signup</a></li>
            </ul><!-- main-menu -->

            <div class="src-area">
                <form>
                    <button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
                    <input class="src-input" type="text" placeholder="Type of search">
                </form>
            </div>

        </div><!-- conatiner -->
    </header>

    <div class="slider">

    </div><!-- slider -->

    <section class="post-area">
        <div class="container">

            <div class="row">

                <div class="col-lg-1 col-md-0"></div>
                <div class="col-lg-10 col-md-12">

                    <div class="main-post">

                        <div class="post-top-area">

                            {{-- <h5 class="pre-title">FASHION</h5> --}}

                            <h3 class="title text-center"><a href="#"><b>Sign Up</b></a></h3>

                            @if(count($errors))
                                <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                  </button>
                              @foreach ($errors->all() as $error)
                                <p style="display: block;" class="text-danger">{{ $error }}</p>
                              @endforeach
                                </div>
                            @endif

                        </div><!-- post-top-area -->

                    </div><!-- main-post -->
                </div><!-- col-lg-8 col-md-12 -->
            </div><!-- row -->
        </div><!-- container -->
    </section><!-- post-area -->

    <section class="comment-section center-text">
        <div class="container">
            {{-- <h4><b>Sign Up Here</b></h4> --}}
            <div class="row">

                <div class="col-lg-1 col-md-0"></div>

                <div class="col-lg-10 col-md-12">

                    <div class="comment-form">
                        <form method="POST" action="{{ route('register') }}" aria-label="{{ __('Register') }}">
                            @csrf
                            <div class="row">

                                <div class="col-sm-6">
                                    <input type="text" aria-required="true" name="first_name" id="first_name" class="form-control"
                                        placeholder="First Name" aria-invalid="true" required value="{{ old('first_name') }}">
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-6">
                                    <input type="text" aria-required="true" name="last_name" id="last_name" class="form-control"
                                        placeholder="Last Name" aria-invalid="true" required value="{{ old('last_name') }}">
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-6">
                                    <input type="email" aria-required="true" name="email" id="email" class="form-control"
                                        placeholder="Email Address" aria-invalid="true" required value="{{ old('email') }}">
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-6">
                                    <input type="phone" aria-required="true" name="phone" id="phone" class="form-control"
                                        placeholder="Phone Number" aria-invalid="true" required value="{{ old('phone') }}">
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-6">
                                    <input type="password" aria-required="true" name="password" id="password" class="form-control"
                                        placeholder="Password" aria-invalid="true" required>
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-6">
                                    <input type="password" aria-required="true" name="password_confirmation" id="password-confirm" class="form-control"
                                        placeholder="Confirm Password" aria-invalid="true" required>
                                </div><!-- col-sm-6 -->

                                {{-- <div class="col-sm-12">
                                    <textarea name="contact-form-message" rows="2" class="text-area-messge form-control"
                                        placeholder="Enter your comment" aria-required="true" aria-invalid="false"></textarea >
                                </div><!-- col-sm-12 --> --}}
                                <div class="col-sm-12">
                                    <button class="submit-btn" type="submit" id="form-submit"><b>Sign Up</b></button>
                                </div><!-- col-sm-12 -->

                            </div><!-- row -->
                        </form>
                    </div><!-- comment-form -->

                    <a class="more-comment-btn" href="{{ route('login') }}"><b>Login</a>

                </div><!-- col-lg-8 col-md-12 -->

            </div><!-- row -->

        </div><!-- container -->
    </section>

    <footer>

        <div class="container">
            <div class="row">

                <div class="col-lg-4 col-md-6">
                    <div class="footer-section">

                        <a class="logo" href="#"><img src="{{ asset('bona') }}/images/logo.png" alt="Logo Image"></a>
                        <p class="copyright">Bona @ 2017. All rights reserved.</p>
                        <p class="copyright">Designed by <a href="https://colorlib.com" target="_blank">Colorlib</a></p>
                        <ul class="icons">
                            <li><a href="#"><i class="ion-social-facebook-outline"></i></a></li>
                            <li><a href="#"><i class="ion-social-twitter-outline"></i></a></li>
                            <li><a href="#"><i class="ion-social-instagram-outline"></i></a></li>
                            <li><a href="#"><i class="ion-social-vimeo-outline"></i></a></li>
                            <li><a href="#"><i class="ion-social-pinterest-outline"></i></a></li>
                        </ul>

                    </div><!-- footer-section -->
                </div><!-- col-lg-4 col-md-6 -->

                <div class="col-lg-4 col-md-6">
                        <div class="footer-section">
                        <h4 class="title"><b>CATAGORIES</b></h4>
                        <ul>
                            <li><a href="#">BEAUTY</a></li>
                            <li><a href="#">HEALTH</a></li>
                            <li><a href="#">MUSIC</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">SPORT</a></li>
                            <li><a href="#">DESIGN</a></li>
                            <li><a href="#">TRAVEL</a></li>
                        </ul>
                    </div><!-- footer-section -->
                </div><!-- col-lg-4 col-md-6 -->

                <div class="col-lg-4 col-md-6">
                    <div class="footer-section">

                        <h4 class="title"><b>SUBSCRIBE</b></h4>
                        <div class="input-area">
                            <form>
                                <input class="email-input" type="text" placeholder="Enter your email">
                                <button class="submit-btn" type="submit"><i class="icon ion-ios-email-outline"></i></button>
                            </form>
                        </div>

                    </div><!-- footer-section -->
                </div><!-- col-lg-4 col-md-6 -->

            </div><!-- row -->
        </div><!-- container -->
    </footer>


    <!-- SCIPTS -->

    <script src="{{ asset('bona') }}/common-js/jquery-3.1.1.min.js"></script>

    <script src="{{ asset('bona') }}/common-js/tether.min.js"></script>

    <script src="{{ asset('bona') }}/common-js/bootstrap.js"></script>

    <script src="{{ asset('bona') }}/common-js/scripts.js"></script>

</body>
</html>
