function show(name){
	return name.style.display = 'block';
}

function hide(name){
	return name.style.display = 'none';
}

function showVegettable(){
	var vegetable = document.getElementById('mon_rau');
	var pork = document.getElementById('mon_thit');
	var egg = document.getElementById('mon_trung');
	var tofu = document.getElementById('mon_dau');
	var fish = document.getElementById('mon_ca');
	hide(pork);
	show(vegetable);
	hide(egg);
	hide(tofu);
	hide(fish);
}

function showPork(){
	var vegetable = document.getElementById('mon_rau');
	var pork = document.getElementById('mon_thit');
	var egg = document.getElementById('mon_trung');
	var tofu = document.getElementById('mon_dau');
	var fish = document.getElementById('mon_ca');
	show(pork);
	hide(vegetable);
	hide(egg);
	hide(tofu);
	hide(fish);
}

function showEgg(){
	var vegetable = document.getElementById('mon_rau');
	var pork = document.getElementById('mon_thit');
	var egg = document.getElementById('mon_trung');
	var tofu = document.getElementById('mon_dau');
	var fish = document.getElementById('mon_ca');
	show(egg);
	hide(vegetable);
	hide(pork);
	hide(tofu);
	hide(fish);
}

function showTofu(){
	var vegetable = document.getElementById('mon_rau');
	var pork = document.getElementById('mon_thit');
	var egg = document.getElementById('mon_trung');
	var tofu = document.getElementById('mon_dau');
	var fish = document.getElementById('mon_ca');
	show(tofu);
	hide(vegetable);
	hide(egg);
	hide(pork);
	hide(fish);
}

function showFish(){
	var vegetable = document.getElementById('mon_rau');
	var pork = document.getElementById('mon_thit');
	var egg = document.getElementById('mon_trung');
	var tofu = document.getElementById('mon_dau');
	var fish = document.getElementById('mon_ca');
	show(fish);
	hide(vegetable);
	hide(egg);
	hide(tofu);
	hide(pork);
}

function checkClick(){
	
}