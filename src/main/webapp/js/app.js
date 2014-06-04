var AkiEsAPI = (function () {
	var mod = {};
	
	var $main = $("#main");
	var templates = {
		"home" : $("#home-template").html(),
		"analysis" : $("#analysis-template").html()
	}
	
	mod.getGeo = function(callback) {
		if ("geolocation" in navigator) {
			// Geo available
			navigator
				.geolocation
				.getCurrentPosition(callback);
		} else {
			// Geo not available
		}
	}
	
	mod.getData = function() {
		return {
			"score" : "100",
			"description" : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
			"details" : [
				{
					"detail" : "police",
					"score" : "80",
					"description": "Lorem ipsum dolor sit amet, consectetur adipisicing elit."
				},
				{
					"detail" : "school",
					"score" : "40",
					"description": "Lorem ipsum dolor sit amet, consectetur adipisicing elit."
				},
				{
					"detail" : "hospital",
					"score" : "90",
					"description": "Lorem ipsum dolor sit amet, consectetur adipisicing elit."
				}
			]
		};
	};
	
	mod.render = function(template, data) {
		var rendering = Mustache.render(template, data);
		$main.html(rendering);
	};
	
	mod.runHomeAnim = function() {
		$(".logo").animo({
			animation: 'bounceInDown',
		}, function() {
			$("#app-name").animo({
				animation: 'fadeIn',
				duration: 1,
				keep: true
			});
		});
		
		$('#begin-btn').animo({
			animation: 'bounceInUp',
			duration: 3
		});
	}
	
	mod.attachHomeEvents = function() {
		$("#begin-btn").click(function(e) {
			e.preventDefault();
			mod.gotoAnalysis();
		});
	}
	
	mod.gotoHome = function() {
		mod.render(templates["home"], {});
		mod.runHomeAnim();
		mod.attachHomeEvents();
	};
	
	mod.runAnalysisAnim = function() {
		$(".navbar").animo({
			animation: "fadeInDown",
			keep: true
		})
		
		$(".badge").animo({
			animation: 'bounceInDown',
		}, function() {
			$(".description").animo({
				animation: 'fadeIn',
				duration: 1,
				keep: true
			});
			
			$('.details').animo({
				animation: 'lightSpeedIn',
				keep: true
			});
		});
	}
	
	mod.attachAnalysisEvents = function() {
		// TODO: Attach events here
	}
	
	mod.gotoAnalysis = function() {
		//mod.getGeo(function(position) {
			$.getJSON(
				"https://akies-wovenware.rhcloud.com/rest/score/1/1",
				[position.coords.latitude, position.coords.longitude],
				function(data) {
					mod.render(templates["analysis"], data);
					mod.runAnalysisAnim();
					mod.attachAnalysisEvents();
				}
			);
		//});
		
		// mod.render(templates["analysis"], mod.getData());
		// mod.runAnalysisAnim();
		// mod.attachAnalysisEvents();
	};
	
	mod.init = function() {
		mod.gotoHome();
	};
	
	mod.init();
	
	return mod;
}());