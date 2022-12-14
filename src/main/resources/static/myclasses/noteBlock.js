function createOneNote(info, prepear, redirectToClient, goToHome) {

    var operationIcon = document.createElement('img')
    operationIcon.className = 'iconHome'
    operationIcon.srcset = '/images/hand-holding-heart.svg'

    var medicamentIcon = document.createElement('img')
    medicamentIcon.className = 'iconHome'
    medicamentIcon.srcset = '/images/preparat.svg'

    var walletIcon = document.createElement('img')
    walletIcon.className = 'iconHome'
    walletIcon.srcset = '/images/wallet.svg'

    var clockIcon = document.createElement('img')
    clockIcon.className = 'iconHome'
    clockIcon.srcset = '/images/clock.svg'

    var note = document.createElement('ul');
    note.className = "calcelementul topmenu";

    var li = document.createElement('li');
    li.style = 'width: 100%'

    var menu = document.createElement('ul');

    if (redirectToClient === true) {
        menu.innerHTML = '<ul class="submenu widgetRowEvently">\n' +
            '                <li><button onclick="deleteOpeartion(' + info.id + ', ' + goToHome + ')" class="operationOptions"><img class="buttonSubMenu" src="/images/iconfinder-trash.png"></button></li>\n' +
            '                <li><button onclick="getClientRequest(' + info.clientId + ')" class="operationOptions"><img class="buttonSubMenu" src="/images/female.png"></button></li>\n' +
            '            </ul>'
    } else {
        menu.innerHTML = '<ul class="submenu widgetRowEvently">\n' +
            '                <li><button onclick="deleteOpeartion(' + info.id + ', ' + goToHome + ')" class="operationOptions"><img class="buttonSubMenu" src="/images/iconfinder-trash.png"></button></li>\n' +
            '            </ul>'
    }


    var header = document.createElement('div');
    header.className = "widgetRow";

    var datePlace = document.createElement('div');
    datePlace.className = 'dateBlock'

    var infoPlace = document.createElement('button');
    infoPlace.style = 'width: 53%;'
    infoPlace.className = 'operationOptions'

    var time = document.createElement('p');
    time.className = "dateTime fontXxxLarge fonts";
    time.innerHTML = info.time;

    var date = document.createElement('p');
    date.className = "dateTime newNoteFonts fontXxxLarge fonts";
    date.style = 'color: #a9a9a9'
    date.innerHTML = getParseDate(info.day);

    var name = document.createElement('p');
    name.className = "blockV3 fontXxxLarge fonts";
    name.innerHTML = info.name;

    var medicamentJs = document.createElement('p');
    medicamentJs.className = "subtitle";
    medicamentJs.innerHTML = info.medicament;

    var summuryJs = document.createElement('p');
    summuryJs.className = "blockV3 niceFont fontXxxLarge fonts";
    summuryJs.innerHTML = info.summury + " ₽";

    var duration = document.createElement('p');
    duration.className = "blockV3 niceFont fontXxxLarge fonts";
    if (info.duration !== null) duration.innerHTML = info.duration + ' мин.'
    else duration.innerHTML = '30 мин.'

    var numbersDataDiv = document.createElement('div');
    numbersDataDiv.className = "widgetRowStart";

    var operationDiv = document.createElement('div');
    operationDiv.className = "widgetRowEnd";

    var medicamentDiv = document.createElement('div');
    medicamentDiv.className = "widgetRowEnd";

    var summDiv = document.createElement('div');
    summDiv.className = "widgetRowEnd";

    var durationDiv = document.createElement('div');
    durationDiv.className = "widgetRowStart";


    // operationDiv.prepend(name, operationIcon)
    medicamentDiv.prepend(medicamentJs, medicamentIcon)

    summDiv.prepend(summuryJs, walletIcon)
    durationDiv.prepend(clockIcon, duration)

    // numbersDataDiv.prepend(clockIcon, duration)

    datePlace.prepend(date, time, durationDiv)

    infoPlace.prepend(name, medicamentDiv, summDiv)
    header.prepend(datePlace, infoPlace);

    li.prepend(header, menu)

    note.prepend(li);
    prepear.prepend(note);
}

function getParseDate(date) {
    var arr = [
        'Янв.',
        'Фев.',
        'Мар.',
        'Апр.',
        'Мая',
        'Июн.',
        'Июл.',
        'Авг.',
        'Сен.',
        'Окт.',
        'Ноя.',
        'Дек.'
    ];

    var d = date.split('T')[0]
    var day = d.split('-')[2]
    var mouth = arr[d.split('-')[1] - 1]
    return day + ' ' + mouth
}

function deleteOpeartion(id, go) {
    if (confirm('Удалить запись?')) {
        $.ajax({
            type: 'POST',
            url: '/delete_operation',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({id}),
            success: function (delete_operation) {
                console.log("tap")
                alert(delete_operation.title)

                if (go === 1) window.location.href = "/home"
                if (go === 2) window.location.href = "/all_operations"
                else window.location.href = "/newnote?" + delete_operation.info
            }
        });
    } else {
        // Do nothing!
    }
}

function getClientRequest(clientId) {
    $.ajax({
        type: 'GET',
        url: '/client_info?id=' + clientId,
        dataType: 'json',
        data: clientId
    });
    window.location.href = "/client_info?id=" + clientId;
}
