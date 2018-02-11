<html>
	<body background= "https://i.ytimg.com/vi/fOl6TUPTcO8/maxresdefault.jpg">
	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	
	<head>
		<style>
			div.container {
				color: white; 
					background-color: #2E4053; 
    					width: 100%;
   				border: 1px solid gray;
				}
	
				header, footer {
   					padding: 1em;
   						color: white;
   			 			background-color: #85929E;
   			 			clear: left;
    					text-align: center;
				}

			aside {
    				float: left;
    				max-width: 160px;
    				margin: 0;
   			 	padding: 1em;
   			 	<!--background-color: #2E4053;--> 
			}

			aside {
   			 	list-style-type: none;
    				padding: 0;
			}
   
			aside ul b{
    				text-decoration: none;
			}

			article {
				color: white; 
   				margin-left: 170px;
    				border-left: 1px solid gray;
    				padding: 1em;
    				overflow: hidden;
			}
			h1{
				font-family: 'Allerta Stencil', monospace;
				font-size: 36px;
				font-style: italic; 
				font-variant: normal;
				font-weight: 900;
				line-height: 26.4px;
				}
			h2{
				font-family: 'Allerta Stencil', monospace;
				font-size: 17px;
				font-style: italic; 
				font-variant: normal;
				font-weight: 900;
				line-height: 26.4px;
				}
			#button1, #button2, #button3{
				display:inline-block;
				font-family: 'Allerta Stencil', monospace;
				font-size: 12px; 
				width: 90px;
				height: 30px;
				}
			#button4{
				display:inline-block;
				font-family: 'Allerta Stencil', monospace;
				font-size: 12px; 
				width: 90px;
				height: 30px;
				}
				
		.card {
    			box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
   		 	transition: 0.3s;
    			width: 40%;
			}

		.card:hover {
    			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
			}

		.container {
   			 padding: 2px 16px;
			}
		.card1 {
    			box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
   		 	transition: 0.3s;
    			width: 40%;
    			
			}

		.card1:hover {
    			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
			}

		.container1 {
   			 padding: 2px 16px;
			}
		.card2 {
    			box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
   		 	transition: 0.3s;
    			width: 40%;
    			
			}

		.card2:hover {
    			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
			}

		.container2 {
   			 padding: 2px 16px;
			}
			
		.card3 {
    			box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
   		 	transition: 0.3s;
    			width: 40%;
    			
			}

		.card3:hover {
    			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
			}

		.container3 {
   			 padding: 2px 16px;
			}
		
		.card4 {
    			box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
   		 	transition: 0.3s;
    			width: 40%;
    			
			}

		.card4:hover {
    			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
			}

		.container4 {
   			 padding: 2px 16px;
			}	
		</style>
	</head>
</body>

	</head>

<script>
		function initalize() {
				document.getElementById("button1").onclick = gameOn;
				document.getElementById("button2").onclick = gameStats; 
				document.getElementById("button3").onclick = exitGame; 
				document.getElementById("card").style.visibility = "hidden"; 
				document.getElementById("aside").style.visibility = "hidden";
				document.getElementById("player1").style.visibility = "hidden";
				document.getElementById("player2").style.visibility = "hidden";
				document.getElementById("player3").style.visibility = "hidden";
				document.getElementById("player4").style.visibility = "hidden";
				document.getElementById("button5").style.visibility = "hidden";
			}
	
	function endCurrentGame() {
		// function to end the current game.
	}		
			
	function exitGame() {
		if (confirm("By pressing OK you will exit the game. Continue?")) {
			location.href = 'http://localhost:7777/toptrumps';
			// endCurrentGame();
		}
	}
	function gameStats() {
		if (confirm("By pressing OK you will exit the game. Continue?")) {
			location.href = 'http://localhost:7777/toptrumps/stats';
			// endCurrentGame();
		}
	}
	function gameOn() {
				setPlayers(document.getElementById("nop").options[document.getElementById("nop").selectedIndex].value);
				
				startGame(); 
				document.getElementById("cardValues1").innerHTML = getCardDescription(1) + "<br />" +getCardSize(1) + "<br />" +
																	getCardSpeed(1) + "<br />" +getCardRange(1) + "<br />" +getCardFirepower(1) + "<br />" +getCardCargo(1);
				document.getElementById("cardValues2").innerHTML = getCardDescription(2) + "<br />" +getCardSize(2) + "<br />" +
																	getCardSpeed(2) + "<br />" +getCardRange(2) + "<br />" +getCardFirepower(2) + "<br />" +getCardCargo(2);
				document.getElementById("cardValues3").innerHTML = getCardDescription(3) + "<br />" +getCardSize(3) + "<br />" +
																	getCardSpeed(3) + "<br />" +getCardRange(3) + "<br />" +getCardFirepower(3) + "<br />" +getCardCargo(3);
				document.getElementById("cardValues4").innerHTML = getCardDescription(4) + "<br />" +getCardSize(4) + "<br />" +
																	getCardSpeed(4) + "<br />" +getCardRange(4) + "<br />" +getCardFirepower(4) + "<br />" +getCardCargo(4);
				
				document.getElementById("button1").style.visibility = "hidden";
				document.getElementById("numberOfPlayers").style.visibility = "hidden"; 
				
				document.getElementById("cardValues").innerHTML = getCardDescription(0) + "<br />" +getCardSize(0) + "<br />" +
																	getCardSpeed(0) + "<br />" +getCardRange(0) + "<br />" +getCardFirepower(0) + "<br />" +getCardCargo(0);
				
				
				document.getElementById("card").style.visibility = "visible"; 
				document.getElementById("aside").style.visibility = "visible";
				
				
				//displayCard();
	}
	function startGame() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startGame");
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					//alert(responseText);
					
 				}
 				xhr.send();
	}
			 
	 
			 
		</script>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->


    	<div class="container" Id = "pickPlayers">
    		<header>
   			<h1>Top Trumps</h1>
		</header>
			<p Id = "numberOfPlayers">

				<br/>
				<b>Choose number of players</b>
				<select id="nop">
	 				<option value="2">2</option>
	  				<option value="3">3</option>
	  				<option value="4">4</option>
	  				<option value="5">5</option>
				</select>
					<button id="button1">Start Game</button>
				<!-- need a method to make this button work -->
			</p>
					<button id="button2">Game Stats</button>
					<button id="button3">Exit</button>
			

			<br/>
		<aside Id ="aside">
			<ul.b>
			<br/>
		   		<li><a><h2>Round Winner</h2></a></li>
		   		<p Id="RoundWinner"></p>
		   		<li><a><h2>Rounds Played</h2></a></li>
		   		<p Id="RoundNumber"></p>
		    		<li><a><h2>Draws</h2></a></li>
		    		<p Id="DrawCount"></p>
   			 	<li><a><h2>Turn</h2></a></li>
   			 	<p Id="Turn">It's your turn to start!</p>
   			 	<br />
   			 	<li><a><h2>Players</h2></a></li>
		   		<p Id="UserPlayer"></p>
		   		<p Id="AIPlayer1"></p>
		   		<p Id="AIPlayer2"></p>
		   		<p Id="AIPlayer3"></p>
		   		<p Id="AIPlayer4"></p>
   			 <ul.b>
		</aside>
	</div>

		<div class = "container" Id="card">

			<article>
  				<h2>Your Card</h2>
 					<p Id = "cardname"></p>
 						<!--BUTTON here should only allow to pick when its the humans turn-->
							<div class="card">
  								<img src="https://vignette.wikia.nocookie.net/starwars/images/2/21/MF_over_Takodana_SWCT.png/revision/latest?cb=20170605041002" alt="Avatar" style="width:100%">
 				 			<div class="container">
							<br/>
 				 			
    							<p Id="cardValues"></p>
    							<p Id="Size"></p>
    							<p Id="Speed"></p>
    							<p Id="Range"></p>
    							<p Id="Firepower"></p>
    							<p Id="Cargo"></p>
  							</div>
						</div>
 					
						</br>
					<p Id = "categoryChoice">
				<b>Select Category</b>
					<select id="no3">
 						<option value="Size">Size</option>
  						<option value="Speed">Speed</option>
  						<option value="Range">Range</option>
  						<option value="Firepower">Firepower</option>
  						<option value="Cargo">Cargo</option>
					</select>
					</p>
					<p>
					<button onclick="playCategory()" id="button4">Play Round</button>
					<button onclick="nextRound()" id="button5">Next Round</button>
				</p>
			<br/>
  					
		</article>
		<article Id="player1">
  				<h2>Player 1</h2>
 					<p Id = "cardplayer1"></p>
 						
							<div class="card1">
  								<img src="https://lumiere-a.akamaihd.net/v1/images/Death-Star-II_b5760154.jpeg?region=0%2C68%2C2160%2C1080" alt="Avatar" style="width:100%">
 				 			<div class="container">
							<br/>
 				 			
    							<p Id="cardValues1"></p>
    							<p Id="Size1"></p>
    							<p Id="Speed1"></p>
    							<p Id="Range1"></p>
    							<p Id="Firepower1"></p>
    							<p Id="Cargo1"></p>
  							</div>
						</div>
					</article>
		<article Id="player2">
  				<h2>Player 2</h2>
 					<p Id = "cardplayer2"></p>
 						
							<div class="card2">
  								<img src="https://lumiere-a.akamaihd.net/v1/images/vaders-tie-fighter_8bcb92e1.jpeg?region=0%2C147%2C1560%2C878&width=768" alt="Avatar" style="width:100%">
 				 			<div class="container">
							<br/>
 				 			
    							<p Id="cardValues2"></p>
    							<p Id="Size2"></p>
    							<p Id="Speed2"></p>
    							<p Id="Range2"></p>
    							<p Id="Firepower2"></p>
    							<p Id="Cargo2"></p>
  							</div>
						</div>
					</article>	
		<article Id="player3">
  				<h2>Player 3</h2>
 					<p Id = "cardplayer3"></p>
 						
							<div class="card3">
  								<img src="https://nerdist.com/wp-content/uploads/2015/04/StarDestroyer04222015-970x545.jpg" alt="Avatar" style="width:100%">
 				 			<div class="container">
							<br/>
 				 			
    							<p Id="cardValues3"></p>
    							<p Id="Size3"></p>
    							<p Id="Speed3"></p>
    							<p Id="Range3"></p>
    							<p Id="Firepower3"></p>
    							<p Id="Cargo3"></p>
  							</div>
						</div>
					</article>	
		<article Id="player4">
  				<h2>Player 4</h2>
 					<p Id = "cardplayer4"></p>
 						
							<div class="card4">
  								<img src="http://cdn2us.denofgeek.com/sites/denofgeekus/files/big_thumb_cd04cc40bd2e7060c7a2b417b1743b74.jpg" alt="Avatar" style="width:100%">
 				 			<div class="container">
							<br/>
 				 			
    							<p Id="cardValues4"></p>
    							<p Id="Size4"></p>
    							<p Id="Speed4"></p>
    							<p Id="Range4"></p>
    							<p Id="Firepower4"></p>
    							<p Id="Cargo4"></p>
  							</div>
						</div>
					</article>				
	</div>
			
			<header class="main-header">
    <div class="container" style="background-color: #cce6ff;">
        <div class="row">
            <div class="col-sm-3 col-xs-3 header-left">
            </div>
            <div class="col-sm6 xs-hidden header-center">
                <h1 class = "start">
                    <br>
                    <span class="start-highlight"> Top Trumps: Let's Play </span>
                </h1>
            </div>
            <div class="col-sm-3 col-xs-9 header-top">
                 <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-2">
                </div>
            <div class="col-md-5"></div>
        </div>
            </div>
        </div>
    </div>
</header>
		
		</div>
		

		<script type="text/javascript">



			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------


	// --------------------------------------------------------------------------
	 // --------------------------------------------------------------------------
			function setPlayers(num)
			 {
			 	
				if (num == 2) {
					document.getElementById("UserPlayer").innerHTML = "You";
					document.getElementById("AIPlayer1").innerHTML = "Player 1";
				} else if (num == 3) {
					document.getElementById("UserPlayer").innerHTML = "You";
					document.getElementById("AIPlayer1").innerHTML = "Player 1";
					document.getElementById("AIPlayer2").innerHTML = "Player 2";
				} else if (num == 4) {
					document.getElementById("UserPlayer").innerHTML = "You";
					document.getElementById("AIPlayer1").innerHTML = "Player 1";
					document.getElementById("AIPlayer2").innerHTML = "Player 2";
					document.getElementById("AIPlayer3").innerHTML = "Player 3";
				} else if (num == 5) {
					document.getElementById("UserPlayer").innerHTML = "You";
					document.getElementById("AIPlayer1").innerHTML = "Player 1";
					document.getElementById("AIPlayer2").innerHTML = "Player 2";
					document.getElementById("AIPlayer3").innerHTML = "Player 3";
					document.getElementById("AIPlayer3").innerHTML = "Player 4";
				}
				
				
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numberOfPlayers?numberOfPlayers="+num); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response


 					//alert(responseText);

 					

 				}
 				xhr.send();
			 };
	
	// --------------------------------------------------------------------------
			function displayCard() {

			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/displayCard"); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response

 					document.getElementById("board").innerHTML = "Your card:";

 					document.getElementById("card").innerHTML = responseText;
 				}
 				xhr.send();
			};
	// --------------------------------------------------------------------------		
			function getCardDescription(player) {
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardDescription?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					
					if (player == 0 ) {
						document.getElementById("cardValues").innerHTML = responseText;
					} else if (player == 1) {
						document.getElementById("cardValues1").innerHTML = responseText;
					} else if (player == 2) {
						document.getElementById("cardValues2").innerHTML = responseText;
					} else if (player == 3) {
						document.getElementById("cardValues3").innerHTML = responseText;
					} else if (player == 4) {
						document.getElementById("cardValues4").innerHTML = responseText;
					}	
 				}
 				xhr.send();
			}
	// --------------------------------------------------------------------------
			function getCardSize(player) {
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardSize?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					
					if (player == 0 ) {
						document.getElementById("Size").innerHTML = "Size: "+ responseText;
					} else if (player == 1) {
						document.getElementById("Size1").innerHTML ="Size: "+  responseText;
					} else if (player == 2) {
						document.getElementById("Size2").innerHTML = "Size: "+ responseText;
					} else if (player == 3) {
						document.getElementById("Size3").innerHTML = "Size: "+ responseText;
					} else if (player == 4) {
						document.getElementById("Size4").innerHTML = "Size: "+ responseText;
					}	
 				
 				}
 				xhr.send();
			}
		function getCardSpeed(player) {
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardSpeed?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					if (player == 0 ) {
						document.getElementById("Speed").innerHTML = "Speed: "+ responseText;
					} else if (player == 1) {
						document.getElementById("Speed1").innerHTML = "Speed: "+responseText;
					} else if (player == 2) {
						document.getElementById("Speed2").innerHTML = "Speed: "+responseText;
					} else if (player == 3) {
						document.getElementById("Speed3").innerHTML = "Speed: "+responseText;
					} else if (player == 4) {
						document.getElementById("Speed4").innerHTML = "Speed: "+responseText;
					}	
 	
 				}
 				xhr.send();
			}
				function getCardRange(player) {
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardRange?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					if (player == 0 ) {
						document.getElementById("Range").innerHTML = "Range: "+responseText;
					} else if (player == 1) {
						document.getElementById("Range1").innerHTML ="Range: "+ responseText;
					} else if (player == 2) {
						document.getElementById("Range2").innerHTML = "Range: "+responseText;
					} else if (player == 3) {
						document.getElementById("Range3").innerHTML = "Range: "+responseText;
					} else if (player == 4) {
						document.getElementById("Range4").innerHTML ="Range: "+ responseText;
					}	

 		
 				}
 				xhr.send();
			}
				function getCardFirepower(player) {
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardFirepower?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					if (player == 0 ) {
						document.getElementById("Firepower").innerHTML = "Firepower: "+responseText;
					} else if (player == 1) {
						document.getElementById("Firepower1").innerHTML ="Firepower: "+ responseText;
					} else if (player == 2) {
						document.getElementById("Firepower2").innerHTML = "Firepower: "+responseText;
					} else if (player == 3) {
						document.getElementById("Firepower3").innerHTML = "Firepower: "+responseText;
					} else if (player == 4) {
						document.getElementById("Firepower4").innerHTML ="Firepower: "+ responseText;
					}	

 					
 				}
 				xhr.send();
			}
				function getCardCargo(player) {
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardCargo?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					if (player == 0 ) {
						document.getElementById("Cargo").innerHTML ="Cargo: "+ responseText;
					} else if (player == 1) {
						document.getElementById("Cargo1").innerHTML = "Cargo: "+ responseText;
					} else if (player == 2) {
						document.getElementById("Cargo2").innerHTML = "Cargo: "+ responseText;
					} else if (player == 3) {
						document.getElementById("Cargo3").innerHTML = "Cargo: "+ responseText;
					} else if (player == 4) {
						document.getElementById("Cargo4").innerHTML = "Cargo: "+ responseText;
					}	

 				}
 				xhr.send();
			}
			function getCardValues(player) {
					var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardValues?player="+player); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response

 					

 					document.getElementById("cardValues").innerHTML = responseText;
 				}
 				xhr.send();
			}
	// --------------------------------------------------------------------------
	
	// --------------------------------------------------------------------------
	var rounds = 0; 
	var drawCount = 0;
	var turn = 0;
	draw = false;
			function playCategory() {
				// 
				rounds++; 
			 	var p = document.getElementById("no3");
			 	var num = p.options[p.selectedIndex].value;
				if ( num == "Size") {
					num = 1;
				} else if ( num == "Speed" ) {
					num = 2;
				} else if ( num == "Range" ) {
					num = 3;
				} else if ( num == "Firepower" ) {
					num = 4;
				} else if ( num == "Cargo" ) {
					num = 5;
				}
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playCategory?Category="+num); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// Returns the winner of the round
 					var responseText = xhr.response; // the text of the response
					
					if (responseText == "0") {
						turn = responseText;
						document.getElementById("RoundWinner").innerHTML = "You won the round!";
						draw = false;
					} else if (responseText == "5") {
						draw = true;
						document.getElementById("RoundWinner").innerHTML = "That round was a draw!";
						drawCount++;
						document.getElementById("DrawCount").innerHTML = drawCount;
					} else {
						turn = responseText;
						document.getElementById("RoundWinner").innerHTML = "Player " + responseText + " won that round!";
						draw =false;
					}
 					document.getElementById("RoundNumber").innerHTML = rounds;
 					
 					roundResults();

 				}
 				xhr.send();
 				
			 };
			 
			 function roundResults() {
			 	document.getElementById("button4").style.visibility = "hidden";
			 	document.getElementById("categoryChoice").style.visibility = "hidden";
			 	document.getElementById("button5").style.visibility = "visible";
			 			document.getElementById("player1").style.visibility = "visible";
						document.getElementById("player2").style.visibility = "visible";
						document.getElementById("player3").style.visibility = "visible";
						document.getElementById("player4").style.visibility = "visible";
		
			 }
			 // thinking that we need to determine which cards to print etc. keep track
			 // of what users that are still in the game. for now every1 will show.
			 // if someone knows a neat way of doing this that would be gr8!
			 var ActiveUser = true;
			 var ActiveAI1 = true;
			 var ActiveAI2 = true;
			 var ActiveAI3 = true;
			 var ActiveAI4 = true;
			 var gameOver; 
			 
			 
			 function nextRound() {
			 			isGameOver();
						if (gameOver==true) {
							gameIsOver();
						}
				 		document.getElementById("cardValues").innerHTML = getCardDescription(0) + "<br />" +getCardSize(0) + "<br />" +
																		getCardSpeed(0) + "<br />" +getCardRange(0) + "<br />" +getCardFirepower(0) + "<br />" +getCardCargo(0);
						document.getElementById("cardValues1").innerHTML = getCardDescription(1) + "<br />" +getCardSize(1) + "<br />" +
																		getCardSpeed(1) + "<br />" +getCardRange(1) + "<br />" +getCardFirepower(1) + "<br />" +getCardCargo(1);
						document.getElementById("cardValues2").innerHTML = getCardDescription(2) + "<br />" +getCardSize(2) + "<br />" +
																		getCardSpeed(2) + "<br />" +getCardRange(2) + "<br />" +getCardFirepower(2) + "<br />" +getCardCargo(2);
						document.getElementById("cardValues3").innerHTML = getCardDescription(3) + "<br />" +getCardSize(3) + "<br />" +
																		getCardSpeed(3) + "<br />" +getCardRange(3) + "<br />" +getCardFirepower(3) + "<br />" +getCardCargo(3);
						document.getElementById("cardValues4").innerHTML = getCardDescription(4) + "<br />" +getCardSize(4) + "<br />" +
																		getCardSpeed(4) + "<br />" +getCardRange(4) + "<br />" +getCardFirepower(4) + "<br />" +getCardCargo(4);
				 		
				 		document.getElementById("player1").style.visibility = "hidden";
						document.getElementById("player2").style.visibility = "hidden";
						document.getElementById("player3").style.visibility = "hidden";
						document.getElementById("player4").style.visibility = "hidden";
						document.getElementById("button5").style.visibility = "hidden";
							if (turn == "0") {
								document.getElementById("Turn").innerHTML = "It's your turn, choose category and play!";
								document.getElementById("categoryChoice").style.visibility = "visible"; 
								document.getElementById("button4").style.visibility = "visible"; 
							} else if (draw) {
								if (turn == 0 ) {
									document.getElementById("Turn").innerHTML = "It's your turn, choose category and play!"; 
									document.getElementById("categoryChoice").style.visibility = "visible"; 
									document.getElementById("button4").style.visibility = "visible";
								} else {
									document.getElementById("Turn").innerHTML = "It's player "+turn+ "'s turn. Press 'Play Round'!"; 
									document.getElementById("categoryChoice").style.visibility = "hidden";
									document.getElementById("button4").style.visibility = "visible";
								}
							} else {
								document.getElementById("Turn").innerHTML = "It's player "+turn+ "'s turn. Press 'Play Round'!";
								document.getElementById("categoryChoice").style.visibility = "hidden";
								document.getElementById("button4").style.visibility = "visible";
							}
					
			 }
			 
			 
			function getNumberOfPlayers() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumberOfPlayers"); // Request type and URL+parameters
					if (!xhr) {
			  			alert("CORS not supported");
					}
					var responseText;
					xhr.onload = function(e) {
						
	 					responseText = xhr.response; // the text of the response
	
	 					
	 					
	
	 				}
	 				xhr.send();
	 				return responseText;
			}
	// --------------------------------------------------------------------------
			function isGameOver() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isGameOver"); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// Returns int , 1 = game is over.
 					var responseText = xhr.response; // the text of the response
					gameOver = responseText;
 					

 				}
 				xhr.send();
 				
			 };
	
	
	// --------------------------------------------------------------------------
			function gameIsOver() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/gameIsOver"); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// Returns the winner, 
 					var responseWinner = xhr.response; // the text of the response

 					//  do what needs to be done with ze winner
 					// 
					alert("GAME IS OVER");
 				}
 				xhr.send();
 				
			}
	// --------------------------------------------------------------------------
			
	
	
	
	
	
	// --------------------------------------------------------------------------
	
		
			
	
	// --------------------------------------------------------------------------
	// ----------------------------Written by Richard----------------------------
	// --------------------------------------------------------------------------
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
	// --------------------------------------------------------------------------

		</script>

		<script type="text/javascript">
		<!-- Here are examples of how to call REST API Methods -->





		</script>

		</body>
</html>
