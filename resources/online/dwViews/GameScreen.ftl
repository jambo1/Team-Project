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
				//setPlayers();
				//alert("Bitches get stitches"); 
				startGame(); 
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
		    		<li><a><h2>Draws</h2></a></li>
   			 	<li><a><h2>Turn</h2></a></li>
   			 <ul.b>
		</aside>
	</div>

		<div class = "container" Id="card">

			<article>
  				<h2>Your Card</h2>
 					<p Id = "cardname"></p>
 						<!--METHOD here should only allow to pick when its the humans turn-->
							<div class="card">
  								<img src="https://vignette.wikia.nocookie.net/starwars/images/2/21/MF_over_Takodana_SWCT.png/revision/latest?cb=20170605041002" alt="Avatar" style="width:100%">
 				 			<div class="container">
							<br/>
 				 			
    							<p Id="cardValues"></p>
    							
  							</div>
						</div>
 					

					<p Id = "categoryChoice">
				<b>Select Category</b>
					<select id="no3">
 						<option value="Size">Size</option>
  						<option value="Speed">Speed</option>
  						<option value="Range">Range</option>
  						<option value="Firepower">Firepower</option>
  						<option value="Cargo">Cargo</option>
					</select>
					<button onclick="playRound()" id="button4">Play Round</button>
				</p>
			<br/>
  					
		</article>
	</div>


			<!-- Add your HTML Here -->
			
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
			function setPlayers()
			 {
			 	var p = document.getElementById("nop");
			 	var num = p.options[p.selectedIndex].value;

			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numberOfPlayers?numberOfPlayers="+num); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response


 					alert(responseText);

 					

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
					document.getElementById("cardValues").innerHTML = responseText;
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

 					$document.getElementById("cardValues").append("Size: " + responseText);

 				
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

 					$document.getElementById("cardValues").append("Speed: " + responseText);

 	
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

 					$document.getElementById("cardValues").append("Range: " + responseText);

 		
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

 					$document.getElementById("cardValues").append("Firepower: " + responseText);

 					
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

 					$document.getElementById("cardValues").append("Cargo: " + responseText);

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
			function playCategory() {
				// 
			 	var p = document.getElementById("no3");
			 	var num = p.options[p.selectedIndex].value;

			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playCategory?Category="+num); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// Returns which category was chosen ie "speed" or "Firepower"
 					var responseText = xhr.response; // the text of the response

 					// document.getElementById("").innerHTML = responseText;
 					

 				}
 				xhr.send();
 				
			 };
		
	// --------------------------------------------------------------------------
			function isGameOver() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isGameOver"); // Request type and URL+parameters
				if (!xhr) {
		  			alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// Returns boolean, true = game is over.
 					var responseBoolean = xhr.response; // the text of the response

 					// call function gameIsOver if responseBoolean is true 
 					// gameIsOver();

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
