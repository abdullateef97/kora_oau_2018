
<!DOCTYPE HTML>
<html lang="en">
<head>
	<title>TITLE</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">

	<!-- Font -->

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">

	<!-- Stylesheets -->

	<link href="{{ asset('bona') }}/common-css/bootstrap.css" rel="stylesheet">

	<link href="{{ asset('bona') }}/common-css/ionicons.css" rel="stylesheet">

	<link href="{{ asset('bona') }}/category/css/styles.css" rel="stylesheet">

	<link href="{{ asset('bona') }}/category/css/responsive.css" rel="stylesheet">

</head>
<body >

	<header>
		<div class="container-fluid position-relative no-side-padding">

			<a href="{{ asset('bona') }}/#" class="logo"><img src="{{ asset('bona') }}/images/logo.png" alt="Logo Image"></a>

			<div class="menu-nav-icon" data-nav-menu="#main-menu"><i class="ion-navicon"></i></div>

			<ul class="main-menu visible-on-click" id="main-menu">
				<li><a href="{{ asset('bona') }}/#">Home</a></li>
				<li><a href="{{ asset('bona') }}/#">Categories</a></li>
				<li><a href="{{ asset('bona') }}/#">Features</a></li>
			</ul><!-- main-menu -->

			<div class="src-area">
				<form>
					<button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
					<input class="src-input" type="text" placeholder="Type of search">
				</form>
			</div>

		</div><!-- conatiner -->
	</header>

	<div class="slider display-table center-text">
		<h1 class="title display-table-cell">
			<b>{{ ucfirst($category->name) }}</b>
		</h1>
	</div><!-- slider -->

	<section class="blog-area section">
		<div class="container">
			<div class="row">

				{{-- diplay all posts --}}
				@if (count($posts) > 0)
					{{-- expr --}}
					@foreach ($posts as $post)
					<div class="col-lg-4 col-md-6">
					    <div class="card h-100">
					        <div class="single-post post-style-1">
					            <div class="blog-image">
					            	<img src="{{ asset('bona') }}/images/marion-michele-330691.jpg" alt="Blog Image">
					            </div>

					            <a class="avatar" alt="{{ $post->user->name }}" name="{{ $post->user->name }}" href="#">
					                <img src="{{ asset('bona') }}/images/icons8-team-355979.jpg" alt="Profile Image">
					            </a>

					            <div class="blog-info">

					                <h4 class="title">
					                    <a href="{{ url('/category') }}/{{ $category->slug }}/{{ $post->slug }}">
					                        <b>{{ $post->title }}</b>
					                    </a>
					                </h4>
					                <p>{!! substr(strip_tags($post->content), 0, 100) !!}...</p>

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

				@else
					<div class="col-lg-12 col-md-12">
						<h3 class="text-center">No Posts For this category</h2>
					</div>
				@endif


			</div><!-- row -->

			{{-- <a class="load-more-btn" href="{{ asset('bona') }}/#"><b>LOAD MORE</b></a> --}}
			{!! $posts->render() !!}

		</div><!-- container -->
	</section><!-- section -->


	<footer>

		<div class="container">
			<div class="row">

				<div class="col-lg-4 col-md-6">
					<div class="footer-section">

						<a class="logo" href="{{ asset('bona') }}/#"><img src="{{ asset('bona') }}/images/logo.png" alt="Logo Image"></a>
						<p class="copyright">{{ env("APP_NAME") }} @ 2018. All rights reserved.</p>
						<p class="copyright">Designed by <a href="{{ asset('bona') }}/https://colorlib.com" target="_blank">Colorlib</a></p>
						<ul class="icons">
							<li><a href="/#"><i class="ion-social-facebook-outline"></i></a></li>
							<li><a href="/#"><i class="ion-social-twitter-outline"></i></a></li>
							<li><a href="/#"><i class="ion-social-instagram-outline"></i></a></li>
							<li><a href="/#"><i class="ion-social-vimeo-outline"></i></a></li>
							<li><a href="/#"><i class="ion-social-pinterest-outline"></i></a></li>
						</ul>

					</div><!-- footer-section -->
				</div><!-- col-lg-4 col-md-6 -->

				<div class="col-lg-4 col-md-6">
						<div class="footer-section">
						<h4 class="title"><b>CATAGORIES</b></h4>

						<ul>
						@foreach ($categories as $category)
								<li><a href="{{ url('/category') }}/{{ $category->slug }}">{{ strtoupper($category->name) }}</a></li>
						@endforeach
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
