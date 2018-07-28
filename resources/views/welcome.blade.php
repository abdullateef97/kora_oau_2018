<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>{{ env("APP_NAME") }} | Home</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">


    <!-- Font -->

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">


    <!-- Stylesheets -->

    <link href="{{ asset('bona') }}/common-css/bootstrap.css" rel="stylesheet">

    <link href="{{ asset('bona') }}/common-css/swiper.css" rel="stylesheet">

    <link href="{{ asset('bona') }}/common-css/ionicons.css" rel="stylesheet">


    <link href="{{ asset('bona') }}/front-page-category/css/styles.css" rel="stylesheet">

    <link href="{{ asset('bona') }}/front-page-category/css/responsive.css" rel="stylesheet">

</head>
<body >

    <header>
        <div class="container-fluid position-relative no-side-padding">

            <a href="#" class="logo"><img src="{{ asset('bona') }}/images/logo.png" alt="Logo Image"></a>

            <div class="menu-nav-icon" data-nav-menu="#main-menu"><i class="ion-navicon"></i></div>

            <ul class="main-menu visible-on-click" id="main-menu">
                <li><a href="#">Home</a></li>
                <li><a href="#">Categories</a></li>
                <li><a href="#">Features</a></li>
            </ul><!-- main-menu -->

            <div class="src-area">
                <form>
                    <button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
                    <input class="src-input" type="text" placeholder="Type of search">
                </form>
            </div>

        </div><!-- conatiner -->
    </header>

    <div class="main-slider">
        <div class="swiper-container position-static" data-slide-effect="slide" data-autoheight="false"
            data-swiper-speed="500" data-swiper-autoplay="10000" data-swiper-margin="0" data-swiper-slides-per-view="4"
            data-swiper-breakpoints="true" data-swiper-loop="true" >
            <div class="swiper-wrapper">
                @foreach ($categories as $category)

                <div class="swiper-slide">
                    <a class="slider-category" href="{{ url('/category') }}/{{ $category->slug }}">
                        <div class="blog-image">
                            @if ($category->image == null || $category->image == "")
                            <img src="{{ asset('bona') }}/{{ $categoryPictures[array_rand($categoryPictures)] }}" alt="Blog Image">
                            @else
                            <img src="{{ asset('storage/posts') }}/{{ $category->image }}" alt="Blog Image">
                            @endif
                        </div>
                        <div class="category">
                            <div class="display-table center-text">
                                <div class="display-table-cell">
                                    <h3><b>{{ ucfirst($category->name) }}</b></h3>
                                </div>
                            </div>
                        </div>
                    </a>
                </div><!-- swiper-slide -->

                @endforeach
            </div><!-- swiper-wrapper -->
        </div><!-- swiper-container -->
    </div><!-- slider -->

    <section class="blog-area section">
        <div class="container">
            <div class="row">

                {{-- diplay all posts --}}
                @foreach ($posts as $post)
                <div class="col-lg-4 col-md-6">
                    <div class="card h-100">
                        <div class="single-post post-style-1">

                            <div class="blog-image"><img src="{{ asset('bona') }}/images/marion-michele-330691.jpg" alt="Blog Image"></div>

                            <a class="avatar" alt="{{ $post->user->name }}" name="{{ $post->user->name }}" href="#">
                                <img src="{{ asset('bona') }}/images/icons8-team-355979.jpg" alt="Profile Image">
                            </a>

                            <div class="blog-info">

                                <h4 class="title">
                                    <a href="{{ url('/category') }}/{{ $post->category->slug }}/{{ $post->slug }}">
                                        <b>{{ $post->title }}</b>
                                    </a>
                                </h4>
                                <p>
                                    {{ $post->content }}
                                </p>

                                <ul class="post-footer">
                                    <li><a href="#"><i class="ion-heart"></i>57</a></li>
                                    <li><a href="#"><i class="ion-chatbubble"></i>{{ count($post->comments) }}</a></li>
                                    <li><a href="#"><i class="ion-android-alarm-clock"></i>{{ $post->target }}</a></li>
                                </ul>

                            </div><!-- blog-info -->
                        </div><!-- single-post -->
                    </div><!-- card -->
                </div><!-- col-lg-4 col-md-6 -->
                @endforeach

                <div class="col-lg-8 col-md-12">
                    <div class="card h-100">
                        <div class="single-post post-style-2">

                            <div class="blog-image"><img src="{{ asset('bona') }}/images/brooke-lark-194251.jpg" alt="Blog Image"></div>

                            <div class="blog-info">

                                <h6 class="pre-title"><a href="#"><b>HEALTH</b></a></h6>

                                <h4 class="title"><a href="#"><b>How Did Van Gogh's Turbulent Mind Depict One of the Most Complex
                                    Concepts in Physics?</b></a></h4>

                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                    ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>

                                <div class="avatar-area">
                                    <a class="avatar" href="#"><img src="{{ asset('bona') }}/images/icons8-team-355979.jpg" alt="Profile Image"></a>
                                    <div class="right-area">
                                        <a class="name" href="#"><b>Lora Plamer</b></a>
                                        <h6 class="date" href="#">on Sep 29, 2017 at 9:48am</h6>
                                    </div>
                                </div>

                                <ul class="post-footer">
                                    <li><a href="#"><i class="ion-heart"></i>57</a></li>
                                    <li><a href="#"><i class="ion-chatbubble"></i>6</a></li>
                                    <li><a href="#"><i class="ion-eye"></i>138</a></li>
                                </ul>

                            </div><!-- blog-right -->

                        </div><!-- single-post extra-blog -->

                    </div><!-- card -->
                </div><!-- col-lg-8 col-md-12 -->

                <div class="col-lg-4 col-md-6">
                    <div class="card h-100">
                        <div class="single-post post-style-1">

                            <div class="blog-image"><img src="{{ asset('bona') }}/images/dmitri-popov-326976.jpg" alt="Blog Image"></div>

                            <a class="avatar" href="#"><img src="{{ asset('bona') }}/images/averie-woodard-319832.jpg" alt="Profile Image"></a>

                            <h4 class="title"><a href="#"><b>How Did Van Gogh's Turbulent Mind Depict One of the Most Complex
                                Concepts in Physics?</b></a></h4>

                            <ul class="post-footer">
                                <li><a href="#"><i class="ion-heart"></i>57</a></li>
                                <li><a href="#"><i class="ion-chatbubble"></i>6</a></li>
                                <li><a href="#"><i class="ion-eye"></i>138</a></li>
                            </ul>

                        </div><!-- single-post -->
                    </div><!-- card -->
                </div><!-- col-lg-4 col-md-6 -->

                <div class="col-lg-4 col-md-6">
                    <div class="card h-100">

                        <div class="single-post post-style-2 post-style-3">

                            <div class="blog-info">

                                <h6 class="pre-title"><a href="#"><b>HEALTH</b></a></h6>

                                <h4 class="title"><a href="#"><b>How Did Van Gogh's Turbulent Mind Depict One of the Most Complex
                                    Concepts in Physics?</b></a></h4>

                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                    ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>

                                <div class="avatar-area">
                                    <a class="avatar" href="#"><img src="{{ asset('bona') }}/images/icons8-team-355979.jpg" alt="Profile Image"></a>
                                    <div class="right-area">
                                        <a class="name" href="#"><b>Lora Plamer</b></a>
                                        <h6 class="date" href="#">on Sep 29, 2017 at 9:48am</h6>
                                    </div>
                                </div>

                                <ul class="post-footer">
                                    <li><a href="#"><i class="ion-heart"></i>57</a></li>
                                    <li><a href="#"><i class="ion-chatbubble"></i>6</a></li>
                                    <li><a href="#"><i class="ion-eye"></i>138</a></li>
                                </ul>

                            </div><!-- blog-right -->

                        </div><!-- single-post extra-blog -->

                    </div><!-- card -->
                </div><!-- col-lg-4 col-md-6 -->

                <div class="col-lg-8 col-md-12">
                    <div class="card h-100">
                        <div class="single-post post-style-2">

                            <div class="blog-image"><img src="{{ asset('bona') }}/images/icons8-team-355990.jpg" alt="Blog Image"></div>

                            <div class="blog-info">

                                <h6 class="pre-title"><a href="#"><b>HEALTH</b></a></h6>

                                <h4 class="title"><a href="#"><b>How Did Van Gogh's Turbulent Mind Depict One of the Most Complex
                                    Concepts in Physics?</b></a></h4>

                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                    ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>

                                <div class="avatar-area">
                                    <a class="avatar" href="#"><img src="{{ asset('bona') }}/images/icons8-team-355979.jpg" alt="Profile Image"></a>
                                    <div class="right-area">
                                        <a class="name" href="#"><b>Lora Plamer</b></a>
                                        <h6 class="date" href="#">on Sep 29, 2017 at 9:48am</h6>
                                    </div>
                                </div>

                                <ul class="post-footer">
                                    <li><a href="#"><i class="ion-heart"></i>57</a></li>
                                    <li><a href="#"><i class="ion-chatbubble"></i>6</a></li>
                                    <li><a href="#"><i class="ion-eye"></i>138</a></li>
                                </ul>

                            </div><!-- blog-right -->

                        </div><!-- single-post extra-blog -->

                    </div><!-- card -->
                </div><!-- col-lg-8 col-md-12 -->

            </div><!-- row -->

            <a class="load-more-btn" href="#"><b>LOAD MORE</b></a>

        </div><!-- container -->
    </section><!-- section -->


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

    <script src="{{ asset('bona') }}/common-js/swiper.js"></script>

    <script src="{{ asset('bona') }}/common-js/scripts.js"></script>

</body>
</html>
