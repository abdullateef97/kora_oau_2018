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

                            <h3 class="title text-center"><a href="#"><b>Create a Category</b></a></h3>

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
                        <form method="POST" action="{{ url('/category') }}" enctype="multipart/form-data">
                            @csrf
                            <div class="row">

                                <div class="col-sm-12">
                                    <input type="text" aria-required="true" name="name" id="name" class="form-control"
                                        placeholder="Category Name" aria-invalid="true" required value="{{ old('name') }}">
                                </div><!-- col-sm-6 -->

                                <div class="col-sm-12">
                                    <textarea name="description" id="description" rows="2" class="text-area-messge form-control"
                                        placeholder="Category Description" aria-required="true" aria-invalid="false"></textarea >
                                </div><!-- col-sm-12 -->

                                <div class="col-sm-12">
                                    <input type="file" name="image" id="image" class="form-control">
                                </div><!-- col-sm-12 -->

                                <div class="col-sm-12">
                                    <button class="submit-btn" type="submit" id="form-submit"><b>Create</b></button>
                                </div><!-- col-sm-12 -->

                            </div><!-- row -->
                        </form>
                    </div><!-- comment-form -->

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
