<html>

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
    			width: 100%;
   			border: 1px solid gray;
		}

			header, footer {
   				padding: 1em;
   				color: white;
   			 	background-color: black;
   			 	clear: left;
    				text-align: center;
			}

			gameStats {
    				float: left;
    				max-width: 160px;
    				margin: 0;
   			 	padding: 1em;
			}

			gameStats {
   			 	list-style-type: none;
    				padding: 0;
			}
   
			gameStats a {
    				text-decoration: none;
			}

			cardBody {
   				margin-left: 170px;
    				border-left: 1px solid gray;
    				padding: 1em;
    				overflow: hidden;
			}
		</style>
	</head>
<body>
	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

    	<div class="container" Id = "pickPlayers">
    		<header>
   			<h1>Top Trumps</h1>
		</header>
			<p Id = "numberOfPlayers">
			<b>Please choose number of players</b>
			<select id="nop">
 				<option value="2">2</option>
  				<option value="3">3</option>
  				<option value="4">4</option>
  				<option value="5">5</option>
			</select>
				<button onclick="gameOn()">start game</button>
			</p>
			<br/>
			<b>Game Statistics</b>
			<!-- need a method to make this button work -->
				<button onclick="gameStats()">Game Statistics</button>
			</p>
			<br/>
		<gameStats>
		   		<p>Round Winner</p>
		    		<p>Draws</p>
   			 	<p>Turn</p>
		</gameStats>

	</div>
		<div class "container">
			<cardBody>
  				<h2>Your Card</h2>
 					<p>Need method to print top card here</p>
 						<!--METHOD here should only allow to pick when its the humans turn-->
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
			<br/>
  					
		</cardBody>
	</div>

		<script type="text/javascript">

			// Method that is called on page load
			function initalize() {

				// --------------------------------------------------------------------------
				//pick players
				//start game
				//choose category
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------



			}

			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------


	// --------------------------------------------------------------------------
			function gameOn() {
				setPlayers();
				document.getElementById("numberOfPlayers").innerHTML = "";
				startGame();
				displayCard();
			}
			
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

 					document.getElementById("messageBoard").innerHTML = responseText;
 					

 				}
 				xhr.send();
			 };
	// --------------------------------------------------------------------------
			function startGame()
			 {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startGame");
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
