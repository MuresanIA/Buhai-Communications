var stompClient = null;
connect();

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://localhost:8080/websocketApp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greeting-' + $("#queueName").text(), function (greeting) {
            var payload = JSON.parse(greeting.body);
            displayMessage(payload.sender + ": " + (getTimeFromTimeStamp(payload.timeStamp)) + ": " + payload.content);
        });
    });
    getMessages();
}

function getMessages() {
    $.get("/api/messages/" + $("#chatRoomId").text(), function (response) {
        console.log(response);
        for (let i = 0; i < response.messages.length; i++) {
            var payload = response.messages[i];
            displayMessage(payload.sender + ": " + (getTimeFromTimeStamp(payload.timeStamp)) + ": " + payload.content);
        }
    });
}

function sendName() {
    stompClient.send("/app/hello/" + $("#queueName").text(), {}, JSON.stringify({
        'sender': $("#name").val(),
        'timeStamp': $("#timeStamp").val(),
        "content": $("#message").val()
    }));
}

function displayMessage(message) {
    $("#conversation").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        sendName();
    });
});

function getTimeFromTimeStamp(timeStamp) {
    var date = new Date(timeStamp);
    var hours = date.getHours();
    var minutes = "0" + date.getMinutes();
    var seconds = "0" + date.getSeconds();
    return hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
}