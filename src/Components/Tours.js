import React, { Component } from 'react';

export default class Tours extends Component {
	render() {
		return (
			<section className="section-tours" id="section-tours">
				<div className="u-center-text u-margin-bottom-big">
					<h2 className="heading-secondary">Most Popular Wallets</h2>
				</div>

				<div className="row">
					<div className="col-1-of-3">
						<div className="card">
							<div className="card__side card__side--front">
								<div className="card__picture card__picture--1">&nbsp;</div>
								<h4 className="card__heading">
									<span className="card__heading-span card__heading-span--1">
										Standard
									</span>
								</h4>
								<div className="card__details">
									<ul>
										<li>Safe Keep Your Money</li>
										<li>Pay to Others Using Tranzact</li>
										<li>Make Withdrawals </li>
										{/* <li>Sleep cozy hotel</li>
										<li>Difficulty: easy</li> */}
									</ul>
								</div>
							</div>
							{/* <div className="card__side card__side--back card__side--back-1">
								<div className="card__cta">
									<div className="card__price-box">
										<p className="card__price-only">Only</p>
										<p className="card__price-value">$297</p>
									</div>
									<a href="#popup" className="btn btn--white">
										Book Now!
									</a>
								</div>
							</div> */}
						</div>
					</div>
					<div className="col-1-of-3">
						<div className="card">
							<div className="card__side card__side--front">
								<div className="card__picture card__picture--2">&nbsp;</div>
								<h4 className="card__heading">
									<span className="card__heading-span card__heading-span--2">
										Kolo
									</span>
								</h4>
								<div className="card__details">
									<ul>
										<li>Save Money for a Period of Time</li>
										<li>Cash Out after after Duration ELapses</li>
										{/* <li>6 tour Guides</li>
										<li>Sleep in provided tents</li>
										<li>Difficulty: medium</li> */}
									</ul>
								</div>
							</div>
							{/* <div className="card__side card__side--back card__side--back-2">
								<div className="card__cta">
									<div className="card__price-box">
										<p className="card__price-only">Only</p>
										<p className="card__price-value">$497</p>
									</div>
									<a href="#popup" className="btn btn--white">
										Book Now!
									</a>
								</div>
							</div> */}
						</div>
					</div>
					<div className="col-1-of-3">
						<div className="card">
							<div className="card__side card__side--front">
								<div className="card__picture card__picture--3">&nbsp;</div>
								<h4 className="card__heading">
									<span className="card__heading-span card__heading-span--3">
										Aajo
									</span>
								</h4>
								<div className="card__details">
									<ul>
										<li>Invite Friends To Save</li>
										<li>Save For a Common Interest</li>
										{/* <li>3 tour Guides</li>
										<li>Sleep in provided tents</li>
										<li>Difficulty: hard</li> */}
									</ul>
								</div>
							</div>
							{/* <div className="card__side card__side--back card__side--back-3">
								<div className="card__cta">
									<div className="card__price-box">
										<p className="card__price-only">Only</p>
										<p className="card__price-value">$897</p>
									</div>
									<a href="#popup" className="btn btn--white">
										Book Now!
									</a>
								</div>
							</div> */}
						</div>
					</div>
				</div>

				{/* <div className="u-center-text u-margin-top-huge">
					<a href="#" className="btn btn--green btn-green">
						Discover all tours
					</a>
				</div> */}
			</section>
		);
	}
}
