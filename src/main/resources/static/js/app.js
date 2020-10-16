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
            displayMessage(payload.sender, payload.content, getTimeFromTimeStamp(payload.timeStamp));
        });
    });
    getMessages();
}

function getMessages() {
    $.get("/api/messages/" + $("#chatRoomId").text(), function (response) {
        console.log(response);
        for (let i = 0; i < response.messages.length; i++) {
            var payload = response.messages[i];
            displayMessage(payload.sender, payload.content, getTimeFromTimeStamp(payload.timeStamp));
            updateScroll();
        }
    });
}

function sendName() {
    stompClient.send("/app/hello/" + $("#queueName").text(), {}, JSON.stringify({
        'sender': $("#username").text(),
        'timeStamp': $("#timeStamp").val(),
        "content": $("#message").val()
    }));
}

function displayMessage(sender, message, time) {
    var colorClass = "";
    const inlineBlockClass = "div-inline-block"
    if (sender === $("#username").text()) {
        colorClass = "chat-message-yellow"
    } else {
        colorClass = "chat-message-green"
    }
    const tr = document.createElement("div");
    tr.classList.add("chat-message-container")

    const senderColumn = document.createElement("div");
    senderColumn.innerText = sender
    senderColumn.classList.add(inlineBlockClass)

    const messageColumn = document.createElement("div");
    messageColumn.classList.add(colorClass)
    messageColumn.classList.add(inlineBlockClass)
    messageColumn.innerText = message;

    const timeColumn = document.createElement("div");
    timeColumn.classList.add(inlineBlockClass)
    timeColumn.innerText = time

    tr.appendChild(senderColumn)
    tr.appendChild(messageColumn)
    tr.appendChild(timeColumn)

    $("#chatroom").append(tr);
    updateScroll();
}

function updateScroll() {
    var element = document.getElementById("messages");
    element.scrollTop = element.scrollHeight;
}

$(function () {
    $("#messageForm").on('submit', function (e) {
        const message = $("#message");
        message.val('');
        message.focus();
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
    return hours + ':' + minutes.substr(-2);
}