function convertDateInterceptor(module) {
	var convs = [ {
		patt : /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})/,
		map : function(m) {
			return new Date(m.input);
		}
	}, {
		patt : /(\d{4})-(\d{2})-(\d{2})/,
		map : function(m) {
			return new Date(parseInt(m[1]), parseInt(m[2]) - 1, parseInt(m[3]));
		}
	} ];
	function convertDateStringsToDates(input) {
		// Ignore things that aren't objects.
		if (typeof input !== "object")
			return input;

		for ( var key in input) {
			if (!input.hasOwnProperty(key))
				continue;

			var value = input[key];
			var match;
			// Check for string properties which look like dates.
			if (typeof value === "string") {
				var i;
				var matchs;
				for (i = 0; i < convs.length && !(matchs = convs[i].patt.exec(value)); i++)
					;
				if (i < convs.length) {
					input[key] = convs[i].map(matchs);
				}
			} else if (typeof value === "object") {
				// Recurse into object
				convertDateStringsToDates(value);
			}
		}
	}

	module.config([ "$httpProvider", function($httpProvider) {
		$httpProvider.defaults.transformResponse.push(function(responseData) {
			convertDateStringsToDates(responseData);
			return responseData;
		});
	} ]);

};