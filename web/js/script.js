var suscribed = false;
var suscribeIcon = "images/suscribe.png";
var suscribeIconHover = "images/suscribed.png";

function showLogin()
{
	document.getElementById("loginPanel").style.display = "block";
	document.getElementById("signupPanel").style.display = "none";
	document.getElementById("btnShowLogin").style.background ="#d83c3c";
	document.getElementById("btnShowSignUp").style.background ="#bf3535";
}


function showSignUp()
{
	document.getElementById("loginPanel").style.display = "none";
	document.getElementById("signupPanel").style.display = "block";
	document.getElementById("btnShowLogin").style.background = "#bf3535";
	document.getElementById("btnShowSignUp").style.background = "#d83c3c";
}

function validate()
{
	var username = document.getElementById("username").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var rpassword = document.getElementById("rpassword").value;
	error = "";
	if(!username.match(/^[a-z0-9_-]{3,15}$/))
	{
		document.getElementById("errorUsername").innerHTML = "Invalid username";
	}
	else if(!email.match(/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/))
	{
		document.getElementById("errorEmail").innerHTML = "The email address is invalid";
	}
	else if(!password.match(/^.{8,}$/))
	{
		document.getElementById("errorPassword").innerHTML = "The password must be at least 8 characters long";
	}
	else if(password != rpassword)
	{
		document.getElementById("errorRpassword").innerHTML = "The passwords do not match";
	}else{
		return true;
	}
	return false;
}


function suscribe()
{
	// document.getElementById("suscribeIcon").src="images/suscribed.png";
	if(suscribed == false)
	{
		suscribed = true;
		suscribeIcon = "images/suscribed.png";
		suscribeIconHover = "images/unsuscribed.png";
	}else{
		suscribed = false;
		suscribeIcon = "images/suscribe.png";
		suscribeIconHover = "images/suscribed.png";
	}
	document.getElementById("suscribeIcon").src = suscribeIcon;
}


function complete()
{
	document.getElementById("completeIcon").src = "images/completed.png";
	document.getElementById("rating").style.display = "inline";
}

function deleteList()
{
	confirm("Do you really want to delete this list?. It will delete all the items in it");
}

function deleteItem()
{
	confirm("Do you really want to delete the item?");
}

function deleteComment()
{
	confirm("Do you really want to delete the comment?");
}

function editList()
{
    document.getElementById("list-title").style.display = "none";
    document.getElementById("list-description").style.display = "none";
    document.getElementById("list-comments").style.display = "none";
    document.getElementById("list-category").style.display = "none";
	document.getElementById("list-edit").style.display = "block";
	var list = document.getElementById("list");

	document.getElementById("ltitle-field").value = document.getElementById("list-title").innerHTML;
	document.getElementById("lcategory-field").value = document.getElementById("list-category").text;
	document.getElementById("ldescription-field").value = document.getElementById("list-description").innerHTML;
}

function editItem()
{
	document.getElementById("component-title").style.display = "none";
	document.getElementById("component-edit").style.display = "block";
	var element = document.getElementById("element");
	document.getElementById("name-field").value = document.getElementById("element").text;
	document.getElementById("url-field").value = element.href;
}


function editComment()
{
	var comment = document.getElementById("comment");
	comment.style.display = "none";
	document.getElementById("comment-edit").style.display = "block";
	document.getElementById("comment-field").value = comment.innerHTML;
}

function hoverSuscribe()
{
	document.getElementById("suscribeIcon").src=suscribeIconHover;
}

function unhoverSuscribe()
{
	document.getElementById("suscribeIcon").src=suscribeIcon;
}
