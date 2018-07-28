import React, { Component } from 'react';

class Header extends Component {
	render() {
		return (
			<header className="header">
				<div className="header__logo-box">
					<img
						src="../img/logo-white.png"
						alt="logo"
						className="header__logo"
					/>
				</div>
				<div className="header__text-box">
					<h1 className="heading-primary">
						<span className="heading-primary--main">Tranzact</span>
						<span className="heading-primary--sub">For The Best Wallet Experience</span>
					</h1>
					{/* <a href="#section-tours" className="btn btn--white btn--animated">
						Discover Our Wallets
					</a> <br/>
					<br/> */}
					
					<a href="#section-tours" className="btn btn--white btn--animated" style={{'margin-right': '20px'}}>
						Sign Up
					</a>
					<a href="#section-tours" className="btn btn--white btn--animated">
						Login
					</a>
				</div>
			</header>
		);
	}
}

export default Header;
