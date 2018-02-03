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

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container" Id = "pickPlayers">
			<p Id = "numberOfPlayers">
			<b>Please choose number of players</b>
			
			<button onclick="setPlayers(2)">2 Players</button>
			<button onclick="setPlayers(3)">3 Players</button>
			<button onclick="setPlayers(4)">4 Players</button>
			<button onclick="setPlayers(5)">5 Players</button>
			</p>
			<br/>
		
		</div>
		<div class "container">
		<h5>This is the message board</h5>
		<br/>
			<p Id = "messageBoard">
			</p>
			<br/>
			<p Id = "startButton">
			</p>
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
		
	
				
			function setPlayers(numberOfPlayers)
			 {
			
			 	
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numberOfPlayers?numberOfPlayers="+numberOfPlayers); // Request type and URL+parameters
				
						if (!xhr) {
		  					alert("CORS not supported");
						}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					
 					document.getElementById("messageBoard").innerHTML = responseText; 
 					
 					// document.getElementById("category").innerHTML = responseText;
				}

				xhr.send();	
				
				var button = document.createElement("button");
				button.innerHTML = "Start Game!";
				var body = document.getElementById("startButton");
				body.appendChild(button);
				button.addEventListener ("click", function() { 
				startGame(); 
				alert("Game started"); 
				});
				
			//var parent = document.getElementById("pickPlayers");
			//var child = document.getElementById("numberOfPlayers");
			//parent.removeChild(child);
			 };
			
		
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
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->

		
		
											

		</script>
		
		</body>
</html>