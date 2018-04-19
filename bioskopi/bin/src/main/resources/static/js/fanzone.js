function thematicprops(){
	$(".welcome").empty();
	$.ajax({
		  method : 'GET',
		  url : "/thematic_props/get_all_thematic_props",
		  success : function(data){
			  console.log("uspelo!");
			  podeliOglase(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
}

function podeliOglase(data) {
	console.log("usao u podelu");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	console.log(oglas.name);
	if(oglas.tptype == "NEW") {
	$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:#A16073"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.name+`">Make an offer</button>
   					<button type="button" class="btn btn-warning izbrisi" id="prati`+oglas.name+`" name="izmjeni`+oglas.name+`">Edit</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>`);
	} else {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:#A16073"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.name+`">Make an offer</button>
   					<button type="button" class="btn btn-warning izbrisi" id="prati`+oglas.name+`" name="izmjeni`+oglas.name+`">Edit</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>`);
	}
		});
}


function izmjeniOglas(obj) {
	var pokusaj = obj.name;
	console.log("pokusaj na "+pokusaj);
	var konacan = pokusaj.split("izmjeni")[1];
	$(document).on("click",obj,function(e) {
		e.preventDefault();		
		$.ajax({
			method : 'GET',
			url : "/thematic_props",
			success : function(data){
				console.log("uspjesno!");
				nadjiOglas(data,konacan);
			},
			error: function(){
				console.log("neuspesno");
			}
		});
		
	});
}

function nadjiOglas(data,kod) {
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	console.log(oglas.name);
	if(oglas.id == kod) {
		sessionStorage.setItem('mijenjanje',JSON.stringify(oglas));
		sessionStorage.setItem('tipRekvizit',"edit");
		window.location.href = 'thematicProps.html';
	}
	});
}



function izbrisiOglas(obj){
	var pokusaj = obj.name;
	console.log("pokusaj na "+pokusaj);
	var konacan = pokusaj.split("izbrisi")[1];
	$(document).on("click",obj,function(e) {
		e.preventDefault();
		console.log("pokusaj na "+pokusaj);
		console.log("konacan "+konacan);		
		$.ajax({
			method : 'GET',
			url : "/thematic_props/delete/"+konacan,
			success : function(data){
				console.log("uspesno!");
				thematicprops();
			},
			error: function(){
				console.log("neuspesno");
			}
		});
		
	});
}
	