
var ajaxReq=new XMLHttpRequest();
var username,password;
$(document).ready(function()
{
    $("#registerbtn").click(function()
    {
        connect();
    });
});
function validate()
{
    username=$("#username").val();
    password=$("#password").val();
   
    var status=true;
    if(username==="")
    {
        
        $("#username").after("<span id='error1'>Username Reqd!</span>");
        $("#error1").css("color","red");
        status= false;
    }
    if(password==="")
    {
        
        $("#password").after("<span id='error2'>Password Reqd!</span>");
        $("#error2").css("color","red");
        status= false;
    }
    $("#error1").fadeOut(4000);
    $("#error2").fadeOut(4000);
    return status;
}
function connect()
{
    //alert("connect called");
    if(!validate())
    {
       
        return ;
    }
   
var mydata={username:username,password:password};
var request=$.post("RegistrationControllerServlet",mydata,processresponse);
request.error(handleError);
}
function processresponse(responseText)
{
    
    
        var resp=responseText;
        resp=resp.trim();
        if(resp==="uap")
        {
            alert("uap");
            $("#regresult").css("color","green");
            $("#regresult").text("Sorry! the username is already present! ");
        }
        else if(resp==="failure")
        {
            alert("inside error");
            $("#regresult").css("color","red");
            $("#regresult").text("Registration Failed ! Tray again");
        }
        else if(resp==="success")
        {
            alert("inside else:"+resp);
            $("#regresult").css("color","green");
            $("#regresult").html("Registration Successful!You can now Login");
            
        }
        else
        {
            
            
        }
            
        
    }
    function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        $("#regresult").html("Error is "+xhr.status);
    }
}

