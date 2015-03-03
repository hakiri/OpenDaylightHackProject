function test(){
			 console.log("Here..");
			  $.getJSON('/hackproject/northbound/getSomething', function(result) {
				     alert("Got result: " + JSON.stringify(result));
		     });
	}