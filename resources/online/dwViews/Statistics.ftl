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

	</head>
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
			</div>
		</style>
	</head>

    <body onload="initalize()" onunload="disconnect()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

		<h5>Top Trumps: Game Statistics</h5>
			<br/>
			<br/>
			<p Id = "Exit">
			<button id="button1" onclick = "exitGame()">Exit</button>
			</p>
		</div>
	<br/>
	<div class = "container" Id="statslist">
			<article>
  				<h2>Last Game Statistics</h2></p>
  					</br>
  					<ul b>
		   				<li>Game Winner: </li>
		   				</br>
		   				<li>Draws:</li>
		   				</br>
		    				<li>Rounds won by per player: </li>
		    			</ul b>
			    		</br>
			    		</br>
		    		<h2>Total Statistics</h2></p>
		    			</br>
		    			<ul b>
		   			 	<li>Number of Games Played Overall: <strong id="gamesPlayed"></strong></li>	
		   			 	</br>
		   			 	<li>Number of games won by human: <strong id="humanwins"></strong></li>
		   			 	</br>
		   			 	<li>Number of games won by AI: <strong id="AIwins"></strong></li>
		   			 	</br>
		   			 	<li>Average number of draws: <strong id="draws"></strong></li>  
		   			 	</br> 
		   			 	<li>Longest game: <strong id="longestGames"></strong> rounds </li>	
		   			 </ul>	
	   			 	</br>
		</article>
	</div>
</head>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				connect();
				readStats();
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
				// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function readStats() {
					totalGames();
					humanWins();
					aiWins();
					averageDraws();
					longestGame();
			
			}
			
			function totalGames() { 
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/totalGames"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 					
					document.getElementById("gamesPlayed").innerHTML = stats;
					
				};
				
				xhr.send();	
			}
		
			function humanWins() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/humanWins"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 					
					document.getElementById("humanwins").innerHTML = stats;
					
				};
				
				xhr.send();		
			}	
				function aiWins() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AIwins"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 				
					document.getElementById("AIwins").innerHTML = stats;
				};
				
				xhr.send();		
			}
			function averageDraws() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/averageDraws"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 					
					document.getElementById("draws").innerHTML = stats;
				};
				
				xhr.send();		
			}
			function longestGame() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/longestGame"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 					
					document.getElementById("longestGames").innerHTML = stats;
				};
				
				xhr.send();		
			}
			
				function connect() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/connect"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 					// if no connection alert?
				};
				
				xhr.send();		
			}
			
				function disconnect() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/disconnect"); // Request type and URL
				
				if (!xhr) {
  					alert("CORS not supported");
				}

				xhr.onload = function(e) {
 					var stats = xhr.response; // the text of the response
 					
				};
				
				xhr.send();		
			}
		
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
	
	//-------------
	//-------------
		
		function exitGame() {
			location.href = 'http://localhost:7777/toptrumps';
		}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>