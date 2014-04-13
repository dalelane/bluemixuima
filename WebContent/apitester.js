/*
 * JavaScript to submit an XHR request to the API and display the response. 
 * 
 * Dale Lane (email@dalelane.co.uk)
 */

function sendUimaRequest(){
    var textToSubmit = document.getElementById("inputtext").value;
    xhrPost(textToSubmit, 
            function(response){
                document.getElementById("response").innerHTML = prettyJson(response);
            },
            function(err){
                document.getElementById("response").innerHTML = err;
            });
}

function createXHR(){
    if(typeof XMLHttpRequest != 'undefined'){
        return new XMLHttpRequest();
    }else{
        try{
            return new ActiveXObject('Msxml2.XMLHTTP');
        }catch(e){
            try{
                return new ActiveXObject('Microsoft.XMLHTTP');
            }catch(e){}
        }
    }
    return null;
}

function xhrPost(content, callback, errback){
    var xhr = new createXHR();
    xhr.open("POST", "/api", true);
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                callback(xhr.responseText);
            }else{
                errback('service not available');
            }
        }
    };
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-type", "text/plain");
    
    xhr.send(content);
}

function prettyJson(str){
    // If browser does not have JSON utilities, just print the raw string value.
    return window.JSON ? JSON.stringify(JSON.parse(str), null, '  ') : str;
}