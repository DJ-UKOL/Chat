let stompClient = null;
let username = 'noname';

function connect() {
    let socket = new SockJS('/ws-connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        username = frame.headers['user-name'];
        stompClient.subscribe('/user/chat_out/receive_message', function (payload) {
            console.log('Received new message: ' + payload);
            let msg = JSON.parse(payload.body);
            $('#chatMessages').append(msg.sender + ':\n' + msg.message + '\n');
        });
    });
}

function sendMessage() {
    let textInput = $('#message');
    let receiver = $('#userName').val();
    stompClient.send("/chat_in/send_message", {},
        JSON.stringify({
            'sender' : username,
            'receiver' : receiver,
            'message': textInput.val()
        }));
    $('#chatMessages').append(username + ':\n' + textInput.val() + '\n');
    textInput.val('');
}

$('#sendButton').click(function() {
    sendMessage();
});

connect();