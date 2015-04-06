function validate()
{
	var username = document.getElementById("username").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var rpassword = document.getElementById("rpassword").value;

	document.getElementById("errorUsername").innerHTML = "";
	document.getElementById("errorEmail").innerHTML = "";
	document.getElementById("errorPassword").innerHTML = "";
	document.getElementById("errorRpassword").innerHTML = "";

	if(!username.match(/^[a-z0-9_-]{3,15}$/))
	{
		document.getElementById("errorUsername").innerHTML = "Invalid username";
	}
	else if(!email.match(/^^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/))
	{
		document.getElementById("errorEmail").innerHTML = "The email address is invalid";
	}
	else if(!password.match(/^.{8,}$/))
	{
		document.getElementById("errorPassword").innerHTML = "The password must be at least 8 characters long";
	}
	else if(password !== rpassword)
	{
		document.getElementById("errorRpassword").innerHTML = "The passwords do not match";
	}else{
		return true;
	}
	return false;
}

function AJAX(url,fun)
{
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.onreadystatechange=function()
                {
                    if (xmlHttpReq.readyState == 4)
                    {
                    	if(xmlHttpReq.status==200)
                    	{
                        	fun(xmlHttpReq.responseText);
                    	}
                    	else
                    	{
                    		document.location.replace(document.location.path);
                    	}
                    }
                }

    xmlHttpReq.open('GET', url, true);
    xmlHttpReq.send();
}

function updateContent(responseText)
{
    document.getElementById("content").innerHTML = responseText;
}

function addComment()
{
    var url = 'AddComment.do?content=';
    url += document.getElementById("comment-field");
    peticionAJAX(url,updateContent);
}

function editComment(id)
{
	var comment = document.getElementById("comment-"+id);
	comment.style.display = "none";
	document.getElementById("comment-edit-"+id).style.display = "block";
	document.getElementById("comment-field-"+id).value = comment.innerHTML;
}

function commitEditComment(id)
{
    var url = 'EditComment.do?commentId='+id;
    url += '&content='+document.getElementById("comment-field-"+id);
    peticionAJAX(url,updateContent);
}

function removeComment(id)
{
	if(confirm("Do you really want to delete the comment?"))
	{
		var url = 'DeleteComment.do?commentId='+id;
		peticionAJAX(url,updateContent);
	}
}



// ///////////////////////////////
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

function hoverSuscribe()
{
	document.getElementById("suscribeIcon").src=suscribeIconHover;
}

function unhoverSuscribe()
{
	document.getElementById("suscribeIcon").src=suscribeIcon;
}
