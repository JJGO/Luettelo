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

var lock = false;
function AJAX(url,fun)
{
    if(!lock)
    {
        lock = true;
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange=function()
            {
                if (xmlHttpReq.readyState === 4)
                {
                    if(xmlHttpReq.status === 200)
                    {
                        fun(xmlHttpReq.responseText);
                    }
                    else
                    {
                        alert("te has pasado de listo");
                        document.location.replace("/");
                    }

                    lock = false;
                }
            };

        xmlHttpReq.open('GET', url, true);
        xmlHttpReq.send();
    }
}

function reloadContent(responseText)
{
    location.reload(); //F5
}

function search()
{
    // Some validation could be added
    var url = "/search/" + document.getElementById("query").value;
    document.location.replace(url);
}

function login()
{
    url = '/Login.auth?username='+document.getElementById("username_login").value+'&password='+document.getElementById("password_login").value;
    AJAX(url,loginJSON);
}

function loginJSON(responseText)
{
    var objectJSON = eval("(" + responseText + ")");
    if(objectJSON.loginError == true)
    {
        document.getElementById("loginError").innerHTML = objectJSON.message;
    }
    else if(objectJSON.loginError == false)
    {
        document.getElementById("loginError").innerHTML = "";
        location.reload(); //F5
    }
}

function signUpJSON(responseText)
{
    var objectJSON = eval("(" + responseText + ")");
    if(objectJSON.signupError == true)
    {
        document.getElementById("signupError").innerHTML = objectJSON.message;
    }
    else if(objectJSON.signupError == false)
    {
        document.getElementById("signupError").innerHTML = "";
        location.reload(); //F5
    }
}

function validateSignUp()
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
        document.getElementById("errorUsername").innerHTML = "The username must contain lowercase letters and numbers and be 3-15 characters long";
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
    }
    else
    {
        url = '/Register.auth?username='+username+'&email='+email+'&password='+password+'&rpassword='+rpassword;
        AJAX(url, signUpJSON)
    }
}

function logout()
{
    url = '/Logout.auth';
    AJAX(url,reloadContent);
}

function updateContent(responseText)
{
    document.getElementById("content").innerHTML = responseText;
}

function updateDisplayList(responseText)
{
    document.getElementById("displayList").innerHTML = responseText;
}

function addComment()
{
    var listId = document.getElementById("listId").value;
    var url = '/AddComment.do?listId='+listId;
    url += '&content='+document.getElementById("comment-field").value;
    AJAX(url,updateContent);
}

function editComment(commentId)
{
    var comment = document.getElementById("comment-"+commentId);
    comment.style.display = "none";
    document.getElementById("comment-edit-"+commentId).style.display = "block";
    document.getElementById("comment-field-"+commentId).value = comment.innerHTML;
    document.getElementById("op-"+commentId).style.display = none;
}

function commitEditComment(commentId)
{
    var listId = document.getElementById("listId").value;
    var url = '/EditComment.do?listId='+listId;
    url += '&commentId='+commentId;
    url += '&content='+document.getElementById("comment-field-"+commentId).value;
    AJAX(url,updateContent);
}

function removeComment(commentId)
{
    if(confirm("Do you really want to delete the comment?"))
    {
        var listId = document.getElementById("listId").value;
        var url = '/RemoveComment.do?listId='+listId;
        url += '&commentId='+commentId;
        AJAX(url,updateContent);
    }
}

function addItem()
{
    var listId = document.getElementById("listId").value;
    var url = '/AddItem.do?listId='+listId;
    url += "&name="+document.getElementById("item-name").value;
    url += "&url="+document.getElementById("item-url").value;
    AJAX(url,updateContent);
}

function editItem(itemId)
{
    document.getElementById("item-title-"+itemId).style.display = "none";
    document.getElementById("item-edit-"+itemId).style.display = "block";
    document.getElementById("name-field-"+itemId).value = document.getElementById("item-url-"+itemId).text;
    document.getElementById("url-field-"+itemId).value = document.getElementById("item-url-"+itemId).href;
}

function commitEditItem(itemId)
{
    var listId = document.getElementById("listId").value;
    var url = '/EditItem.do?listId='+listId;
    url += '&itemId='+itemId;
    url += "&name="+document.getElementById("name-field-"+itemId).value;
    url += "&url="+document.getElementById("url-field-"+itemId).value;
    AJAX(url,updateContent);
}

function removeItem(itemId)
{
    if(confirm("Do you really want to delete the item?"))
    {
        var listId = document.getElementById("listId").value;
        var url = '/RemoveItem.do?listId='+listId;
        url += '&itemId='+itemId;
        AJAX(url,updateContent);
    }
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

function commitEditList()
{
    var listId = document.getElementById("listId").value;
    var url = '/EditList.do?listId='+listId;
    url += "&name="+ document.getElementById("ltitle-field").value;
    url += "&category="+ document.getElementById("lcategory-field").value;
    url += "&description="+ document.getElementById("ldescription-field").value;
    AJAX(url,updateDisplayList);
}

function removeList()
{
    if(confirm("Do you really want to delete this list?. This will delete all the items in it (This action cannot be undone)"))
    {
        var listId = document.getElementById("listId").value;
        var url = '/RemoveList.do?listId='+listId;
        document.location.replace(url);
    }
}

function subscribeList()
{
    var listId = document.getElementById("listId").value;
    var url = '/SubscribeList.do?listId='+listId;
    AJAX(url,updateContent);
}

function unsubscribeList()
{
    var listId = document.getElementById("listId").value;
    var url = '/UnsubscribeList.do?listId='+listId;
    AJAX(url,updateContent);
}

function checkItem(itemId)
{
    var listId = document.getElementById("listId").value;
    var url = '/CheckItem.do?listId='+listId;
    url += '&itemId='+itemId;
    AJAX(url,updateContent);
}

function uncheckItem(itemId)
{
    var listId = document.getElementById("listId").value;
    var url = '/UncheckItem.do?listId='+listId;
    url += '&itemId='+itemId;
    AJAX(url,updateContent);
}

function rateItem(itemId,rating)
{
    var listId = document.getElementById("listId").value;
    var url = '/RateItem.do?listId='+listId;
    url += '&itemId='+itemId;
    url += "&rating="+rating;
    AJAX(url,updateContent);
}

function hoverSubscribe()
{
	document.getElementById("subscribeIcon").src="/images/subscribed.png";
}

function unhoverSubscribe()
{
	document.getElementById("subscribeIcon").src="/images/subscribe.png";
}

function hoverUnsubscribe()
{
	document.getElementById("subscribeIcon").src="/images/unsubscribed.png";
}

function unhoverUnsubscribe()
{
	document.getElementById("subscribeIcon").src="/images/subscribed.png";
}

function hoverCheck(itemId)
{
	document.getElementById("checkIcon-"+itemId).src="/images/checked.png";
}

function unhoverCheck(itemId)
{
	document.getElementById("checkIcon-"+itemId).src="/images/check.png";
}

function hoverUncheck(itemId)
{
	document.getElementById("checkIcon-"+itemId).src="/images/unchecked.png";
}

function unhoverUncheck(itemId)
{
	document.getElementById("checkIcon-"+itemId).src="/images/checked.png";
}