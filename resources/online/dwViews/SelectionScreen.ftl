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

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container" col-xs-4 col1 center-block>
		<article>
			</br>
			</br>
			</br>
			</br>
			</br>
			<button onclick="setSelection(1)" align: center>Play game</button>					
			<button onclick="setSelection(2)" align: center>View game statistics</button>
			
		</article>	
			
		</div>
		
		<script type="text/javascript">
		
		
		
			// Method that is called on page load
			function initalize() {
			

				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Top Trumps Online");

			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
	// --------------------------------------------------------------------------		
			function setSelection(selection) {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selection?Selection="+selection); // Request type and URL+parameters
				
						if (!xhr) {
		  					alert("CORS not supported");
						}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					alert(responseText); 
 					
 					// document.getElementById("category").innerHTML = responseText;
				}

				xhr.send();		
				if (selection === 1) {
    				location.href = 'http://localhost:7777/toptrumps/game';
				} else {
					location.href = 'http://localhost:7777/toptrumps/stats';
				}
					
				
			};
		
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
	

		</script>
		
		</body>
</html>