function sendMessage() {
    var name = $("#uName").val();
    var message = $("#uMessage").val();
    var objMessage = { "name": name, "message": message };
    $.ajax({
        url: 'chatServlet',
        type: 'GET',
        contentType: 'application/json',
        data: objMessage,
        dataType: 'json',
        success: function(value) {
            $("#test").val = value.name;
            console.log("Success");
            console.log("value of name is:" + value.name);
        }
    });
}

function refresh() {
    $.ajax({
        url: 'chatServlet',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        success: refreshMessages
    });
}

function refreshMessages(val) {
    var messages = val;

    $("#messageArea li").remove();
    for (var i = 0; i < val.length; i++) 
    {
        $('#messageArea').append('<li class="media"><div class="media-body"><div class="media"><a class="pull-left" href="#"><img class="media-object img-circle " src="assets/img/user.gif" /></a><div class="media-body">'+ messages[i].message +'<br /><small class="text-muted">'+messages[i].name+' | 23rd June at 5:00pm</small><hr /></div></div></div></li>');
    }


}

function refreshUserList() {
    $.ajax({
        url: 'LoginCheck',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        success: refreshUsers
    });
}

function refreshUsers(val) {
    var users = val;
    console.log(users);
    console.log(users.length);
    console.log(users[0].name);
    $("#userArea li").remove();
    for (var i = 0; i < val.length; i++) {
            
        $('#userArea').append('<li class="media"><div class="media-body"><div class="media"><a class="pull-left" href="#"><img class="media-object img-circle" style="max-height:40px;" src="assets/img/user.gif" /></a><div class="media-body" ><h5>'+users[i].name+'</h5><small class="text-muted">Active From 3 hours</small></div></div></div></li>')

//        $('#tableRows').append('<tr><td>' + messages[i].name + '</td><td>' + messages[i].message + '</td > </tr>');
    }


}